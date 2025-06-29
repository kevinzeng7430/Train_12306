package com.kz.business.service;

import com.kz.business.domain.DailyTrain;
import com.kz.business.request.DailyTrainTicketQueryReq;
import com.kz.business.request.DailyTrainTicketSaveReq;
import com.kz.business.response.DailyTrainTicketQueryResp;
import com.kz.common.response.PageResp;

import java.util.Date;

public interface DailyTrainTicketService {
    void save(DailyTrainTicketSaveReq req);

    PageResp<DailyTrainTicketQueryResp> queryList(DailyTrainTicketQueryReq req);

    void delete(Long id);

    void genDaily(DailyTrain dailyTrain, Date date, String trainCode);
}
