package com.heli.project.service;

import java.util.List;
import com.heli.project.domain.ProjectInfoTable;

/**
 * 项目基本信息表Service接口
 * 
 * @author Teandron
 * @date 2024-04-26
 */
public interface IProjectInfoTableService 
{
    /**
     * 查询项目基本信息表
     * 
     * @param projectId 项目基本信息表主键
     * @return 项目基本信息表
     */
    public ProjectInfoTable selectProjectInfoTableByProjectId(Long projectId);

    /**
     * 查询项目基本信息表列表
     * 
     * @param projectInfoTable 项目基本信息表
     * @return 项目基本信息表集合
     */
    public List<ProjectInfoTable> selectProjectInfoTableList(ProjectInfoTable projectInfoTable);

    /**
     * 新增项目基本信息表
     * 
     * @param projectInfoTable 项目基本信息表
     * @return 结果
     */
    public int insertProjectInfoTable(ProjectInfoTable projectInfoTable);

    /**
     * 修改项目基本信息表
     * 
     * @param projectInfoTable 项目基本信息表
     * @return 结果
     */
    public int updateProjectInfoTable(ProjectInfoTable projectInfoTable);

    /**
     * 批量删除项目基本信息表
     * 
     * @param projectIds 需要删除的项目基本信息表主键集合
     * @return 结果
     */
    public int deleteProjectInfoTableByProjectIds(Long[] projectIds);

    /**
     * 删除项目基本信息表信息
     * 
     * @param projectId 项目基本信息表主键
     * @return 结果
     */
    public int deleteProjectInfoTableByProjectId(Long projectId);
}
