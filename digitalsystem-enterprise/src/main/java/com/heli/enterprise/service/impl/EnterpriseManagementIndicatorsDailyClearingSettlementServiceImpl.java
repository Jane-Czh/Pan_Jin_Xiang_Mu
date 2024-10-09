package com.heli.enterprise.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.enterprise.domain.EnterpriseManagementDailyClearingReadEntity;
import com.heli.enterprise.domain.EnterpriseManagementSalaryTable;
import com.heli.enterprise.listener.DailyClearingTableListener;
import com.heli.enterprise.listener.SalaryTableListener;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementIndicatorsDailyClearingSettlementMapper;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.heli.enterprise.service.IEnterpriseManagementIndicatorsDailyClearingSettlementService;

/**
 * 日清日结
 * Service业务层处理
 *
 * @author hong
 * @date 2024-09-12
 */
@Slf4j
@Service
public class EnterpriseManagementIndicatorsDailyClearingSettlementServiceImpl implements IEnterpriseManagementIndicatorsDailyClearingSettlementService {
    @Autowired
    private EnterpriseManagementIndicatorsDailyClearingSettlementMapper enterpriseManagementIndicatorsDailyClearingSettlementMapper;


    @Override
    public Boolean checkDailyClearingDataIsExisted(Date yearAndMonth) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.checkDailyClearingDataIsExisted(yearAndMonth);
    }

    /**
     * 查询日清日结
     * @param edId 日清日结 主键
     * @return 日清日结
     */
    @Override
    public EnterpriseManagementIndicatorsDailyClearingSettlement selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(String edId) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(edId);
    }

    /**
     * 查询日清日结
     * 列表
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 日清日结
     */
    @Override
    public List<EnterpriseManagementIndicatorsDailyClearingSettlement> selectEnterpriseManagementIndicatorsDailyClearingSettlementList(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(enterpriseManagementIndicatorsDailyClearingSettlement);
    }

    /**
     * 新增日清日结
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
        enterpriseManagementIndicatorsDailyClearingSettlement.setCreateTime(DateUtils.getNowDate());
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.insertEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement);
    }

    /**
     * 修改日清日结
     *
     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
        enterpriseManagementIndicatorsDailyClearingSettlement.setUpdateTime(DateUtils.getNowDate());
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.updateEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement);
    }

    /**
     * 批量删除日清日结
     *
     * @param edIds 需要删除的日清日结
     *              主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(String[] edIds) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(edIds);
    }

    /**
     * 删除日清日结
     * 信息
     *
     * @param edId 日清日结
     *             主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(String edId) {
        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(edId);
    }


    @Override
    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream,Date yearAndMonth) {
        try {

            // 读取文件内容
            log.info("开始读取文件: {}", fileName);
            EasyExcel.read(inputStream, EnterpriseManagementDailyClearingReadEntity.class, new DailyClearingTableListener(enterpriseManagementIndicatorsDailyClearingSettlementMapper,yearAndMonth)).sheet().doRead();
            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,您需要工资表,当前上传的文件为：" + fileName);
        }
    }

}


//package com.heli.enterprise.service.impl;
//
//import java.util.Date;
//import java.util.List;
//
//import com.ruoyi.common.utils.DateUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.heli.enterprise.mapper.EnterpriseManagementIndicatorsDailyClearingSettlementMapper;
//import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
//import com.heli.enterprise.service.IEnterpriseManagementIndicatorsDailyClearingSettlementService;
//
///**
// * 日清日结
// * Service业务层处理
// *
// * @author hong
// * @date 2024-05-15
// */
//@Service
//public class EnterpriseManagementIndicatorsDailyClearingSettlementServiceImpl implements IEnterpriseManagementIndicatorsDailyClearingSettlementService {
//    @Autowired
//    private EnterpriseManagementIndicatorsDailyClearingSettlementMapper enterpriseManagementIndicatorsDailyClearingSettlementMapper;
//
//    @Override
//    public Boolean checkDailyClearingDataIsExisted(Date yearAndMonth) {
//        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.checkDailyClearingDataIsExisted(yearAndMonth);
//    }
//
//    /**
//     * 查询日清日结
//     *
//     * @param edId 日清日结
//     *             主键
//     * @return 日清日结
//     */
//    @Override
//    public EnterpriseManagementIndicatorsDailyClearingSettlement selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(Long edId) {
//        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(edId);
//    }
//
//    /**
//     * 查询日清日结
//     * 列表
//     *
//     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
//     * @return 日清日结
//     */
//    @Override
//    public List<EnterpriseManagementIndicatorsDailyClearingSettlement> selectEnterpriseManagementIndicatorsDailyClearingSettlementList(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
//        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(enterpriseManagementIndicatorsDailyClearingSettlement);
//    }
//
//    /**
//     * 新增日清日结
//     *
//     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
//     * @return 结果
//     */
//    @Override
//    public int insertEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
//        enterpriseManagementIndicatorsDailyClearingSettlement.setCreateTime(DateUtils.getNowDate());
//        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.insertEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement);
//    }
//
//    /**
//     * 修改日清日结
//     *
//     * @param enterpriseManagementIndicatorsDailyClearingSettlement 日清日结
//     * @return 结果
//     */
//    @Override
//    public int updateEnterpriseManagementIndicatorsDailyClearingSettlement(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
//        enterpriseManagementIndicatorsDailyClearingSettlement.setUpdateTime(DateUtils.getNowDate());
//        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.updateEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement);
//    }
//
//    /**
//     * 批量删除日清日结
//     *
//     * @param edIds 需要删除的日清日结
//     *              主键
//     * @return 结果
//     */
//    @Override
//    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(Long[] edIds) {
//        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(edIds);
//    }
//
//    /**
//     * 删除日清日结
//     * 信息
//     *
//     * @param edId 日清日结
//     *             主键
//     * @return 结果
//     */
//    @Override
//    public int deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(Long edId) {
//        return enterpriseManagementIndicatorsDailyClearingSettlementMapper.deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(edId);
//    }
//}
