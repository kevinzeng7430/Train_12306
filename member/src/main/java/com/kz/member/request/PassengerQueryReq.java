package com.kz.member.request;

import com.kz.common.request.PageReq;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PassengerQueryReq extends PageReq {
    private Long memberId;

    @Override
    public String toString() {
        return "PassengerQueryReq{" +
                "memberId=" + memberId +
                '}';
    }
}
