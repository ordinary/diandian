package com.diandian.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by zhutao on 14/10/6.
 */
public class Button {

    /**
     * 否	二级菜单数组，个数应为1~5个
     */
    @JSONField(name="sub_button")
    private List<Button> subButton;

    /**
     * 是 菜单的响应动作类型
     */

    private String type;
    /**
     * 是 菜单标题
     * ，不超过16个字节，子菜单不超过40个字节
     */
    private String name;
    /**
     * click等点击类型必须	菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;

    private String url;

    public List<Button> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Button> subButton) {
        this.subButton = subButton;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
