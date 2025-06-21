package com.kz.member.controller;


import com.kz.common.context.LoginMemberContext;
import com.kz.common.response.CommonResp;
import com.kz.member.request.PassengerQueryReq;
import com.kz.member.request.PassengerSaveReq;
import com.kz.member.response.PassengerQueryResp;
import com.kz.member.service.PassengerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    /*
        * 保存乘客信息
     */
    @PostMapping("/save")
    public CommonResp<Object> save(@Validated @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/queryList")
    public CommonResp<List<PassengerQueryResp>> queryList(@Validated PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        List<PassengerQueryResp> passengerList = passengerService.queryList(req);
        return new CommonResp<>(passengerList);
    }
}


//        @Validated
//        用于开启参数校验功能
//        会验证请求参数对象中的字段是否符合在 PassengerSaveReq 类中定义的校验规则
//        比如可以检查必填字段、字符串长度、数字范围等
//        如果验证失败，会抛出 MethodArgumentNotValidException 异常
//        @RequestBody
//                表示方法参数应该从请求体(request body)中获取
//        自动将 JSON 格式的请求数据转换为 Java 对象
//        通常用于处理 POST/PUT 请求中的 JSON 数据