package com.kz.business.service.serviceImp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kz.business.domain.TrainCarriage;
import com.kz.business.domain.TrainSeat;
import com.kz.business.domain.TrainSeatExample;
import com.kz.business.enums.SeatColEnum;
import com.kz.business.mapper.TrainSeatMapper;
import com.kz.business.request.TrainSeatQueryReq;
import com.kz.business.request.TrainSeatSaveReq;
import com.kz.business.response.TrainSeatQueryResp;
import com.kz.business.service.TrainCarriageService;
import com.kz.business.service.TrainSeatService;
import com.kz.common.response.PageResp;
import com.kz.common.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class TrainSeatServiceImpl implements TrainSeatService {

    @Resource
    private TrainSeatMapper trainSeatMapper;
    @Resource
    private TrainCarriageService trainCarriageService;

    @Override
    public void save(TrainSeatSaveReq req) {
        DateTime now = DateTime.now();
        TrainSeat trainSeat = BeanUtil.copyProperties(req, TrainSeat.class);
        if(ObjectUtil.isNotEmpty(trainSeat.getId())){
            // 检查唯一键
           trainSeat.setId(SnowUtil.getSnowflakeNextId());
           trainSeat.setCreateTime( now);
           trainSeat.setUpdateTime(now);
           trainSeatMapper.insert(trainSeat);
        }else{
            trainSeat.setUpdateTime(now);
            trainSeatMapper.updateByPrimaryKey(trainSeat);
        }
    }

    @Override
    public PageResp<TrainSeatQueryResp> queryList(TrainSeatQueryReq req) {
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        trainSeatExample.setOrderByClause("train_code asc, carriage_index asc, carriage_seat_index asc");
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        if(ObjectUtil.isNotEmpty(req.getTrainCode())){
            criteria.andTrainCodeEqualTo(req.getTrainCode());
        }
        log.info("查询页码：{}", req.getPageNum());
        log.info("查询条数：{}", req.getPageSize());
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<TrainSeat> trainSeatList = trainSeatMapper.selectByExample(trainSeatExample);
        PageInfo<TrainSeat> pageInfo = new PageInfo<>(trainSeatList);
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());
        List<TrainSeatQueryResp> list = BeanUtil.copyToList(trainSeatList, TrainSeatQueryResp.class);
        PageResp<TrainSeatQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    @Override
    public void delete(Long id) {
        trainSeatMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void genTrainSeat(String trainCode) {
        DateTime now = DateTime.now();
        // 清空当前车次下的所有的座位记录
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        TrainSeatExample.Criteria criteria = trainSeatExample.createCriteria();
        criteria.andTrainCodeEqualTo(trainCode);
        trainSeatMapper.deleteByExample(trainSeatExample);

        // 查找当前车次下的所有的车厢
        List<TrainCarriage> carriageList = trainCarriageService.selectByTrainCode(trainCode);
        log.info("当前车次下的车厢数：{}", carriageList.size());

        // 循环生成每个车厢的座位
        for (TrainCarriage trainCarriage : carriageList) {
            // 拿到车厢数据：行数、座位类型(得到列数)
            Integer rowCount = trainCarriage.getRowCount();
            String seatType = trainCarriage.getSeatType();
            int seatIndex = 1;

            // 根据车厢的座位类型，筛选出所有的列，比如车箱类型是一等座，则筛选出columnList={ACDF}
            List<SeatColEnum> colEnumList = SeatColEnum.getColsByType(seatType);
            log.info("根据车厢的座位类型，筛选出所有的列：{}", colEnumList);

            // 循环行数
            for (int row = 1; row <= rowCount; row++) {
                // 循环列数
                for (SeatColEnum seatColEnum : colEnumList) {
                    // 构造座位数据并保存数据库
                    TrainSeat trainSeat = new TrainSeat();
                    trainSeat.setId(SnowUtil.getSnowflakeNextId());
                    trainSeat.setTrainCode(trainCode);
                    trainSeat.setCarriageIndex(trainCarriage.getIndex());
                    trainSeat.setRow(StrUtil.fillBefore(String.valueOf(row), '0', 2));
                    trainSeat.setCol(seatColEnum.getCode());
                    trainSeat.setSeatType(seatType);
                    trainSeat.setCarriageSeatIndex(seatIndex++);
                    trainSeat.setCreateTime(now);
                    trainSeat.setUpdateTime(now);
                    trainSeatMapper.insert(trainSeat);
                }
            }
        }
    }

    @Override
    public List<TrainSeat> selectByTrainCode(String trainCode) {
        TrainSeatExample trainSeatExample = new TrainSeatExample();
        trainSeatExample.setOrderByClause("`id` asc");
        trainSeatExample.createCriteria().andTrainCodeEqualTo(trainCode);
        return trainSeatMapper.selectByExample(trainSeatExample);
    }
}
