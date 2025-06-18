package com.kz.common.context;

import com.kz.common.response.MemberLoginResp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginMemberContext {
    private static final ThreadLocal<MemberLoginResp> member = new ThreadLocal<>();

    public static MemberLoginResp getMember(){
        return member.get();
    }
    public static void setMember(MemberLoginResp member){
        LoginMemberContext.member.set(member);
    }
    public static Long getId(){
        try{
            return member.get().getId();
        }catch(Exception e){
            log.info("获取登录会员信息异常",e);
            throw e;
        }
    }
}
