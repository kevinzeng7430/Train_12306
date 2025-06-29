package com.kz.business.service;

import com.kz.business.request.DailyTrainStationQueryReq;
import com.kz.business.request.DailyTrainStationSaveReq;
import com.kz.business.response.DailyTrainStationQueryResp;
import com.kz.common.response.PageResp;

import java.util.Date;

public interface DailyTrainStationService {
    void save(DailyTrainStationSaveReq req);

    PageResp<DailyTrainStationQueryResp> queryList(DailyTrainStationQueryReq req);

    void delete(Long id);

    void genDaily(Date date, String trainCode);
}
