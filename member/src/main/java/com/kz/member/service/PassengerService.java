package com.kz.member.service;

import com.kz.member.request.PassengerQueryReq;
import com.kz.member.request.PassengerSaveReq;
import com.kz.member.response.PassengerQueryResp;

import java.util.List;


public interface PassengerService {
    void save(PassengerSaveReq req);
    List<PassengerQueryResp> queryList(PassengerQueryReq req);
}
