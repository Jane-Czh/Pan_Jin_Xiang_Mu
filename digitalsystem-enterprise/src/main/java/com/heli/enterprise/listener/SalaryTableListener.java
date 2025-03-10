package com.heli.enterprise.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.heli.enterprise.domain.EnterpriseManagementSalaryTable;
import com.heli.enterprise.mapper.EnterpriseManagementSalaryTableMapper;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SalaryTableListener implements ReadListener<EnterpriseManagementSalaryTable> {


    private static final Logger log = LoggerFactory.getLogger(SalaryTableListener.class);
    private static final int BATCH_COUNT = 100;

    @Autowired
    private EnterpriseManagementSalaryTableMapper enterpriseManagementSalaryTableMapper;
    private String username;

    private List<EnterpriseManagementSalaryTable> cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public SalaryTableListener(EnterpriseManagementSalaryTableMapper enterpriseManagementSalaryTableMapper, String username) {
        this.enterpriseManagementSalaryTableMapper = enterpriseManagementSalaryTableMapper;
        this.username = username;
    }

    /**
     * 批量读取Excel写入DB
     *
     * @param registerInfoExcel 全局监听内容
     * @param analysisContext   读取到的Excel内容
     */
    @Override
    public void invoke(EnterpriseManagementSalaryTable registerInfoExcel, AnalysisContext analysisContext) {
        // 将监听到的数据存入缓存集合中
        if (registerInfoExcel.getEmployeeId() != null) {
//            registerInfoExcel.setSalaryTableIndex(registerInfoExcel.getSalaryCalculationYearMonth().getTime() +"-"+registerInfoExcel.getEmployeeId());
//            registerInfoExcel.setCreateTime(DateUtils.getNowDate());
//            registerInfoExcel.setCreateBy(username);
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
        saveToDB();
        log.info("所有数据解析完成");
    }


    /**
     * 将读取到的内容写入DB
     */
    private void saveToDB() {
        log.info("开始写入数据库");
        enterpriseManagementSalaryTableMapper.batchInsertSalaryTable(cacheDataList);
    }
}
