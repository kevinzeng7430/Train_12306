package com.kz.member.req;

/*
参数会自动映射到对应的属性，所以属性名和参数名需要一致
 */
public class MemberRegisterReq {
    String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
