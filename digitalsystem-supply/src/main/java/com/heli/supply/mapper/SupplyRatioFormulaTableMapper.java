package com.heli.supply.mapper;

import java.util.List;
import com.heli.supply.domain.SupplyRatioFormulaTable;

/**
 * 月供货比例统计Mapper接口
 * 
 * @author Teandron
 * @date 2024-04-24
 */
public interface SupplyRatioFormulaTableMapper 
{
    /**
     * 查询月供货比例统计
     * 
     * @param srftId 月供货比例统计主键
     * @return 月供货比例统计
     */
    public SupplyRatioFormulaTable selectSupplyRatioFormulaTableBySrftId(Long srftId);

    /**
     * 查询月供货比例统计列表
     * 
     * @param supplyRatioFormulaTable 月供货比例统计
     * @return 月供货比例统计集合
     */
    public List<SupplyRatioFormulaTable> selectSupplyRatioFormulaTableList(SupplyRatioFormulaTable supplyRatioFormulaTable);

    /**
     * 新增月供货比例统计
     * 
     * @param supplyRatioFormulaTable 月供货比例统计
     * @return 结果
     */
    public int insertSupplyRatioFormulaTable(SupplyRatioFormulaTable supplyRatioFormulaTable);


    //查询数据库最后一条数据id
    public Long selectLastId();
    /**
     * 修改月供货比例统计
     * 
     * @param supplyRatioFormulaTable 月供货比例统计
     * @return 结果
     */
    public int updateSupplyRatioFormulaTable(SupplyRatioFormulaTable supplyRatioFormulaTable);

    /**
     * 删除月供货比例统计
     * 
     * @param srftId 月供货比例统计主键
     * @return 结果
     */
    public int deleteSupplyRatioFormulaTableBySrftId(Long srftId);

    /**
     * 批量删除月供货比例统计
     * 
     * @param srftIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplyRatioFormulaTableBySrftIds(Long[] srftIds);

    public void deleteAll();

}
