package com.kz.member.service;

import com.kz.member.req.MemberRegisterReq;

public interface MemberService {
    int count();
    long register(MemberRegisterReq req);
}
