package com.heli.financial.service.impl;

import java.util.Date;
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
public class FinancialIndicatorsHandfillTableServiceImpl implements IFinancialIndicatorsHandfillTableService {
    @Autowired
    private FinancialIndicatorsHandfillTableMapper financialIndicatorsHandfillTableMapper;

    @Override
    public FinancialIndicatorsHandfillTable selectMaxMonthHandfill() {
        return financialIndicatorsHandfillTableMapper.selectMaxMonthHandfill();
    }

    @Override
    public Date selectMaxYearAndMonth() {
        return financialIndicatorsHandfillTableMapper.selectMaxYearAndMonth();
    }

    @Override
    public boolean checkDataExists() {
        return financialIndicatorsHandfillTableMapper.checkDataExists();
    }

    /**
     * @description: 检查手动填报数据是否存在
     * @author: hong
     * @date: 2024/4/15 14:53
     **/
    @Override
    public boolean checkHandFillDataIsExisted(Date date) {
        return financialIndicatorsHandfillTableMapper.checkHandFillDataIsExisted(date);
    }




    @Override
    public List<Date> selectAllHandFillYearAndMonth() {
        return financialIndicatorsHandfillTableMapper.selectAllHandFillYearAndMonth();
    }

    /**
     * @description: 按月查询手动填报数据
     * @author: hong
     * @date: 2024/4/23 11:11
     **/
    @Override
    public FinancialIndicatorsHandfillTable selectFinancialIndicatorsHandfillTableByYearAndMonth(Date yearAndMonth) {
        return financialIndicatorsHandfillTableMapper.selectFinancialIndicatorsHandfillTableByYearAndMonth(yearAndMonth);
    }

    @Override
    public FinancialIndicatorsHandfillTable selectHandfillSumInfoByYear(Date yearAndMonth) {
        return financialIndicatorsHandfillTableMapper.selectHandfillSumInfoByYear(yearAndMonth);
    }

    @Override
    public FinancialIndicatorsHandfillTable selectHandfillRateByYear(Date yearAndMonth) {
        return financialIndicatorsHandfillTableMapper.selectHandfillRateByYear(yearAndMonth);
    }

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
