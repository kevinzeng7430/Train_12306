package com.kz.business.service;


import com.kz.business.domain.Station;
import com.kz.business.request.StationQueryReq;
import com.kz.business.request.StationSaveReq;
import com.kz.business.response.StationQueryResp;
import com.kz.common.response.PageResp;

import java.util.List;

public interface StationService {

    void save(StationSaveReq req);

    Station selectByUnique(String name);

    PageResp<StationQueryResp> queryList(StationQueryReq req);

    void delete(Long id);

    List<StationQueryResp> queryAll();

}
