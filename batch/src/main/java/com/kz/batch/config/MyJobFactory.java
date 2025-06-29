package com.kz.batch.config;

import jakarta.annotation.Resource;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
public class MyJobFactory extends SpringBeanJobFactory {
    @Resource
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    /**
     * 该方法用于创建并自动装配 Quartz 作业实例：
     * 调用父类方法 super.createJobInstance(bundle) 创建作业实例；
     * 使用 Spring 的 autowireBean 方法对创建的作业实例进行依赖注入；
     * 返回已装配好的作业实例。
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        autowireCapableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
