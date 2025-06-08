package com.kz.member.service.serviceImp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.kz.common.Exception.BusinessException;
import com.kz.common.Exception.Enum.BusinessExceptionEnum;
import com.kz.common.util.SnowUtil;
import com.kz.member.domain.Member;
import com.kz.member.domain.MemberExample;
import com.kz.member.mapper.MemberMapper;
import com.kz.member.req.MemberRegisterReq;
import com.kz.member.req.MemberSendCodeReq;
import com.kz.member.service.MemberService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
     *
     * @param req
     * @return
     */
    @Override
    public long register(MemberRegisterReq req) {
        // 检查手机号是否已存在
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(members)) {
            // 如果手机号已存在，返回-1
            //return members.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
//        member.setId(System.currentTimeMillis());
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    /**
     * 发送验证码
     *
     * @param req
     * @return
     */
    @Override
    public void sendCode(MemberSendCodeReq req) {
        // 检查手机号是否已存在
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);

        // 如果手机号不存在，则插入
        if(ObjectUtil.isEmpty(memberDB)){
            log.info("手机号不存在，准备插入新会员，手机号：{}", mobile);
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }else{
            log.info("手机号已存在：{}", mobile);
        }
        // 生成验证码
        String code = "8888"; //方便测试
//        code = RandomUtil.randomString(4);
        log.info("生成验证码短信：{}", code);

        // TODO:短信验证逻辑
        // 保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        log.info("保存短信记录表");

        // 对接短信通道，发送短信
        log.info("对接短信通道");
    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }
}


