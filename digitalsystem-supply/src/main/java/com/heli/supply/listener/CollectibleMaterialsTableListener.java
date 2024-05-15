package com.heli.supply.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.heli.supply.domain.SupplyIndicatorsCollectibleMaterialsTable;
import com.heli.supply.domain.SupplyIndicatorsPurchaseOrderTable;
import com.heli.supply.mapper.SupplyIndicatorsCollectibleMaterialsTableMapper;
import com.heli.supply.mapper.SupplyIndicatorsPurchaseOrderTableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description: 监听器，读取excel集采物资字典文件信息，并存入数据库
 * @author: hong
 * @date: 2024/4/28 10:51
 **/
public class CollectibleMaterialsTableListener implements ReadListener<SupplyIndicatorsCollectibleMaterialsTable> {

    private static final Logger log = LoggerFactory.getLogger(CollectibleMaterialsTableListener.class);
    private static final int BATCH_COUNT = 100;

    @Autowired
    private SupplyIndicatorsCollectibleMaterialsTableMapper supplyIndicatorsCollectibleMaterialsTableMapper;

    private List<SupplyIndicatorsCollectibleMaterialsTable> cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public CollectibleMaterialsTableListener(SupplyIndicatorsCollectibleMaterialsTableMapper supplyIndicatorsCollectibleMaterialsTableMapper) {
        this.supplyIndicatorsCollectibleMaterialsTableMapper = supplyIndicatorsCollectibleMaterialsTableMapper;
    }

    /**
     * 批量读取Excel写入DB
     *
     * @param registerInfoExcel 全局监听内容
     * @param analysisContext   读取到的Excel内容
     */
    @Override
    public void invoke(SupplyIndicatorsCollectibleMaterialsTable registerInfoExcel, AnalysisContext analysisContext) {

        // 将监听到的数据存入缓存集合中
        cacheDataList.add(registerInfoExcel);
        log.info("当前读取的数据为:" + registerInfoExcel);

        // 批量处理缓存的数据
        if (cacheDataList.size() >= BATCH_COUNT) {
            saveToDB();
            cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 处理结尾, 不足100条
     *
     * @param analysisContext 全局监听内容
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveToDB();
        log.info("所有数据解析完成");
    }

    /**
     * 将读取到的内容写入DB
     */
    private void saveToDB() {
        log.info("开始写入数据库");
        supplyIndicatorsCollectibleMaterialsTableMapper.batchInsertCollectibleMaterialsTable(cacheDataList);
    }

}