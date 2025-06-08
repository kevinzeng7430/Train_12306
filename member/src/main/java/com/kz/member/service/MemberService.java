package com.kz.member.service;

import com.kz.member.req.MemberRegisterReq;
import com.kz.member.req.MemberSendCodeReq;

public interface MemberService {
    int count();
    long register(MemberRegisterReq req);
    void sendCode(MemberSendCodeReq req);
}
