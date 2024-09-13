package com.heli.enterprise.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementIndicatorsManagementMapper;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
import com.heli.enterprise.service.IEnterpriseManagementIndicatorsManagementService;

/**
 * 十一项管理指标
 * Service业务层处理
 *
 * @author hong
 * @date 2024-09-13
 */
@Service
public class EnterpriseManagementIndicatorsManagementServiceImpl implements IEnterpriseManagementIndicatorsManagementService {
    @Autowired
    private EnterpriseManagementIndicatorsManagementMapper enterpriseManagementIndicatorsManagementMapper;

    @Override
    public Boolean checkManagementDataIsExisted(Date yearAndMonth) {
        return enterpriseManagementIndicatorsManagementMapper.checkManagementDataIsExisted(yearAndMonth);
    }

    /**
     * 查询十一项管理指标
     *
     * @param emId 十一项管理指标
     *             主键
     * @return 十一项管理指标
     */
    @Override
    public EnterpriseManagementIndicatorsManagement selectEnterpriseManagementIndicatorsManagementByEmId(String emId) {
        return enterpriseManagementIndicatorsManagementMapper.selectEnterpriseManagementIndicatorsManagementByEmId(emId);
    }

    /**
     * 查询十一项管理指标
     * 列表
     *
     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
     * @return 十一项管理指标
     */
    @Override
    public List<EnterpriseManagementIndicatorsManagement> selectEnterpriseManagementIndicatorsManagementList(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
        return enterpriseManagementIndicatorsManagementMapper.selectEnterpriseManagementIndicatorsManagementList(enterpriseManagementIndicatorsManagement);
    }

    /**
     * 新增十一项管理指标
     *
     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementIndicatorsManagement(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
        enterpriseManagementIndicatorsManagement.setCreateTime(DateUtils.getNowDate());
        return enterpriseManagementIndicatorsManagementMapper.insertEnterpriseManagementIndicatorsManagement(enterpriseManagementIndicatorsManagement);
    }

    /**
     * 修改十一项管理指标
     *
     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementIndicatorsManagement(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
        enterpriseManagementIndicatorsManagement.setUpdateTime(DateUtils.getNowDate());
        return enterpriseManagementIndicatorsManagementMapper.updateEnterpriseManagementIndicatorsManagement(enterpriseManagementIndicatorsManagement);
    }

    /**
     * 批量删除十一项管理指标
     *
     * @param emIds 需要删除的十一项管理指标
     *              主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementIndicatorsManagementByEmIds(String[] emIds) {
        return enterpriseManagementIndicatorsManagementMapper.deleteEnterpriseManagementIndicatorsManagementByEmIds(emIds);
    }

    /**
     * 删除十一项管理指标
     * 信息
     *
     * @param emId 十一项管理指标
     *             主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementIndicatorsManagementByEmId(String emId) {
        return enterpriseManagementIndicatorsManagementMapper.deleteEnterpriseManagementIndicatorsManagementByEmId(emId);
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
//import com.heli.enterprise.mapper.EnterpriseManagementIndicatorsManagementMapper;
//import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
//import com.heli.enterprise.service.IEnterpriseManagementIndicatorsManagementService;
//
///**
// * 十一项管理指标
// * Service业务层处理
// *
// * @author hong
// * @date 2024-05-15
// */
//@Service
//public class EnterpriseManagementIndicatorsManagementServiceImpl implements IEnterpriseManagementIndicatorsManagementService {
//    @Autowired
//    private EnterpriseManagementIndicatorsManagementMapper enterpriseManagementIndicatorsManagementMapper;
//
//
//    @Override
//    public Boolean checkManagementDataIsExisted(Date yearAndMonth) {
//        return enterpriseManagementIndicatorsManagementMapper.checkManagementDataIsExisted(yearAndMonth);
//    }
//
//    /**
//     * 查询十一项管理指标
//     *
//     * @param emId 十一项管理指标
//     *             主键
//     * @return 十一项管理指标
//     */
//    @Override
//    public EnterpriseManagementIndicatorsManagement selectEnterpriseManagementIndicatorsManagementByEmId(Long emId) {
//        return enterpriseManagementIndicatorsManagementMapper.selectEnterpriseManagementIndicatorsManagementByEmId(emId);
//    }
//
//    /**
//     * 查询十一项管理指标
//     * 列表
//     *
//     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
//     * @return 十一项管理指标
//     */
//    @Override
//    public List<EnterpriseManagementIndicatorsManagement> selectEnterpriseManagementIndicatorsManagementList(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
//        return enterpriseManagementIndicatorsManagementMapper.selectEnterpriseManagementIndicatorsManagementList(enterpriseManagementIndicatorsManagement);
//    }
//
//    /**
//     * 新增十一项管理指标
//     *
//     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
//     * @return 结果
//     */
//    @Override
//    public int insertEnterpriseManagementIndicatorsManagement(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
//        enterpriseManagementIndicatorsManagement.setCreateTime(DateUtils.getNowDate());
//        return enterpriseManagementIndicatorsManagementMapper.insertEnterpriseManagementIndicatorsManagement(enterpriseManagementIndicatorsManagement);
//    }
//
//    /**
//     * 修改十一项管理指标
//     *
//     * @param enterpriseManagementIndicatorsManagement 十一项管理指标
//     * @return 结果
//     */
//    @Override
//    public int updateEnterpriseManagementIndicatorsManagement(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
//        enterpriseManagementIndicatorsManagement.setUpdateTime(DateUtils.getNowDate());
//        return enterpriseManagementIndicatorsManagementMapper.updateEnterpriseManagementIndicatorsManagement(enterpriseManagementIndicatorsManagement);
//    }
//
//    /**
//     * 批量删除十一项管理指标
//     *
//     * @param emIds 需要删除的十一项管理指标
//     *              主键
//     * @return 结果
//     */
//    @Override
//    public int deleteEnterpriseManagementIndicatorsManagementByEmIds(Long[] emIds) {
//        return enterpriseManagementIndicatorsManagementMapper.deleteEnterpriseManagementIndicatorsManagementByEmIds(emIds);
//    }
//
//    /**
//     * 删除十一项管理指标
//     * 信息
//     *
//     * @param emId 十一项管理指标
//     *             主键
//     * @return 结果
//     */
//    @Override
//    public int deleteEnterpriseManagementIndicatorsManagementByEmId(Long emId) {
//        return enterpriseManagementIndicatorsManagementMapper.deleteEnterpriseManagementIndicatorsManagementByEmId(emId);
//    }
//}
