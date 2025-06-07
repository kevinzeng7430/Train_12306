package com.kz.member.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*
参数会自动映射到对应的属性，所以属性名和参数名需要一致
 */
@Data
public class MemberRegisterReq {
    @NotBlank(message = "手机号不能为空")
    String mobile;

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
