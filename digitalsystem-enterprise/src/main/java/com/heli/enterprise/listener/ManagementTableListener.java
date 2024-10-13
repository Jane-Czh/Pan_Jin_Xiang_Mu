package com.heli.enterprise.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.util.ListUtils;
import com.heli.enterprise.domain.EnterpriseManagementDailyClearingReadEntity;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
import com.heli.enterprise.domain.EnterpriseManagementManagementReadEntity;
import com.heli.enterprise.mapper.EnterpriseManagementIndicatorsDailyClearingSettlementMapper;
import com.heli.enterprise.mapper.EnterpriseManagementIndicatorsManagementMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Slf4j
public class ManagementTableListener implements ReadListener<EnterpriseManagementManagementReadEntity> {
    @Autowired
    private EnterpriseManagementIndicatorsManagementMapper managementMapper;
    private Date yearAndMonth;

    public ManagementTableListener(EnterpriseManagementIndicatorsManagementMapper managementMapper, Date yearAndMonth) {
        this.managementMapper = managementMapper;
        this.yearAndMonth = yearAndMonth;
    }

    /**
     * 批量读取Excel写入DB
     *
     * @param registerInfoExcel 全局监听内容
     * @param analysisContext   读取到的Excel内容
     */
    @Override
    public void invoke(EnterpriseManagementManagementReadEntity registerInfoExcel, AnalysisContext analysisContext) {
        log.info("当前读取的数据为:" + registerInfoExcel);

        // 数据处理，将表格数据转化为实体数据
        EnterpriseManagementIndicatorsManagement management = new EnterpriseManagementIndicatorsManagement();

        // 时间转换,字符串匹配
        if (registerInfoExcel.getYearAndMonth().contains("月")) {
            log.info("当前月份:" + registerInfoExcel.getYearAndMonth());

            String month = registerInfoExcel.getYearAndMonth().substring(0, registerInfoExcel.getYearAndMonth().indexOf("月"));
            int monthToInt = chineseToArabic(month);

            //将date转化为localDateTime
            LocalDateTime localDateTime = yearAndMonth.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            //利用localDateTime工具得到当前月份的后三个月个月，并且重新转化为Date类型
            Date from = Date.from(localDateTime.plusMonths(monthToInt - 1).atZone(ZoneId.systemDefault()).toInstant());

            management.setYearAndMonth(from);
            log.info("时间" + DateUtils.format(from));
        } else {
            management.setYearAndMonth(yearAndMonth);
        }

        // 标识符转换
        if (Objects.equals(registerInfoExcel.getValue(), "股份目标值")) {
            management.setFlag(1);
        } else if (Objects.equals(registerInfoExcel.getValue(), "盘锦目标值")) {
            management.setFlag(2);
        } else if (Objects.equals(registerInfoExcel.getValue(), "实际值")) {
            management.setFlag(3);
        } else if (Objects.equals(registerInfoExcel.getValue(), "实际得分")) {
            management.setFlag(4);
        }

        // 通过前两个条件筛选，判断数据是否已经存在
        List<EnterpriseManagementIndicatorsManagement> list = managementMapper.selectEnterpriseManagementIndicatorsManagementList(management);


        // 数据转换
        if (registerInfoExcel.getSdSalesordervalidity() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getSdSalesordervalidity().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setSdSalesordervalidity(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getPpManualpocreationratio() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getPpManualpocreationratio().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setPpManualpocreationratio(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getPpDeliveredunreportedratio() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getPpDeliveredunreportedratio().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setPpDeliveredunreportedratio(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getMesLateworkreportingrate() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getMesLateworkreportingrate().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setMesLateworkreportingrate(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getQmExternalinspectiondelay() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getQmExternalinspectiondelay().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setQmExternalinspectiondelay(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getMmPurchaseorderlatedelivery() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getMmPurchaseorderlatedelivery().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setMmPurchaseorderlatedelivery(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getMmManualpocreation() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getMmManualpocreation().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setMmManualpocreation(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getMmUnsettledpurchaserequests() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getMmUnsettledpurchaserequests().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setMmUnsettledpurchaserequests(new BigDecimal(replace));
            }

        }
        if (registerInfoExcel.getFicoMonthlystandardpricevariation() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getFicoMonthlystandardpricevariation().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                replace = replace.replaceAll("\\u00A0", "");
                replace = replace.replaceAll("\\s+", "");
                // replace转换为BigDecimal
                log.info("转换后" + replace);
                management.setFicoMonthlystandardpricevariation(new BigDecimal(replace));
            }

        }
        if (registerInfoExcel.getCrossMonthProductionOrders() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getCrossMonthProductionOrders().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setCrossMonthProductionOrders(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getPmLatemaintenanceordercompletion() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getPmLatemaintenanceordercompletion().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setPmLatemaintenanceordercompletion(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getIndicator1() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getIndicator1().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setIndicator1(new BigDecimal(replace));
            }
        }
        if (registerInfoExcel.getIndicator2() != null) {
            // 去掉百分号
            String replace = registerInfoExcel.getIndicator2().replace("%", "");
            if (!replace.isEmpty()) {
                replace = replace.replace("≤", "");
                // replace转换为BigDecimal
                management.setIndicator2(new BigDecimal(replace));
            }
        }

        log.info("数据转换完成" + management);


        // 如果转换后的数据都为null，则不进行写入
        if (management.getSdSalesordervalidity() == null && management.getPpManualpocreationratio() == null
                && management.getPpDeliveredunreportedratio() == null && management.getMesLateworkreportingrate() == null
                && management.getQmExternalinspectiondelay() == null && management.getMmPurchaseorderlatedelivery() == null
                && management.getMmManualpocreation() == null && management.getMmUnsettledpurchaserequests() == null
                && management.getFicoMonthlystandardpricevariation() == null && management.getCrossMonthProductionOrders() == null
                && management.getPmLatemaintenanceordercompletion() == null && management.getIndicator1() == null && management.getIndicator2() == null) {
            log.info("当前数据为空，不进行写入");
        }else {
            if (list.size() == 0) {
                managementMapper.insertEnterpriseManagementIndicatorsManagement(management);
            } else {
                management.setEmId(list.get(0).getEmId());
                managementMapper.updateEnterpriseManagementIndicatorsManagement(management);
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
    }

    private static final Map<String, Integer> digitMap = new HashMap<>();

    static {
        // 初始化映射表
        digitMap.put("零", 0);
        digitMap.put("一", 1);
        digitMap.put("二", 2);
        digitMap.put("三", 3);
        digitMap.put("四", 4);
        digitMap.put("五", 5);
        digitMap.put("六", 6);
        digitMap.put("七", 7);
        digitMap.put("八", 8);
        digitMap.put("九", 9);
        digitMap.put("十", 10);
        digitMap.put("十一", 11);
        digitMap.put("十二", 12);
    }

    public static int chineseToArabic(String chineseNumeral) {
        return digitMap.get(chineseNumeral);
    }

}


//
//    @Autowired
//    private EnterpriseManagementIndicatorsDailyClearingSettlementMapper dailyClearingMapper;
//    private Date yearAndMonth;
//
//
//    private List<EnterpriseManagementIndicatorsDailyClearingSettlement> cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//
//    public DailyClearingTableListener(EnterpriseManagementIndicatorsDailyClearingSettlementMapper dailyClearingMapper, Date yearAndMonth) {
//        this.dailyClearingMapper = dailyClearingMapper;
//        this.yearAndMonth = yearAndMonth;
//    }
//
//    /**
//     * 批量读取Excel写入DB
//     *
//     * @param registerInfoExcel 全局监听内容
//     * @param analysisContext   读取到的Excel内容
//     */
//    @Override
//    public void invoke(EnterpriseManagementDailyClearingReadEntity registerInfoExcel, AnalysisContext analysisContext) {
//        log.info("当前读取的数据为:" + registerInfoExcel);
//
//        // 数据处理，将表格数据转化为实体数据
//        EnterpriseManagementIndicatorsDailyClearingSettlement dailyClear = new EnterpriseManagementIndicatorsDailyClearingSettlement();
//
//
//        // 时间转换
//        if (Objects.equals(registerInfoExcel.getYearAndMonth(), "盘锦目值")) {
//            dailyClear.setYearAndMonth(yearAndMonth);
//        } else {
//            String month = registerInfoExcel.getYearAndMonth().substring(0, registerInfoExcel.getYearAndMonth().indexOf("月"));
//            int monthToInt = Integer.parseInt(month);
//            log.info("当前读取的数据为:" + monthToInt);
//            //将date转化为localDateTime
//            LocalDateTime localDateTime = yearAndMonth.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//            //利用localDateTime工具得到当前月份的后三个月个月，并且重新转化为Date类型
//            Date from = Date.from(localDateTime.plusMonths(monthToInt - 1).atZone(ZoneId.systemDefault()).toInstant());
//            dailyClear.setYearAndMonth(from);
//        }
//
//        // 标识符转换
//        if (Objects.equals(registerInfoExcel.getValue(), "目标值")) {
//            dailyClear.setFlag(1);
//        } else if (Objects.equals(registerInfoExcel.getValue(), "实际值")) {
//            dailyClear.setFlag(2);
//        } else if (Objects.equals(registerInfoExcel.getValue(), "得分")) {
//            dailyClear.setFlag(3);
//        }
//
//        // 通过前两个条件筛选，判断数据是否已经存在
//        List<EnterpriseManagementIndicatorsDailyClearingSettlement> list = dailyClearingMapper.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(dailyClear);
//
//
//        // 数据转换
//        if (registerInfoExcel.getOrderEntryDelayRatio() != null) {
//            // 去掉百分号
//            String replace = registerInfoExcel.getOrderEntryDelayRatio().replace("%", "");
//            // replace转换为BigDecimal
//            dailyClear.setOrderEntryDelayRatio(new BigDecimal(replace));
//        }
//        if (registerInfoExcel.getShipmentDelayRatio() != null) {
//            // 去掉百分号
//            String replace = registerInfoExcel.getShipmentDelayRatio().replace("%", "");
//            // replace转换为BigDecimal
//            dailyClear.setShipmentDelayRatio(new BigDecimal(replace));
//        }
//        if (registerInfoExcel.getProductionReportDelayRatio() != null) {
//            // 去掉百分号
//            String replace = registerInfoExcel.getProductionReportDelayRatio().replace("%", "");
//            // replace转换为BigDecimal
//            dailyClear.setProductionReportDelayRatio(new BigDecimal(replace));
//        }
//        if (registerInfoExcel.getInspectionDelayRate() != null) {
//            // 去掉百分号
//            String replace = registerInfoExcel.getInspectionDelayRate().replace("%", "");
//            // replace转换为BigDecimal
//            dailyClear.setInspectionDelayRate(new BigDecimal(replace));
//        }
//        if (registerInfoExcel.getInvoicePostingDelayRate() != null) {
//            // 去掉百分号
//            String replace = registerInfoExcel.getInvoicePostingDelayRate().replace("%", "");
//            // replace转换为BigDecimal
//            dailyClear.setInvoicePostingDelayRate(new BigDecimal(replace));
//        }
//        if (registerInfoExcel.getUnsettledAccountsRatio() != null) {
//            // 去掉百分号
//            String replace = registerInfoExcel.getUnsettledAccountsRatio().replace("%", "");
//            // replace转换为BigDecimal
//            dailyClear.setUnsettledAccountsRatio(new BigDecimal(replace));
//        }
//
//        log.info("当前转换后的值为:" + dailyClear);
//
//        // 如果转换后的数据都为null，则不进行写入
//        if (dailyClear.getOrderEntryDelayRatio() == null && dailyClear.getShipmentDelayRatio() == null
//                && dailyClear.getProductionReportDelayRatio() == null && dailyClear.getInspectionDelayRate() == null
//                && dailyClear.getInvoicePostingDelayRate() == null && dailyClear.getUnsettledAccountsRatio() == null) {
//            log.info("当前数据为空，不进行写入");
//        } else {
//            if (list.size() == 0) {
//                dailyClearingMapper.insertEnterpriseManagementIndicatorsDailyClearingSettlement(dailyClear);
//            } else {
//                dailyClear.setEdId(list.get(0).getEdId());
//                dailyClearingMapper.updateEnterpriseManagementIndicatorsDailyClearingSettlement(dailyClear);
//            }
//        }
//
//
//    }
//
//
//    /**
//     * 处理结尾, 不足100条
//     *
//     * @param analysisContext 全局监听内容
//     */
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        saveToDB();
//        log.info("所有数据解析完成");
//    }
//
//
//    /**
//     * 将读取到的内容写入DB
//     */
//    private void saveToDB() {
//        log.info("开始写入数据库");
////        enterpriseManagementSalaryTableMapper.batchInsertSalaryTable(cacheDataList);
//    }
//}
