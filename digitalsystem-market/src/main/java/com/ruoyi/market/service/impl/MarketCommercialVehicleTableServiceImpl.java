package com.ruoyi.market.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.excel.util.ListUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.*;
import com.ruoyi.market.service.IMarketFunctionComparisonDeliverydaysService;
import com.ruoyi.market.utils.ExcelUtils;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.SplitDate;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketCommercialVehicleTableMapper;
import com.ruoyi.market.service.IMarketCommercialVehicleTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品车台账Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-12
 */
@Service
public class MarketCommercialVehicleTableServiceImpl implements IMarketCommercialVehicleTableService 
{
    @Autowired
    private MarketCommercialVehicleTableMapper marketCommercialVehicleTableMapper;

    private static final int BATCH_COUNT = 5000; // 批处理数量
    //缓存一批数据
    private List<MarketCommercialVehicleTable> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//    @Override
//    public int CVimportInterests(MultipartFile excelFile) throws IOException {
//        MarketCommercialVehicleTable marketCommercialVehicleTable;
//        InputStream is = null;
//        try {
//            List<MarketCommercialVehicleTable> marketCommercialVehicleTables = ExcelUtils.CVparseExcel(excelFile);
//            int i = 0;
//            while (i < marketCommercialVehicleTables.size()){
//                marketCommercialVehicleTable = marketCommercialVehicleTables.get(i);
//                Long lastid = selectLastId();
//                if(lastid == null){
//                    lastid = 0L;
//                }
//                Long MCV_id = GenerateId.getNextId(lastid);
//                marketCommercialVehicleTable.setMcvId(MCV_id);
//                marketCommercialVehicleTable.setCreatedTime(getTime.getCurrentDate());
//                if (marketCommercialVehicleTable.getVehicleModel() == null){
//                    continue;
//                }
//                marketCommercialVehicleTableMapper.insertMarketCommercialVehicleTable(marketCommercialVehicleTable);
//                i++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new ServiceException("excel解析失败");
//        } finally {
//            if (is != null) {
//                is.close();
//            }
//        }
//
//        return 0;
//    }
@Override
public int CVimportInterests(MultipartFile excelFile) throws IOException {
    try (InputStream is = excelFile.getInputStream()) {
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = ExcelUtils.CVparseExcel(excelFile);
        List<MarketCommercialVehicleTable> batchInsertList = new ArrayList<>();

        for (MarketCommercialVehicleTable marketCommercialVehicleTable : marketCommercialVehicleTables) {
            if (marketCommercialVehicleTable.getVehicleModel() == null) {
                continue;
            }
            marketCommercialVehicleTable.setCreatedTime(getTime.getCurrentDate());

            cachedDataList.add(marketCommercialVehicleTable);
            if (cachedDataList.size() >= BATCH_COUNT) {
                marketCommercialVehicleTableMapper.batchInsertMarketCommercialVehicleTable(cachedDataList);
                cachedDataList.clear();
                System.out.println("插入一轮");
            }
            batchInsertList.add(marketCommercialVehicleTable);
        }
        if (cachedDataList.size() > 0){
            marketCommercialVehicleTableMapper.batchInsertMarketCommercialVehicleTable(cachedDataList);
            cachedDataList.clear();
        }


        // 批量插入
//        marketCommercialVehicleTableMapper.batchInsertMarketCommercialVehicleTable(batchInsertList);
    } catch (IOException e) {
        e.printStackTrace();
        throw new ServiceException("excel解析失败");
    }
    return 0;
}


     private Long selectLastId() {
        return marketCommercialVehicleTableMapper.selectLastId();
    }

    /**
     * 查询商品车台账
     * 
     * @param mcvId 商品车台账主键
     * @return 商品车台账
     */
    @Override
    public MarketCommercialVehicleTable selectMarketCommercialVehicleTableByMcvId(Long mcvId)
    {
        return marketCommercialVehicleTableMapper.selectMarketCommercialVehicleTableByMcvId(mcvId);
    }

    /**
     * 查询商品车台账列表
     * 
     * @param marketCommercialVehicleTable 商品车台账
     * @return 商品车台账
     */
    @Override
    public List<MarketCommercialVehicleTable> selectMarketCommercialVehicleTableList(MarketCommercialVehicleTable marketCommercialVehicleTable)
    {
        return marketCommercialVehicleTableMapper.selectMarketCommercialVehicleTableList(marketCommercialVehicleTable);
    }
    /**
     * 新增商品车台账
     * 
     * @param marketCommercialVehicleTable 商品车台账
     * @return 结果
     */
    @Override
    public int insertMarketCommercialVehicleTable(MarketCommercialVehicleTable marketCommercialVehicleTable)
    {
        return marketCommercialVehicleTableMapper.insertMarketCommercialVehicleTable(marketCommercialVehicleTable);
    }

    /**
     * 修改商品车台账
     * 
     * @param marketCommercialVehicleTable 商品车台账
     * @return 结果
     */
    @Override
    public int updateMarketCommercialVehicleTable(MarketCommercialVehicleTable marketCommercialVehicleTable)
    {
        return marketCommercialVehicleTableMapper.updateMarketCommercialVehicleTable(marketCommercialVehicleTable);
    }

    /**
     * 批量删除商品车台账
     * 
     * @param mcvIds 需要删除的商品车台账主键
     * @return 结果
     */
    @Override
    public int deleteMarketCommercialVehicleTableByMcvIds(Long[] mcvIds)
    {
        return marketCommercialVehicleTableMapper.deleteMarketCommercialVehicleTableByMcvIds(mcvIds);
    }

    /**
     * 删除商品车台账信息
     * 
     * @param mcvId 商品车台账主键
     * @return 结果
     */
    @Override
    public int deleteMarketCommercialVehicleTableByMcvId(Long mcvId)
    {
        return marketCommercialVehicleTableMapper.deleteMarketCommercialVehicleTableByMcvId(mcvId);
    }

    @Override
    public List<AverageResult> selectcountAverage() {
        return marketCommercialVehicleTableMapper.selectcountAverage();
    }

    @Override
    public List<MarketCommercialVehicleTable> selectMarketCommercialVehicleTableList1() {
        return marketCommercialVehicleTableMapper.selectMarketCommercialVehicleTableList1();
    }
}
