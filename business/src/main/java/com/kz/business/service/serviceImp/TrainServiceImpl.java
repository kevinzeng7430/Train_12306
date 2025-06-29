package com.kz.business.service.serviceImp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kz.business.domain.Train;
import com.kz.business.domain.TrainExample;
import com.kz.business.mapper.TrainMapper;
import com.kz.business.request.TainSaveReq;
import com.kz.business.request.TrainQueryReq;
import com.kz.business.response.TrainQueryResp;
import com.kz.business.service.TrainService;
import com.kz.common.Exception.BusinessException;
import com.kz.common.Exception.Enum.BusinessExceptionEnum;
import com.kz.common.response.PageResp;
import com.kz.common.util.SnowUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TrainServiceImpl implements TrainService {

    @Resource
    private TrainMapper trainMapper;

    @Override
    public void save(TainSaveReq req) {
        DateTime now = DateTime.now();
        Train train = BeanUtil.copyProperties(req, Train.class);
        if(ObjectUtil.isNull((train.getId()))){
            //保存前先校验唯一键是否存在
            Train trainDB = selectByUnique(req.getCode());
            if(ObjectUtil.isNotEmpty(trainDB)){
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_TRAIN_CODE_UNIQUE_ERROR);
            }
            train.setId(SnowUtil.getSnowflakeNextId());
            train.setCreateTime(now);
            train.setUpdateTime(now);
            trainMapper.insert(train);
        }else{
            train.setUpdateTime(now);
            trainMapper.updateByPrimaryKey(train); //除了主键外，通常只更新非空字段
        }
    }

    @Override
    public Train selectByUnique(String code) {
        TrainExample trainExample = new TrainExample(); //查询条件构造器类
        trainExample.createCriteria().andCodeEqualTo(code); // 添加查询条件(createCriteria()：创建一个新的查询条件对象（相当于 SQL 中的 WHERE 条件）。
//        andCodeEqualTo(code)：添加一个条件，表示字段 code 等于传入的参数 code。)
        List<Train> trainList = trainMapper.selectByExample(trainExample);
        if(CollUtil.isNotEmpty(trainList)){
            return trainList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public PageResp<TrainQueryResp> queryList(TrainQueryReq req) {
        TrainExample trainExample = new TrainExample();
        trainExample.setOrderByClause("code asc");
        TrainExample.Criteria criteria = trainExample.createCriteria();

        log.info("查询页码：{}", req.getPageNum());
        log.info("每页条数：{}", req.getPageSize());
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<Train> trainList = trainMapper.selectByExample(trainExample);

        PageInfo<Train> pageInfo = new PageInfo<>(trainList);
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());

        List<TrainQueryResp> list =BeanUtil.copyToList(trainList,TrainQueryResp.class);
        PageResp<TrainQueryResp> pageResp = new PageResp<>();
        log.info("总行数:{}", pageInfo.getTotal());
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    @Override
    public void delete(Long id) {
        trainMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TrainQueryResp> queryAll() {
        TrainExample trainExample = new TrainExample();
        trainExample.setOrderByClause("code asc");
        List<Train> trainList = trainMapper.selectByExample(trainExample);
        return BeanUtil.copyToList(trainList, TrainQueryResp.class);
    }
    @Override
    public List<Train> selectAll() {
        TrainExample trainExample = new TrainExample();
        trainExample.setOrderByClause("code asc");
        return trainMapper.selectByExample(trainExample);
    }
}
