package com.kz.batch.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SchedulerConfig {

    @Resource
    private MyJobFactory myJobFactory;

    /**
     * 该方法配置并返回一个 SchedulerFactoryBean，用于初始化 Quartz 调度器。功能如下：
     * 设置数据源：将传入的 dataSource 设置给调度工厂，用于任务持久化存储。
     * 设置任务工厂：使用自定义的 MyJobFactory 来创建 Job 实例，支持 Spring Bean 的自动注入。
     * 设置启动延迟：调度器启动后延迟 2 秒再开始执行任务。
     * @param dataSource
     * @return
     * @throws IOException
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource")DataSource dataSource) throws IOException{
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJobFactory(myJobFactory);
        factoryBean.setStartupDelay(2);
        return factoryBean;
    }
}
