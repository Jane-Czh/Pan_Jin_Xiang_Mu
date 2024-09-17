package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.ProjectInfoRecodeMapper;
import com.ruoyi.project.domain.ProjectInfoRecode;
import com.ruoyi.project.service.IProjectInfoRecodeService;

/**
 * 项目修改记录Service业务层处理
 * 
 * @author Teandron
 * @date 2024-09-17
 */
@Service
public class ProjectInfoRecodeServiceImpl implements IProjectInfoRecodeService 
{
    @Autowired
    private ProjectInfoRecodeMapper projectInfoRecodeMapper;

    /**
     * 查询项目修改记录
     * 
     * @param recodeId 项目修改记录主键
     * @return 项目修改记录
     */
    @Override
    public ProjectInfoRecode selectProjectInfoRecodeByRecodeId(Long recodeId)
    {
        return projectInfoRecodeMapper.selectProjectInfoRecodeByRecodeId(recodeId);
    }

    /**
     * 查询项目修改记录列表
     * 
     * @param projectInfoRecode 项目修改记录
     * @return 项目修改记录
     */
    @Override
    public List<ProjectInfoRecode> selectProjectInfoRecodeList(ProjectInfoRecode projectInfoRecode)
    {
        return projectInfoRecodeMapper.selectProjectInfoRecodeList(projectInfoRecode);
    }

    /**
     * 新增项目修改记录
     * 
     * @param projectInfoRecode 项目修改记录
     * @return 结果
     */
    @Override
    public int insertProjectInfoRecode(ProjectInfoRecode projectInfoRecode)
    {
        return projectInfoRecodeMapper.insertProjectInfoRecode(projectInfoRecode);
    }

    /**
     * 修改项目修改记录
     * 
     * @param projectInfoRecode 项目修改记录
     * @return 结果
     */
    @Override
    public int updateProjectInfoRecode(ProjectInfoRecode projectInfoRecode)
    {
        projectInfoRecode.setUpdateTime(DateUtils.getNowDate());
        return projectInfoRecodeMapper.updateProjectInfoRecode(projectInfoRecode);
    }

    /**
     * 批量删除项目修改记录
     * 
     * @param recodeIds 需要删除的项目修改记录主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoRecodeByRecodeIds(Long[] recodeIds)
    {
        return projectInfoRecodeMapper.deleteProjectInfoRecodeByRecodeIds(recodeIds);
    }

    /**
     * 删除项目修改记录信息
     * 
     * @param recodeId 项目修改记录主键
     * @return 结果
     */
    @Override
    public int deleteProjectInfoRecodeByRecodeId(Long recodeId)
    {
        return projectInfoRecodeMapper.deleteProjectInfoRecodeByRecodeId(recodeId);
    }
}
