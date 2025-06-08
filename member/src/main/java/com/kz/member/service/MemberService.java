package com.kz.member.service;

import com.kz.member.req.MemberLoginReq;
import com.kz.member.req.MemberRegisterReq;
import com.kz.member.req.MemberSendCodeReq;
import com.kz.member.response.MemberLoginResp;

public interface MemberService {
    int count();
    long register(MemberRegisterReq req);
    void sendCode(MemberSendCodeReq req);
    MemberLoginResp login(MemberLoginReq req);
}
