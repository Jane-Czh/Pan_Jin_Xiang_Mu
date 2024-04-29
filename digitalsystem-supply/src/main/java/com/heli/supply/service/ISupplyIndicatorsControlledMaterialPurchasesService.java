package com.heli.supply.service;

import java.util.Date;
import java.util.List;

import com.heli.supply.domain.SupplyIndicatorsControlledMaterialPurchases;
import com.ruoyi.common.core.domain.entity.DisplayEntity;

/**
 * @description: 供应-指标-集团管控物资占比Service接口
 * @author hong
 * @date 2024-04-29
 */
public interface ISupplyIndicatorsControlledMaterialPurchasesService {

    /**
     * 检查当月数据是否上传
     */
    Boolean checkControlledPurchaseAmountRatioIsExisted(Date date);

    /**
     * 安全插入、更新集采物资比例表
     */
    int batchInsertOrUpdateControlledMaterialPurchases(List<SupplyIndicatorsControlledMaterialPurchases> controlledMaterialPurchasesList);

    /**
     * 集团管控物资采购金额占比 指标31-展示
     */
    List<DisplayEntity> selectControlledPurchaseAmountRatio(Date startTime, Date endTime);

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
     * 批量删除供应-指标-集团管控物资占比
     *
     * @param scpIds 需要删除的供应-指标-集团管控物资占比主键集合
     * @return 结果
     */
    public int deleteSupplyIndicatorsControlledMaterialPurchasesByScpIds(Long[] scpIds);

    /**
     * 删除供应-指标-集团管控物资占比信息
     *
     * @param scpId 供应-指标-集团管控物资占比主键
     * @return 结果
     */
    public int deleteSupplyIndicatorsControlledMaterialPurchasesByScpId(Long scpId);
}
