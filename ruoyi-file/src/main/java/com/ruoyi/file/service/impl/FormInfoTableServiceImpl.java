package com.ruoyi.file.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ruoyi.file.domain.RegulationsInfoTable;
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
     * 根据id查找filename
     * 流程部分依赖使用
     * @param formsIds
     * @return
     */
    @Override
    public List<String> selectFileNamesByIds(String formsIds) {
        // 去除方括号和空格，将字符串解析为ID列表
        String cleanedIds = formsIds.replaceAll("[\\[\\]\\s]", "");
        List<String> idsList = Arrays.asList(cleanedIds.split(","));
        System.out.println("formsIdsList===========>"+idsList);
        List<String> fileNames = formInfoTableMapper.selectFileNamesByIds(idsList);
        System.out.println("formsFileNamesList===>"+fileNames);
        return fileNames;
    }

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
     * 查询表单文件管理列表(全部)
     *
     * @param formInfoTable 表单文件管理
     * @return 表单文件管理
     */
    @Override
    public List<FormInfoTable> selectAllFormList(FormInfoTable formInfoTable)
    {
        return formInfoTableMapper.selectAllFormList(formInfoTable);
    }

    /**
     * 查询历史版本文件列表
     *
     * @param currentId 文件管理
     * @return 文件管理
     */
    @Override
    public List<FormInfoTable> getFormHistory(Long currentId) {
        //返回结果
        List<FormInfoTable> historyList = new ArrayList<>();
        Long oldId = formInfoTableMapper.selectFormInfoTableByFormId(currentId).getOldFormId();
        while (oldId != null) {
            FormInfoTable record = formInfoTableMapper.selectFormInfoTableByFormId(oldId);
            if (record != null) {
                historyList.add(record);
                oldId = record.getOldFormId();
            } else {
                break;
            }
        }
        return historyList;
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
        formInfoTableMapper.insertFormInfoTable(formInfoTable);
        Long rId = formInfoTable.getFormId();
        return Math.toIntExact(rId);
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
