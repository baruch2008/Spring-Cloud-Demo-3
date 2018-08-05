package com.huawei.allinone.common.cache;

public interface ICacheManager {
    public void saveObject(String key, Object value);

    public Object getObject(String key);
    
    public void deleteObject(String key);
}
