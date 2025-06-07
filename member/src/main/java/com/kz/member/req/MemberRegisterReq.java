package com.kz.member.req;

import lombok.Data;

/*
参数会自动映射到对应的属性，所以属性名和参数名需要一致
 */
@Data
public class MemberRegisterReq {
    String mobile;

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
