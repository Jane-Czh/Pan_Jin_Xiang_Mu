package com.ruoyi.file.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.FormInfoTableMapper;
import com.ruoyi.file.domain.FormInfoTable;
import com.ruoyi.file.service.IFormInfoTableService;

/**
 * 表单文件管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-14
 */
@Service
public class FormInfoTableServiceImpl implements IFormInfoTableService 
{
    @Autowired
    private FormInfoTableMapper formInfoTableMapper;

    /**
     * 查询表单文件管理
     * 
     * @param formId 表单文件管理主键
     * @return 表单文件管理
     */
    @Override
    public FormInfoTable selectFormInfoTableByFormId(Long formId)
    {
        return formInfoTableMapper.selectFormInfoTableByFormId(formId);
    }

    /**
     * 查询表单文件管理列表
     * 
     * @param formInfoTable 表单文件管理
     * @return 表单文件管理
     */
    @Override
    public List<FormInfoTable> selectFormInfoTableList(FormInfoTable formInfoTable)
    {
        return formInfoTableMapper.selectFormInfoTableList(formInfoTable);
    }

    /**
     * 新增表单文件管理
     * 
     * @param formInfoTable 表单文件管理
     * @return 结果
     */
    @Override
    public int insertFormInfoTable(FormInfoTable formInfoTable)
    {
        return formInfoTableMapper.insertFormInfoTable(formInfoTable);
    }

    /**
     * 修改表单文件管理
     * 
     * @param formInfoTable 表单文件管理
     * @return 结果
     */
    @Override
    public int updateFormInfoTable(FormInfoTable formInfoTable)
    {
        return formInfoTableMapper.updateFormInfoTable(formInfoTable);
    }

    /**
     * 批量删除表单文件管理
     * 
     * @param formIds 需要删除的表单文件管理主键
     * @return 结果
     */
    @Override
    public int deleteFormInfoTableByFormIds(Long[] formIds)
    {
        return formInfoTableMapper.deleteFormInfoTableByFormIds(formIds);
    }

    /**
     * 删除表单文件管理信息
     * 
     * @param formId 表单文件管理主键
     * @return 结果
     */
    @Override
    public int deleteFormInfoTableByFormId(Long formId)
    {
        return formInfoTableMapper.deleteFormInfoTableByFormId(formId);
    }
}
