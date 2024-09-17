package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.ProjectInfoRecode;

/**
 * 项目修改记录Service接口
 * 
 * @author Teandron
 * @date 2024-09-17
 */
public interface IProjectInfoRecodeService 
{
    /**
     * 查询项目修改记录
     * 
     * @param recodeId 项目修改记录主键
     * @return 项目修改记录
     */
    public ProjectInfoRecode selectProjectInfoRecodeByRecodeId(Long recodeId);

    /**
     * 查询项目修改记录列表
     * 
     * @param projectInfoRecode 项目修改记录
     * @return 项目修改记录集合
     */
    public List<ProjectInfoRecode> selectProjectInfoRecodeList(ProjectInfoRecode projectInfoRecode);

    /**
     * 新增项目修改记录
     * 
     * @param projectInfoRecode 项目修改记录
     * @return 结果
     */
    public int insertProjectInfoRecode(ProjectInfoRecode projectInfoRecode);

    /**
     * 修改项目修改记录
     * 
     * @param projectInfoRecode 项目修改记录
     * @return 结果
     */
    public int updateProjectInfoRecode(ProjectInfoRecode projectInfoRecode);

    /**
     * 批量删除项目修改记录
     * 
     * @param recodeIds 需要删除的项目修改记录主键集合
     * @return 结果
     */
    public int deleteProjectInfoRecodeByRecodeIds(Long[] recodeIds);

    /**
     * 删除项目修改记录信息
     * 
     * @param recodeId 项目修改记录主键
     * @return 结果
     */
    public int deleteProjectInfoRecodeByRecodeId(Long recodeId);
}
