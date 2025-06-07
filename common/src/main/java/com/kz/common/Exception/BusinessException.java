package com.kz.common.Exception;

import com.kz.common.Exception.Enum.BusinessExceptionEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessException extends RuntimeException{

    private BusinessExceptionEnum e;

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    /**
     * 业务异常,不写入堆栈信息
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
