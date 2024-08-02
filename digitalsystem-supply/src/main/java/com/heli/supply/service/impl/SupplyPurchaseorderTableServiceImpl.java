package com.heli.supply.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.util.ListUtils;
import com.heli.supply.utils.ExcelUtils;
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
 * @author Teandron
 * @date 2024-07-30
 */
@Service
public class SupplyPurchaseorderTableServiceImpl implements ISupplyPurchaseorderTableService 
{
    @Autowired
    private SupplyPurchaseorderTableMapper supplyPurchaseorderTableMapper;

    private static final int BATCH_COUNT = 5000; // 批处理数量
    //缓存一批数据
    private List<SupplyPurchaseorderTable> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    @Override
    public void importInterests(MultipartFile excelFile) throws IOException {

        InputStream is = null;
        try {
            List<SupplyPurchaseorderTable> SupplyPurchaseorderTables = ExcelUtils.parseExcel2SupplyPurchaseorderTable(excelFile);

            int i = 0;

            while (i < SupplyPurchaseorderTables.size()){
//                supplyPurchaseorderTable = SupplyPurchaseorderTables.get(i);
//                insertSupplyPurchaseorderTable(supplyPurchaseorderTable);
                cachedDataList.add(SupplyPurchaseorderTables.get(i));
                if (cachedDataList.size() >= BATCH_COUNT) {
                    supplyPurchaseorderTableMapper.batchInsert(cachedDataList);
                    cachedDataList.clear();
                    System.out.println("插入一轮");
                }
                i++;
            }

            if (cachedDataList.size() > 0){
                supplyPurchaseorderTableMapper.batchInsert(cachedDataList);
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
