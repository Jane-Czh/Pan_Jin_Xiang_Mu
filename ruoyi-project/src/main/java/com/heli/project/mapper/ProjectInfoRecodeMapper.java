package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.ProjectInfoRecode;

/**
 * 项目修改记录Mapper接口
 * 
 * @author Teandron
 * @date 2024-09-17
 */
public interface ProjectInfoRecodeMapper 
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
     * 删除项目修改记录
     * 
     * @param recodeId 项目修改记录主键
     * @return 结果
     */
    public int deleteProjectInfoRecodeByRecodeId(Long recodeId);

    /**
     * 批量删除项目修改记录
     * 
     * @param recodeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectInfoRecodeByRecodeIds(Long[] recodeIds);
}
