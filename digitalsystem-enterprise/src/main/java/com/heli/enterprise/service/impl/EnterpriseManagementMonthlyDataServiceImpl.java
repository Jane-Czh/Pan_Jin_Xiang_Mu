package com.heli.enterprise.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementMonthlyDataMapper;
import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import com.heli.enterprise.service.IEnterpriseManagementMonthlyDataService;

/**
 * [企业管理]指标月度数据Service业务层处理
 *
 * @author hong
 * @date 2024-04-27
 */
@Service
public class EnterpriseManagementMonthlyDataServiceImpl implements IEnterpriseManagementMonthlyDataService {
    @Autowired
    private EnterpriseManagementMonthlyDataMapper enterpriseManagementMonthlyDataMapper;

    @Override
    public Boolean checkEMMonthlyDataIsExisted(Date date) {
        return enterpriseManagementMonthlyDataMapper.checkEMMonthlyDataIsExisted(date);
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
//        计算公司月度平均从业人数
        Long employeesNumberLastMonth = enterpriseManagementMonthlyDataMapper.selectEmployeesNumber(DateUtils.getLastMonth(enterpriseManagementMonthlyData.getYearAndMonth()));
        enterpriseManagementMonthlyData.setEmployeesAvgMonthlyNumber((enterpriseManagementMonthlyData.getEmployeesNumber() + employeesNumberLastMonth) / 2);


        return enterpriseManagementMonthlyDataMapper.insertEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData);
    }


    /**
     * 查询[企业管理]指标月度数据
     *
     * @param emId [企业管理]指标月度数据主键
     * @return [企业管理]指标月度数据
     */
    @Override
    public EnterpriseManagementMonthlyData selectEnterpriseManagementMonthlyDataByEmId(Long emId) {
        return enterpriseManagementMonthlyDataMapper.selectEnterpriseManagementMonthlyDataByEmId(emId);
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
     * 修改[企业管理]指标月度数据
     *
     * @param enterpriseManagementMonthlyData [企业管理]指标月度数据
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementMonthlyData(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
        enterpriseManagementMonthlyData.setUpdateTime(DateUtils.getNowDate());
        return enterpriseManagementMonthlyDataMapper.updateEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData);
    }

    /**
     * 批量删除[企业管理]指标月度数据
     *
     * @param emIds 需要删除的[企业管理]指标月度数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementMonthlyDataByEmIds(Long[] emIds) {
        return enterpriseManagementMonthlyDataMapper.deleteEnterpriseManagementMonthlyDataByEmIds(emIds);
    }

    /**
     * 删除[企业管理]指标月度数据信息
     *
     * @param emId [企业管理]指标月度数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementMonthlyDataByEmId(Long emId) {
        return enterpriseManagementMonthlyDataMapper.deleteEnterpriseManagementMonthlyDataByEmId(emId);
    }
}
