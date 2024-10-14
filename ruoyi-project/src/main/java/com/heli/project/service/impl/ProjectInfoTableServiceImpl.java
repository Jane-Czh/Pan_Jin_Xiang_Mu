package com.heli.project.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.util.ListUtils;
import com.heli.project.utils.ExcelUtils;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.project.mapper.ProjectInfoTableMapper;
import com.heli.project.domain.ProjectInfoTable;
import com.heli.project.service.IProjectInfoTableService;
import com.ruoyi.project.domain.ProjectInfoRecode;
import org.springframework.web.multipart.MultipartFile;

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

    private static final int BATCH_COUNT = 5000; // 批处理数量
    //缓存一批数据
    private List<ProjectInfoTable> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public int importInterests(MultipartFile excelFile) throws IOException {
        ProjectInfoTable marketCarType = new ProjectInfoTable();

        InputStream is = null;
        try {
            List<ProjectInfoTable> marketCarTypes = ExcelUtils.parseExcel2ProjectInfoTable(excelFile);

            int i = 0;
            while (i < marketCarTypes.size()){
                marketCarType = marketCarTypes.get(i);
//                insertMarketCarType(marketCarType);
                cachedDataList.add(marketCarType);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    projectInfoTableMapper.batchInsert(cachedDataList);
                    cachedDataList.clear();
                    System.out.println("插入一轮");
                }
                i++;
            }
            if (cachedDataList.size() > 0){
                projectInfoTableMapper.batchInsert(cachedDataList);
                cachedDataList.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return 0;
    }

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
