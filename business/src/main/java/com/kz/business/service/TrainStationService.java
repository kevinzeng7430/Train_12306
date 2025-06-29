package com.kz.business.service;

import com.kz.business.domain.TrainStation;
import com.kz.business.request.TrainStationQueryReq;
import com.kz.business.request.TrainStationSaveReq;
import com.kz.business.response.TrainStationQueryResp;
import com.kz.common.response.PageResp;

import java.util.List;

public interface TrainStationService {

    void save(TrainStationSaveReq req);

    TrainStation selectByUnique(String trainCode, Integer  index);

    TrainStation selectByUnique(String trainCode, String trainName);

    PageResp<TrainStationQueryResp> queryList(TrainStationQueryReq req);

    void delete(Long id);

    List<TrainStation> selectByTrainCode(String trainCode);

}
