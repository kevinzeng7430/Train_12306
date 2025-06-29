package com.kz.business.service;

import com.kz.business.domain.Train;
import com.kz.business.request.DailyTrainQueryReq;
import com.kz.business.request.DailyTrainSaveReq;
import com.kz.business.response.DailyTrainQueryResp;
import com.kz.common.response.PageResp;

import java.util.Date;

public interface DailyTrainService {
    void save(DailyTrainSaveReq req);

    PageResp<DailyTrainQueryResp> queryList(DailyTrainQueryReq req);

    void delete(Long id);

    void genDaily(Date date);

    void genDailyTrain(Date date, Train train);
}
