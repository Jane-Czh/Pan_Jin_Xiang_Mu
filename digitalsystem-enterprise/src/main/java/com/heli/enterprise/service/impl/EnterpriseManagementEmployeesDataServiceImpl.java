package com.heli.enterprise.service.impl;

import java.util.Date;
import java.util.List;

import com.heli.enterprise.controller.EnterpriseManagementSalaryTableController;
import com.heli.enterprise.domain.EnterpriseManagementSalaryTable;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementEmployeesDataMapper;
import com.heli.enterprise.domain.EnterpriseManagementEmployeesData;
import com.heli.enterprise.service.IEnterpriseManagementEmployeesDataService;

/**
 * [企业管理]指标月度数据Service业务层处理
 *
 * @author hong
 * @date 2024-04-27
 */
@Service
public class EnterpriseManagementEmployeesDataServiceImpl implements IEnterpriseManagementEmployeesDataService {
    @Autowired
    private EnterpriseManagementEmployeesDataMapper enterpriseManagementEmployeesDataMapper;
    private static final Logger log = LoggerFactory.getLogger(EnterpriseManagementEmployeesDataServiceImpl.class);


    @Override
    public Boolean checkEMEmployeesDataIsExisted(Date date) {
        return enterpriseManagementEmployeesDataMapper.checkEMEmployeesDataIsExisted(date);
    }

    /**
     * 计算月度指标
     */
    public int calculateEmployeesIndicators(Date yearAndMonth) {

        EnterpriseManagementEmployeesData employeesData = new EnterpriseManagementEmployeesData();

        employeesData.setYearAndMonth(yearAndMonth);
        //计算公司月度平均从业人数
        Long employeesNumber = enterpriseManagementEmployeesDataMapper.selectEmployeesNumberByMonth(yearAndMonth);
        Long employeesNumberLastMonth = enterpriseManagementEmployeesDataMapper.selectEmployeesNumberByMonth(DateUtils.getLastMonth(yearAndMonth));
        //更新时，如果上月数据未上传，则使用本月数据代替上月数据
        if (employeesNumberLastMonth == null) {
            employeesNumberLastMonth = employeesNumber;
        }
        employeesData.setEmployeesAvgMonthlyNumber((employeesNumber + employeesNumberLastMonth) / 2);

        //计算公司年度平均从业人数
        int sumEmployeesNumber = enterpriseManagementEmployeesDataMapper.selectEmployeesAVGMonthlyNumber(yearAndMonth);
        employeesData.setEmployeesAvgAnnualNumber((long) sumEmployeesNumber);

        return enterpriseManagementEmployeesDataMapper.updateCalculateEmployeesDataByMonth(employeesData);


    }

    /**
     * 新增[企业管理]指标月度数据
     *
     * @param enterpriseManagementEmployeesData [企业管理]指标月度数据
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementEmployeesData(EnterpriseManagementEmployeesData enterpriseManagementEmployeesData) {
        enterpriseManagementEmployeesData.setCreateTime(DateUtils.getNowDate());
//        计算公司月度平均从业人数
//        Long employeesNumberLastMonth = enterpriseManagementEmployeesDataMapper.selectEmployeesNumberByMonth(DateUtils.getLastMonth(enterpriseManagementEmployeesData.getYearAndMonth()));
//        enterpriseManagementEmployeesData.setEmployeesAvgMonthlyNumber((enterpriseManagementEmployeesData.getEmployeesNumber() + employeesNumberLastMonth) / 2);
        enterpriseManagementEmployeesDataMapper.insertEnterpriseManagementEmployeesData(enterpriseManagementEmployeesData);

        return calculateEmployeesIndicators(enterpriseManagementEmployeesData.getYearAndMonth());
    }


    /**
     * 查询[企业管理]指标月度数据
     *
     * @param emId [企业管理]指标月度数据主键
     * @return [企业管理]指标月度数据
     */
    @Override
    public EnterpriseManagementEmployeesData selectEnterpriseManagementEmployeesDataByEmId(Long emId) {
        return enterpriseManagementEmployeesDataMapper.selectEnterpriseManagementEmployeesDataByEmId(emId);
    }

    /**
     * 查询[企业管理]指标月度数据列表
     *
     * @param enterpriseManagementEmployeesData [企业管理]指标月度数据
     * @return [企业管理]指标月度数据
     */
    @Override
    public List<EnterpriseManagementEmployeesData> selectEnterpriseManagementEmployeesDataList(EnterpriseManagementEmployeesData enterpriseManagementEmployeesData) {
        return enterpriseManagementEmployeesDataMapper.selectEnterpriseManagementEmployeesDataList(enterpriseManagementEmployeesData);
    }

    /**
     * 修改[企业管理]指标月度数据
     *
     * @param enterpriseManagementEmployeesData [企业管理]指标月度数据
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementEmployeesData(EnterpriseManagementEmployeesData enterpriseManagementEmployeesData) {
        enterpriseManagementEmployeesData.setUpdateTime(DateUtils.getNowDate());
        enterpriseManagementEmployeesDataMapper.updateEnterpriseManagementEmployeesData(enterpriseManagementEmployeesData);
        //查询今年已经上传的月份
        Date maxMonths = enterpriseManagementEmployeesDataMapper.selectMaxMonths(enterpriseManagementEmployeesData.getYearAndMonth());

        log.info("查询最大月份：" + maxMonths);
        //如果查询月份大于等于当前月份，则执行更新数据库操作
        for (Date i = enterpriseManagementEmployeesData.getYearAndMonth(); i.before(DateUtils.getNextMonth(maxMonths)); i = DateUtils.getNextMonth(i)) {
            log.info("更新月份：" + i);
            calculateEmployeesIndicators(i);
        }

        return 1;
    }

    /**
     * 批量删除[企业管理]指标月度数据
     *
     * @param emIds 需要删除的[企业管理]指标月度数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementEmployeesDataByEmIds(Long[] emIds) {
        return enterpriseManagementEmployeesDataMapper.deleteEnterpriseManagementEmployeesDataByEmIds(emIds);
    }

    /**
     * 删除[企业管理]指标月度数据信息
     *
     * @param emId [企业管理]指标月度数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementEmployeesDataByEmId(Long emId) {
        return enterpriseManagementEmployeesDataMapper.deleteEnterpriseManagementEmployeesDataByEmId(emId);
    }
}
