package com.heli.safety.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.heli.safety.domain.SafetyEpKeyEquipmentIndex;
import com.heli.safety.domain.SafetyEpMaintenanceTable;
import com.heli.safety.mapper.SafetyEpKeyEquipmentIndexMapper;
import com.heli.safety.mapper.SafetyEpMaintenanceTableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description: 监听器，读取excel文件信息，并存入数据库
 * @author: hong
 * @date: 2024/4/11 16:39
 **/
public class KeyEquipmentIndexListener implements ReadListener<SafetyEpKeyEquipmentIndex> {

    private static final Logger log = LoggerFactory.getLogger(KeyEquipmentIndexListener.class);
    private static final int BATCH_COUNT = 200;

    @Autowired
    private SafetyEpKeyEquipmentIndexMapper safetyEpKeyEquipmentIndexMapper;

    private List<SafetyEpKeyEquipmentIndex> cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public KeyEquipmentIndexListener(SafetyEpKeyEquipmentIndexMapper safetyEpKeyEquipmentIndexMapper) {
        this.safetyEpKeyEquipmentIndexMapper = safetyEpKeyEquipmentIndexMapper;
    }

    /**
     * 批量读取Excel写入DB
     *
     * @param registerInfoExcel 全局监听内容
     * @param analysisContext   读取到的Excel内容
     */
    @Override
    public void invoke(SafetyEpKeyEquipmentIndex registerInfoExcel, AnalysisContext analysisContext) {
        // 将监听到的数据存入缓存集合中
        cacheDataList.add(registerInfoExcel);
//        log.info("开始将数据存入缓存中");
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
        safetyEpKeyEquipmentIndexMapper.batchInsertKeyEquipment(cacheDataList);
    }
}
