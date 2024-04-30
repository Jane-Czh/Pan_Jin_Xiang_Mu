package com.heli.enterprise.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
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
        return enterpriseManagementAnnualDataMapper.insertEnterpriseManagementAnnualData(enterpriseManagementAnnualData);
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
        return enterpriseManagementAnnualDataMapper.updateEnterpriseManagementAnnualData(enterpriseManagementAnnualData);
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
