package com.kz.member.service.serviceImp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kz.common.context.LoginMemberContext;
import com.kz.common.response.PageResp;
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
        //用做编辑
        if(ObjectUtil.isNull(passenger.getId())){
            //如果乘客ID为空，说明是新增操作
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            log.info("保存save passenger:{}", passenger);
            passengerMapper.insert(passenger);
        }else{
            passenger.setUpdateTime(now);
            passengerMapper.updateByPrimaryKey(passenger);
        }

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
    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        passengerExample.setOrderByClause("id desc"); // 设置排序规则，id降序排列
        if(ObjectUtil.isNotNull(req.getMemberId())){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(req.getPageNum(),req.getPageSize()); // 分页查询，当前页2，每页2条数据
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);

        PageInfo<Passenger> pageInfo = new  PageInfo<>(passengerList);
        //PageInfo 是 PageHelper 插件提供的分页信息封装类。
        //它的作用是把查询结果 passengerList 包装起来，方便获取分页相关的数据，比如总记录数（getTotal）总页数（getPages）当前页码（getPageNum）每页条数（getPageSize）当前页数据列表（getList）
        log.info("总行数:{}", pageInfo.getTotal());
        log.info("总页数:{}", pageInfo.getPages());
        List<PassengerQueryResp> list =BeanUtil.copyToList(passengerList,PassengerQueryResp.class);
        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 删除乘客信息
     * @param id
     */
    @Override
    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }
}
