package com.kz.business.service;

import com.kz.business.domain.TrainSeat;
import com.kz.business.request.TrainSeatQueryReq;
import com.kz.business.request.TrainSeatSaveReq;
import com.kz.business.response.TrainSeatQueryResp;
import com.kz.common.response.PageResp;

import java.util.List;

public interface TrainSeatService {

    void save(TrainSeatSaveReq req);
    PageResp<TrainSeatQueryResp> queryList(TrainSeatQueryReq req);
    void delete(Long id);
    void genTrainSeat(String trainCode);
    List<TrainSeat> selectByTrainCode(String trainCode);

}
