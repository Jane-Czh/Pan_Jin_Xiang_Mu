package com.heli.production.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.heli.production.domain.ProductionCommercialVehicleTable;
import com.heli.production.domain.ProductionTable;
import com.heli.production.mapper.ProductionCommercialVehicleTableMapper;
import com.heli.production.mapper.ProductionTableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description: 监听器，读取excel文件信息，并存入数据库
 * @author: hong
 * @date: 2024/4/11 16:39
 **/

public class ProductionTableListener implements ReadListener<ProductionTable> {

    private static final Logger log = LoggerFactory.getLogger(ProductionTableListener.class);
    private static final int BATCH_COUNT = 500;

    @Autowired
    private ProductionTableMapper productionTableMapper;
    @Autowired
    private ProductionCommercialVehicleTableMapper commercialVehicleTableMapper;

    private List<ProductionTable> cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private List<ProductionCommercialVehicleTable> vehicleCacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public ProductionTableListener(ProductionTableMapper productionTableMapper, ProductionCommercialVehicleTableMapper commercialVehicleTableMapper) {
        this.productionTableMapper = productionTableMapper;
        this.commercialVehicleTableMapper = commercialVehicleTableMapper;
    }

    /**
     * 批量读取Excel写入DB
     *
     * @param registerInfoExcel 全局监听内容
     * @param analysisContext   读取到的Excel内容
     */
    @Override
    public void invoke(ProductionTable registerInfoExcel, AnalysisContext analysisContext) {
        // 将监听到的数据存入缓存集合中
        cacheDataList.add(registerInfoExcel);
//        log.info("开始将数据存入缓存中");
        log.info("当前读取的数据为:" + registerInfoExcel);

        // 将数据封装进新表中
        ProductionCommercialVehicleTable vehicleTable = new ProductionCommercialVehicleTable();
        vehicleTable.setLaunchDate(registerInfoExcel.getLaunchDate());
        vehicleTable.setAcceptanceDate(registerInfoExcel.getAcceptanceDate());
        vehicleTable.setAmount(registerInfoExcel.getAmount());
        vehicleTable.setPrecisionCompletionPeriod(registerInfoExcel.getPrecisionCompletionPeriod());
        vehicleTable.setPlannedCompletionPeriod(registerInfoExcel.getPlannedCompletionPeriod());
        vehicleTable.setCarNumber(registerInfoExcel.getCarNumber());
        vehicleCacheDataList.add(vehicleTable);

        //将原本数据中的计划完工期替换为精整完工期
        registerInfoExcel.setPlannedCompletionPeriod(registerInfoExcel.getPrecisionCompletionPeriod());

        // 批量处理缓存的数据
        if (cacheDataList.size() >= BATCH_COUNT) {
            saveToDB();
            cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
            vehicleCacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
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
        productionTableMapper.batchInsertProductionTable(cacheDataList);
        commercialVehicleTableMapper.batchInsertProductionCommercialVehicleTable(vehicleCacheDataList);
    }
}
