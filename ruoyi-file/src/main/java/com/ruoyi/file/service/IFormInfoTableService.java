package com.ruoyi.file.service;

import java.util.List;
import com.ruoyi.file.domain.FormInfoTable;
import com.ruoyi.file.domain.RegulationsInfoTable;

/**
 * 表单文件管理Service接口
 * 
 * @author ruoyi
 * @date 2024-04-14
 */
public interface IFormInfoTableService 
{
    /**
     * 根据id查找filename
     * 流程部分依赖使用
     */

    List<String> selectFileNamesByIds(String formsIds);

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
     * 查询历史版本文件管理列表
     *
     * @param currentId 文件管理
     * @return 文件管理集合
     */
    public List<FormInfoTable> getFormHistory(Long currentId);

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
