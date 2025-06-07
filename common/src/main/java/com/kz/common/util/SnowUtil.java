package com.kz.common.util;

import cn.hutool.core.util.IdUtil;

public class SnowUtil {

    private static long datacenterId = 1; // 数据中心ID
    private static long machineId = 1; // 机器ID
    public static long getSnowflakeNextId(){
        return IdUtil.getSnowflake(machineId, datacenterId).nextId();
    }
    public static String getSnowflakeNextIdStr() {
        return IdUtil.getSnowflake(machineId, datacenterId).nextIdStr();
    }

}
