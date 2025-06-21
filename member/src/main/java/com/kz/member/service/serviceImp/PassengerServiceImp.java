package com.kz.member.service.serviceImp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.kz.common.context.LoginMemberContext;
import com.kz.common.util.SnowUtil;
import com.kz.member.domain.Passenger;
import com.kz.member.domain.PassengerExample;
import com.kz.member.mapper.PassengerMapper;
import com.kz.member.request.PassengerQueryReq;
import com.kz.member.request.PassengerSaveReq;
import com.kz.member.response.PassengerQueryResp;
import com.kz.member.service.PassengerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PassengerServiceImp implements PassengerService {
    @Resource
    private PassengerMapper passengerMapper;

    /**
     * 保存乘客信息
     * @param req
     */
    @Override
    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        log.info("保存save passenger:{}", passenger);
        passengerMapper.insert(passenger);
    }

    /**
     * 查询乘客列表
     * PassengerExample passengerExample = new PassengerExample();
     * 创建一个用于查询条件的PassengerExample对象，通常用于构建数据库查询的条件。
     * PassengerExample.Criteria criteria = passengerExample.createCriteria();
     * 创建一个Criteria对象，用于在passengerExample中添加具体的查询条件（比如 where 字句）
     * @param req
     * @return
     */
    @Override
    public List<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if(ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(req.getPageNum(),req.getPageSize()); // 分页查询，当前页2，每页2条数据
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(passengerList, PassengerQueryResp.class);
    }
}
