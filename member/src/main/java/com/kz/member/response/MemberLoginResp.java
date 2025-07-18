package com.kz.member.response;

import lombok.Data;

@Data
public class MemberLoginResp {
    private Long id;

    private String mobile;

    private String token;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mobile=").append(mobile);
        sb.append(", token=").append(token);
        sb.append("]");
        return sb.toString();
    }
}