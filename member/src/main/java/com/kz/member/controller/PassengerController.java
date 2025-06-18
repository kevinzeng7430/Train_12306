package com.kz.member.controller;


import com.kz.common.response.CommonResp;
import com.kz.member.req.PassengerSaveReq;
import com.kz.member.service.PassengerService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Validated @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();


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