package com.diandian.process.process;

import com.diandian.process.IProcess;
import com.diandian.process.process.event.ClickEventProcess;
import com.diandian.process.process.event.SubscribeEventProcess;
import com.diandian.process.process.event.UnsubscribeEventProcess;
import com.diandian.utils.MessageUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhutao on 14/10/6.
 */
public class MessageTypeEventProcess implements IProcess {

    private static Map<String,IProcess> eventProcesses=new HashMap<String, IProcess>();

    static {
        eventProcesses.put(MessageUtil.EVENT_TYPE_SUBSCRIBE,new SubscribeEventProcess());
        eventProcesses.put(MessageUtil.EVENT_TYPE_UNSUBSCRIBE,new UnsubscribeEventProcess());
        eventProcesses.put(MessageUtil.EVENT_TYPE_CLICK,new ClickEventProcess());
    }

    @Override
    public String dealwith(Map<String, String> req) {
        String eventType = req.get("Event");
        IProcess process=eventProcesses.get(eventType);
        if (process!=null){
            return process.dealwith(req);
        }
        return null;
    }
}
