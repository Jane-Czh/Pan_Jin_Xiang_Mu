package com.heli.tech.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.heli.tech.domain.TechNewProjectDesign;
import com.heli.tech.mapper.TechNewProjectDesignMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class TechNewProjectListener implements ReadListener<TechNewProjectDesign> {


    private static final Logger log = LoggerFactory.getLogger(TechNewProjectListener.class);
    private static final int BATCH_COUNT = 200;
    private Date yearAndMonth;

    @Autowired
    private TechNewProjectDesignMapper techNewProjectDesignMapper;

    private List<TechNewProjectDesign> cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public TechNewProjectListener(TechNewProjectDesignMapper techNewProjectDesignMapper, Date yearAndMonth) {
        this.techNewProjectDesignMapper = techNewProjectDesignMapper;
        this.yearAndMonth = yearAndMonth;

    }

    /**
     * 批量读取Excel写入DB
     *
     * @param registerInfoExcel 全局监听内容
     * @param analysisContext   读取到的Excel内容
     */
    @Override
    public void invoke(TechNewProjectDesign registerInfoExcel, AnalysisContext analysisContext) {
        // 将监听到的数据存入缓存集合中

        if (registerInfoExcel.getEstablishmentState() == null) {
            registerInfoExcel.setEstablishmentState("");
        }
        if (registerInfoExcel.getDesignPlanState() == null) {
            registerInfoExcel.setDesignPlanState("");
        }
        if (registerInfoExcel.getDrawingDesignState() == null) {
            registerInfoExcel.setDrawingDesignState("");
        }
        if (registerInfoExcel.getPrototypeProductionState() == null) {
            registerInfoExcel.setPrototypeProductionState("");
        }
        if (registerInfoExcel.getPrototypeTestState() == null) {
            registerInfoExcel.setPrototypeTestState("");
        }
        if (registerInfoExcel.getPrototypeReviewState() == null) {
            registerInfoExcel.setPrototypeReviewState("");
        }
        if (registerInfoExcel.getPilotReleaseState() == null) {
            registerInfoExcel.setPilotReleaseState("");
        }
        if (registerInfoExcel.getMassReleaseState() == null) {
            registerInfoExcel.setMassReleaseState("");
        }
        if (registerInfoExcel.getCompletionRate() == null) {
            registerInfoExcel.setCompletionRate("0");
        } else {
            //去掉百分号
            registerInfoExcel.setCompletionRate(registerInfoExcel.getCompletionRate().replace("%", ""));
        }

        if (registerInfoExcel.getCompletionRate().equals("100")){
            registerInfoExcel.setIsCompleted(1);
        } else {
            registerInfoExcel.setIsCompleted(0);
        }
//        if (registerInfoExcel.getCompletionRate().equals("100") ||
//                (registerInfoExcel.getEstablishmentState().equals("完成") && registerInfoExcel.getDesignPlanState().equals("完成")
//                 && registerInfoExcel.getDrawingDesignState().equals("完成") && registerInfoExcel.getPrototypeProductionState().equals("完成")
//                 && registerInfoExcel.getPrototypeTestState().equals("完成") && registerInfoExcel.getPrototypeReviewState().equals("完成")
//                 && registerInfoExcel.getPilotReleaseState().equals("完成") && registerInfoExcel.getMassReleaseState().equals("完成"))){
//            registerInfoExcel.setIsCompleted(1);
//        } else {
//            registerInfoExcel.setIsCompleted(0);
//        }


        if (registerInfoExcel.getProjectName() != null){
            registerInfoExcel.setYearAndMonth(yearAndMonth);
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
//        techNonStandardOrderMapper.batchInsertNonStandardOrder(cacheDataList);
        techNewProjectDesignMapper.batchInsertNewProjectDesign(cacheDataList);
    }
}
