package com.heli.enterprise.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.ruoyi.common.core.domain.R;

/**
 * 日清日结
 * Service接口
 *
 * @author hong
 * @date 2024-09-12
 */
public interface IEnterpriseManagementIndicatorsDailyClearingSettlementService {

    Boolean checkDailyClearingDataIsExisted(Date yearAndMonth);

    /**
     * 查询日清日结
     *
     * @param edId 日清日结
     *             主键
     * @return 日清日结
     */
    public EnterpriseManagementIndicatorsDailyClearingSettlement selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(String edId);

    /**
     * 查询日清日结
     * 列表
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 日清日结
     * 集合
     */
    public List<EnterpriseManagementIndicatorsDailyClearingSettlement> selectEnterpriseManagementIndicatorsDailyClearingSettlementList(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement);

    /**
     * 新增日清日结
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 结果
     */
    public int insertEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement);

    /**
     * 修改日清日结
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 结果
     */
    public int updateEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement);

    /**
     * 批量删除日清日结
     *
     * @param edIds 需要删除的日清日结
     *              主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(String[] edIds);

    /**
     * 删除日清日结
     * 信息
     *
     * @param edId 日清日结
     *             主键
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(String edId);

//    void readSalaryExcelToDB(String originalFilename, InputStream inputStream);
    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream,Date yearAndMonth);
}


//package com.heli.enterprise.service;
//
//import java.util.Date;
//import java.util.List;
//
//import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
//
///**
// * 日清日结
// * Service接口
// *
// * @author hong
// * @date 2024-05-15
// */
//public interface IEnterpriseManagementIndicatorsDailyClearingSettlementService {
//
//
//    Boolean checkDailyClearingDataIsExisted(Date yearAndMonth);
//
//
//    /**
//     * 查询日清日结
//     *
//     * @param edId 日清日结
//     *             主键
//     * @return 日清日结
//     */
//    public EnterpriseManagementIndicatorsDailyClearingSettlement selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(Long edId);
//
//    /**
//     * 查询日清日结
//     * 列表
//     *
//     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
//     * @return 日清日结
//     * 集合
//     */
//    public List<EnterpriseManagementIndicatorsDailyClearingSettlement> selectEnterpriseManagementIndicatorsDailyClearingSettlementList(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement);
//
//    /**
//     * 新增日清日结
//     *
//     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
//     * @return 结果
//     */
//    public int insertEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement);
//
//    /**
//     * 修改日清日结
//     *
//     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
//     * @return 结果
//     */
//    public int updateEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement);
//
//    /**
//     * 批量删除日清日结
//     *
//     * @param edIds 需要删除的日清日结
//     *              主键集合
//     * @return 结果
//     */
//    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(Long[] edIds);
//
//    /**
//     * 删除日清日结
//     * 信息
//     *
//     * @param edId 日清日结
//     *             主键
//     * @return 结果
//     */
//    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(Long edId);
//}
