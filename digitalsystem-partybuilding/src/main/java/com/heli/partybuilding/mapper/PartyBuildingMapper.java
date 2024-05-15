package com.heli.partybuilding.mapper;

import java.util.Date;
import java.util.List;

import com.heli.partybuilding.domain.PartyBuilding;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;

/**
 * @description: [党建]指标填报Mapper接口
 * @author: hong
 * @date: 2024/4/9 15:42
 **/
@DataSource(value = DataSourceType.SLAVE)
public interface PartyBuildingMapper {

    boolean checkPartyBuildingDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);


    /**
     * @description: 按年查询党建数据
     * @author: hong
     * @date: 2024/4/9 15:42
     * @param: [year]
     * @return: java.util.List<com.heli.partybuilding.domain.PartyBuilding>
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
     * 删除[党建]指标填报
     *
     * @param pbId [党建]指标填报主键
     * @return 结果
     */
    public int deletePartyBuildingByPbId(String pbId);

    /**
     * 批量删除[党建]指标填报
     *
     * @param pbIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePartyBuildingByPbIds(String[] pbIds);
}
