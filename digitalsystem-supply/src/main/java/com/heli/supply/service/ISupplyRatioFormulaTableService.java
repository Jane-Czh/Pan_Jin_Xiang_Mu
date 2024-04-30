package com.heli.supply.service;

import java.io.IOException;
import java.util.List;

import com.heli.supply.domain.SupplyPurchaseorderTable;
import com.heli.supply.domain.SupplyRatioFormulaTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 月供货比例统计Service接口
 * 
 * @author Teandron
 * @date 2024-04-24
 */
public interface ISupplyRatioFormulaTableService 
{


    /**
     * 导入excel
     *
     *
     *
     */
    public void importInterests(MultipartFile excelFile) throws IOException;

    public void Synchronization(List<SupplyPurchaseorderTable> list);
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

    /**
     * 修改月供货比例统计
     * 
     * @param supplyRatioFormulaTable 月供货比例统计
     * @return 结果
     */
    public int updateSupplyRatioFormulaTable(SupplyRatioFormulaTable supplyRatioFormulaTable);

    /**
     * 批量删除月供货比例统计
     * 
     * @param srftIds 需要删除的月供货比例统计主键集合
     * @return 结果
     */
    public int deleteSupplyRatioFormulaTableBySrftIds(Long[] srftIds);

    /**
     * 删除月供货比例统计信息
     * 
     * @param srftId 月供货比例统计主键
     * @return 结果
     */
    public int deleteSupplyRatioFormulaTableBySrftId(Long srftId);
}
