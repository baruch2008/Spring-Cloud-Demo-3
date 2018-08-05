package com.huawei.allinone.common.lock;

import java.util.concurrent.TimeUnit;

public interface IDistributedLock {
    void lock();

    void unLock();

    boolean tryLock();

    boolean tryLock(long waitTime, TimeUnit timeUnit);
}
