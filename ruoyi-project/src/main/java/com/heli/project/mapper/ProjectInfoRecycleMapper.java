package com.heli.project.mapper;

import java.util.List;
import com.heli.project.domain.ProjectInfoRecycle;

/**
 * 项目信息回收Mapper接口
 * 
 * @author Teandron
 * @date 2024-08-06
 */
public interface ProjectInfoRecycleMapper 
{
    /**
     * 查询项目信息回收
     * 
     * @param projectId 项目信息回收主键
     * @return 项目信息回收
     */
    public ProjectInfoRecycle selectProjectInfoRecycleByProjectId(Long projectId);

    /**
     * 查询项目信息回收列表
     * 
     * @param projectInfoRecycle 项目信息回收
     * @return 项目信息回收集合
     */
    public List<ProjectInfoRecycle> selectProjectInfoRecycleList(ProjectInfoRecycle projectInfoRecycle);

    /**
     * 新增项目信息回收
     * 
     * @param projectInfoRecycle 项目信息回收
     * @return 结果
     */
    public int insertProjectInfoRecycle(ProjectInfoRecycle projectInfoRecycle);

    /**
     * 修改项目信息回收
     * 
     * @param projectInfoRecycle 项目信息回收
     * @return 结果
     */
    public int updateProjectInfoRecycle(ProjectInfoRecycle projectInfoRecycle);

    /**
     * 删除项目信息回收
     * 
     * @param projectId 项目信息回收主键
     * @return 结果
     */
    public int deleteProjectInfoRecycleByProjectId(Long projectId);

    /**
     * 批量删除项目信息回收
     * 
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectInfoRecycleByProjectIds(Long[] projectIds);
}
