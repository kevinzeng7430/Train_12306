package com.kz.member.req;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberLoginReq {
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确") //以1开头，第二位是3-9，后面9位数字的11位手机号。
    String mobile;
    String code;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MemberLoginReq{");
        sb.append("mobile='").append(mobile).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
