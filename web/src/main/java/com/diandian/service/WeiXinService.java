package com.diandian.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.diandian.entity.Button;
import com.diandian.entity.Menu;
import com.diandian.process.IProcess;
import com.diandian.process.MessageTypeProcessFactory;
import com.diandian.utils.MessageUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhutao on 14/9/26.
 */
public class WeiXinService {

    private final static int MAX_RETRY = 5;

    private final static String APP_ID = "wxb72f8f3147275f81";

    private final static String APP_SECRET = "cbf06d4544bbe5534ddcec4df58f6b79";

    private final static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;

    private String getAccessToken() throws IOException {
        String accessToken = (String) EhcacheService.getCache(EhcacheService.ACCESS_TOKEN_CACHE_KEY, EhcacheService.ACCESS_TOKEN_KEY);
        if (!StringUtils.isEmpty(accessToken)) {
            return accessToken;
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(GET_ACCESS_TOKEN_URL);
        CloseableHttpResponse response = null;
        try {
            int i = 0;
            while (i < MAX_RETRY) {
                response = httpclient.execute(httpget);
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        long len = entity.getContentLength();
                        if (len != -1 && len < 2048) {
                            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity));
                            accessToken = jsonObject.getString("access_token");
                            if (!StringUtils.isEmpty(accessToken)) {
                                int expire = jsonObject.getInteger("expires_in");
//                                EhcacheService.addCache(EhcacheService.ACCESS_TOKEN_CACHE_KEY, EhcacheService.ACCESS_TOKEN_KEY, accessToken, expire);
                            }
                        }
                    }
                }
                if (!StringUtils.isEmpty(accessToken)) {
                    return accessToken;
                }
            }
        } finally {
            response.close();
        }
        throw new RuntimeException("accessToken cann't get now !!!");
    }


    public String createMenu(String params) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + getAccessToken());
        httpPost.setEntity(new StringEntity(params, "UTF-8"));
        HttpResponse response = httpclient.execute(httpPost);
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(jsonStr);
        JSONObject object = JSON.parseObject(jsonStr);
        return object.getString("errmsg");
    }


    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        List<Button> buttons = new ArrayList<Button>();
        menu.setButtons(buttons);
        Button button1 = new Button();
        buttons.add(button1);
        button1.setName("看");
        List<Button> subButtons = new ArrayList<Button>();
        button1.setSubButton(subButtons);

        Button subButton13 = new Button();
        subButtons.add(subButton13);
        subButton13.setName("看最新商品");
        subButton13.setType("scancode_waitmsg");
        subButton13.setKey("rselfmenu_0_2");

        Button subButton14 = new Button();
        subButtons.add(subButton14);
        subButton14.setName("看最新求购");
        subButton14.setType("scancode_waitmsg");
        subButton14.setKey("rselfmenu_0_2");

        Button subButton11 = new Button();
        subButtons.add(subButton11);
        subButton11.setName("看商品");
        subButton11.setType("scancode_waitmsg");
        subButton11.setKey("rselfmenu_0_1");

        Button subButton12 = new Button();
        subButtons.add(subButton12);
        subButton12.setName("看求购");
        subButton12.setType("scancode_waitmsg");
        subButton12.setKey("rselfmenu_0_2");





        Button button2 = new Button();
        buttons.add(button2);
        button2.setName("发");
        List<Button> subButtons2 = new ArrayList<Button>();
        button2.setSubButton(subButtons2);
        Button subButton21 = new Button();
        subButtons2.add(subButton21);
        subButton21.setName("发布商品");
        subButton21.setType("scancode_waitmsg");
        subButton21.setKey("rselfmenu_1_0");

        Button subButton22 = new Button();
        subButtons2.add(subButton22);
        subButton22.setName("求购商品");
        subButton22.setType("scancode_waitmsg");
        subButton22.setKey("rselfmenu_2_0");


        Button button3 = new Button();
        buttons.add(button3);
        button3.setName("我");
        List<Button> subButtons3 = new ArrayList<Button>();
        button3.setSubButton(subButtons3);

        Button subButton33 = new Button();
        subButtons3.add(subButton33);
        subButton33.setName("消息");
        subButton33.setType("location_select");
        subButton33.setKey("rselfmenu_4_0");



        Button subButton32 = new Button();
        subButtons3.add(subButton32);
        subButton32.setName("发布信息");
        subButton32.setType("location_select");
        subButton32.setKey("rselfmenu_4_0");


        Button subButton31 = new Button();
        subButtons3.add(subButton31);
        subButton31.setName("个人中心");
        subButton31.setType("view");
        subButton31.setUrl("http://www.soso.com/");

        new WeiXinService().createMenu(JSON.toJSONString(menu));
    }


    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            IProcess process = MessageTypeProcessFactory.getProcess(msgType);
            return process.dealwith(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}

