package com.heli.tech.listener;



import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.heli.tech.domain.TechNonStandardOrder;
import com.heli.tech.mapper.TechNonStandardOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class TechNonStandardOrderListener implements ReadListener<TechNonStandardOrder> {


    private static final Logger log = LoggerFactory.getLogger(TechNonStandardOrderListener.class);
    private static final int BATCH_COUNT = 200;
    private Date yearAndMonth;

    @Autowired
    private TechNonStandardOrderMapper techNonStandardOrderMapper;

    private List<TechNonStandardOrder> cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public TechNonStandardOrderListener(TechNonStandardOrderMapper techNonStandardOrderMapper, Date yearAndMonth) {
        this.techNonStandardOrderMapper = techNonStandardOrderMapper;
        this.yearAndMonth = yearAndMonth;

    }

    /**
     * 批量读取Excel写入DB
     *
     * @param registerInfoExcel 全局监听内容
     * @param analysisContext   读取到的Excel内容
     */
    @Override
    public void invoke(TechNonStandardOrder registerInfoExcel, AnalysisContext analysisContext) {
        // 将监听到的数据存入缓存集合中
//        if (registerInfoExcel.getSalaryCalculationYearMonth() != null) {
//            registerInfoExcel.setSalaryTableIndex(registerInfoExcel.getSalaryCalculationYearMonth().getTime() +"-"+registerInfoExcel.getEmployeeId());
//            registerInfoExcel.setCreateTime(DateUtils.getNowDate());
//            registerInfoExcel.setCreateBy(username);
//            cacheDataList.add(registerInfoExcel);
//        }
        if (registerInfoExcel.getOrderId() != null){
            registerInfoExcel.setYearAndMonth(yearAndMonth);
            registerInfoExcel.setTotalDurationDays(registerInfoExcel.getTotalDurationHours().divide(new BigDecimal(8), 4, RoundingMode.HALF_UP));
            cacheDataList.add(registerInfoExcel);
        }

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
        if (cacheDataList.size() > 0) {
            saveToDB();
        }
        log.info("所有数据解析完成");
    }


    /**
     * 将读取到的内容写入DB
     */
    private void saveToDB() {
        log.info("开始写入数据库");
//        enterpriseManagementSalaryTableMapper.batchInsertSalaryTable(cacheDataList);
        techNonStandardOrderMapper.batchInsertNonStandardOrder(cacheDataList);
    }
}
