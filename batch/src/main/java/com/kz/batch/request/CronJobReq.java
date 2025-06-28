package com.kz.batch.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CronJobReq {
    /**
     * 任务所属组名，用于对定时任务进行分类管理
     */
    private String group;

    /**
     * 定时任务的名称，用于唯一标识一个定时任务
     */
    private String name;

    /**
     * 任务描述信息，可用于说明该任务的具体作用或业务含义
     */
    private String description;

    /**
     * cron表达式，用于定义任务执行的时间规则，例如："0 0/5 * * * ?"
     */
    private String cronExpression;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CronJobDto{");
        sb.append("cronExpression='").append(cronExpression).append('\'');
        sb.append(", group='").append(group).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
