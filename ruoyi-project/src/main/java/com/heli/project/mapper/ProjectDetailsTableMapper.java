package com.heli.project.mapper;

import java.util.List;
import com.heli.project.domain.ProjectDetailsTable;

/**
 * 项目详情Mapper接口
 * 
 * @author Teandron
 * @date 2024-04-26
 */
public interface ProjectDetailsTableMapper 
{
    /**
     * 查询项目详情
     * 
     * @param detailsId 项目详情主键
     * @return 项目详情
     */
    public ProjectDetailsTable selectProjectDetailsTableByDetailsId(Long detailsId);

    /**
     * 查询项目详情列表
     * 
     * @param projectDetailsTable 项目详情
     * @return 项目详情集合
     */
    public List<ProjectDetailsTable> selectProjectDetailsTableList(ProjectDetailsTable projectDetailsTable);

    /**
     * 新增项目详情
     * 
     * @param projectDetailsTable 项目详情
     * @return 结果
     */
    public int insertProjectDetailsTable(ProjectDetailsTable projectDetailsTable);

    /**
     * 修改项目详情
     * 
     * @param projectDetailsTable 项目详情
     * @return 结果
     */
    public int updateProjectDetailsTable(ProjectDetailsTable projectDetailsTable);

    /**
     * 删除项目详情
     * 
     * @param detailsId 项目详情主键
     * @return 结果
     */
    public int deleteProjectDetailsTableByDetailsId(Long detailsId);

    /**
     * 批量删除项目详情
     * 
     * @param detailsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectDetailsTableByDetailsIds(Long[] detailsIds);
}
