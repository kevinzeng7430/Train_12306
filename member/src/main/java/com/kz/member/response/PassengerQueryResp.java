package com.kz.member.response;

import lombok.Data;


import java.util.Date;

@Data
public class PassengerQueryResp {

    private Long memberId;

    private String name;

    private String idCard;

    private String type;

    private Date createTime;

    private Date updateTime;

}