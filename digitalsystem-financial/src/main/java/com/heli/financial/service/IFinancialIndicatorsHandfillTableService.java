package com.heli.financial.service;

import com.heli.financial.domain.FinancialIndicatorsHandfillTable;

import java.util.Date;
import java.util.List;


/**
 * [财务]手动填报指标Service接口
 * 
 * @author loophong
 * @date 2024-03-29
 */
public interface IFinancialIndicatorsHandfillTableService {


    boolean checkHandFillDataIsExisted(Date date);


    List<Date> selectExistedYearAndMonth();

    /**
     * 查询[财务]手动填报指标
     * 
     * @param fihfId [财务]手动填报指标主键
     * @return [财务]手动填报指标
     */
    public FinancialIndicatorsHandfillTable selectFinancialIndicatorsHandfillTableByFihfId(Long fihfId);

    /**
     * 查询[财务]手动填报指标列表
     * 
     * @param financialIndicatorsHandfillTable [财务]手动填报指标
     * @return [财务]手动填报指标集合
     */
    public List<FinancialIndicatorsHandfillTable> selectFinancialIndicatorsHandfillTableList(FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable);

    /**
     * 新增[财务]手动填报指标
     * 
     * @param financialIndicatorsHandfillTable [财务]手动填报指标
     * @return 结果
     */
    public int insertFinancialIndicatorsHandfillTable(FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable);

    /**
     * 修改[财务]手动填报指标
     * 
     * @param financialIndicatorsHandfillTable [财务]手动填报指标
     * @return 结果
     */
    public int updateFinancialIndicatorsHandfillTable(FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable);

    /**
     * 批量删除[财务]手动填报指标
     * 
     * @param fihfIds 需要删除的[财务]手动填报指标主键集合
     * @return 结果
     */
    public int deleteFinancialIndicatorsHandfillTableByFihfIds(Long[] fihfIds);

    /**
     * 删除[财务]手动填报指标信息
     * 
     * @param fihfId [财务]手动填报指标主键
     * @return 结果
     */
    public int deleteFinancialIndicatorsHandfillTableByFihfId(Long fihfId);


}
