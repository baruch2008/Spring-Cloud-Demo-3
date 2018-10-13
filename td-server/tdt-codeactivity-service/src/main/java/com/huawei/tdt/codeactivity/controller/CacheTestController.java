package com.huawei.tdt.codeactivity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.allinone.common.cache.RedisCacheManager;
import com.huawei.tdt.codeactivity.model.DomainUser;

@RestController
public class CacheTestController {
    @Autowired
    private RedisCacheManager cacheMgr;

    @RequestMapping(value = "/saveObject", method = RequestMethod.POST)
    public void saveObject() {
        List<String> list = new ArrayList<>();
        list.add("Test1");
        list.add("Test2");
        cacheMgr.saveObject("Test", list);
    }

    @RequestMapping(value = "/getObject", method = RequestMethod.GET)
    public Object getObject() {
        Object obj = cacheMgr.getObject("Test");
        if (obj instanceof List) {
            List<?> list = (List<?>)obj;
            for (Object ele : list) {
                System.out.println(ele);
            }
        }

        return obj;
    }

    @RequestMapping(value = "/deleteObject", method = RequestMethod.DELETE)
    public void deleteObject() {
        cacheMgr.delete("Test");
    }

    @RequestMapping(value = "/saveList", method = RequestMethod.POST)
    public void saveList() {
        List<String> list = new ArrayList<>();
        list.add("Test1");
        list.add("Test3");
        cacheMgr.saveList("Test1", list);
    }

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<?> getList() {
        List<?> list = cacheMgr.getList("Test1");
        for (Object ele : list) {
            System.out.println(ele);
        }
        return list;
    }

    @RequestMapping(value = "/saveMap", method = RequestMethod.POST)
    public void saveMap() {
        Map<String, DomainUser> map = new HashMap<>();
        map.put("Test1", new DomainUser());
        map.put("Test2", new DomainUser());
        cacheMgr.saveMap("Test3", map);
    }

    @RequestMapping(value = "/getMap", method = RequestMethod.GET)
    public Map<?, ?> getMap() {
        Map<?, ?> map = cacheMgr.getMap("Test3");
        Set<?> entrySet = map.entrySet();
        for (Object obj : entrySet) {
            if (obj instanceof Entry) {
                Entry<?, ?> entry = (Entry<?, ?>)obj;
                System.out.println(entry.getKey() + ", " + entry.getValue());
            }
        }

        return map;
    }

    @RequestMapping(value = "/getMapValue", method = RequestMethod.GET)
    public Object getMapValue() {
        Object value = cacheMgr.getMapValue("Test3", "Test1");
        System.out.println(value);
        return value;
    }

    @RequestMapping(value = "/saveMapWithTimeout", method = RequestMethod.POST)
    public void saveMapWithTimeout() {
        Map<String, DomainUser> map = new HashMap<>();
        map.put("Test1", new DomainUser());
        map.put("Test2", new DomainUser());
        cacheMgr.saveMapWithTimeout("Test4", map);
    }

    @RequestMapping(value = "/getBoundMapValue", method = RequestMethod.GET)
    public Object getBoundMapValue() {
        Object value = cacheMgr.getBoundMapValue("Test4", "Test1");
        System.out.println(value);
        return value;
    }
}
