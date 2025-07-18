package com.kz.batch.controller;


import com.kz.batch.request.CronJobReq;
import com.kz.batch.response.CronJobResp;
import com.kz.common.response.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/job")
@Slf4j
public class JobController {

   @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @RequestMapping("/run")
    public CommonResp<Object> run(@RequestBody CronJobReq cronJobReq) throws SchedulerException {
        String jobClassName = cronJobReq.getName();
        String jobGroupName = cronJobReq.getGroup();
        log.info("手动执行任务开始：{}, {}", jobClassName, jobGroupName);
        schedulerFactoryBean.getScheduler().triggerJob(JobKey.jobKey(jobClassName, jobGroupName));
        return new CommonResp<>();
    }


    @RequestMapping(value = "/add")
    public CommonResp<Object> add(@RequestBody CronJobReq cronJobReq) {
        String jobClassName = cronJobReq.getName();
        String jobGroupName = cronJobReq.getGroup();
        String jobDescription = cronJobReq.getDescription();
        String cronExpression = cronJobReq.getCronExpression();
        log.info("创建定时任务开始：{}，{}，{}，{}", jobClassName, jobGroupName, cronExpression, jobDescription);
        CommonResp<Object> commonResp = new CommonResp<>();

        try{
            // 通过SchedulerFactory获取一个调度器实例
            Scheduler sched = schedulerFactoryBean.getScheduler();

            // 启动调度器
            sched.start();

            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(jobClassName)).withIdentity(jobClassName, jobGroupName).build();

            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withDescription(jobDescription).withSchedule(scheduleBuilder).build();

            sched.scheduleJob(jobDetail, trigger);
        } catch (ClassNotFoundException e) {
            log.error("创建定时任务失败:" + e);
            commonResp.setSuccess(false);
            commonResp.setMessage("创建定时任务失败:调度异常");
        } catch (SchedulerException e) {
            log.error("创建定时任务失败:" + e);
            commonResp.setSuccess(false);
            commonResp.setMessage("创建定时任务失败：任务类不存在");
        }
        log.info("创建定时任务结束：{}", commonResp);
        return commonResp;
    }

    @RequestMapping(value = "/pause")
    public CommonResp<Void> pause(@RequestBody CronJobReq cronJobReq) {
        String jobClassName = cronJobReq.getName();
        String jobGroupName = cronJobReq.getGroup();
        log.info("暂停定时任务开始：{}，{}", jobClassName, jobGroupName);
        CommonResp<Void> commonResp = new CommonResp<>();
        try {
            Scheduler sched = schedulerFactoryBean.getScheduler();
            sched.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败:" + e);
            commonResp.setSuccess(false);
            commonResp.setMessage("暂停定时任务失败:调度异常");
        }
        log.info("暂停定时任务结束：{}", commonResp);
        return commonResp;
    }

    @RequestMapping(value = "/resume")
    public CommonResp<Void> resume(@RequestBody CronJobReq cronJobReq) {
        String jobClassName = cronJobReq.getName();
        String jobGroupName = cronJobReq.getGroup();
        log.info("重启定时任务开始：{}，{}", jobClassName, jobGroupName);
        CommonResp<Void> commonResp = new CommonResp<>();
        try {
            Scheduler sched = schedulerFactoryBean.getScheduler();
            sched.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("重启定时任务失败:" + e);
            commonResp.setSuccess(false);
            commonResp.setMessage("重启定时任务失败:调度异常");
        }
        log.info("重启定时任务结束：{}", commonResp);
        return commonResp;
    }

    @RequestMapping(value = "/reschedule")
    public CommonResp<Void> reschedule(@RequestBody CronJobReq cronJobReq) {
        String jobClassName = cronJobReq.getName();
        String jobGroupName = cronJobReq.getGroup();
        String cronExpression = cronJobReq.getCronExpression();
        String description = cronJobReq.getDescription();
        log.info("更新定时任务开始：{}，{}，{}，{}", jobClassName, jobGroupName, cronExpression, description);
        CommonResp<Void> commonResp = new CommonResp<>();
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTriggerImpl trigger1 = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
            trigger1.setStartTime(new Date()); // 重新设置开始时间
            CronTrigger trigger = trigger1;

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withDescription(description).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            log.error("更新定时任务失败:" + e);
            commonResp.setSuccess(false);
            commonResp.setMessage("更新定时任务失败:调度异常");
        }
        log.info("更新定时任务结束：{}", commonResp);
        return commonResp;
    }

    @RequestMapping(value = "/delete")
    public CommonResp<Void> delete(@RequestBody CronJobReq cronJobReq) {
        String jobClassName = cronJobReq.getName();
        String jobGroupName = cronJobReq.getGroup();
        log.info("删除定时任务开始：{}，{}", jobClassName, jobGroupName);
        CommonResp<Void> commonResp = new CommonResp<>();
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("删除定时任务失败:" + e);
            commonResp.setSuccess(false);
            commonResp.setMessage("删除定时任务失败:调度异常");
        }
        log.info("删除定时任务结束：{}", commonResp);
        return commonResp;
    }


    @RequestMapping(value="/query")
    public CommonResp<Object> query() {
        log.info("查看所有定时任务开始");
        CommonResp<Object> commonResp = new CommonResp<>();
        List<CronJobResp> cronJobDtoList = new ArrayList<>();
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    CronJobResp cronJobResp = new CronJobResp();
                    cronJobResp.setName(jobKey.getName());
                    cronJobResp.setGroup(jobKey.getGroup());

                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    CronTrigger cronTrigger = (CronTrigger) triggers.get(0);
                    cronJobResp.setNextFireTime(cronTrigger.getNextFireTime());
                    cronJobResp.setPreFireTime(cronTrigger.getPreviousFireTime());
                    cronJobResp.setCronExpression(cronTrigger.getCronExpression());
                    cronJobResp.setDescription(cronTrigger.getDescription());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(cronTrigger.getKey());
                    cronJobResp.setState(triggerState.name());

                    cronJobDtoList.add(cronJobResp);
                }

            }
        } catch (SchedulerException e) {
            log.error("查看定时任务失败:" + e);
            commonResp.setSuccess(false);
            commonResp.setMessage("查看定时任务失败:调度异常");
        }
        commonResp.setContent(cronJobDtoList);
        log.info("查看定时任务结束：{}", commonResp);
        return commonResp;
    }

}
