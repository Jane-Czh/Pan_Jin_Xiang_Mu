package com.heli.enterprise.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementIndicatorsDailyClearingSettlementMapper;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.heli.enterprise.service.IEnterpriseManagementIndicatorsDailyClearingSettlementService;

/**
 * 日清日结
 * Service业务层处理
 *
 * @author hong
 * @date 2024-05-15
 */
@Service
public class EnterpriseManagementIndicatorsDailyClearingSettlementServiceImpl implements IEnterpriseManagementIndicatorsDailyClearingSettlementService {
    @Autowired
    private EnterpriseManagementIndicatorsDailyClearingSettlementMapper enterpriseManagementIndicatorsDailyClearingSettlementMapper;

    @Override
    public Boolean checkDailyClearingDataIsExisted(Date yearAndMonth) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.checkDailyClearingDataIsExisted(yearAndMonth);
    }

    /**
     * 查询日清日结
     *
     * @param edId 日清日结
     *             主键
     * @return 日清日结
     */
    @Override
    public EnterpriseManagementIndicatorsDailyClearingSettlement selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(Long edId) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(edId);
    }

    /**
     * 查询日清日结
     * 列表
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 日清日结
     */
    @Override
    public List<EnterpriseManagementIndicatorsDailyClearingSettlement> selectEnterpriseManagementIndicatorsDailyClearingSettlementList(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(enterpriseManagementIndicatorsDailyClearingSettlement);
    }

    /**
     * 新增日清日结
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
        enterpriseManagementIndicatorsDailyClearingSettlement.setCreateTime(DateUtils.getNowDate());
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.insertEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement);
    }

    /**
     * 修改日清日结
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
        enterpriseManagementIndicatorsDailyClearingSettlement.setUpdateTime(DateUtils.getNowDate());
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.updateEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement);
    }

    /**
     * 批量删除日清日结
     *
     * @param edIds 需要删除的日清日结
     *              主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(Long[] edIds) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(edIds);
    }

    /**
     * 删除日清日结
     * 信息
     *
     * @param edId 日清日结
     *             主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(Long edId) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(edId);
    }
}
