package com.kz.business.service;

import com.kz.business.domain.TrainCarriage;
import com.kz.business.request.TrainCarriageQueryReq;
import com.kz.business.request.TrainCarriageSaveReq;
import com.kz.business.response.TrainCarriageQueryResp;
import com.kz.common.response.PageResp;

import java.util.List;

public interface TrainCarriageService {

    void save(TrainCarriageSaveReq req);

    TrainCarriage selectByUnique(String trainCode, Integer index);

    PageResp<TrainCarriageQueryResp> queryList(TrainCarriageQueryReq req);

    void delete(Long id);

    List<TrainCarriage> selectByTrainCode(String trainCode);

}
