package com.heli.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.project.mapper.ProjectInfoTableMapper;
import com.heli.project.domain.ProjectInfoTable;
import com.heli.project.service.IProjectInfoTableService;
import com.ruoyi.project.domain.ProjectInfoRecode;
/**
 * 项目基本信息Service业务层处理
 * 
 * @author Teandron
 * @date 2024-05-08
 */
@Service
public class ProjectInfoTableServiceImpl implements IProjectInfoTableService 
{
    @Autowired
    private ProjectInfoTableMapper projectInfoTableMapper;

    /**
     * 查询项目基本信息
     * 
     * @param projectId 项目基本信息主键
     * @return 项目基本信息
     */
    @Override
    public ProjectInfoTable selectProjectInfoTableByProjectId(Long projectId)
    {
        return projectInfoTableMapper.selectProjectInfoTableByProjectId(projectId);
    }

    @Override
    public List<ProjectInfoRecode> selectProjectRecodingByProjectName(String projectName){
        return projectInfoTableMapper.selectProjectRecodingByProjectName(projectName);
    }

    /**
     * 查询项目基本信息列表
     * 
     * @param projectInfoTable 项目基本信息
     * @return 项目基本信息
     */
    @Override
    public List<ProjectInfoTable> selectProjectInfoTableList(ProjectInfoTable projectInfoTable)
    {
        return projectInfoTableMapper.selectProjectInfoTableList(projectInfoTable);
    }

    /**
     * 新增项目基本信息
     * 
     * @param projectInfoTable 项目基本信息
     * @return 结果
     */
    @Override
    public int insertProjectInfoTable(ProjectInfoTable projectInfoTable)
    {
        return projectInfoTableMapper.insertProjectInfoTable(projectInfoTable);
    }

    /**
     * 修改项目基本信息
     * 
     * @param projectInfoTable 项目基本信息
     * @return 结果
     */
    @Override
    public int updateProjectInfoTable(ProjectInfoTable projectInfoTable)
    {
        return projectInfoTableMapper.updateProjectInfoTable(projectInfoTable);
    }

    /**
     * 批量删除项目基本信息
     * 
     * @param projectIds 需要删除的项目基本信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoTableByProjectIds(Long[] projectIds)
    {
        return projectInfoTableMapper.deleteProjectInfoTableByProjectIds(projectIds);
    }

    /**
     * 删除项目基本信息信息
     * 
     * @param projectId 项目基本信息主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoTableByProjectId(Long projectId)
    {
        return projectInfoTableMapper.deleteProjectInfoTableByProjectId(projectId);
    }
}
