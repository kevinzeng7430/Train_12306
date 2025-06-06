package com.kz.member.service.serviceImp;

import cn.hutool.core.collection.CollUtil;
import com.kz.member.domain.Member;
import com.kz.member.domain.MemberExample;
import com.kz.member.mapper.MemberMapper;
import com.kz.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService {
    @Resource
    private MemberMapper memberMapper;
    @Override
    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    /**
     * 注册会员
     * @param mobile
     * @return
     */
    @Override
    public long register(String mobile) {
        // 检查手机号是否已存在
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if(CollUtil.isNotEmpty(members)){
            // 如果手机号已存在，返回-1
            //return members.get(0).getId();
            throw new RuntimeException("手机号已存在");
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}


