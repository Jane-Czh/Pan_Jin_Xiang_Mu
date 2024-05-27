package com.heli.quality.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.quality.domain.QualityCountNumberEntity;
import com.heli.quality.domain.QualityIndicatorsMetrics;
import com.heli.quality.listener.AfterSalesTableListener;
import com.heli.quality.mapper.QualityIndicatorsMetricsMapper;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.quality.mapper.QualityAfterSalesRecordMapper;
import com.heli.quality.domain.QualityAfterSalesRecord;
import com.heli.quality.service.IQualityAfterSalesRecordService;

/**
 * 售后台账部分字段Service业务层处理
 *
 * @author hong
 * @date 2024-05-16
 */
@Service
public class QualityAfterSalesRecordServiceImpl implements IQualityAfterSalesRecordService {
    @Autowired
    private QualityAfterSalesRecordMapper qualityAfterSalesRecordMapper;
    @Autowired
    private QualityIndicatorsMetricsMapper qualityIndicatorsMetricsMapper;

    private static final Logger log = LoggerFactory.getLogger(QualityAfterSalesRecordServiceImpl.class);


    @Override
    public R<String> readSalesAfterExcelToDB(String fileName, InputStream inputStream) {
        try {
            // 读取文件内容
            log.info("开始读取文件: {}", fileName);
            EasyExcel.read(inputStream, QualityAfterSalesRecord.class, new AfterSalesTableListener(qualityAfterSalesRecordMapper)).sheet(0).doRead();
            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,您需要售后台账,当前上传的文件为：" + fileName);
        }
    }


    /**
     * @author: hong
     * @date: 2024/5/27 13:15
     * @description: 售后台账计算,从给定的月份开始计算
     * @param: startTime
     */
    public void calculateAfterSalesRecord() {
//
//        //检查数据是否齐全
//
//
//        List<QualityCountNumberEntity> monthlyRecallCount = qualityAfterSalesRecordMapper.selectMonthlyRecallCount();
//
//        List<QualityCountNumberEntity> productionLiabilityAfterSalesIssues = qualityAfterSalesRecordMapper.selectProductionLiabilityAfterSalesIssues();
//
//        List<QualityCountNumberEntity> monthlyNewCarFeedbackCount = qualityAfterSalesRecordMapper.selectMonthlyNewCarFeedbackCount();
//
//        log.info("monthlyRecallCount: {}", monthlyRecallCount);
//        log.info("productionLiabilityAfterSalesIssues: {}", productionLiabilityAfterSalesIssues);
//        log.info("monthlyFeedbackCount: {}", monthlyNewCarFeedbackCount);
//
//        ArrayList<QualityIndicatorsMetrics> metricsArrayList = new ArrayList<>();
//
//        for (int i = 0; i < monthlyRecallCount.size(); i++) {
//            //查询截至当月在保车车辆数
//            BigDecimal warrantyVehicles = qualityAfterSalesRecordMapper.selectInWarrantyVehicles(DateUtils.getInWarrantyTime(monthlyRecallCount.get(i).getMonths()), monthlyRecallCount.get(i).getMonths());
//
//            log.info("截至当月在保车车辆数" + warrantyVehicles);
//
////            log.info("开始时间" + DateUtils.getInWarrantyTime(monthlyRecallCount.get(i).getMonths())+"结束时间"+monthlyRecallCount.get(i).getMonths());
//
//
//            QualityIndicatorsMetrics qualityIndicatorsMetrics = new QualityIndicatorsMetrics();
//            //年月
//            qualityIndicatorsMetrics.setYearAndMonth(monthlyRecallCount.get(i).getMonths());
//            //新车病车数
//            qualityIndicatorsMetrics.setNewCarDefects(monthlyNewCarFeedbackCount.get(i).getTotalNumber());
//            //生产责任次数
//            qualityIndicatorsMetrics.setProductionLiabilityIssues(productionLiabilityAfterSalesIssues.get(i).getTotalNumber());
//            //月度售后质量问题总数
//            qualityIndicatorsMetrics.setMonthlyAfterSalesIssues(monthlyRecallCount.get(i).getTotalNumber());
//            //三包期内新车返修率
//            qualityIndicatorsMetrics.setWarrantyRepairRate(new BigDecimal(monthlyNewCarFeedbackCount.get(i).getTotalNumber()).divide(warrantyVehicles, 2, BigDecimal.ROUND_HALF_UP));
//            //三包期内整车月度返修率
//            qualityIndicatorsMetrics.setWarrantyVehicleRepairRate(new BigDecimal(monthlyRecallCount.get(i).getTotalNumber()).divide(warrantyVehicles, 2, BigDecimal.ROUND_HALF_UP));
//
//            //外部质量损失率
//            //主营业务收入
//            BigDecimal mainRevenue = qualityAfterSalesRecordMapper.selectMainRevenue(monthlyRecallCount.get(i).getMonths());
//            //当月质量损失
//            BigDecimal externalMassLossAmount = qualityAfterSalesRecordMapper.selectMoleculeExternalMassLossRate(monthlyRecallCount.get(i).getMonths());
//            qualityIndicatorsMetrics.setExternalLossRate(externalMassLossAmount.divide(mainRevenue, 2, BigDecimal.ROUND_HALF_UP));
//
//
//            log.info(String.valueOf(qualityIndicatorsMetrics));
//
//            metricsArrayList.add(qualityIndicatorsMetrics);
//
//
//        }
//        // 计算完成后，批量插入数据库
//        qualityIndicatorsMetricsMapper.batchInsertMetrics(metricsArrayList);
//
//
//
//
    }

    /**
     * @author: hong
     * @date: 2024/5/27 13:15
     * @description: 质量科-售后台账表统计
     * @param: startTime
     */
    public void statisticsAfterSalesTable(Date date) {

        List<QualityCountNumberEntity> monthlyRecallCount = qualityAfterSalesRecordMapper.selectMonthlyRecallCount(date);

        List<QualityCountNumberEntity> productionLiabilityAfterSalesIssues = qualityAfterSalesRecordMapper.selectProductionLiabilityAfterSalesIssues(date);

        List<QualityCountNumberEntity> monthlyNewCarFeedbackCount = qualityAfterSalesRecordMapper.selectMonthlyNewCarFeedbackCount(date);

        log.info("monthlyRecallCount: {}", monthlyRecallCount);
        log.info("productionLiabilityAfterSalesIssues: {}", productionLiabilityAfterSalesIssues);
        log.info("monthlyFeedbackCount: {}", monthlyNewCarFeedbackCount);

        ArrayList<QualityIndicatorsMetrics> metricsArrayList = new ArrayList<>();

        for (int i = 0; i < monthlyRecallCount.size(); i++) {

            QualityIndicatorsMetrics qualityIndicatorsMetrics = new QualityIndicatorsMetrics();
            //年月
            qualityIndicatorsMetrics.setYearAndMonth(monthlyRecallCount.get(i).getMonths());
            //新车病车数
            qualityIndicatorsMetrics.setNewCarDefects(monthlyNewCarFeedbackCount.get(i).getTotalNumber());
            //生产责任次数
            qualityIndicatorsMetrics.setProductionLiabilityIssues(productionLiabilityAfterSalesIssues.get(i).getTotalNumber());
            //月度售后质量问题总数
            qualityIndicatorsMetrics.setMonthlyAfterSalesIssues(monthlyRecallCount.get(i).getTotalNumber());

            log.info(String.valueOf(qualityIndicatorsMetrics));

            metricsArrayList.add(qualityIndicatorsMetrics);

        }
        // 统计完成后，批量插入数据库
        qualityIndicatorsMetricsMapper.batchInsertMetrics(metricsArrayList);

    }



    /**
     * @return
     * @author: hong
     * @date: 2024/5/27 13:15
     * @description: 质量科指标计算
     * @param: startTime
     */
    public R<String> calculateQualityMetrics(Date date) {

        //检查数据是否齐全
        //1、财务科-填报-整车销量，date之前十二个月数据是否齐全
        List<Date> dates = qualityAfterSalesRecordMapper.checkInWarrantyVehiclesIsExisted(DateUtils.getInWarrantyTime(date), date);
        if (dates.size() != 12){
            Date d = DateUtils.getInWarrantyTime(date);
            //遍历dates，找到缺少的月份
            for (int i = 0; i < dates.size(); i++, d = DateUtils.getNextMonth(d)){
                if (!dates.get(i).equals(d)){
                    log.info("缺少" + d + "数据");
                    return R.fail(dates.get(i) + "月在保车辆数据未上传");
                }
            }

        }

        //2、财务科-利润表-主营业务收入，当月数据是否存在
        if (!qualityAfterSalesRecordMapper.checkMainRevenueIsExisted(date)){
            log.info(date + "月利润表数据未上传");
            return R.fail(date + "月利润表数据未上传");
        }

        //3、质量科-填报-外部质量损失，当月数据是否存在
        if (!qualityAfterSalesRecordMapper.checkMoleculeExternalMassLossRateIsExisted(date)){
            log.info(date + "月外部质量损失率数据未上传");
            return R.fail(date + "月外部质量损失率数据未上传");
        }

        //4、质量科-售后表，当月表格是否上传
        if (!qualityAfterSalesRecordMapper.checkAfterSalesTableIsExisted(date)){
            log.info(date + "月售后台账数据未上传");
            return R.fail(date + "月售后台账数据未上传");
        }


        //数据齐全，开始计算
        ArrayList<QualityIndicatorsMetrics> metricsArrayList = new ArrayList<>();


        QualityIndicatorsMetrics qualityIndicatorsMetrics = qualityIndicatorsMetricsMapper.selectQualityIndicatorsMetricsByMonth(date);

        //截止当月外部在保车数
        BigDecimal warrantyVehicles = qualityAfterSalesRecordMapper.selectInWarrantyVehicles(DateUtils.getInWarrantyTime(date), date);

        //1、三包期内新车返修率
        qualityIndicatorsMetrics.setWarrantyRepairRate(new BigDecimal(qualityIndicatorsMetrics.getNewCarDefects()).divide(warrantyVehicles, 2, BigDecimal.ROUND_HALF_UP));

        //2、三包期内整车月度返修率
        qualityIndicatorsMetrics.setWarrantyVehicleRepairRate(new BigDecimal(qualityIndicatorsMetrics.getMonthlyAfterSalesIssues()).divide(warrantyVehicles, 2, BigDecimal.ROUND_HALF_UP));

        //3、外部质量损失率
        //主营业务收入
        BigDecimal mainRevenue = qualityAfterSalesRecordMapper.selectMainRevenue(date);
        //当月质量损失
        BigDecimal externalMassLossAmount = qualityAfterSalesRecordMapper.selectMoleculeExternalMassLossRate(date);
        qualityIndicatorsMetrics.setExternalLossRate(externalMassLossAmount.divide(mainRevenue, 2, BigDecimal.ROUND_HALF_UP));


        log.info(String.valueOf(qualityIndicatorsMetrics));


        metricsArrayList.add(qualityIndicatorsMetrics);
        // 计算完成后，批量插入数据库
        qualityIndicatorsMetricsMapper.batchInsertMetrics(metricsArrayList);

        return R.ok();

    }


    /**
     * 查询售后台账部分字段
     *
     * @param qaId 售后台账部分字段主键
     * @return 售后台账部分字段
     */
    @Override
    public QualityAfterSalesRecord selectQualityAfterSalesRecordByQaId(Long qaId) {
        return qualityAfterSalesRecordMapper.selectQualityAfterSalesRecordByQaId(qaId);
    }

    /**
     * 查询售后台账部分字段列表
     *
     * @param qualityAfterSalesRecord 售后台账部分字段
     * @return 售后台账部分字段
     */
    @Override
    public List<QualityAfterSalesRecord> selectQualityAfterSalesRecordList(QualityAfterSalesRecord qualityAfterSalesRecord) {
        return qualityAfterSalesRecordMapper.selectQualityAfterSalesRecordList(qualityAfterSalesRecord);
    }

    /**
     * 新增售后台账部分字段
     *
     * @param qualityAfterSalesRecord 售后台账部分字段
     * @return 结果
     */
    @Override
    public int insertQualityAfterSalesRecord(QualityAfterSalesRecord qualityAfterSalesRecord) {
        qualityAfterSalesRecord.setCreateTime(DateUtils.getNowDate());
        return qualityAfterSalesRecordMapper.insertQualityAfterSalesRecord(qualityAfterSalesRecord);
    }

    /**
     * 修改售后台账部分字段
     *
     * @param qualityAfterSalesRecord 售后台账部分字段
     * @return 结果
     */
    @Override
    public int updateQualityAfterSalesRecord(QualityAfterSalesRecord qualityAfterSalesRecord) {
        qualityAfterSalesRecord.setUpdateTime(DateUtils.getNowDate());
        return qualityAfterSalesRecordMapper.updateQualityAfterSalesRecord(qualityAfterSalesRecord);
    }

    /**
     * 批量删除售后台账部分字段
     *
     * @param qaIds 需要删除的售后台账部分字段主键
     * @return 结果
     */
    @Override
    public int deleteQualityAfterSalesRecordByQaIds(Long[] qaIds) {
        return qualityAfterSalesRecordMapper.deleteQualityAfterSalesRecordByQaIds(qaIds);
    }

    /**
     * 删除售后台账部分字段信息
     *
     * @param qaId 售后台账部分字段主键
     * @return 结果
     */
    @Override
    public int deleteQualityAfterSalesRecordByQaId(Long qaId) {
        return qualityAfterSalesRecordMapper.deleteQualityAfterSalesRecordByQaId(qaId);
    }

}
