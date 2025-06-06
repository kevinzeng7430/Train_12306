package com.kz.member.service.serviceImp;

import com.kz.member.mapper.MemberMapper;
import com.kz.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService {
    @Resource
    private MemberMapper memberMapper;
    @Override
    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}


