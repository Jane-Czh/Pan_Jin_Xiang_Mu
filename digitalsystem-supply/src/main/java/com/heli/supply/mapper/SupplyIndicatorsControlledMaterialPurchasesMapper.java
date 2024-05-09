package com.heli.supply.mapper;

import java.util.Date;
import java.util.List;

import com.heli.supply.domain.SupplyIndicatorsControlledMaterialPurchases;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 供应-指标-集团管控物资占比Mapper接口
 * @author hong
 * @date 2024-04-29
 */
@DataSource(value = DataSourceType.SLAVE)
public interface SupplyIndicatorsControlledMaterialPurchasesMapper {

    /**
     * 集团管控物资采购金额占比 指标31-展示
     */
    List<DisplayEntity> selectControlledPurchaseAmountRatio(@Param("startTime") Date startTime,
                                                            @Param("endTime") Date endTime);
    /**
     * 批量安全插入、更新，数据存在则更新，不存在则插入
     */
    int batchInsertOrUpdateControlledMaterialPurchases(@Param("controlledMaterialPurchasesList") List<SupplyIndicatorsControlledMaterialPurchases> controlledMaterialPurchasesList);

    /**
     * 检查当月数据是否上传
     */
    Boolean checkControlledPurchaseAmountRatioIsExisted(@Param("date") Date date);


    /**
     * 查询供应-指标-集团管控物资占比
     *
     * @param scpId 供应-指标-集团管控物资占比主键
     * @return 供应-指标-集团管控物资占比
     */
    public SupplyIndicatorsControlledMaterialPurchases selectSupplyIndicatorsControlledMaterialPurchasesByScpId(Long scpId);

    /**
     * 查询供应-指标-集团管控物资占比列表
     *
     * @param supplyIndicatorsControlledMaterialPurchases 供应-指标-集团管控物资占比
     * @return 供应-指标-集团管控物资占比集合
     */
    public List<SupplyIndicatorsControlledMaterialPurchases> selectSupplyIndicatorsControlledMaterialPurchasesList(SupplyIndicatorsControlledMaterialPurchases supplyIndicatorsControlledMaterialPurchases);

    /**
     * 新增供应-指标-集团管控物资占比
     *
     * @param supplyIndicatorsControlledMaterialPurchases 供应-指标-集团管控物资占比
     * @return 结果
     */
    public int insertSupplyIndicatorsControlledMaterialPurchases(SupplyIndicatorsControlledMaterialPurchases supplyIndicatorsControlledMaterialPurchases);

    /**
     * 修改供应-指标-集团管控物资占比
     *
     * @param supplyIndicatorsControlledMaterialPurchases 供应-指标-集团管控物资占比
     * @return 结果
     */
    public int updateSupplyIndicatorsControlledMaterialPurchases(SupplyIndicatorsControlledMaterialPurchases supplyIndicatorsControlledMaterialPurchases);

    /**
     * 删除供应-指标-集团管控物资占比
     *
     * @param scpId 供应-指标-集团管控物资占比主键
     * @return 结果
     */
    public int deleteSupplyIndicatorsControlledMaterialPurchasesByScpId(Long scpId);

    /**
     * 批量删除供应-指标-集团管控物资占比
     *
     * @param scpIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplyIndicatorsControlledMaterialPurchasesByScpIds(Long[] scpIds);
}
