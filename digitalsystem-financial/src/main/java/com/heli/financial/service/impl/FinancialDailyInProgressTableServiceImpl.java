package com.heli.financial.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.financial.mapper.FinancialDailyInProgressTableMapper;
import com.heli.financial.domain.FinancialDailyInProgressTable;
import com.heli.financial.service.IFinancialDailyInProgressTableService;

/**
 * [财务]每日填报指标[当日再制品金额]Service业务层处理
 *
 * @author hong
 * @date 2024-05-06
 */
@Service
public class FinancialDailyInProgressTableServiceImpl implements IFinancialDailyInProgressTableService {
    @Autowired
    private FinancialDailyInProgressTableMapper financialDailyInProgressTableMapper;

    @Override
    public boolean checkDailyInProgressTableIsExist(Date yearAndMonth) {

        return financialDailyInProgressTableMapper.checkDailyInProgressTableIsExist(yearAndMonth);
    }

    /**
     * 查询[财务]每日填报指标[当日再制品金额]
     *
     * @param id [财务]每日填报指标[当日再制品金额]主键
     * @return [财务]每日填报指标[当日再制品金额]
     */
    @Override
    public FinancialDailyInProgressTable selectFinancialDailyInProgressTableById(Long id) {
        return financialDailyInProgressTableMapper.selectFinancialDailyInProgressTableById(id);
    }

    /**
     * 查询[财务]每日填报指标[当日再制品金额]列表
     *
     * @param financialDailyInProgressTable [财务]每日填报指标[当日再制品金额]
     * @return [财务]每日填报指标[当日再制品金额]
     */
    @Override
    public List<FinancialDailyInProgressTable> selectFinancialDailyInProgressTableList(FinancialDailyInProgressTable financialDailyInProgressTable) {
        return financialDailyInProgressTableMapper.selectFinancialDailyInProgressTableList(financialDailyInProgressTable);
    }

    /**
     * 新增[财务]每日填报指标[当日再制品金额]
     *
     * @param financialDailyInProgressTable [财务]每日填报指标[当日再制品金额]
     * @return 结果
     */
    @Override
    public int insertFinancialDailyInProgressTable(FinancialDailyInProgressTable financialDailyInProgressTable) {
        financialDailyInProgressTable.setCreateTime(DateUtils.getNowDate());
        return financialDailyInProgressTableMapper.insertFinancialDailyInProgressTable(financialDailyInProgressTable);
    }

    /**
     * 修改[财务]每日填报指标[当日再制品金额]
     *
     * @param financialDailyInProgressTable [财务]每日填报指标[当日再制品金额]
     * @return 结果
     */
    @Override
    public int updateFinancialDailyInProgressTable(FinancialDailyInProgressTable financialDailyInProgressTable) {
        financialDailyInProgressTable.setUpdateTime(DateUtils.getNowDate());
        return financialDailyInProgressTableMapper.updateFinancialDailyInProgressTable(financialDailyInProgressTable);
    }

    /**
     * 批量删除[财务]每日填报指标[当日再制品金额]
     *
     * @param ids 需要删除的[财务]每日填报指标[当日再制品金额]主键
     * @return 结果
     */
    @Override
    public int deleteFinancialDailyInProgressTableByIds(Long[] ids) {
        return financialDailyInProgressTableMapper.deleteFinancialDailyInProgressTableByIds(ids);
    }

    /**
     * 删除[财务]每日填报指标[当日再制品金额]信息
     *
     * @param id [财务]每日填报指标[当日再制品金额]主键
     * @return 结果
     */
    @Override
    public int deleteFinancialDailyInProgressTableById(Long id) {
        return financialDailyInProgressTableMapper.deleteFinancialDailyInProgressTableById(id);
    }
}
