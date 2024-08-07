package com.heli.financial.mapper;

import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * [财务]手动填报指标Mapper接口
 * 
 * @author hong
 * @date 2024-03-29
 */
//@DataSource(value = DataSourceType.SLAVE)
public interface FinancialIndicatorsHandfillTableMapper {

    Date selectMaxYearAndMonth();

    List<Date> selectAllHandFillYearAndMonth();

    boolean checkDataExists();


    FinancialIndicatorsHandfillTable selectHandfillSumInfoByYear(@Param("yearAndMonth") Date yearAndMonth);
    FinancialIndicatorsHandfillTable selectHandfillRateByYear(@Param("yearAndMonth") Date yearAndMonth);
    FinancialIndicatorsHandfillTable selectMaxMonthHandfill();

    /**
     * @description: 按月查询手动填报数据
     * @author: hong
     * @date: 2024/4/23 11:09
     **/
    FinancialIndicatorsHandfillTable selectFinancialIndicatorsHandfillTableByYearAndMonth(@Param("yearAndMonth") Date yearAndMonth);


    /**
     * @description: 检查当月手动填报数据是否存在
     * @author: hong
     * @date: 2024/4/15 14:59
     **/
    boolean checkHandFillDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);

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
     * 删除[财务]手动填报指标
     * 
     * @param fihfId [财务]手动填报指标主键
     * @return 结果
     */
    public int deleteFinancialIndicatorsHandfillTableByFihfId(Long fihfId);

    /**
     * 批量删除[财务]手动填报指标
     * 
     * @param fihfIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialIndicatorsHandfillTableByFihfIds(Long[] fihfIds);


}
