package com.kz.business.request;

import com.kz.common.request.PageReq;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TrainStationQueryReq extends PageReq {
    private String trainCode;

    @Override
    public String toString() {
        return "TrainStationQueryReq{" +
                "trainCode='" + trainCode + '\'' +
                '}';
    }
}
