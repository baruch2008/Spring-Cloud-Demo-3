package com.huawei.allinone.common.count;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CountTaskProcessorMgr {
    private Map<String, Object> processors = new ConcurrentHashMap<>();
    
    public void addCountTaskProcessor(Object obj)
    {
        processors.put(obj.getClass().getName(), obj);
    }
}
