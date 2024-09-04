package com.heli.enterprise.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.heli.enterprise.mapper.EnterpriseManagementMonthlyDataMapper;
import com.heli.enterprise.service.IEnterpriseManagementMonthlyDataService;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementAnnualDataMapper;
import com.heli.enterprise.domain.EnterpriseManagementAnnualData;
import com.heli.enterprise.service.IEnterpriseManagementAnnualDataService;

/**
 * [企业管理]指标年度数据Service业务层处理
 *
 * @author hong
 * @date 2024-04-27
 */
@Service
public class EnterpriseManagementAnnualDataServiceImpl implements IEnterpriseManagementAnnualDataService {
    @Autowired
    private EnterpriseManagementAnnualDataMapper enterpriseManagementAnnualDataMapper;
    @Autowired
    private EnterpriseManagementMonthlyDataMapper enterpriseManagementMonthlyDataMapper;

    @Autowired
    private IEnterpriseManagementMonthlyDataService enterpriseManagementMonthlyDataService;

    private static final Logger log = LoggerFactory.getLogger(EnterpriseManagementAnnualDataServiceImpl.class);


    @Override
    public Boolean checkEMAnnualDataIsExisted(Integer naturalYear) {
        return enterpriseManagementAnnualDataMapper.checkEMAnnualDataIsExisted(naturalYear);
    }

    /**
     * 查询[企业管理]指标年度数据
     *
     * @param emasId [企业管理]指标年度数据主键
     * @return [企业管理]指标年度数据
     */
    @Override
    public EnterpriseManagementAnnualData selectEnterpriseManagementAnnualDataByEmasId(Long emasId) {
        return enterpriseManagementAnnualDataMapper.selectEnterpriseManagementAnnualDataByEmasId(emasId);
    }

    /**
     * 查询[企业管理]指标年度数据列表
     *
     * @param enterpriseManagementAnnualData [企业管理]指标年度数据
     * @return [企业管理]指标年度数据
     */
    @Override
    public List<EnterpriseManagementAnnualData> selectEnterpriseManagementAnnualDataList(EnterpriseManagementAnnualData enterpriseManagementAnnualData) {
        return enterpriseManagementAnnualDataMapper.selectEnterpriseManagementAnnualDataList(enterpriseManagementAnnualData);
    }

    /**
     * 新增[企业管理]指标年度数据
     *
     * @param enterpriseManagementAnnualData [企业管理]指标年度数据
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementAnnualData(EnterpriseManagementAnnualData enterpriseManagementAnnualData) {

        enterpriseManagementAnnualData.setCreateTime(DateUtils.getNowDate());
        enterpriseManagementAnnualDataMapper.insertEnterpriseManagementAnnualData(enterpriseManagementAnnualData);
        Date nowYearDate = new Date(enterpriseManagementAnnualData.getNaturalYear() -1900, 0, 1);


        Date minMonth = enterpriseManagementMonthlyDataMapper.selectMinMonthByYear(nowYearDate);
        Date maxMonth = enterpriseManagementMonthlyDataMapper.selectMaxMonthByYear(nowYearDate);
        log.info("最小月份："+minMonth);
        log.info("最大月份："+maxMonth);

        if (minMonth == null || maxMonth == null){
            return 1;
        }


        int i = 0;
        //更新

        //从当前月份更新到最大月份
        for (Date date = minMonth; date.before(DateUtils.getNextMonth(maxMonth)) ; date = DateUtils.getNextMonth(date),i++) {
            log.info("当前更新的月份为："+ date);
            enterpriseManagementMonthlyDataService.calculateHandFillIndicators(date);
        }

        return i;
    }

    /**
     * 修改[企业管理]指标年度数据
     *
     * @param enterpriseManagementAnnualData [企业管理]指标年度数据
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementAnnualData(EnterpriseManagementAnnualData enterpriseManagementAnnualData) {
        enterpriseManagementAnnualData.setUpdateTime(DateUtils.getNowDate());
        enterpriseManagementAnnualDataMapper.updateEnterpriseManagementAnnualData(enterpriseManagementAnnualData);
        Date nowYearDate = new Date(enterpriseManagementAnnualData.getNaturalYear() -1900, 0, 1);


        Date minMonth = enterpriseManagementMonthlyDataMapper.selectMinMonthByYear(nowYearDate);
        Date maxMonth = enterpriseManagementMonthlyDataMapper.selectMaxMonthByYear(nowYearDate);
        log.info("最小月份："+minMonth);
        log.info("最大月份："+maxMonth);

        if (minMonth == null || maxMonth == null){
            return 1;
        }


        int i = 0;
        //更新

        //从当前月份更新到最大月份
        for (Date date = minMonth; date.before(DateUtils.getNextMonth(maxMonth)) ; date = DateUtils.getNextMonth(date),i++) {
            log.info("当前更新的月份为："+ date);
            enterpriseManagementMonthlyDataService.calculateHandFillIndicators(date);
        }

        return i;
    }

    /**
     * 批量删除[企业管理]指标年度数据
     *
     * @param emasIds 需要删除的[企业管理]指标年度数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementAnnualDataByEmasIds(Long[] emasIds) {
        return enterpriseManagementAnnualDataMapper.deleteEnterpriseManagementAnnualDataByEmasIds(emasIds);
    }

    /**
     * 删除[企业管理]指标年度数据信息
     *
     * @param emasId [企业管理]指标年度数据主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementAnnualDataByEmasId(Long emasId) {
        return enterpriseManagementAnnualDataMapper.deleteEnterpriseManagementAnnualDataByEmasId(emasId);
    }
}
