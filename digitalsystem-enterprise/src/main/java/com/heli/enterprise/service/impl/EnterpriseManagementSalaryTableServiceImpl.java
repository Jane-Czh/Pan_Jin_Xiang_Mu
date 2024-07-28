package com.heli.enterprise.service.impl;

import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.enterprise.listener.SalaryTableListener;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.enterprise.mapper.EnterpriseManagementSalaryTableMapper;
import com.heli.enterprise.domain.EnterpriseManagementSalaryTable;
import com.heli.enterprise.service.IEnterpriseManagementSalaryTableService;

/**
 * 工资-部分字段Service业务层处理
 *
 * @author hong
 * @date 2024-05-07
 */
@Service
public class EnterpriseManagementSalaryTableServiceImpl implements IEnterpriseManagementSalaryTableService {
    @Autowired
    private EnterpriseManagementSalaryTableMapper enterpriseManagementSalaryTableMapper;

    private static final Logger log = LoggerFactory.getLogger(EnterpriseManagementSalaryTableServiceImpl.class);


    @Override
    public void clearSalaryTableAllInfo() {
        enterpriseManagementSalaryTableMapper.clearSalaryTableAllInfo();
    }

    /**
     * 从Excel文件中读取薪资数据并将其存储到数据库中。
     *
     * @param fileName 要读取的Excel文件名称
     * @param inputStream Excel文件的输入流
     * @return 返回操作结果，成功则返回成功消息，失败则返回失败消息
     */
    @Override
    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream,String username) {
        try {
            // 读取文件前清空数据库
            log.info("开始清空数据库");
            enterpriseManagementSalaryTableMapper.clearSalaryTableAllInfo();
            // 读取文件内容
            log.info("开始读取文件: {}", fileName);
            EasyExcel.read(inputStream, EnterpriseManagementSalaryTable.class, new SalaryTableListener(enterpriseManagementSalaryTableMapper,username)).sheet(4).doRead();
            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,您需要工资表,当前上传的文件为：" + fileName);
        }
    }

    /**
     * 查询工资-部分字段
     *
     * @param esId 工资-部分字段主键
     * @return 工资-部分字段
     */
    @Override
    public EnterpriseManagementSalaryTable selectEnterpriseManagementSalaryTableByEsId(Long esId) {
        return enterpriseManagementSalaryTableMapper.selectEnterpriseManagementSalaryTableByEsId(esId);
    }

    /**
     * 查询工资-部分字段列表
     *
     * @param enterpriseManagementSalaryTable 工资-部分字段
     * @return 工资-部分字段
     */
    @Override
    public List<EnterpriseManagementSalaryTable> selectEnterpriseManagementSalaryTableList(EnterpriseManagementSalaryTable enterpriseManagementSalaryTable) {
        return enterpriseManagementSalaryTableMapper.selectEnterpriseManagementSalaryTableList(enterpriseManagementSalaryTable);
    }

    /**
     * 新增工资-部分字段
     *
     * @param enterpriseManagementSalaryTable 工资-部分字段
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementSalaryTable(EnterpriseManagementSalaryTable enterpriseManagementSalaryTable) {
//        enterpriseManagementSalaryTable.setCreateTime(DateUtils.getNowDate());
        return enterpriseManagementSalaryTableMapper.insertEnterpriseManagementSalaryTable(enterpriseManagementSalaryTable);
    }

    /**
     * 修改工资-部分字段
     *
     * @param enterpriseManagementSalaryTable 工资-部分字段
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementSalaryTable(EnterpriseManagementSalaryTable enterpriseManagementSalaryTable) {
//        enterpriseManagementSalaryTable.setUpdateTime(DateUtils.getNowDate());
        return enterpriseManagementSalaryTableMapper.updateEnterpriseManagementSalaryTable(enterpriseManagementSalaryTable);
    }

    /**
     * 批量删除工资-部分字段
     *
     * @param esIds 需要删除的工资-部分字段主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementSalaryTableByEsIds(Long[] esIds) {
        return enterpriseManagementSalaryTableMapper.deleteEnterpriseManagementSalaryTableByEsIds(esIds);
    }

    /**
     * 删除工资-部分字段信息
     *
     * @param esId 工资-部分字段主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementSalaryTableByEsId(Long esId) {
        return enterpriseManagementSalaryTableMapper.deleteEnterpriseManagementSalaryTableByEsId(esId);
    }
}
