package com.heli.enterprise.mapper;

import java.util.Date;
import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
import org.apache.ibatis.annotations.Param;

/**
 * 十一项管理指标
 * Mapper接口
 *
 * @author hong
 * @date 2024-09-13
 */
public interface EnterpriseManagementIndicatorsManagementMapper {

    Boolean checkManagementDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);

    /**
     * 查询十一项管理指标
     *
     * @param emId 十一项管理指标
     *             主键
     * @return 十一项管理指标
     */
    public EnterpriseManagementIndicatorsManagement selectEnterpriseManagementIndicatorsManagementByEmId(String emId);

    /**
     * 查询十一项管理指标
     * 列表
     *
     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
     * @return 十一项管理指标
     * 集合
     */
    public List<EnterpriseManagementIndicatorsManagement> selectEnterpriseManagementIndicatorsManagementList(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement);

    /**
     * 新增十一项管理指标
     *
     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
     * @return 结果
     */
    public int insertEnterpriseManagementIndicatorsManagement(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement);

    /**
     * 修改十一项管理指标
     *
     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
     * @return 结果
     */
    public int updateEnterpriseManagementIndicatorsManagement(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement);

    /**
     * 删除十一项管理指标
     *
     * @param emId 十一项管理指标
     *             主键
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsManagementByEmId(String emId);

    /**
     * 批量删除十一项管理指标
     *
     * @param emIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsManagementByEmIds(String[] emIds);
}


//package com.heli.enterprise.mapper;
//
//import java.util.Date;
//import java.util.List;
//
//import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
//import org.apache.ibatis.annotations.Param;
//
///**
// * 十一项管理指标
// * Mapper接口
// *
// * @author hong
// * @date 2024-05-15
// */
//public interface EnterpriseManagementIndicatorsManagementMapper {
//
//
//    Boolean checkManagementDataIsExisted(@Param("yearAndMonth")Date yearAndMonth);
//
//    /**
//     * 查询十一项管理指标
//     *
//     * @param emId 十一项管理指标
//     *             主键
//     * @return 十一项管理指标
//     */
//    public EnterpriseManagementIndicatorsManagement selectEnterpriseManagementIndicatorsManagementByEmId(Long emId);
//
//    /**
//     * 查询十一项管理指标
//     * 列表
//     *
//     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
//     * @return 十一项管理指标
//     * 集合
//     */
//    public List<EnterpriseManagementIndicatorsManagement> selectEnterpriseManagementIndicatorsManagementList(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement);
//
//    /**
//     * 新增十一项管理指标
//     *
//     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
//     * @return 结果
//     */
//    public int insertEnterpriseManagementIndicatorsManagement(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement);
//
//    /**
//     * 修改十一项管理指标
//     *
//     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
//     * @return 结果
//     */
//    public int updateEnterpriseManagementIndicatorsManagement(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement);
//
//    /**
//     * 删除十一项管理指标
//     *
//     * @param emId 十一项管理指标
//     *             主键
//     * @return 结果
//     */
//    public int deleteEnterpriseManagementIndicatorsManagementByEmId(Long emId);
//
//    /**
//     * 批量删除十一项管理指标
//     *
//     * @param emIds 需要删除的数据主键集合
//     * @return 结果
//     */
//    public int deleteEnterpriseManagementIndicatorsManagementByEmIds(Long[] emIds);
//}
