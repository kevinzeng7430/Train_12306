package com.kz.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用响应类
 * 用于统一API响应格式
 */

@Setter
@Getter
public class CommonResp<T> {
    /**
     * 是否成功
     */
    private boolean success = true;
    /**
     * 响应码
     */
    private String message;
    /**
     * 响应内容
     */
    private T content;

    public  CommonResp() {}

    public CommonResp(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommonResp{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
