package com.heli.project.service.impl;

import java.io.IOException;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.project.listener.ProjectHistoryInfoDataListener;
import com.heli.project.utils.ExcelUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.project.mapper.ProjectHistoryInfoTableMapper;
import com.heli.project.domain.ProjectHistoryInfoTable;
import com.heli.project.service.IProjectHistoryInfoTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 历史项目信息管理Service业务层处理
 * 
 * @author Teandron
 * @date 2024-05-21
 */
@Service
public class ProjectHistoryInfoTableServiceImpl implements IProjectHistoryInfoTableService 
{
    @Autowired
    private ProjectHistoryInfoTableMapper projectHistoryInfoTableMapper;

    @Override
    public void importInterests(MultipartFile excelFile){
        try {
            List<ProjectHistoryInfoTable> data = ExcelUtils.parseExcel(excelFile);
            System.out.println(data);
            int size = data.size(), i = 0;

            while(i < size){
                projectHistoryInfoTableMapper.insertProjectHistoryInfoTable(data.get(i));
                i++;
            }
//            projectHistoryInfoTableMapper.batchInsert(data);

//            EasyExcel.read(excelFile.getInputStream(), ProjectHistoryInfoTable.class, new ProjectHistoryInfoDataListener()).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询历史项目信息管理
     * 
     * @param projectId 历史项目信息管理主键
     * @return 历史项目信息管理
     */
    @Override
    public ProjectHistoryInfoTable selectProjectHistoryInfoTableByProjectId(Long projectId)
    {
        return projectHistoryInfoTableMapper.selectProjectHistoryInfoTableByProjectId(projectId);
    }

    /**
     * 查询历史项目信息管理列表
     * 
     * @param projectHistoryInfoTable 历史项目信息管理
     * @return 历史项目信息管理
     */
    @Override
    public List<ProjectHistoryInfoTable> selectProjectHistoryInfoTableList(ProjectHistoryInfoTable projectHistoryInfoTable)
    {
        return projectHistoryInfoTableMapper.selectProjectHistoryInfoTableList(projectHistoryInfoTable);
    }

    /**
     * 新增历史项目信息管理
     * 
     * @param projectHistoryInfoTable 历史项目信息管理
     * @return 结果
     */
    @Override
    public int insertProjectHistoryInfoTable(ProjectHistoryInfoTable projectHistoryInfoTable)
    {
        return projectHistoryInfoTableMapper.insertProjectHistoryInfoTable(projectHistoryInfoTable);
    }

    /**
     * 修改历史项目信息管理
     * 
     * @param projectHistoryInfoTable 历史项目信息管理
     * @return 结果
     */
    @Override
    public int updateProjectHistoryInfoTable(ProjectHistoryInfoTable projectHistoryInfoTable)
    {
        return projectHistoryInfoTableMapper.updateProjectHistoryInfoTable(projectHistoryInfoTable);
    }

    /**
     * 批量删除历史项目信息管理
     * 
     * @param projectIds 需要删除的历史项目信息管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectHistoryInfoTableByProjectIds(Long[] projectIds)
    {
        return projectHistoryInfoTableMapper.deleteProjectHistoryInfoTableByProjectIds(projectIds);
    }

    /**
     * 删除历史项目信息管理信息
     * 
     * @param projectId 历史项目信息管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectHistoryInfoTableByProjectId(Long projectId)
    {
        return projectHistoryInfoTableMapper.deleteProjectHistoryInfoTableByProjectId(projectId);
    }
}
