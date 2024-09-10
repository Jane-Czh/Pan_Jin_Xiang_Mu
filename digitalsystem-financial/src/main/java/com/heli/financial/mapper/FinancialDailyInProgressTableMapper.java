package com.heli.financial.mapper;

import java.util.Date;
import java.util.List;

import com.heli.financial.domain.FinancialDailyInProgressTable;
import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import org.apache.ibatis.annotations.Param;

/**
 * [财务]每日填报指标[当日再制品金额]Mapper接口
 *
 * @author hong
 * @date 2024-05-06
 */
public interface FinancialDailyInProgressTableMapper {

    FinancialDailyInProgressTable selectDailyInProgressSumInfoByMonth(@Param("yearAndMonth") Date yearAndMonth);

    FinancialDailyInProgressTable selectNewDateDailyInProgress();


    //检查指定时间当日在制造品金额是否存在
    boolean checkDailyInProgressTableIsExist(Date yearAndMonth);

    /**
     * 查询[财务]每日填报指标[当日再制品金额]
     *
     * @param id [财务]每日填报指标[当日再制品金额]主键
     * @return [财务]每日填报指标[当日再制品金额]
     */
    public FinancialDailyInProgressTable selectFinancialDailyInProgressTableById(Long id);

    /**
     * 查询[财务]每日填报指标[当日再制品金额]列表
     *
     * @param financialDailyInProgressTable [财务]每日填报指标[当日再制品金额]
     * @return [财务]每日填报指标[当日再制品金额]集合
     */
    public List<FinancialDailyInProgressTable> selectFinancialDailyInProgressTableList(FinancialDailyInProgressTable financialDailyInProgressTable);

    /**
     * 新增[财务]每日填报指标[当日再制品金额]
     *
     * @param financialDailyInProgressTable [财务]每日填报指标[当日再制品金额]
     * @return 结果
     */
    public int insertFinancialDailyInProgressTable(FinancialDailyInProgressTable financialDailyInProgressTable);

    /**
     * 修改[财务]每日填报指标[当日再制品金额]
     *
     * @param financialDailyInProgressTable [财务]每日填报指标[当日再制品金额]
     * @return 结果
     */
    public int updateFinancialDailyInProgressTable(FinancialDailyInProgressTable financialDailyInProgressTable);

    /**
     * 删除[财务]每日填报指标[当日再制品金额]
     *
     * @param id [财务]每日填报指标[当日再制品金额]主键
     * @return 结果
     */
    public int deleteFinancialDailyInProgressTableById(Long id);

    /**
     * 批量删除[财务]每日填报指标[当日再制品金额]
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialDailyInProgressTableByIds(Long[] ids);


}
