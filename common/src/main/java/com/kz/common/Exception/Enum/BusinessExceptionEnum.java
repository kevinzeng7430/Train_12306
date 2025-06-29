package com.kz.common.Exception.Enum;

import lombok.Getter;


@Getter
public enum BusinessExceptionEnum {

    MEMBER_MOBILE_EXIST("手机号已存在"),
    MEMBER_MOBILE_CODE_ERROR("验证码错误"),
    MEMBER_MOBILE_NOT_EXIST("手机号不存在"),

    BUSINESS_STATION_NAME_UNIQUE_ERROR("车站已存在"),
    BUSINESS_TRAIN_CODE_UNIQUE_ERROR("车次编号已存在"),
    BUSINESS_TRAIN_STATION_INDEX_UNIQUE_ERROR("同车次站序已存在"),
    BUSINESS_TRAIN_STATION_NAME_UNIQUE_ERROR("同车次站名已存在"),
    BUSINESS_TRAIN_CARRIAGE_INDEX_UNIQUE_ERROR("同车次厢号已存在");

    private final String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
