package com.kz.business.service;

import com.kz.business.request.ConfirmOrderDoReq;
import com.kz.business.request.ConfirmOrderQueryReq;
import com.kz.business.response.ConfirmOrderQueryResp;
import com.kz.common.response.PageResp;

public interface ConfirmOrderService {
    void save(ConfirmOrderDoReq req);
    PageResp<ConfirmOrderQueryResp> queryList(ConfirmOrderQueryReq req);
    void delete(Long id);
    void doConfirm(ConfirmOrderDoReq req);
}
