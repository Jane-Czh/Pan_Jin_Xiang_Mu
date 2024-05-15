package com.heli.supply.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.heli.supply.utils.ExcelUtils;
import com.heli.supply.utils.GenerateId;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.supply.mapper.SupplyPurchaseorderTableMapper;
import com.heli.supply.domain.SupplyPurchaseorderTable;
import com.heli.supply.service.ISupplyPurchaseorderTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采购订单导入Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
@Service
public class SupplyPurchaseorderTableServiceImpl implements ISupplyPurchaseorderTableService 
{
    @Autowired
    private SupplyPurchaseorderTableMapper supplyPurchaseorderTableMapper;


    @Override
    public void importInterests(MultipartFile excelFile) throws IOException {

        SupplyPurchaseorderTable supplyPurchaseorderTable = new SupplyPurchaseorderTable();
        InputStream is = null;

        try {
            List<SupplyPurchaseorderTable> SupplyPurchaseorderTables = ExcelUtils.parseExcel2SupplyPurchaseorderTable(excelFile);

            int i = 0;
            while (i < SupplyPurchaseorderTables.size()){
                supplyPurchaseorderTable = SupplyPurchaseorderTables.get(i);

                insertSupplyPurchaseorderTable(supplyPurchaseorderTable);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return supplyPurchaseorderTableMapper.selectLastId();
    }

    /**
     * 查询采购订单导入
     * 
     * @param spId 采购订单导入主键
     * @return 采购订单导入
     */
    @Override
    public SupplyPurchaseorderTable selectSupplyPurchaseorderTableBySpId(Long spId)
    {
        return supplyPurchaseorderTableMapper.selectSupplyPurchaseorderTableBySpId(spId);
    }

    /**
     * 查询采购订单导入列表
     * 
     * @param supplyPurchaseorderTable 采购订单导入
     * @return 采购订单导入
     */
    @Override
    public List<SupplyPurchaseorderTable> selectSupplyPurchaseorderTableList(SupplyPurchaseorderTable supplyPurchaseorderTable)
    {
        return supplyPurchaseorderTableMapper.selectSupplyPurchaseorderTableList(supplyPurchaseorderTable);
    }

    /**
     * 新增采购订单导入
     * 
     * @param supplyPurchaseorderTable 采购订单导入
     * @return 结果
     */
    @Override
    public int insertSupplyPurchaseorderTable(SupplyPurchaseorderTable supplyPurchaseorderTable)
    {
        Long lastid = selectLastId();
        if(lastid == null){
            lastid = 0L;
        }
        Long SpId = GenerateId.getNextId(lastid);
        supplyPurchaseorderTable.setSpId(SpId);
        return supplyPurchaseorderTableMapper.insertSupplyPurchaseorderTable(supplyPurchaseorderTable);
    }

    /**
     * 修改采购订单导入
     * 
     * @param supplyPurchaseorderTable 采购订单导入
     * @return 结果
     */
    @Override
    public int updateSupplyPurchaseorderTable(SupplyPurchaseorderTable supplyPurchaseorderTable)
    {
        return supplyPurchaseorderTableMapper.updateSupplyPurchaseorderTable(supplyPurchaseorderTable);
    }

    /**
     * 批量删除采购订单导入
     * 
     * @param spIds 需要删除的采购订单导入主键
     * @return 结果
     */
    @Override
    public int deleteSupplyPurchaseorderTableBySpIds(Long[] spIds)
    {
        return supplyPurchaseorderTableMapper.deleteSupplyPurchaseorderTableBySpIds(spIds);
    }

    /**
     * 删除采购订单导入信息
     * 
     * @param spId 采购订单导入主键
     * @return 结果
     */
    @Override
    public int deleteSupplyPurchaseorderTableBySpId(Long spId)
    {
        return supplyPurchaseorderTableMapper.deleteSupplyPurchaseorderTableBySpId(spId);
    }
}
