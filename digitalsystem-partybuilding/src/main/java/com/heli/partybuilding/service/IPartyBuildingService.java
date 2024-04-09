package com.heli.partybuilding.service;

import java.util.List;

import com.heli.partybuilding.domain.PartyBuilding;

/**
 * @description: [党建]指标Service接口
 * @author: hong
 * @date: 2024/4/9 15:40
 **/
public interface IPartyBuildingService {

    /**
     * @description: 按年查询数据
     * @author: hong
     * @date: 2024/4/9 15:39
     **/
    List<PartyBuilding> selectPartyBuildingListByYear(int year);

    /**
     * 查询[党建]指标填报
     *
     * @param pbId [党建]指标填报主键
     * @return [党建]指标填报
     */
    public PartyBuilding selectPartyBuildingByPbId(String pbId);

    /**
     * 查询[党建]指标填报列表
     *
     * @param partyBuilding [党建]指标填报
     * @return [党建]指标填报集合
     */
    public List<PartyBuilding> selectPartyBuildingList(PartyBuilding partyBuilding);

    /**
     * 新增[党建]指标填报
     *
     * @param partyBuilding [党建]指标填报
     * @return 结果
     */
    public int insertPartyBuilding(PartyBuilding partyBuilding);

    /**
     * 修改[党建]指标填报
     *
     * @param partyBuilding [党建]指标填报
     * @return 结果
     */
    public int updatePartyBuilding(PartyBuilding partyBuilding);

    /**
     * 批量删除[党建]指标填报
     *
     * @param pbIds 需要删除的[党建]指标填报主键集合
     * @return 结果
     */
    public int deletePartyBuildingByPbIds(String[] pbIds);

    /**
     * 删除[党建]指标填报信息
     *
     * @param pbId [党建]指标填报主键
     * @return 结果
     */
    public int deletePartyBuildingByPbId(String pbId);
}
