package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.AugustBusinessModules;
import com.ruoyi.system.domain.AugustDepartments;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-01
 */
public interface AugustDepartmentsMapper extends BaseMapper<AugustDepartments>
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
     * 删除【请填写功能名称】
     *
     * @param depId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAugustDepartmentsByDepId(Long depId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param depIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAugustDepartmentsByDepIds(Long[] depIds);
}
