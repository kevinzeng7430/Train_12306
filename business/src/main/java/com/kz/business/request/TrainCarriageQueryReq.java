package com.kz.business.request;

import com.kz.common.request.PageReq;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainCarriageQueryReq extends PageReq {
    private String trainCode;

    @Override
    public String toString() {
        return "TrainCarriageQueryReq{" +
                "trainCode='" + trainCode + '\'' +
                '}';
    }
}
