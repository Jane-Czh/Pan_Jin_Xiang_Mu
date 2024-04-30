package com.heli.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.project.mapper.ProjectDetailsTableMapper;
import com.heli.project.domain.ProjectDetailsTable;
import com.heli.project.service.IProjectDetailsTableService;

/**
 * 项目详情Service业务层处理
 * 
 * @author Teandron
 * @date 2024-04-26
 */
@Service
public class ProjectDetailsTableServiceImpl implements IProjectDetailsTableService 
{
    @Autowired
    private ProjectDetailsTableMapper projectDetailsTableMapper;

    /**
     * 查询项目详情
     * 
     * @param detailsId 项目详情主键
     * @return 项目详情
     */
    @Override
    public ProjectDetailsTable selectProjectDetailsTableByDetailsId(Long detailsId)
    {
        return projectDetailsTableMapper.selectProjectDetailsTableByDetailsId(detailsId);
    }

    /**
     * 查询项目详情列表
     * 
     * @param projectDetailsTable 项目详情
     * @return 项目详情
     */
    @Override
    public List<ProjectDetailsTable> selectProjectDetailsTableList(ProjectDetailsTable projectDetailsTable)
    {
        return projectDetailsTableMapper.selectProjectDetailsTableList(projectDetailsTable);
    }

    /**
     * 新增项目详情
     * 
     * @param projectDetailsTable 项目详情
     * @return 结果
     */
    @Override
    public int insertProjectDetailsTable(ProjectDetailsTable projectDetailsTable)
    {
        return projectDetailsTableMapper.insertProjectDetailsTable(projectDetailsTable);
    }

    /**
     * 修改项目详情
     * 
     * @param projectDetailsTable 项目详情
     * @return 结果
     */
    @Override
    public int updateProjectDetailsTable(ProjectDetailsTable projectDetailsTable)
    {
        return projectDetailsTableMapper.updateProjectDetailsTable(projectDetailsTable);
    }

    /**
     * 批量删除项目详情
     * 
     * @param detailsIds 需要删除的项目详情主键
     * @return 结果
     */
    @Override
    public int deleteProjectDetailsTableByDetailsIds(Long[] detailsIds)
    {
        return projectDetailsTableMapper.deleteProjectDetailsTableByDetailsIds(detailsIds);
    }

    /**
     * 删除项目详情信息
     * 
     * @param detailsId 项目详情主键
     * @return 结果
     */
    @Override
    public int deleteProjectDetailsTableByDetailsId(Long detailsId)
    {
        return projectDetailsTableMapper.deleteProjectDetailsTableByDetailsId(detailsId);
    }
}
