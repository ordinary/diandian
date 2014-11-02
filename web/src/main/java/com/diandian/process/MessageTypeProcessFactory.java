package com.diandian.process;

import com.diandian.process.process.*;
import com.diandian.utils.MessageUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhutao on 14/10/6.
 */
public class MessageTypeProcessFactory {

    private static Map<String, IProcess> processes = new HashMap<String, IProcess>();

    static {
        processes.put(MessageUtil.REQ_MESSAGE_TYPE_TEXT, new TextProcess());
        processes.put(MessageUtil.REQ_MESSAGE_TYPE_IMAGE, new ImageProcess());
        processes.put(MessageUtil.REQ_MESSAGE_TYPE_LOCATION, new LocationProcess());
        processes.put(MessageUtil.REQ_MESSAGE_TYPE_LINK, new LinkProcess());
        processes.put(MessageUtil.REQ_MESSAGE_TYPE_VOICE, new VoiceProcess());
    }

    public static IProcess getProcess(String msgType) {
        return processes.get(msgType);
    }
}
