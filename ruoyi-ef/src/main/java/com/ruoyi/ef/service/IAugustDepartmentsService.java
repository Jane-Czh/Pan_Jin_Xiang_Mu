package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AugustDepartments;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-08-01
 */
public interface IAugustDepartmentsService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param depId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AugustDepartments selectAugustDepartmentsByDepId(Long depId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param augustDepartments 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AugustDepartments> selectAugustDepartmentsList(AugustDepartments augustDepartments);

    /**
     * 新增【请填写功能名称】
     * 
     * @param augustDepartments 【请填写功能名称】
     * @return 结果
     */
    public int insertAugustDepartments(AugustDepartments augustDepartments);

    /**
     * 修改【请填写功能名称】
     * 
     * @param augustDepartments 【请填写功能名称】
     * @return 结果
     */
    public int updateAugustDepartments(AugustDepartments augustDepartments);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param depIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAugustDepartmentsByDepIds(Long[] depIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param depId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAugustDepartmentsByDepId(Long depId);
}
