package com.huawei.allinone.common.interceptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.huawei.allinone.common.annotation.CountTaskProcessor;

@Component
public class CountTaskProcessorInterceptor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //if (bean.getClass().getName().equals("com.huawei.tdt.authenticate.count.handler.TestHandler")) {
        //if(bean instanceof ICountTaskProcessor) {
            if (bean.getClass().isAnnotationPresent(CountTaskProcessor.class)) {
                System.out.println(beanName);
            }
        //}
        return bean;
    }
}
