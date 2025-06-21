package com.kz.member.service;

import com.kz.common.response.PageResp;
import com.kz.member.request.PassengerQueryReq;
import com.kz.member.request.PassengerSaveReq;
import com.kz.member.response.PassengerQueryResp;


public interface PassengerService {
    void save(PassengerSaveReq req);
    PageResp<PassengerQueryResp> queryList(PassengerQueryReq req);
    void delete(Long id);
}
