package com.heli.project.mapper;

import java.util.List;
import com.heli.project.domain.ProjectInfoTable;

/**
 * 项目基本信息Mapper接口
 * 
 * @author Teandron
 * @date 2024-05-08
 */
public interface ProjectInfoTableMapper 
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
     * 删除项目基本信息
     * 
     * @param projectId 项目基本信息主键
     * @return 结果
     */
    public int deleteProjectInfoTableByProjectId(Long projectId);

    /**
     * 批量删除项目基本信息
     * 
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectInfoTableByProjectIds(Long[] projectIds);
}
