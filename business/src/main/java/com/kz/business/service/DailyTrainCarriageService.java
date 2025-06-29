package com.kz.business.service;

import com.kz.business.request.DailyTrainCarriageQueryReq;
import com.kz.business.request.DailyTrainCarriageSaveReq;
import com.kz.business.response.DailyTrainCarriageQueryResp;
import com.kz.common.response.PageResp;

import java.util.Date;

public interface DailyTrainCarriageService {
    void save(DailyTrainCarriageSaveReq req);
    PageResp<DailyTrainCarriageQueryResp> queryList(DailyTrainCarriageQueryReq req);

    void delete(Long id);

    void genDaily(Date date, String trainCode);

}
