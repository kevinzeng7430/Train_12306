package com.kz.member.service.serviceImp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.kz.common.util.SnowUtil;
import com.kz.member.domain.Passenger;
import com.kz.member.mapper.PassengerMapper;
import com.kz.member.req.PassengerSaveReq;
import com.kz.member.service.PassengerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PassengerServiceImp implements PassengerService {
    @Resource
    private PassengerMapper passengerMapper;
    @Override
    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        log.info("保存save passenger:{}", passenger);
        passengerMapper.insert(passenger);
    }
}
