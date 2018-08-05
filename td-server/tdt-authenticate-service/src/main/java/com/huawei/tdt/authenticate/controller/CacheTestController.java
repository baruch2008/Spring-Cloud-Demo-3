package com.huawei.tdt.authenticate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.allinone.common.cache.RedisCacheManager;

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
    public void getObject() {
        Object obj = cacheMgr.getObject("Test");
        if (obj instanceof List) {
            List<?> list = (List<?>)obj;
            for (Object ele : list) {
                System.out.println(ele);
            }
        }
    }
    
    @RequestMapping(value = "/deleteObject", method = RequestMethod.DELETE)
    public void deleteObject() {
        cacheMgr.deleteObject("Test");
    }
}
