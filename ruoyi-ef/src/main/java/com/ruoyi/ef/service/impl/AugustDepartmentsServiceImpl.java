package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AugustDepartmentsMapper;
import com.ruoyi.system.domain.AugustDepartments;
import com.ruoyi.system.service.IAugustDepartmentsService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-01
 */
@Service
public class AugustDepartmentsServiceImpl implements IAugustDepartmentsService 
{
    @Autowired
    private AugustDepartmentsMapper augustDepartmentsMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param depId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AugustDepartments selectAugustDepartmentsByDepId(Long depId)
    {
        return augustDepartmentsMapper.selectAugustDepartmentsByDepId(depId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param augustDepartments 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AugustDepartments> selectAugustDepartmentsList(AugustDepartments augustDepartments)
    {
        return augustDepartmentsMapper.selectAugustDepartmentsList(augustDepartments);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param augustDepartments 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAugustDepartments(AugustDepartments augustDepartments)
    {
        return augustDepartmentsMapper.insertAugustDepartments(augustDepartments);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param augustDepartments 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAugustDepartments(AugustDepartments augustDepartments)
    {
        return augustDepartmentsMapper.updateAugustDepartments(augustDepartments);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param depIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAugustDepartmentsByDepIds(Long[] depIds)
    {
        return augustDepartmentsMapper.deleteAugustDepartmentsByDepIds(depIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param depId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAugustDepartmentsByDepId(Long depId)
    {
        return augustDepartmentsMapper.deleteAugustDepartmentsByDepId(depId);
    }
}
