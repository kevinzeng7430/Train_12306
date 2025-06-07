package com.kz.member.controller;

import com.kz.common.response.CommonResp;
import com.kz.member.req.MemberRegisterReq;
import com.kz.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/member/count")
    public CommonResp<Integer> count() {
        // 这里可以调用MemberService来获取会员数量
        // return memberService.count();
        int count = memberService.count();
        CommonResp<Integer> resp = new CommonResp<>();
        resp.setMessage("会员数量查询成功");
        resp.setContent(count);
        return resp; // 仅为示例，实际应返回真实数据
    }

    /**
     * 注册会员
     * @param req
     * @return
     */
    @PostMapping("/member/register")
    public CommonResp<Long> register(MemberRegisterReq req) {
        long count = memberService.register(req);
//        CommonResp<Long> resp = new CommonResp<>();
//        resp.setMessage("会员注册成功");
//        resp.setContent(count);
//        return resp;
        return  new CommonResp<>(count);
    }
}
