package com.kz.business.request;

import com.kz.common.request.PageReq;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyTrainSeatQueryReq extends PageReq {

    private String trainCode;

    @Override
    public String toString() {
        return "DailyTrainSeatQueryReq{" +
                "trainCode='" + trainCode + '\'' +
                "} " + super.toString();
    }
}

