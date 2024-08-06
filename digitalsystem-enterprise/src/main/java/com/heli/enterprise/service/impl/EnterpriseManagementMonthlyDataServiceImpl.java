package com.heli.enterprise.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.enterprise.mapper.EnterpriseManagementAnnualDataMapper;
import com.heli.enterprise.service.IEnterpriseManagementAnnualDataService;
import com.heli.enterprise.service.IEnterpriseManagementAnnualDataService;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementMonthlyDataMapper;
import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import com.heli.enterprise.service.IEnterpriseManagementMonthlyDataService;

import javax.xml.crypto.Data;

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

    /**
     * @description: 统计人数，从花名册表
     * 一线从业人数：三个筛选条件。生产管理科、员工组合同制和劳务派遣，员工子组生产类
     * 生产实习生人数：生产管理科、员工组实习生
     * @author: hong
     * @date: 2024/8/5 19:32
     **/
    @Override
    public int countEmployeesNumber(Date yearAndMonth) {
        int internNumbers = enterpriseManagementMonthlyDataMapper.selectInternNumbers();
        int employeeNumbers = enterpriseManagementMonthlyDataMapper.selectFrontLineEmployeeNumbers();
        EnterpriseManagementMonthlyData monthlyData = new EnterpriseManagementMonthlyData();
        monthlyData.setEmployeesNumber((long) employeeNumbers);
        monthlyData.setProductionInternNumbers((long) internNumbers);
        monthlyData.setYearAndMonth(yearAndMonth);
        return enterpriseManagementMonthlyDataMapper.insertOrUpdateEmployeesData(monthlyData);
    }

    /**
     * @description: 计算员工数据
     * @author: hong
     * @date: 2024/8/5 23:53
     **/
    @Override
    public int calculateEmployeesNumber(Date yearAndMonth) {
        EnterpriseManagementMonthlyData enterpriseManagementMonthlyData = enterpriseManagementMonthlyDataMapper.selectMonthDateByDate(yearAndMonth);
        log.info("计算前数据：" + yearAndMonth + enterpriseManagementMonthlyData);

        //如果当月和上月数据都存在则，计算月度平均人数
        if (enterpriseManagementMonthlyDataMapper.checkEMEmployeesDataIsExisted(yearAndMonth) && enterpriseManagementMonthlyDataMapper.checkEMEmployeesDataIsExisted(DateUtils.getLastMonth(yearAndMonth))) {
            Long employeesNumber = enterpriseManagementMonthlyDataMapper.selectEmployeesNumberByMonth(yearAndMonth);
            Long employeesNumberLastMonth = enterpriseManagementMonthlyDataMapper.selectEmployeesNumberByMonth(DateUtils.getLastMonth(yearAndMonth));
            enterpriseManagementMonthlyData.setEmployeesAvgMonthlyNumber((employeesNumber + employeesNumberLastMonth) / 2);
        } else {
            enterpriseManagementMonthlyData.setEmployeesAvgMonthlyNumber(null);
        }


        //如果截止到当月数据条数 = 当前月数，则计算平均人数
        int monthDataNumber = enterpriseManagementMonthlyDataMapper.countMonthDataNumber(yearAndMonth);
        if (monthDataNumber == DateUtils.getMonth(yearAndMonth)) {
            int numberByYear = enterpriseManagementMonthlyDataMapper.countEmployeesNumberByYear(yearAndMonth);
            enterpriseManagementMonthlyData.setEmployeesAvgAnnualNumber((long) (numberByYear / DateUtils.getMonth(yearAndMonth)));
        } else {
            enterpriseManagementMonthlyData.setEmployeesAvgAnnualNumber(null);
        }
        log.info("计算后数据：" + yearAndMonth + enterpriseManagementMonthlyData);


        return enterpriseManagementMonthlyDataMapper.updateMonthlyData(enterpriseManagementMonthlyData);
    }

    /**
     * @description: 计算填报的月度工资总额数据
     * @author: hong
     * @date: 2024/8/6 0:27
     **/
    public int calculateSalaryFillNumber(Date yearAndMonth) {
        EnterpriseManagementMonthlyData enterpriseManagementMonthlyData = enterpriseManagementMonthlyDataMapper.selectMonthDateByDate(yearAndMonth);
        log.info("计算前数据：" + yearAndMonth + enterpriseManagementMonthlyData);

        if (!enterpriseManagementAnnualDataService.checkEMAnnualDataIsExisted(DateUtils.getYear(yearAndMonth))) {
            enterpriseManagementMonthlyData.setMonthlySalaryRatio(null);
            enterpriseManagementMonthlyData.setAnnualSalaryRatio(null);
        } else {
            BigDecimal annualSalary = enterpriseManagementMonthlyDataMapper.selectAnnualSalary(DateUtils.getYear(yearAndMonth));
            BigDecimal monthlySalary = enterpriseManagementMonthlyDataMapper.selectMonthlySalary(yearAndMonth);

            if (monthlySalary != null) {
                enterpriseManagementMonthlyData.setMonthlySalaryRatio(monthlySalary.divide(annualSalary, 4, ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)));
            }else {
                enterpriseManagementMonthlyData.setMonthlySalaryRatio(null);
            }

            if (enterpriseManagementMonthlyDataMapper.countMonthSalaryFillDataNumber(yearAndMonth) == DateUtils.getMonth(yearAndMonth)) {
                BigDecimal salaryFillNumberByYear = enterpriseManagementMonthlyDataMapper.countSalaryFillNumberByYear(yearAndMonth);
                enterpriseManagementMonthlyData.setAnnualSalaryRatio(salaryFillNumberByYear.divide(annualSalary, 4, ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)));
            }else {
                enterpriseManagementMonthlyData.setAnnualSalaryRatio(null);
            }

        }

        return enterpriseManagementMonthlyDataMapper.updateMonthlyData(enterpriseManagementMonthlyData);
    }

    /**
     * @description: 统计当月工资表数据
     * @author: hong
     * @date: 2024/8/6 14:15
     **/
    public void statisticsSalaryTableIndicators(Date date) {

        EnterpriseManagementMonthlyData monthlyData = new EnterpriseManagementMonthlyData();
        monthlyData.setYearAndMonth(date);

        // 统计当月人均收入
        BigDecimal monthlyAvgIncome = enterpriseManagementMonthlyDataMapper.selectMonthlyCumulativeAverageIncome();
        log.info("当月人均收入"+monthlyAvgIncome);
        monthlyData.setMonthlyCumulativeAverageIncome(monthlyAvgIncome);

        // 统计当月生产人均收入
        BigDecimal monthlyProductionAverageIncome = enterpriseManagementMonthlyDataMapper.selectMonthlyProductionAverageIncome();
        log.info("当月生产人均收入"+monthlyProductionAverageIncome);
        monthlyData.setMonthlyProductionAvgIncome(monthlyProductionAverageIncome);

        // 统计当月职能人均收入
        BigDecimal monthlyFunctionalAverageIncome = enterpriseManagementMonthlyDataMapper.selectMonthlyFunctionalAverageIncome();
        log.info("当月职能人均收入" + monthlyFunctionalAverageIncome);
        monthlyData.setMonthlyFunctionalAvgIncome(monthlyFunctionalAverageIncome);

        // 统计当月职能人均加班费o
        BigDecimal overtimeCost = enterpriseManagementMonthlyDataMapper.selectMonthlyFunctionalAverageOvertimeCost();
        BigDecimal otherBonuses = enterpriseManagementMonthlyDataMapper.selectMonthlyFunctionalAverageOtherBonuses();
        log.info("当月职能人均加班费" + overtimeCost +"其他奖金"+ otherBonuses);
        monthlyData.setFunctionalDeptOvertimeCost(overtimeCost.add(otherBonuses));

        log.info("统计-"+DateUtils.getMonth(date)+"月工资表中相关数据：" + monthlyData);

        enterpriseManagementMonthlyDataMapper.updateCalculateSalaryDataByMonth(monthlyData);
    }

    /**
     * @description: 计算月度数据，工资表数据累计值
     * @author: hong
     * @date: 2024/8/6 14:49
     **/
    @Override
    public int calculateMonthlyDataSalary(Date yearAndMonth) {

        EnterpriseManagementMonthlyData enterpriseManagementMonthlyData = enterpriseManagementMonthlyDataMapper.selectMonthDateByDate(yearAndMonth);
        log.info("计算前数据：" + yearAndMonth + enterpriseManagementMonthlyData);


        //如果截止到当月数据条数 = 当前月数，则计算平均人数
        int monthDataNumber = enterpriseManagementMonthlyDataMapper.countMonthDataSalaryNumber(yearAndMonth);

        if (monthDataNumber == DateUtils.getMonth(yearAndMonth)) {

            BigDecimal cumulativeAverageIncome = enterpriseManagementMonthlyDataMapper.selectCumulativeAverageIncome(yearAndMonth);
            log.info("累计人均收入"+cumulativeAverageIncome);
            enterpriseManagementMonthlyData.setCumulativeAverageIncome(cumulativeAverageIncome);

            BigDecimal productionAVGIncome = enterpriseManagementMonthlyDataMapper.selectProductionAVGIncome(yearAndMonth);
            log.info("累计生产人均收入"+productionAVGIncome);
            enterpriseManagementMonthlyData.setProductionAvgIncome(productionAVGIncome);

            BigDecimal functionalAVGIncome = enterpriseManagementMonthlyDataMapper.selectFunctionalAVGIncome(yearAndMonth);
            log.info("累计职能人均收入"+functionalAVGIncome);
            enterpriseManagementMonthlyData.setFunctionalAvgIncome(functionalAVGIncome);
        } else {
            enterpriseManagementMonthlyData.setCumulativeAverageIncome(null);
            enterpriseManagementMonthlyData.setProductionAvgIncome(null);
            enterpriseManagementMonthlyData.setCumulativeAverageIncome(null);
        }
        log.info("计算后数据：" + yearAndMonth + enterpriseManagementMonthlyData);


        return enterpriseManagementMonthlyDataMapper.updateMonthlyData(enterpriseManagementMonthlyData);
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
            return 0;
//            employeesNumberLastMonth = employeesNumber;
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
        // 计算当月人均收入
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
        log.info("当月职能人均收入" + monthlyFunctionalAverageIncome);
        monthlyData.setMonthlyFunctionalAvgIncome(monthlyFunctionalAverageIncome);


        //当月职能人均加班费
        BigDecimal monthlyFunctionalAverageOvertimeCost = enterpriseManagementMonthlyDataMapper.selectMonthlyFunctionalAverageOvertimeCost(date);
        BigDecimal monthlyFunctionalAverageOtherBonuses = enterpriseManagementMonthlyDataMapper.selectMonthlyFunctionalAverageOtherBonuses(date);
        log.info("当月职能人均加班费" + monthlyFunctionalAverageOvertimeCost +"其他奖金"+ monthlyFunctionalAverageOtherBonuses);
        monthlyData.setFunctionalDeptOvertimeCost(monthlyFunctionalAverageOvertimeCost.add(monthlyFunctionalAverageOtherBonuses));

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
