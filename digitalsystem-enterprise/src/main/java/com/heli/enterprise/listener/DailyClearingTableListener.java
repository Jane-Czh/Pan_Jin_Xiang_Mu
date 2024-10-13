package com.heli.enterprise.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.heli.enterprise.domain.EnterpriseManagementDailyClearingReadEntity;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.heli.enterprise.domain.EnterpriseManagementSalaryTable;
import com.heli.enterprise.mapper.EnterpriseManagementIndicatorsDailyClearingSettlementMapper;
import com.heli.enterprise.mapper.EnterpriseManagementSalaryTableMapper;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
public class DailyClearingTableListener implements ReadListener<EnterpriseManagementDailyClearingReadEntity> {

    private static final int BATCH_COUNT = 100;

    @Autowired
    private EnterpriseManagementIndicatorsDailyClearingSettlementMapper dailyClearingMapper;
    private Date yearAndMonth;


    private List<EnterpriseManagementIndicatorsDailyClearingSettlement> cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public DailyClearingTableListener(EnterpriseManagementIndicatorsDailyClearingSettlementMapper dailyClearingMapper, Date yearAndMonth) {
        this.dailyClearingMapper = dailyClearingMapper;
        this.yearAndMonth = yearAndMonth;
    }

    /**
     * 批量读取Excel写入DB
     *
     * @param registerInfoExcel 全局监听内容
     * @param analysisContext   读取到的Excel内容
     */
    @Override
    public void invoke(EnterpriseManagementDailyClearingReadEntity registerInfoExcel, AnalysisContext analysisContext) {
        log.info("当前读取的数据为:" + registerInfoExcel);

        // 数据处理，将表格数据转化为实体数据
        EnterpriseManagementIndicatorsDailyClearingSettlement dailyClear = new EnterpriseManagementIndicatorsDailyClearingSettlement();


        // 时间转换
        if (Objects.equals(registerInfoExcel.getYearAndMonth(), "盘锦目值")) {
            dailyClear.setYearAndMonth(yearAndMonth);
        } else {
            String month = registerInfoExcel.getYearAndMonth().substring(0, registerInfoExcel.getYearAndMonth().indexOf("月"));
            int monthToInt = Integer.parseInt(month);
            log.info("当前读取的数据为:" + monthToInt);
            //将date转化为localDateTime
            LocalDateTime localDateTime = yearAndMonth.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            //利用localDateTime工具得到当前月份的后三个月个月，并且重新转化为Date类型
            Date from = Date.from(localDateTime.plusMonths(monthToInt - 1).atZone(ZoneId.systemDefault()).toInstant());
            dailyClear.setYearAndMonth(from);
        }

        // 标识符转换
        if (Objects.equals(registerInfoExcel.getValue(), "目标值")) {
            dailyClear.setFlag(1);
        } else if (Objects.equals(registerInfoExcel.getValue(), "实际值")) {
            dailyClear.setFlag(2);
        } else if (Objects.equals(registerInfoExcel.getValue(), "得分")) {
            dailyClear.setFlag(3);
        }

        // 通过前两个条件筛选，判断数据是否已经存在
        List<EnterpriseManagementIndicatorsDailyClearingSettlement> list = dailyClearingMapper.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(dailyClear);


        // 数据转换
        if (registerInfoExcel.getOrderEntryDelayRatio() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getOrderEntryDelayRatio().replace("%", "");
            // replace转换为BigDecimal
            dailyClear.setOrderEntryDelayRatio(new BigDecimal(replace));
        }
        if (registerInfoExcel.getShipmentDelayRatio() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getShipmentDelayRatio().replace("%", "");
            // replace转换为BigDecimal
            dailyClear.setShipmentDelayRatio(new BigDecimal(replace));
        }
        if (registerInfoExcel.getProductionReportDelayRatio() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getProductionReportDelayRatio().replace("%", "");
            // replace转换为BigDecimal
            dailyClear.setProductionReportDelayRatio(new BigDecimal(replace));
        }
        if (registerInfoExcel.getInspectionDelayRate() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getInspectionDelayRate().replace("%", "");
            // replace转换为BigDecimal
            dailyClear.setInspectionDelayRate(new BigDecimal(replace));
        }
        if (registerInfoExcel.getInvoicePostingDelayRate() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getInvoicePostingDelayRate().replace("%", "");
            // replace转换为BigDecimal
            dailyClear.setInvoicePostingDelayRate(new BigDecimal(replace));
        }
        if (registerInfoExcel.getUnsettledAccountsRatio() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getUnsettledAccountsRatio().replace("%", "");
            // replace转换为BigDecimal
            dailyClear.setUnsettledAccountsRatio(new BigDecimal(replace));
        }

        log.info("当前转换后的值为:" + dailyClear);

        // 如果转换后的数据都为null，则不进行写入
        if (dailyClear.getOrderEntryDelayRatio() == null && dailyClear.getShipmentDelayRatio() == null
                && dailyClear.getProductionReportDelayRatio() == null && dailyClear.getInspectionDelayRate() == null
                && dailyClear.getInvoicePostingDelayRate() == null && dailyClear.getUnsettledAccountsRatio() == null) {
            log.info("当前数据为空，不进行写入");
        } else {
            if (list.size() == 0) {
                dailyClearingMapper.insertEnterpriseManagementIndicatorsDailyClearingSettlement(dailyClear);
            } else {
                dailyClear.setEdId(list.get(0).getEdId());
                dailyClearingMapper.updateEnterpriseManagementIndicatorsDailyClearingSettlement(dailyClear);
            }
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
//        enterpriseManagementSalaryTableMapper.batchInsertSalaryTable(cacheDataList);
    }
}
