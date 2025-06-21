package com.kz.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PageResp<T> implements Serializable {
    /**
     * 总记录数
     */
    private Long total;
    /*
        * 当前页码列表
     */
    private List<T> list;
}
