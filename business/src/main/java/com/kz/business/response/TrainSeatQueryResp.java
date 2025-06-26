package com.kz.business.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TrainSeatQueryResp {

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 车次编号
     */
    private String trainCode;

    /**
     * 厢序
     */
    private Integer carriageIndex;

    /**
     * 排号|01, 02
     */
    private String row;

    /**
     * 列号|枚举[SeatColEnum]
     */
    private String col;

    /**
     * 座位类型|枚举[SeatTypeEnum]
     */
    private String seatType;

    /**
     * 同车厢座序
     */
    private Integer carriageSeatIndex;

    /**
     * 新增时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @Override
    public String toString() {
        return "TrainSeatQueryResp{" +
                "id=" + id +
                ", trainCode='" + trainCode + '\'' +
                ", carriageIndex=" + carriageIndex +
                ", row='" + row + '\'' +
                ", col='" + col + '\'' +
                ", seatType='" + seatType + '\'' +
                ", carriageSeatIndex=" + carriageSeatIndex +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
