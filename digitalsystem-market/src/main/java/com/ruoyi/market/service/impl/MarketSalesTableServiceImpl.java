package com.ruoyi.market.service.impl;

import com.alibaba.excel.util.ListUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.domain.MarketSalesTableStorage;
import com.ruoyi.market.mapper.MarketSalesTableMapper;
import com.ruoyi.market.mapper.MarketSalesTableStorageMapper;
import com.ruoyi.market.service.IMarketSalesTableService;
import com.ruoyi.market.utils.*;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 市场科销售台账Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Service
public class MarketSalesTableServiceImpl implements IMarketSalesTableService 
{
    @Autowired
    private MarketSalesTableMapper marketSalesTableMapper;
    @Autowired
    private MarketSalesTableStorageMapper marketSalesTableStorageMapper;

    private static final int BATCH_COUNT = 5000; // 批处理数量
    //缓存一批数据
    private List<MarketSalesTable> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * @description: 利润表导入
     * @author: hong
     * @date: 2024/3/31 13:50
     * @param: excel文件
     * @return: 影响的行数
     **/
    @Override
    public int importInterests(MultipartFile excelFile) throws IOException {
        MarketSalesTable marketSalesTable;
        MarketSalesTableStorage marketSalesTableStorage = new MarketSalesTableStorage();
        InputStream is = null;
        try {
            List<MarketSalesTable> marketSalesTables = ExcelUtils.parseExcel(excelFile);

            //删除
            marketSalesTableMapper.deleteAll();

            int i = 0;
            while (i < marketSalesTables.size()){
                marketSalesTable = marketSalesTables.get(i);
                if (    marketSalesTable.getBranch() == null ||
                        marketSalesTable.getContractNumber() == null ||
                        marketSalesTable.getOrderNumber() == null ||
                        marketSalesTable.getOrderAcceptanceTime() == null ||
                        marketSalesTable.getVehicleModel() == null ||
                        marketSalesTable.getValveBlock() == null ||
                        marketSalesTable.getFork() == null ||
                        marketSalesTable.getDoorFrame() == null ||
                        marketSalesTable.getTyre() == null ||
                        marketSalesTable.getConfiguration() == null ||
                        marketSalesTable.getDeliveryForm() == null ||
                        marketSalesTable.getDeliveryLocation() == null ||
                        marketSalesTable.getContacts() == null ||
                        marketSalesTable.getOrderSystemDeliveryTime() == null ||
                        marketSalesTable.getOrderOverdueWarning() == null
                ){
                    i++;
                    continue;
                }
//                Long lastid = selectLastId();
//                if(lastid == null){
//                    lastid = 0L;
//                }
//                Long MS_id = GenerateId.getNextId(lastid);
//                marketSalesTable.setMsId(MS_id);
                marketSalesTable.setCreatedTime(getTime.getCurrentDate());

//                marketSalesTableMapper.insertMarketSalesTable(marketSalesTable);
                cachedDataList.add(marketSalesTable);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    marketSalesTableMapper.batchInsert(cachedDataList);
                    cachedDataList.clear();
                    System.out.println("插入一轮");
                }
                //查看当天的台账信息，单独存储
                if (getTime.isSameDay(marketSalesTable.getOrderAcceptanceTime(),getTime.getCurrentDate())) {
                    //接单日期为今日，进行存储
                    System.out.println("记录当日台账");
                    Long Storagelastid = marketSalesTableStorageMapper.selectLastId();
                    if (Storagelastid == null){
                        marketSalesTableStorage.setMstsId(GenerateId.getNextId(0L));
                    }else {
                        marketSalesTableStorage.setMstsId(GenerateId.getNextId(Storagelastid));
                    } //主键
                    marketSalesTableStorage.setBranch(marketSalesTable.getBranch()); //网点
                    marketSalesTableStorage.setContractNumber(marketSalesTable.getContractNumber()); //合同号
                    marketSalesTableStorage.setOrderNumber(marketSalesTable.getOrderNumber()); //订单号
                    marketSalesTableStorage.setOrderAcceptanceTime(marketSalesTable.getOrderAcceptanceTime()); //接单日期
                    marketSalesTableStorage.setVehicleModel(marketSalesTable.getVehicleModel()); //车型
                    marketSalesTableStorage.setNumber(marketSalesTable.getNumber()); //数量
                    marketSalesTableStorage.setValveBlock(marketSalesTable.getValveBlock()); //阀片
                    marketSalesTableStorage.setFork(marketSalesTable.getFork()); //货叉
                    marketSalesTableStorage.setDoorFrame(marketSalesTable.getDoorFrame()); //门架
                    marketSalesTableStorage.setAirFilter(marketSalesTable.getAirFilter()); //空滤
                    marketSalesTableStorage.setAccessory(marketSalesTable.getAccessory()); //属具
                    marketSalesTableStorage.setTyre(marketSalesTable.getTyre()); //轮胎
                    marketSalesTableStorage.setConfiguration(marketSalesTable.getConfiguration()); //配置
                    marketSalesTableStorage.setCarNumber(marketSalesTable.getCarNumber()); //车号
                    marketSalesTableStorage.setOrderSystemDeliveryTime(marketSalesTable.getOrderSystemDeliveryTime()); //订单系统交货期

                    marketSalesTableStorageMapper.insertMarketSalesTableStorage(marketSalesTableStorage);
                }
                i++;
            }

            if (cachedDataList.size() > 0){
                marketSalesTableMapper.batchInsert(cachedDataList);
                cachedDataList.clear();
            }

            //查看当天的台账信息，单独存储
//            i = 0;
//            while (i < marketSalesTables.size()){
//                MarketSalesTableStorage marketSalesTableStorage = new MarketSalesTableStorage();
//                marketSalesTable = marketSalesTables.get(i);
//                if (marketSalesTable.getOrderAcceptanceTime().equals(getTime.getCurrentDate())){
//                    //接单日期为今日，进行存储
//                    Long lastid = marketSalesTableStorageMapper.selectLastId();
//                    if (lastid == null){
//                        marketSalesTableStorage.setMstsId(GenerateId.getNextId(0L));
//                    }else {
//                        marketSalesTableStorage.setMstsId(GenerateId.getNextId(lastid));
//                    }
//                    marketSalesTableStorage.setBranch(marketSalesTable.getBranch()); //网点
//                    marketSalesTableStorage.setContractNumber(marketSalesTable.getContractNumber()); //合同号
//                    marketSalesTableStorage.setOrderNumber(marketSalesTable.getOrderNumber()); //订单号
//                    marketSalesTableStorage.setOrderAcceptanceTime(marketSalesTable.getOrderAcceptanceTime()); //接单日期
//                    marketSalesTableStorage.setVehicleModel(marketSalesTable.getVehicleModel()); //车型
//                    marketSalesTableStorage.setNumber(marketSalesTable.getNumber()); //数量
//                    marketSalesTableStorage.setValveBlock(marketSalesTable.getValveBlock()); //阀片
//                    marketSalesTableStorage.setFork(marketSalesTable.getFork()); //货叉
//                    marketSalesTableStorage.setDoorFrame(marketSalesTable.getDoorFrame()); //门架
//                    marketSalesTableStorage.setAirFilter(marketSalesTable.getAirFilter()); //空滤
//                    marketSalesTableStorage.setAccessory(marketSalesTable.getAccessory()); //属具
//                    marketSalesTableStorage.setTyre(marketSalesTable.getTyre()); //轮胎
//                    marketSalesTableStorage.setConfiguration(marketSalesTable.getConfiguration()); //配置
//                    marketSalesTableStorage.setCarNumber(marketSalesTable.getCarNumber()); //车号
//                    marketSalesTableStorage.setOrderSystemDeliveryTime(marketSalesTable.getOrderSystemDeliveryTime()); //订单系统交货期
//
//                    marketSalesTableStorageMapper.insertMarketSalesTableStorage(marketSalesTableStorage);
//                }
//                i++;
//            }

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

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return marketSalesTableMapper.selectLastId();
    }

    /**
     * 查询市场科销售台账
     * 
     * @param msId 市场科销售台账主键
     * @return 市场科销售台账
     */
    @Override
    public MarketSalesTable selectMarketSalesTableByMsId(Long msId)
    {
        return marketSalesTableMapper.selectMarketSalesTableByMsId(msId);
    }

    /**
     * 查询市场科销售台账列表
     * 
     * @param marketSalesTable 市场科销售台账
     * @return 市场科销售台账
     */
    @Override
    public List<MarketSalesTable> selectMarketSalesTableList(MarketSalesTable marketSalesTable)
    {
        return marketSalesTableMapper.selectMarketSalesTableList(marketSalesTable);
    }

    /**
     * 查询市场科销售台账列表
     *
//     * @param marketSalesTable 市场科销售台账
     * @return 市场科销售台账
     */
    @Override
    public List<MarketSalesTable> selectMarketSalesTableList1()
    {
        return marketSalesTableMapper.selectMarketSalesTableList1();
    }


    /**
     * 新增市场科销售台账
     * 
     * @param marketSalesTable 市场科销售台账
     * @return 结果
     */
    @Override
    public int insertMarketSalesTable(MarketSalesTable marketSalesTable)
    {
        Long lastid = selectLastId();
        if(lastid == null){
            lastid = 0L;
        }
        Long MS_id = GenerateId.getNextId(lastid);
        marketSalesTable.setMsId(MS_id);
        return marketSalesTableMapper.insertMarketSalesTable(marketSalesTable);
    }

    /**
     * 修改市场科销售台账
     * 
     * @param marketSalesTable 市场科销售台账
     * @return 结果
     */
    @Override
    public int updateMarketSalesTable(MarketSalesTable marketSalesTable)
    {
        return marketSalesTableMapper.updateMarketSalesTable(marketSalesTable);
    }

    /**
     * 批量删除市场科销售台账
     * 
     * @param msIds 需要删除的市场科销售台账主键
     * @return 结果
     */
    @Override
    public int deleteMarketSalesTableByMsIds(Long[] msIds)
    {
        return marketSalesTableMapper.deleteMarketSalesTableByMsIds(msIds);
    }

    /**
     * 删除市场科销售台账信息
     * 
     * @param msId 市场科销售台账主键
     * @return 结果
     */
    @Override
    public int deleteMarketSalesTableByMsId(Long msId)
    {
        return marketSalesTableMapper.deleteMarketSalesTableByMsId(msId);
    }
}
