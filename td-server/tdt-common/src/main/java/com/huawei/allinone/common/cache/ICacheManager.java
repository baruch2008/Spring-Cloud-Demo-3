package com.huawei.allinone.common.cache;

import java.util.List;
import java.util.Map;

public interface ICacheManager {
    void saveObject(String key, Object value);

    Object getObject(String key);

    void delete(String key);

    void saveList(String key, List<?> values);

    List<?> getList(String key);

    void saveMap(String key, Map<?, ?> value);

    Map<?, ?> getMap(String key);

    Object getMapValue(String key, String hashKey);
}
