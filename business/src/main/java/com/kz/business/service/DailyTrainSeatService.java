package com.kz.business.service;

import com.kz.business.request.DailyTrainSeatQueryReq;
import com.kz.business.request.DailyTrainSeatSaveReq;
import com.kz.business.response.DailyTrainSeatQueryResp;
import com.kz.common.response.PageResp;

import java.util.Date;

public interface DailyTrainSeatService {

    void save(DailyTrainSeatSaveReq req);

    PageResp<DailyTrainSeatQueryResp> queryList(DailyTrainSeatQueryReq req);

    void delete(Long id);

    void genDaily(Date date, String trainCode);

    int countSeat(Date date, String trainCode, String seatType);
}
