package com.ruoyi.Technology.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.Technology.Utils.ExcelUtils;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Technology.mapper.TechnologyFunctionChangerorderTableMapper;
import com.ruoyi.Technology.domain.TechnologyFunctionChangerorderTable;
import com.ruoyi.Technology.service.ITechnologyFunctionChangerorderTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 变更单留存Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-02
 */
@Service
public class TechnologyFunctionChangerorderTableServiceImpl implements ITechnologyFunctionChangerorderTableService 
{
    @Autowired
    private TechnologyFunctionChangerorderTableMapper technologyFunctionChangerorderTableMapper;

    /**
     * 查询变更单留存
     * 
     * @param changetableId 变更单留存主键
     * @return 变更单留存
     */
    @Override
    public TechnologyFunctionChangerorderTable selectTechnologyFunctionChangerorderTableByChangetableId(Long changetableId)
    {
        return technologyFunctionChangerorderTableMapper.selectTechnologyFunctionChangerorderTableByChangetableId(changetableId);
    }

    /**
     * 查询变更单留存列表
     * 
     * @param technologyFunctionChangerorderTable 变更单留存
     * @return 变更单留存
     */
    @Override
    public List<TechnologyFunctionChangerorderTable> selectTechnologyFunctionChangerorderTableList(TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable)
    {
        return technologyFunctionChangerorderTableMapper.selectTechnologyFunctionChangerorderTableList(technologyFunctionChangerorderTable);
    }

    /**
     * 新增变更单留存
     * 
     * @param technologyFunctionChangerorderTable 变更单留存
     * @return 结果
     */
    @Override
    public int insertTechnologyFunctionChangerorderTable(TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable)
    {
        return technologyFunctionChangerorderTableMapper.insertTechnologyFunctionChangerorderTable(technologyFunctionChangerorderTable);
    }

    /**
     * 修改变更单留存
     * 
     * @param technologyFunctionChangerorderTable 变更单留存
     * @return 结果
     */
    @Override
    public int updateTechnologyFunctionChangerorderTable(TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable)
    {
        return technologyFunctionChangerorderTableMapper.updateTechnologyFunctionChangerorderTable(technologyFunctionChangerorderTable);
    }

    /**
     * 批量删除变更单留存
     * 
     * @param changetableIds 需要删除的变更单留存主键
     * @return 结果
     */
    @Override
    public int deleteTechnologyFunctionChangerorderTableByChangetableIds(Long[] changetableIds)
    {
        return technologyFunctionChangerorderTableMapper.deleteTechnologyFunctionChangerorderTableByChangetableIds(changetableIds);
    }

    /**
     * 删除变更单留存信息
     * 
     * @param changetableId 变更单留存主键
     * @return 结果
     */
    @Override
    public int deleteTechnologyFunctionChangerorderTableByChangetableId(Long changetableId)
    {
        return technologyFunctionChangerorderTableMapper.deleteTechnologyFunctionChangerorderTableByChangetableId(changetableId);
    }

    @Override
    public int importInterests(MultipartFile excelFile) throws IOException {
        try (InputStream is = excelFile.getInputStream()) {
            List<TechnologyFunctionChangerorderTable> technologyFunctionChangerorderTables = ExcelUtils.parseExcel(excelFile);
            List<TechnologyFunctionChangerorderTable> batchInsertList = new ArrayList<>();

            for (TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable : technologyFunctionChangerorderTables) {
                if (technologyFunctionChangerorderTable.getChangeorderId() == null) {
                    continue;
                }
                batchInsertList.add(technologyFunctionChangerorderTable);
            }

            // 批量插入
            technologyFunctionChangerorderTableMapper.batchInsertMarketCommercialVehicleTable(batchInsertList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        }
        return 0;
    }


}
