package com.kz.business.service;

import com.kz.business.domain.Train;
import com.kz.business.request.TainSaveReq;
import com.kz.business.request.TrainQueryReq;
import com.kz.business.response.TrainQueryResp;
import com.kz.common.response.PageResp;

import java.util.List;

public interface TrainService {

    void save(TainSaveReq req);

    Train selectByUnique(String code);

    PageResp<TrainQueryResp> queryList(TrainQueryReq req);

    void delete(Long id);

    List<TrainQueryResp> queryAll();
}
