package com.diandian.process.process.event;

import com.diandian.process.IProcess;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhutao on 14/10/6.
 */
public class ClickEventProcess  implements IProcess {

    private static Map<String,IProcess> clickProcesses=new HashMap<String, IProcess>();

    static {

    }

    @Override
    public String dealwith(Map<String, String> req) {
        String eventKey = req.get("EventKey");
        IProcess process=clickProcesses.get(eventKey);
        if (process!=null){
            return process.dealwith(req);
        }
        return null;
    }
}
