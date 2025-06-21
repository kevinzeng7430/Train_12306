package com.kz.member.service;

import com.kz.member.request.MemberLoginReq;
import com.kz.member.request.MemberRegisterReq;
import com.kz.member.request.MemberSendCodeReq;
import com.kz.member.response.MemberLoginResp;

public interface MemberService {
    int count();
    long register(MemberRegisterReq req);
    void sendCode(MemberSendCodeReq req);
    MemberLoginResp login(MemberLoginReq req);
}
