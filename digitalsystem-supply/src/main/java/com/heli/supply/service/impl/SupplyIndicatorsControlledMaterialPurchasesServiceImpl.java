package com.heli.supply.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.entity.DisplayEntity;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.supply.mapper.SupplyIndicatorsControlledMaterialPurchasesMapper;
import com.heli.supply.domain.SupplyIndicatorsControlledMaterialPurchases;
import com.heli.supply.service.ISupplyIndicatorsControlledMaterialPurchasesService;

/**
 * @description: 供应-指标-集团管控物资占比Service业务层处理
 * @author: hong
 * @date: 2024-04-29
 */
@Service
public class SupplyIndicatorsControlledMaterialPurchasesServiceImpl implements ISupplyIndicatorsControlledMaterialPurchasesService {
    @Autowired
    private SupplyIndicatorsControlledMaterialPurchasesMapper supplyIndicatorsControlledMaterialPurchasesMapper;

    /**
     * @description: 检查当月数据是否上传
     * @author: hong
     * @date: 2024/4/29 23:07
     **/
    @Override
    public Boolean checkControlledPurchaseAmountRatioIsExisted(Date date) {
        return supplyIndicatorsControlledMaterialPurchasesMapper.checkControlledPurchaseAmountRatioIsExisted(date);
    }

    /**
     * @description: 安全插入、更新集采物资比例表
     * @author: hong
     * @date: 2024/4/29 23:07
     **/
    @Override
    public int batchInsertOrUpdateControlledMaterialPurchases(List<SupplyIndicatorsControlledMaterialPurchases> controlledMaterialPurchasesList) {
        return supplyIndicatorsControlledMaterialPurchasesMapper.batchInsertOrUpdateControlledMaterialPurchases(controlledMaterialPurchasesList);
    }

    /**
     * @description: 集团管控物资采购金额占比 指标31-展示
     * @author: hong
     * @date: 2024/4/29 23:07
     **/
    @Override
    public List<DisplayEntity> selectControlledPurchaseAmountRatio(Date startTime, Date endTime) {
        return supplyIndicatorsControlledMaterialPurchasesMapper.selectControlledPurchaseAmountRatio(startTime, endTime);
    }

    /**
     * 查询供应-指标-集团管控物资占比
     *
     * @param scpId 供应-指标-集团管控物资占比主键
     * @return 供应-指标-集团管控物资占比
     */
    @Override
    public SupplyIndicatorsControlledMaterialPurchases selectSupplyIndicatorsControlledMaterialPurchasesByScpId(Long scpId) {
        return supplyIndicatorsControlledMaterialPurchasesMapper.selectSupplyIndicatorsControlledMaterialPurchasesByScpId(scpId);
    }

    /**
     * 查询供应-指标-集团管控物资占比列表
     *
     * @param supplyIndicatorsControlledMaterialPurchases 供应-指标-集团管控物资占比
     * @return 供应-指标-集团管控物资占比
     */
    @Override
    public List<SupplyIndicatorsControlledMaterialPurchases> selectSupplyIndicatorsControlledMaterialPurchasesList(SupplyIndicatorsControlledMaterialPurchases supplyIndicatorsControlledMaterialPurchases) {
        return supplyIndicatorsControlledMaterialPurchasesMapper.selectSupplyIndicatorsControlledMaterialPurchasesList(supplyIndicatorsControlledMaterialPurchases);
    }

    /**
     * 新增供应-指标-集团管控物资占比
     *
     * @param supplyIndicatorsControlledMaterialPurchases 供应-指标-集团管控物资占比
     * @return 结果
     */
    @Override
    public int insertSupplyIndicatorsControlledMaterialPurchases(SupplyIndicatorsControlledMaterialPurchases supplyIndicatorsControlledMaterialPurchases) {
        supplyIndicatorsControlledMaterialPurchases.setCreateTime(DateUtils.getNowDate());
        return supplyIndicatorsControlledMaterialPurchasesMapper.insertSupplyIndicatorsControlledMaterialPurchases(supplyIndicatorsControlledMaterialPurchases);
    }

    /**
     * 修改供应-指标-集团管控物资占比
     *
     * @param supplyIndicatorsControlledMaterialPurchases 供应-指标-集团管控物资占比
     * @return 结果
     */
    @Override
    public int updateSupplyIndicatorsControlledMaterialPurchases(SupplyIndicatorsControlledMaterialPurchases supplyIndicatorsControlledMaterialPurchases) {
        supplyIndicatorsControlledMaterialPurchases.setUpdateTime(DateUtils.getNowDate());
        return supplyIndicatorsControlledMaterialPurchasesMapper.updateSupplyIndicatorsControlledMaterialPurchases(supplyIndicatorsControlledMaterialPurchases);
    }

    /**
     * 批量删除供应-指标-集团管控物资占比
     *
     * @param scpIds 需要删除的供应-指标-集团管控物资占比主键
     * @return 结果
     */
    @Override
    public int deleteSupplyIndicatorsControlledMaterialPurchasesByScpIds(Long[] scpIds) {
        return supplyIndicatorsControlledMaterialPurchasesMapper.deleteSupplyIndicatorsControlledMaterialPurchasesByScpIds(scpIds);
    }

    /**
     * 删除供应-指标-集团管控物资占比信息
     *
     * @param scpId 供应-指标-集团管控物资占比主键
     * @return 结果
     */
    @Override
    public int deleteSupplyIndicatorsControlledMaterialPurchasesByScpId(Long scpId) {
        return supplyIndicatorsControlledMaterialPurchasesMapper.deleteSupplyIndicatorsControlledMaterialPurchasesByScpId(scpId);
    }
}
