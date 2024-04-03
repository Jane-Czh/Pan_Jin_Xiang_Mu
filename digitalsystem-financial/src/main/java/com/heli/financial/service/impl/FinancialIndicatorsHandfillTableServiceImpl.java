package com.heli.financial.service.impl;

import java.util.List;

import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.heli.financial.mapper.FinancialIndicatorsHandfillTableMapper;
import com.heli.financial.service.IFinancialIndicatorsHandfillTableService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * [财务]手动填报指标Service业务层处理
 *
 * @author loophong
 * @date 2024-03-29
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class FinancialIndicatorsHandfillTableServiceImpl implements IFinancialIndicatorsHandfillTableService {
    @Autowired
    private FinancialIndicatorsHandfillTableMapper financialIndicatorsHandfillTableMapper;

    /**
     * 查询[财务]手动填报指标
     *
     * @param fihfId [财务]手动填报指标主键
     * @return [财务]手动填报指标
     */
    @Override
    public FinancialIndicatorsHandfillTable selectFinancialIndicatorsHandfillTableByFihfId(Long fihfId) {
        return financialIndicatorsHandfillTableMapper.selectFinancialIndicatorsHandfillTableByFihfId(fihfId);
    }

    /**
     * 查询[财务]手动填报指标列表
     *
     * @param financialIndicatorsHandfillTable [财务]手动填报指标
     * @return [财务]手动填报指标
     */
    @Override
    public List<FinancialIndicatorsHandfillTable> selectFinancialIndicatorsHandfillTableList(FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {
        return financialIndicatorsHandfillTableMapper.selectFinancialIndicatorsHandfillTableList(financialIndicatorsHandfillTable);
    }

    /**
     * 新增[财务]手动填报指标
     *
     * @param financialIndicatorsHandfillTable [财务]手动填报指标
     * @return 结果
     */
    @Override
    public int insertFinancialIndicatorsHandfillTable(FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {
        return financialIndicatorsHandfillTableMapper.insertFinancialIndicatorsHandfillTable(financialIndicatorsHandfillTable);
    }

    /**
     * 修改[财务]手动填报指标
     *
     * @param financialIndicatorsHandfillTable [财务]手动填报指标
     * @return 结果
     */
    @Override
    public int updateFinancialIndicatorsHandfillTable(FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {
        return financialIndicatorsHandfillTableMapper.updateFinancialIndicatorsHandfillTable(financialIndicatorsHandfillTable);
    }

    /**
     * 批量删除[财务]手动填报指标
     *
     * @param fihfIds 需要删除的[财务]手动填报指标主键
     * @return 结果
     */
    @Override
    public int deleteFinancialIndicatorsHandfillTableByFihfIds(Long[] fihfIds) {
        return financialIndicatorsHandfillTableMapper.deleteFinancialIndicatorsHandfillTableByFihfIds(fihfIds);
    }

    /**
     * 删除[财务]手动填报指标信息
     *
     * @param fihfId [财务]手动填报指标主键
     * @return 结果
     */
    @Override
    public int deleteFinancialIndicatorsHandfillTableByFihfId(Long fihfId) {
        return financialIndicatorsHandfillTableMapper.deleteFinancialIndicatorsHandfillTableByFihfId(fihfId);
    }
}
