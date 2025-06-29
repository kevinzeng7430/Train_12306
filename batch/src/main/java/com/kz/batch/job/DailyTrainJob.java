package com.kz.batch.job;

import com.kz.batch.feign.BusinessFeign;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
@Slf4j
public class DailyTrainJob implements Job {

    @Resource
    private BusinessFeign businessFeign;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("DailyTrainJob开始");
        log.info("DailyTrainJob结束");
    }
}
