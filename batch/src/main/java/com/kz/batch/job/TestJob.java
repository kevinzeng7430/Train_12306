package com.kz.batch.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class TestJob implements Job {
    /**
     * Job执行入口方法，Quartz框架会在触发器触发时调用此方法
     * @param jobExecutionContext 执行上下文，包含Job和Trigger相关信息
     * @throws JobExecutionException 如果执行过程中发生异常
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 记录任务开始执行的日志
        System.out.println("TestJob TEST开始");
        
        // 模拟耗时操作（被注释）
        // try {
        //     Thread.sleep(3000); // 暂停3秒，模拟长时间运行的任务
        // } catch (InterruptedException e) {
        //     e.printStackTrace(); // 打印中断异常
        // }
        
        // 记录任务结束日志
        System.out.println("TestJob TEST结束");
    }
}
