package com.ruoyi.file.service;

import java.util.List;
import com.ruoyi.file.domain.FormInfoTable;

/**
 * 表单文件管理Service接口
 * 
 * @author ruoyi
 * @date 2024-04-14
 */
public interface IFormInfoTableService 
{
    /**
     * 查询表单文件管理
     * 
     * @param formId 表单文件管理主键
     * @return 表单文件管理
     */
    public FormInfoTable selectFormInfoTableByFormId(Long formId);

    /**
     * 查询表单文件管理列表
     * 
     * @param formInfoTable 表单文件管理
     * @return 表单文件管理集合
     */
    public List<FormInfoTable> selectFormInfoTableList(FormInfoTable formInfoTable);

    /**
     * 新增表单文件管理
     * 
     * @param formInfoTable 表单文件管理
     * @return 结果
     */
    public int insertFormInfoTable(FormInfoTable formInfoTable);

    /**
     * 修改表单文件管理
     * 
     * @param formInfoTable 表单文件管理
     * @return 结果
     */
    public int updateFormInfoTable(FormInfoTable formInfoTable);

    /**
     * 批量删除表单文件管理
     * 
     * @param formIds 需要删除的表单文件管理主键集合
     * @return 结果
     */
    public int deleteFormInfoTableByFormIds(Long[] formIds);

    /**
     * 删除表单文件管理信息
     * 
     * @param formId 表单文件管理主键
     * @return 结果
     */
    public int deleteFormInfoTableByFormId(Long formId);
}
