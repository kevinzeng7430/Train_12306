package com.kz.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PassengerSaveReq {
    private Long id;
    private Long memberId;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "身份证号不能为空")
    private String idCard;
    @NotBlank(message = "乘客类型不能为空")
    private String type;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", name=").append(name);
        sb.append(", idCard=").append(idCard);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}