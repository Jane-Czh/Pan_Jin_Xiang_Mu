package com.heli.partybuilding.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.partybuilding.mapper.PartyBuildingMapper;
import com.heli.partybuilding.domain.PartyBuilding;
import com.heli.partybuilding.service.IPartyBuildingService;

/**
 * @description: [党建]指标Service业务层处理
 * @author: hong
 * @date: 2024/4/9 15:41
 **/
@Service
public class PartyBuildingServiceImpl implements IPartyBuildingService {
    @Autowired
    private PartyBuildingMapper partyBuildingMapper;

    @Override
    public Boolean checkPartyBuildingDataIsExisted(Date yearAndMonth) {
        return partyBuildingMapper.checkPartyBuildingDataIsExisted(yearAndMonth);
    }

    /**
     * @description: 按年查询党建数据
     * @author: hong
     * @date: 2024/4/9 15:41
     * @param: [year]
     * @return: java.util.List<com.heli.partybuilding.domain.PartyBuilding>
     **/
    @Override
    public List<PartyBuilding> selectPartyBuildingListByYear(int year) {
        return partyBuildingMapper.selectPartyBuildingListByYear(year);
    }

    /**
     * 查询[党建]指标填报
     *
     * @param pbId [党建]指标填报主键
     * @return [党建]指标填报
     */
    @Override
    public PartyBuilding selectPartyBuildingByPbId(String pbId) {
        return partyBuildingMapper.selectPartyBuildingByPbId(pbId);
    }

    /**
     * 查询[党建]指标填报列表
     *
     * @param partyBuilding [党建]指标填报
     * @return [党建]指标填报
     */
    @Override
    public List<PartyBuilding> selectPartyBuildingList(PartyBuilding partyBuilding) {
        return partyBuildingMapper.selectPartyBuildingList(partyBuilding);
    }

    /**
     * 新增[党建]指标填报
     *
     * @param partyBuilding [党建]指标填报
     * @return 结果
     */
    @Override
    public int insertPartyBuilding(PartyBuilding partyBuilding) {
        partyBuilding.setCreateTime(DateUtils.getNowDate());
        return partyBuildingMapper.insertPartyBuilding(partyBuilding);
    }

    /**
     * 修改[党建]指标填报
     *
     * @param partyBuilding [党建]指标填报
     * @return 结果
     */
    @Override
    public int updatePartyBuilding(PartyBuilding partyBuilding) {
        partyBuilding.setUpdateTime(DateUtils.getNowDate());
        return partyBuildingMapper.updatePartyBuilding(partyBuilding);
    }

    /**
     * 批量删除[党建]指标填报
     *
     * @param pbIds 需要删除的[党建]指标填报主键
     * @return 结果
     */
    @Override
    public int deletePartyBuildingByPbIds(String[] pbIds) {
        return partyBuildingMapper.deletePartyBuildingByPbIds(pbIds);
    }

    /**
     * 删除[党建]指标填报信息
     *
     * @param pbId [党建]指标填报主键
     * @return 结果
     */
    @Override
    public int deletePartyBuildingByPbId(String pbId) {
        return partyBuildingMapper.deletePartyBuildingByPbId(pbId);
    }
}
