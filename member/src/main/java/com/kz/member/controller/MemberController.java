package com.kz.member.controller;

import com.kz.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/member/count")
    public int count() {
        // 这里可以调用MemberService来获取会员数量
        // return memberService.count();
        return memberService.count(); // 仅为示例，实际应返回真实数据
    }
}
