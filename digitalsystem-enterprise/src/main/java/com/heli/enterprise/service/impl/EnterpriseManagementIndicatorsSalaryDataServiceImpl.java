package com.heli.enterprise.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.enterprise.mapper.EnterpriseManagementDisplayMapper;
import com.heli.enterprise.mapper.EnterpriseManagementSalaryTableMapper;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementIndicatorsSalaryDataMapper;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsSalaryData;
import com.heli.enterprise.service.IEnterpriseManagementIndicatorsSalaryDataService;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * [企业管理]指标工资数据Service业务层处理
 *
 * @author hong
 * @date 2024-05-08
 */
@Service
public class EnterpriseManagementIndicatorsSalaryDataServiceImpl implements IEnterpriseManagementIndicatorsSalaryDataService {
    @Autowired
    private EnterpriseManagementIndicatorsSalaryDataMapper enterpriseManagementIndicatorsSalaryDataMapper;


    //计算工资相关数据
    public void calculateSalaryData(Date date) {
        EnterpriseManagementIndicatorsSalaryData salaryData = new EnterpriseManagementIndicatorsSalaryData();
        // 计算工资月度占比
        BigDecimal monthlySalary = enterpriseManagementIndicatorsSalaryDataMapper.selectMonthlySalary(date);
        BigDecimal annualSalary = enterpriseManagementIndicatorsSalaryDataMapper.selectAnnualSalary(DateUtils.getYear(date));

        salaryData.setMonthlyAalaryRatio(monthlySalary.divide(annualSalary, 2, ROUND_HALF_UP));
        BigDecimal totalNumber = enterpriseManagementIndicatorsSalaryDataMapper.selectSalaryTotalNumber(date);

        salaryData.setAnnualSalaryRatio(totalNumber.divide(annualSalary, 2, ROUND_HALF_UP));

        enterpriseManagementIndicatorsSalaryDataMapper.updateSalaryTableByMonth(salaryData);
    }

    //计算工资表中相关数据
    public void salaryCalculator(Date date) {
        EnterpriseManagementIndicatorsSalaryData salaryData = new EnterpriseManagementIndicatorsSalaryData();
        // 计算累计人均收入
        BigDecimal allIncome = enterpriseManagementIndicatorsSalaryDataMapper.selectAnnualAllIncome(date);
        salaryData.setCumulativeAverageIncome(allIncome.divide(BigDecimal.valueOf(DateUtils.getMonth(date)), 2, ROUND_HALF_UP));
        System.out.println("当前月份："+BigDecimal.valueOf(DateUtils.getMonth(date)));

        // 当月生产人均收入
        salaryData.setMonthlyProductionAVGIncome(enterpriseManagementIndicatorsSalaryDataMapper.selectMonthlyProductionAverageIncome(date));

        //当月职能人均收入
        salaryData.setMonthlyFunctionalAVGIncome(enterpriseManagementIndicatorsSalaryDataMapper.selectMonthlyFunctionalAverageIncome(date));

        //当月职能人均加班费
        salaryData.setFunctionalDeptOvertimeCost(enterpriseManagementIndicatorsSalaryDataMapper.selectMonthlyFunctionalAverageOvertimeCost(date));

        enterpriseManagementIndicatorsSalaryDataMapper.updateSalaryTableByMonth(salaryData);
    }

    /**
     * 查询[企业管理]指标工资数据
     *
     * @param esId [企业管理]指标工资数据主键
     * @return [企业管理]指标工资数据
     */
    @Override
    public EnterpriseManagementIndicatorsSalaryData selectEnterpriseManagementIndicatorsSalaryDataByEsId(Long esId) {
        return enterpriseManagementIndicatorsSalaryDataMapper.selectEnterpriseManagementIndicatorsSalaryDataByEsId(esId);
    }

    /**
     * 查询[企业管理]指标工资数据列表
     *
     * @param enterpriseManagementIndicatorsSalaryData [企业管理]指标工资数据
     * @return [企业管理]指标工资数据
     */
    @Override
    public List<EnterpriseManagementIndicatorsSalaryData> selectEnterpriseManagementIndicatorsSalaryDataList(EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData) {
        return enterpriseManagementIndicatorsSalaryDataMapper.selectEnterpriseManagementIndicatorsSalaryDataList(enterpriseManagementIndicatorsSalaryData);
    }

    /**
     * 新增[企业管理]指标工资数据
     *
     * @param enterpriseManagementIndicatorsSalaryData [企业管理]指标工资数据
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementIndicatorsSalaryData(EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData) {
        enterpriseManagementIndicatorsSalaryData.setCreateTime(DateUtils.getNowDate());
        calculateSalaryData(enterpriseManagementIndicatorsSalaryData.getYearAndMonth());
        return enterpriseManagementIndicatorsSalaryDataMapper.insertEnterpriseManagementIndicatorsSalaryData(enterpriseManagementIndicatorsSalaryData);
    }

    /**
     * 修改[企业管理]指标工资数据
     *
     * @param enterpriseManagementIndicatorsSalaryData [企业管理]指标工资数据
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementIndicatorsSalaryData(EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData) {
        enterpriseManagementIndicatorsSalaryData.setUpdateTime(DateUtils.getNowDate());
        return enterpriseManagementIndicatorsSalaryDataMapper.updateEnterpriseManagementIndicatorsSalaryData(enterpriseManagementIndicatorsSalaryData);
    }

    /**
     * 批量删除[企业管理]指标工资数据
     *
     * @param esIds 需要删除的[企业管理]指标工资数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementIndicatorsSalaryDataByEsIds(Long[] esIds) {
        return enterpriseManagementIndicatorsSalaryDataMapper.deleteEnterpriseManagementIndicatorsSalaryDataByEsIds(esIds);
    }

    /**
     * 删除[企业管理]指标工资数据信息
     *
     * @param esId [企业管理]指标工资数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementIndicatorsSalaryDataByEsId(Long esId) {
        return enterpriseManagementIndicatorsSalaryDataMapper.deleteEnterpriseManagementIndicatorsSalaryDataByEsId(esId);
    }
}
