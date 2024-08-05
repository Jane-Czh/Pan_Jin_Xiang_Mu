package com.heli.enterprise.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.enterprise.mapper.EnterpriseManagementAnnualDataMapper;
import com.heli.enterprise.service.IEnterpriseManagementAnnualDataService;
import com.heli.enterprise.service.IEnterpriseManagementAnnualDataService;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementMonthlyDataMapper;
import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import com.heli.enterprise.service.IEnterpriseManagementMonthlyDataService;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * [企业管理]指标月度数据Service业务层处理
 *
 * @author hong
 * @date 2024-05-09
 */
@Service
public class EnterpriseManagementMonthlyDataServiceImpl implements IEnterpriseManagementMonthlyDataService {
    @Autowired
    private EnterpriseManagementMonthlyDataMapper enterpriseManagementMonthlyDataMapper;
    @Autowired
    private IEnterpriseManagementAnnualDataService enterpriseManagementAnnualDataService;

    private static final Logger log = LoggerFactory.getLogger(EnterpriseManagementMonthlyDataServiceImpl.class);


    @Override
    public Boolean checkEMEmployeesDataIsExisted(Date date) {
        return enterpriseManagementMonthlyDataMapper.checkEMEmployeesDataIsExisted(date);
    }

    @Override
    public int insertMonthlyFillingDataByMonth(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
        return enterpriseManagementMonthlyDataMapper.insertMonthlyFillingDataByMonth(enterpriseManagementMonthlyData);
    }

    @Override
    public Boolean checkEMMonthlyDataIsExisted() {
        return enterpriseManagementMonthlyDataMapper.checkEMMonthlyDataIsExisted();
    }

    @Override
    public boolean checkEMMonthlyDataIsMinMonth(Date yearAndMonth) {
        return enterpriseManagementMonthlyDataMapper.checkEMMonthlyDataIsMinMonth(yearAndMonth);

    }

    /**
     * 计算填报相关月度指标
     */
    public int calculateHandFillIndicators(Date yearAndMonth) {

        if (!enterpriseManagementAnnualDataService.checkEMAnnualDataIsExisted(DateUtils.getYear(yearAndMonth))) {
            return 0;
        }



        if (!enterpriseManagementMonthlyDataMapper.checkEMEmployeesDataIsExisted(yearAndMonth) || !enterpriseManagementMonthlyDataMapper.checkEMEmployeesDataIsExisted(DateUtils.getLastMonth(yearAndMonth))) {
            return 0;
        }

        EnterpriseManagementMonthlyData monthlyData = new EnterpriseManagementMonthlyData();
        if (!enterpriseManagementAnnualDataService.checkEMAnnualDataIsExisted(DateUtils.getYear(yearAndMonth))) {
            System.out.println("year"+DateUtils.getYear(yearAndMonth));
            return 0;
        }


        monthlyData.setYearAndMonth(yearAndMonth);
        //计算公司月度平均从业人数
        Long employeesNumber = enterpriseManagementMonthlyDataMapper.selectEmployeesNumberByMonth(yearAndMonth);
        Long employeesNumberLastMonth = enterpriseManagementMonthlyDataMapper.selectEmployeesNumberByMonth(DateUtils.getLastMonth(yearAndMonth));
        //更新时，如果上月数据未上传，则使用本月数据代替上月数据
        if (employeesNumberLastMonth == null) {
            employeesNumberLastMonth = employeesNumber;
        }
        monthlyData.setEmployeesAvgMonthlyNumber((employeesNumber + employeesNumberLastMonth) / 2);

        //计算公司年度平均从业人数
        int sumEmployeesNumber = enterpriseManagementMonthlyDataMapper.selectEmployeesAVGMonthlyNumber(yearAndMonth);
        monthlyData.setEmployeesAvgAnnualNumber((long) sumEmployeesNumber);


        // 计算工资月度占比
        BigDecimal monthlySalary = enterpriseManagementMonthlyDataMapper.selectMonthlySalary(yearAndMonth);
        BigDecimal annualSalary = enterpriseManagementMonthlyDataMapper.selectAnnualSalary(DateUtils.getYear(yearAndMonth));

        monthlyData.setMonthlySalaryRatio(monthlySalary.divide(annualSalary, 4, ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)));
        BigDecimal totalNumber = enterpriseManagementMonthlyDataMapper.selectSalaryTotalNumber(yearAndMonth);

        monthlyData.setAnnualSalaryRatio(totalNumber.divide(annualSalary, 4, ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)));

        log.info("计算月度指标数据：" + monthlyData);
//        return enterpriseManagementMonthlyDataMapper.updateCalculateEmployeesDataByMonth(monthlyData);
        return enterpriseManagementMonthlyDataMapper.updateCalculateMonthlyDataByMonth(monthlyData);

    }


    //计算工资表中相关数据
    public void calculateSalaryTableIndicators(Date date) {

        EnterpriseManagementMonthlyData monthlyData = new EnterpriseManagementMonthlyData();
        monthlyData.setYearAndMonth(date);
        // 计算累计人均收入
        BigDecimal allIncome = enterpriseManagementMonthlyDataMapper.selectAnnualAllIncome(date);
        int countEmployeesNumberByYear = enterpriseManagementMonthlyDataMapper.selectCountEmployeesNumberByYear(date);
        int monthsByYear = enterpriseManagementMonthlyDataMapper.selectCountMonthsByYear(date);
        log.info("当年工资总额"+allIncome +"年度总人工数");
        monthlyData.setCumulativeAverageIncome(allIncome.divide(BigDecimal.valueOf(countEmployeesNumberByYear), 2, ROUND_HALF_UP)
                .divide(BigDecimal.valueOf(monthsByYear), 2, ROUND_HALF_UP));
        System.out.println("当前月份："+BigDecimal.valueOf(DateUtils.getMonth(date)));

        // 当月生产人均收入
        BigDecimal monthlyProductionAverageIncome = enterpriseManagementMonthlyDataMapper.selectMonthlyProductionAverageIncome(date);
        log.info("当月生产人均收入"+monthlyProductionAverageIncome);
        monthlyData.setMonthlyProductionAvgIncome(monthlyProductionAverageIncome);

        //当月职能人均收入
        BigDecimal monthlyFunctionalAverageIncome = enterpriseManagementMonthlyDataMapper.selectMonthlyFunctionalAverageIncome(date);
        log.info("当月职能人均收入"+monthlyFunctionalAverageIncome);
        monthlyData.setMonthlyFunctionalAvgIncome(monthlyFunctionalAverageIncome);


        //当月职能人均加班费
        BigDecimal monthlyFunctionalAverageOvertimeCost = enterpriseManagementMonthlyDataMapper.selectMonthlyFunctionalAverageOvertimeCost(date);
        log.info("当月职能人均加班费"+monthlyFunctionalAverageOvertimeCost);
        monthlyData.setFunctionalDeptOvertimeCost(monthlyFunctionalAverageOvertimeCost);

        log.info("计算工资表中相关数据：" + monthlyData);

        enterpriseManagementMonthlyDataMapper.updateCalculateSalaryDataByMonth(monthlyData);
    }



    /**
     * 查询[企业管理]指标月度数据
     *
     * @param esId [企业管理]指标月度数据主键
     * @return [企业管理]指标月度数据
     */
    @Override
    public EnterpriseManagementMonthlyData selectEnterpriseManagementMonthlyDataByEsId(Long esId) {
        return enterpriseManagementMonthlyDataMapper.selectEnterpriseManagementMonthlyDataByEsId(esId);
    }

    /**
     * 查询[企业管理]指标月度数据列表
     *
     * @param enterpriseManagementMonthlyData [企业管理]指标月度数据
     * @return [企业管理]指标月度数据
     */
    @Override
    public List<EnterpriseManagementMonthlyData> selectEnterpriseManagementMonthlyDataList(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
        return enterpriseManagementMonthlyDataMapper.selectEnterpriseManagementMonthlyDataList(enterpriseManagementMonthlyData);
    }

    /**
     * 新增[企业管理]指标月度数据
     *
     * @param enterpriseManagementMonthlyData [企业管理]指标月度数据
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementMonthlyData(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
        enterpriseManagementMonthlyData.setCreateTime(DateUtils.getNowDate());
        return enterpriseManagementMonthlyDataMapper.insertEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData);
    }

    /**
     * 修改[企业管理]指标月度数据
     *
     * @param enterpriseManagementMonthlyData [企业管理]指标月度数据
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementMonthlyData(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
        enterpriseManagementMonthlyData.setUpdateTime(DateUtils.getNowDate());
        enterpriseManagementMonthlyDataMapper.updateEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData);
        int i = 0;
        //更新
        //获取当年数据最大月份
        Date maxMonth = enterpriseManagementMonthlyDataMapper.selectMaxMonthByYear(enterpriseManagementMonthlyData.getYearAndMonth());
        log.info("最大月份：" + maxMonth);
        //从当前月份更新到最大月份
        for (Date date = enterpriseManagementMonthlyData.getYearAndMonth(); date.before(DateUtils.getNextMonth(maxMonth)); date = DateUtils.getNextMonth(date), i++) {
            log.info("当前更新的月份为：" + date);
            calculateHandFillIndicators(date);
        }
        return i;
    }

    /**
     * 批量删除[企业管理]指标月度数据
     *
     * @param esIds 需要删除的[企业管理]指标月度数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementMonthlyDataByEsIds(Long[] esIds) {
        return enterpriseManagementMonthlyDataMapper.deleteEnterpriseManagementMonthlyDataByEsIds(esIds);
    }

    /**
     * 删除[企业管理]指标月度数据信息
     *
     * @param esId [企业管理]指标月度数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementMonthlyDataByEsId(Long esId) {
        return enterpriseManagementMonthlyDataMapper.deleteEnterpriseManagementMonthlyDataByEsId(esId);
    }
}
