package com.ruoyi.Technology.mapper;

import java.util.List;
import com.ruoyi.Technology.domain.TechnologyFunctionChangerorderTable;

/**
 * 变更单留存Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-02
 */
public interface TechnologyFunctionChangerorderTableMapper 
{
    /**
     * 查询变更单留存
     * 
     * @param changetableId 变更单留存主键
     * @return 变更单留存
     */
    public TechnologyFunctionChangerorderTable selectTechnologyFunctionChangerorderTableByChangetableId(Long changetableId);

    /**
     * 查询变更单留存列表
     * 
     * @param technologyFunctionChangerorderTable 变更单留存
     * @return 变更单留存集合
     */
    public List<TechnologyFunctionChangerorderTable> selectTechnologyFunctionChangerorderTableList(TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable);

    /**
     * 新增变更单留存
     * 
     * @param technologyFunctionChangerorderTable 变更单留存
     * @return 结果
     */
    public int insertTechnologyFunctionChangerorderTable(TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable);

    /**
     * 修改变更单留存
     * 
     * @param technologyFunctionChangerorderTable 变更单留存
     * @return 结果
     */
    public int updateTechnologyFunctionChangerorderTable(TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable);

    /**
     * 删除变更单留存
     * 
     * @param changetableId 变更单留存主键
     * @return 结果
     */
    public int deleteTechnologyFunctionChangerorderTableByChangetableId(Long changetableId);

    /**
     * 批量删除变更单留存
     * 
     * @param changetableIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTechnologyFunctionChangerorderTableByChangetableIds(Long[] changetableIds);

    void batchInsertMarketCommercialVehicleTable(List<TechnologyFunctionChangerorderTable> technologyFunctionChangerorderTables);
}
