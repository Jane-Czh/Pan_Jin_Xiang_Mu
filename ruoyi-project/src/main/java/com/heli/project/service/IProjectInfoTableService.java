package com.heli.project.service;

import java.util.List;
import com.heli.project.domain.ProjectInfoTable;

/**
 * 项目基本信息Service接口
 * 
 * @author Teandron
 * @date 2024-05-08
 */
public interface IProjectInfoTableService 
{
    /**
     * 查询项目基本信息
     * 
     * @param projectId 项目基本信息主键
     * @return 项目基本信息
     */
    public ProjectInfoTable selectProjectInfoTableByProjectId(Long projectId);

    /**
     * 查询项目基本信息列表
     * 
     * @param projectInfoTable 项目基本信息
     * @return 项目基本信息集合
     */
    public List<ProjectInfoTable> selectProjectInfoTableList(ProjectInfoTable projectInfoTable);

    /**
     * 新增项目基本信息
     * 
     * @param projectInfoTable 项目基本信息
     * @return 结果
     */
    public int insertProjectInfoTable(ProjectInfoTable projectInfoTable);

    /**
     * 修改项目基本信息
     * 
     * @param projectInfoTable 项目基本信息
     * @return 结果
     */
    public int updateProjectInfoTable(ProjectInfoTable projectInfoTable);

    /**
     * 批量删除项目基本信息
     * 
     * @param projectIds 需要删除的项目基本信息主键集合
     * @return 结果
     */
    public int deleteProjectInfoTableByProjectIds(Long[] projectIds);

    /**
     * 删除项目基本信息信息
     * 
     * @param projectId 项目基本信息主键
     * @return 结果
     */
    public int deleteProjectInfoTableByProjectId(Long projectId);
}
