package com.kz.business.service.serviceImp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kz.business.domain.Station;
import com.kz.business.domain.StationExample;
import com.kz.business.mapper.StationMapper;
import com.kz.business.request.StationQueryReq;
import com.kz.business.request.StationSaveReq;
import com.kz.business.response.StationQueryResp;
import com.kz.business.service.StationService;
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
public class StationServiceImpl implements StationService {

    @Resource
    private StationMapper stationMapper;

    /**
     * 保存站点信息
     * @param req
     */
    @Override
    public void save(StationSaveReq req) {
        DateTime now = DateTime.now();
        Station station = BeanUtil.copyProperties(req, Station.class);

        if(ObjectUtil.isNull((station.getId()))) {
            //保存前先校验唯一键是否存在
            Station stationDB = selectByUnique(req.getName());
            if(ObjectUtil.isNotEmpty(stationDB)) {
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_STATION_NAME_UNIQUE_ERROR);
            }
            station.setId(SnowUtil.getSnowflakeNextId());
            station.setCreateTime(now);
            station.setUpdateTime(now);
            stationMapper.insert(station);
        }else{
            station.setUpdateTime(now);
            stationMapper.updateByPrimaryKey(station);
        }
    }

    @Override
    public Station selectByUnique(String name) {
        StationExample stationExample = new StationExample();
        stationExample.createCriteria().andNameEqualTo(name);
        List<Station> stationList = stationMapper.selectByExample(stationExample);
        if(CollUtil.isNotEmpty(stationList)) {
            return  stationList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public PageResp<StationQueryResp> queryList(StationQueryReq req) {
        StationExample stationExample = new StationExample();
        stationExample.setOrderByClause("id desc");
        StationExample.Criteria criteria = stationExample.createCriteria();

        log.info("查询页码:{}",req.getPageNum());
        log.info("每页条数:{}",req.getPageSize());

        PageHelper.startPage(req.getPageNum(), req.getPageSize()); //启动分页功能，设置当前页码和每页条数，后续的 SQL 查询会自动分页
        List<Station> stationList = stationMapper.selectByExample(stationExample); //执行数据库查询，获取分页后的站点列表
        PageInfo<Station> pageInfo = new PageInfo<>(stationList); //用 PageInfo 包装查询结果，方便获取总条数等分页信息
        List<StationQueryResp> respList = BeanUtil.copyToList(stationList, StationQueryResp.class); //将实体列表转换为响应对象列表，便于前端展示。
        PageResp<StationQueryResp> pageResp = new PageResp<>(); //创建分页响应对象
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    @Override
    public void delete(Long id) {
        stationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<StationQueryResp> queryAll() {
        StationExample stationExample = new StationExample();
        stationExample.setOrderByClause("name_pinyin asc");
        List<Station> stationList = stationMapper.selectByExample(stationExample);
        return BeanUtil.copyToList(stationList, StationQueryResp.class);
    }
}
