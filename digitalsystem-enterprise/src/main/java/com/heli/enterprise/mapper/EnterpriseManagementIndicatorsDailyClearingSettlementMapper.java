package com.heli.enterprise.mapper;

import java.util.Date;
import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import org.apache.ibatis.annotations.Param;

/**
 * 日清日结
 * Mapper接口
 *
 * @author hong
 * @date 2024-05-15
 */
public interface EnterpriseManagementIndicatorsDailyClearingSettlementMapper {

    Boolean checkDailyClearingDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);

    /**
     * 查询日清日结
     *
     * @param edId 日清日结
     *             主键
     * @return 日清日结
     */
    public EnterpriseManagementIndicatorsDailyClearingSettlement selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(Long edId);

    /**
     * 查询日清日结
     * 列表
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 日清日结
     * 集合
     */
    public List<EnterpriseManagementIndicatorsDailyClearingSettlement> selectEnterpriseManagementIndicatorsDailyClearingSettlementList(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement);

    /**
     * 新增日清日结
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 结果
     */
    public int insertEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement);

    /**
     * 修改日清日结
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 结果
     */
    public int updateEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement);

    /**
     * 删除日清日结
     *
     * @param edId 日清日结
     *             主键
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(Long edId);

    /**
     * 批量删除日清日结
     *
     * @param edIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(Long[] edIds);
}
