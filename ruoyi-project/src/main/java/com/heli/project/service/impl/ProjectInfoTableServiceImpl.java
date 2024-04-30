package com.heli.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.project.mapper.ProjectInfoTableMapper;
import com.heli.project.domain.ProjectInfoTable;
import com.heli.project.service.IProjectInfoTableService;

/**
 * 项目基本信息表Service业务层处理
 * 
 * @author Teandron
 * @date 2024-04-26
 */
@Service
public class ProjectInfoTableServiceImpl implements IProjectInfoTableService 
{
    @Autowired
    private ProjectInfoTableMapper projectInfoTableMapper;

    /**
     * 查询项目基本信息表
     * 
     * @param projectId 项目基本信息表主键
     * @return 项目基本信息表
     */
    @Override
    public ProjectInfoTable selectProjectInfoTableByProjectId(Long projectId)
    {
        return projectInfoTableMapper.selectProjectInfoTableByProjectId(projectId);
    }

    /**
     * 查询项目基本信息表列表
     * 
     * @param projectInfoTable 项目基本信息表
     * @return 项目基本信息表
     */
    @Override
    public List<ProjectInfoTable> selectProjectInfoTableList(ProjectInfoTable projectInfoTable)
    {
        return projectInfoTableMapper.selectProjectInfoTableList(projectInfoTable);
    }

    /**
     * 新增项目基本信息表
     * 
     * @param projectInfoTable 项目基本信息表
     * @return 结果
     */
    @Override
    public int insertProjectInfoTable(ProjectInfoTable projectInfoTable)
    {
        return projectInfoTableMapper.insertProjectInfoTable(projectInfoTable);
    }

    /**
     * 修改项目基本信息表
     * 
     * @param projectInfoTable 项目基本信息表
     * @return 结果
     */
    @Override
    public int updateProjectInfoTable(ProjectInfoTable projectInfoTable)
    {
        return projectInfoTableMapper.updateProjectInfoTable(projectInfoTable);
    }

    /**
     * 批量删除项目基本信息表
     * 
     * @param projectIds 需要删除的项目基本信息表主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoTableByProjectIds(Long[] projectIds)
    {
        return projectInfoTableMapper.deleteProjectInfoTableByProjectIds(projectIds);
    }

    /**
     * 删除项目基本信息表信息
     * 
     * @param projectId 项目基本信息表主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoTableByProjectId(Long projectId)
    {
        return projectInfoTableMapper.deleteProjectInfoTableByProjectId(projectId);
    }
}
