package com.huawei.tdt.authenticate.count.handler;

import org.springframework.stereotype.Component;

import com.huawei.allinone.common.annotation.CountTaskProcessHandler;
import com.huawei.allinone.common.annotation.CountTaskProcessor;
import com.huawei.allinone.common.count.ICountTaskProcessor;

@Component
@CountTaskProcessor
public class TestHandler implements ICountTaskProcessor {

    @CountTaskProcessHandler
    public void process() {

    }
}
