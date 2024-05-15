package com.heli.enterprise.mapper;

import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementAnnualData;
import org.apache.ibatis.annotations.Param;

/**
 * [企业管理]指标年度数据Mapper接口
 *
 * @author hong
 * @date 2024-04-27
 */
public interface EnterpriseManagementAnnualDataMapper {


    Boolean checkEMAnnualDataIsExisted(@Param("naturalYear") Integer naturalYear);


    /**
     * 查询[企业管理]指标年度数据
     *
     * @param emasId [企业管理]指标年度数据主键
     * @return [企业管理]指标年度数据
     */
    public EnterpriseManagementAnnualData selectEnterpriseManagementAnnualDataByEmasId(Long emasId);

    /**
     * 查询[企业管理]指标年度数据列表
     *
     * @param enterpriseManagementAnnualData [企业管理]指标年度数据
     * @return [企业管理]指标年度数据集合
     */
    public List<EnterpriseManagementAnnualData> selectEnterpriseManagementAnnualDataList(EnterpriseManagementAnnualData enterpriseManagementAnnualData);

    /**
     * 新增[企业管理]指标年度数据
     *
     * @param enterpriseManagementAnnualData [企业管理]指标年度数据
     * @return 结果
     */
    public int insertEnterpriseManagementAnnualData(EnterpriseManagementAnnualData enterpriseManagementAnnualData);

    /**
     * 修改[企业管理]指标年度数据
     *
     * @param enterpriseManagementAnnualData [企业管理]指标年度数据
     * @return 结果
     */
    public int updateEnterpriseManagementAnnualData(EnterpriseManagementAnnualData enterpriseManagementAnnualData);

    /**
     * 删除[企业管理]指标年度数据
     *
     * @param emasId [企业管理]指标年度数据主键
     * @return 结果
     */
    public int deleteEnterpriseManagementAnnualDataByEmasId(Long emasId);

    /**
     * 批量删除[企业管理]指标年度数据
     *
     * @param emasIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementAnnualDataByEmasIds(Long[] emasIds);
}
