package com.heli.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.project.mapper.ProjectInfoRecycleMapper;
import com.heli.project.domain.ProjectInfoRecycle;
import com.heli.project.service.IProjectInfoRecycleService;

/**
 * 项目信息回收Service业务层处理
 * 
 * @author Teandron
 * @date 2024-08-06
 */
@Service
public class ProjectInfoRecycleServiceImpl implements IProjectInfoRecycleService 
{
    @Autowired
    private ProjectInfoRecycleMapper projectInfoRecycleMapper;

    /**
     * 查询项目信息回收
     * 
     * @param projectId 项目信息回收主键
     * @return 项目信息回收
     */
    @Override
    public ProjectInfoRecycle selectProjectInfoRecycleByProjectId(Long projectId)
    {
        return projectInfoRecycleMapper.selectProjectInfoRecycleByProjectId(projectId);
    }

    /**
     * 查询项目信息回收列表
     * 
     * @param projectInfoRecycle 项目信息回收
     * @return 项目信息回收
     */
    @Override
    public List<ProjectInfoRecycle> selectProjectInfoRecycleList(ProjectInfoRecycle projectInfoRecycle)
    {
        return projectInfoRecycleMapper.selectProjectInfoRecycleList(projectInfoRecycle);
    }

    /**
     * 新增项目信息回收
     * 
     * @param projectInfoRecycle 项目信息回收
     * @return 结果
     */
    @Override
    public int insertProjectInfoRecycle(ProjectInfoRecycle projectInfoRecycle)
    {
        return projectInfoRecycleMapper.insertProjectInfoRecycle(projectInfoRecycle);
    }

    /**
     * 修改项目信息回收
     * 
     * @param projectInfoRecycle 项目信息回收
     * @return 结果
     */
    @Override
    public int updateProjectInfoRecycle(ProjectInfoRecycle projectInfoRecycle)
    {
        return projectInfoRecycleMapper.updateProjectInfoRecycle(projectInfoRecycle);
    }

    /**
     * 批量删除项目信息回收
     * 
     * @param projectIds 需要删除的项目信息回收主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoRecycleByProjectIds(Long[] projectIds)
    {
        return projectInfoRecycleMapper.deleteProjectInfoRecycleByProjectIds(projectIds);
    }

    /**
     * 删除项目信息回收信息
     * 
     * @param projectId 项目信息回收主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoRecycleByProjectId(Long projectId)
    {
        return projectInfoRecycleMapper.deleteProjectInfoRecycleByProjectId(projectId);
    }
}
