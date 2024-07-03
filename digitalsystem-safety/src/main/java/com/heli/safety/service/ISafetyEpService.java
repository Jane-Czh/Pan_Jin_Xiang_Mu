package com.heli.safety.service;

import java.util.Date;
import java.util.List;

import com.heli.safety.domain.SafetyEp;
import org.apache.ibatis.annotations.Param;

/**
 * [安全环保]指标填报Service接口
 *
 * @author ruoyi
 * @date 2024-04-16
 */
public interface ISafetyEpService {

    Boolean deleteEquipmentFailureCategoryDistributionByMonth(Long[] safetyEpIds);






    Boolean checkSafetyFillingDataIsExisted(Date date);


    public int InsertOrUpdateSafetyEp(SafetyEp safetyEp);



    /**
     * 查询[安全环保]指标填报
     *
     * @param safetyEpId [安全环保]指标填报主键
     * @return [安全环保]指标填报
     */
    public SafetyEp selectSafetyEpBySafetyEpId(Long safetyEpId);

    /**
     * 查询[安全环保]指标填报列表
     *
     * @param safetyEp [安全环保]指标填报
     * @return [安全环保]指标填报集合
     */
    public List<SafetyEp> selectSafetyEpList(SafetyEp safetyEp);

    /**
     * 新增[安全环保]指标填报
     *
     * @param safetyEp [安全环保]指标填报
     * @return 结果
     */
    public int insertSafetyEp(SafetyEp safetyEp);

    /**
     * 修改[安全环保]指标填报
     *
     * @param safetyEp [安全环保]指标填报
     * @return 结果
     */
    public int updateSafetyEp(SafetyEp safetyEp);

    /**
     * 批量删除[安全环保]指标填报
     *
     * @param safetyEpIds 需要删除的[安全环保]指标填报主键集合
     * @return 结果
     */
    public int deleteSafetyEpBySafetyEpIds(Long[] safetyEpIds);

    /**
     * 删除[安全环保]指标填报信息
     *
     * @param safetyEpId [安全环保]指标填报主键
     * @return 结果
     */
    public int deleteSafetyEpBySafetyEpId(Long safetyEpId);
}
