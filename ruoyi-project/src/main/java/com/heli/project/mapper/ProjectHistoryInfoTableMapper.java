package com.heli.project.mapper;

import java.util.List;
import com.heli.project.domain.ProjectHistoryInfoTable;

/**
 * 历史项目信息管理Mapper接口
 * 
 * @author Teandron
 * @date 2024-05-21
 */
public interface ProjectHistoryInfoTableMapper 
{
    /**
     * 查询历史项目信息管理
     * 
     * @param projectId 历史项目信息管理主键
     * @return 历史项目信息管理
     */
    public ProjectHistoryInfoTable selectProjectHistoryInfoTableByProjectId(Long projectId);

    /**
     * 查询历史项目信息管理列表
     * 
     * @param projectHistoryInfoTable 历史项目信息管理
     * @return 历史项目信息管理集合
     */
    public List<ProjectHistoryInfoTable> selectProjectHistoryInfoTableList(ProjectHistoryInfoTable projectHistoryInfoTable);

    /**
     * 新增历史项目信息管理
     * 
     * @param projectHistoryInfoTable 历史项目信息管理
     * @return 结果
     */
    public int insertProjectHistoryInfoTable(ProjectHistoryInfoTable projectHistoryInfoTable);

    /**
     * 修改历史项目信息管理
     * 
     * @param projectHistoryInfoTable 历史项目信息管理
     * @return 结果
     */
    public int updateProjectHistoryInfoTable(ProjectHistoryInfoTable projectHistoryInfoTable);

    /**
     * 删除历史项目信息管理
     * 
     * @param projectId 历史项目信息管理主键
     * @return 结果
     */
    public int deleteProjectHistoryInfoTableByProjectId(Long projectId);

    /**
     * 批量删除历史项目信息管理
     * 
     * @param projectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectHistoryInfoTableByProjectIds(Long[] projectIds);

    /**
     * 批量插入历史项目信息管理
     *
     * @return 结果
     */
    public void batchInsert(List<ProjectHistoryInfoTable> projectHistoryInfos);
}
