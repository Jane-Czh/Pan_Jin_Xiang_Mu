package com.ruoyi.product.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.domain.MarketFunctionQuickReport;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.SplitDate;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProductionFunctionStatisticsOfPlanCompletionStatusMapper;
import com.ruoyi.product.domain.ProductionFunctionStatisticsOfPlanCompletionStatus;
import com.ruoyi.product.service.IProductionFunctionStatisticsOfPlanCompletionStatusService;

/**
 * 计划完成情况Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
@Service
public class ProductionFunctionStatisticsOfPlanCompletionStatusServiceImpl implements IProductionFunctionStatisticsOfPlanCompletionStatusService 
{
    @Autowired
    private ProductionFunctionStatisticsOfPlanCompletionStatusMapper productionFunctionStatisticsOfPlanCompletionStatusMapper;

    @Override
    public void Synchronization(List<MarketCommercialVehicleTable> list, ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus1) {
            int count1 = 0;
            int count2 = 0;
            int totaltotalNumberWaitingTimeForProduction = 0;
            int totaltotalNumberCompletionPeriodOfDoorFrameAssembly = 0;
            int totaltotalNumberTrialCompletionPeriod = 0;
            int totaltotalNumberSpecialOperations = 0;
            int tolaltotalNumberlauncheddate = 0;
            int totaltotalNumberPrecisionCompletionPeriod = 0;
            HashMap<String, ProductionFunctionStatisticsOfPlanCompletionStatus> result = new HashMap<String, ProductionFunctionStatisticsOfPlanCompletionStatus>();
            // 获取当前日期
            Date currentDate = new Date();
            System.out.println(currentDate);  // 输出当前日期和时间

            // 遍历列表中的数据进行统计
            while (count2 < list.size()){
                MarketCommercialVehicleTable marketCommercialVehicleTable = list.get(count2);
                // 获取车体上线日期
                Date vehicleLaunchDate = marketCommercialVehicleTable.getVehicleLaunchDate();
                if (vehicleLaunchDate!=null) {
                    if (SplitDate.splitDate(vehicleLaunchDate)[0] == SplitDate.splitDate(currentDate)[0] && SplitDate.splitDate(vehicleLaunchDate)[1] == SplitDate.splitDate(currentDate)[1] && SplitDate.splitDate(vehicleLaunchDate)[2] == SplitDate.splitDate(currentDate)[2]) {
                        totaltotalNumberWaitingTimeForProduction++;
                    }
                }

                // 获取门架合装完工期
                Date completionPeriodOfDoorFrameAssembly = marketCommercialVehicleTable.getCompletionPeriodOfDoorFrameAssembly();
                if (completionPeriodOfDoorFrameAssembly!=null) {
                    if (SplitDate.splitDate(completionPeriodOfDoorFrameAssembly)[0] == SplitDate.splitDate(currentDate)[0] && SplitDate.splitDate(completionPeriodOfDoorFrameAssembly)[1] == SplitDate.splitDate(currentDate)[1] && SplitDate.splitDate(completionPeriodOfDoorFrameAssembly)[2] == SplitDate.splitDate(currentDate)[2]) {
                        totaltotalNumberCompletionPeriodOfDoorFrameAssembly++;
                    }
                }

                // 获取试车完工期
                Date trialCompletionPeriod = marketCommercialVehicleTable.getTrialCompletionPeriod();
                if (trialCompletionPeriod!=null) {
                    if (SplitDate.splitDate(trialCompletionPeriod)[0] == SplitDate.splitDate(currentDate)[0] && SplitDate.splitDate(trialCompletionPeriod)[1] == SplitDate.splitDate(currentDate)[1] && SplitDate.splitDate(trialCompletionPeriod)[2] == SplitDate.splitDate(currentDate)[2]) {
                        totaltotalNumberTrialCompletionPeriod++;
                    }
                }


  /*          // 获取特种作业*/
                String specialOperations = marketCommercialVehicleTable.getSpecialOperations();
                if (specialOperations!=null){
                    totaltotalNumberSpecialOperations++;
                }


                // 获取精整完工期
                Date precisionCompletionPeriod = marketCommercialVehicleTable.getPrecisionCompletionPeriod();
                if (precisionCompletionPeriod!=null) {
                    if (SplitDate.splitDate(precisionCompletionPeriod)[0] == SplitDate.splitDate(currentDate)[0] && SplitDate.splitDate(precisionCompletionPeriod)[1] == SplitDate.splitDate(currentDate)[1] && SplitDate.splitDate(precisionCompletionPeriod)[2] == SplitDate.splitDate(currentDate)[2]) {
                        totaltotalNumberPrecisionCompletionPeriod++;
                    }
                }

                count2++;
                //获取上线日期
                Date launchDate = marketCommercialVehicleTable.getLaunchDate();
                if(vehicleLaunchDate!=null){
                    if ((vehicleLaunchDate.getTime()-launchDate.getTime())!=0){
                    tolaltotalNumberlauncheddate++;
                    }
                }

            }
            System.out.println(tolaltotalNumberlauncheddate);
            System.out.println(list.size());
            float rate = ((float) tolaltotalNumberlauncheddate / list.size())*100;
            System.out.println(rate);

            while (count1 < list.size()){
                MarketCommercialVehicleTable marketCommercialVehicleTable = list.get(count1);
                String carNumber = marketCommercialVehicleTable.getCarNumber();//车号
                Date AcceptanceDate = marketCommercialVehicleTable.getAcceptanceDate(); //接单日期
                Date LaunchDate = marketCommercialVehicleTable.getLaunchDate(); //上线日期
                Date vehicleLaunchDate = marketCommercialVehicleTable.getVehicleLaunchDate();//车体上线日期
                Date PlannedCompletionPeriod = marketCommercialVehicleTable.getPlannedCompletionPeriod(); //计划完成期，
                Date PrecisionCompletionPeriod = marketCommercialVehicleTable.getPrecisionCompletionPeriod(); //精整完工期


                //hash中没有当前车号数据，新建一个MarketStatisticsCompletionStatus类插入其中，分别给出其初始值
                if (result.get(carNumber) == null){
                    ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus = new ProductionFunctionStatisticsOfPlanCompletionStatus();
                    /*新建车号并将相关数据从商品车台账表导入到市场科计划完成情况统计表*/
                    productionFunctionStatisticsOfPlanCompletionStatus.setCarNumber(carNumber);
                    productionFunctionStatisticsOfPlanCompletionStatus.setAcceptanceDate(AcceptanceDate);
                    productionFunctionStatisticsOfPlanCompletionStatus.setLaunchDate(LaunchDate);
                    productionFunctionStatisticsOfPlanCompletionStatus.setVehicleLaunchDate(vehicleLaunchDate);
                    productionFunctionStatisticsOfPlanCompletionStatus.setPlannedCompletionPeriod(PlannedCompletionPeriod);
                    productionFunctionStatisticsOfPlanCompletionStatus.setPrecisionCompletionPeriod(PrecisionCompletionPeriod);
                    productionFunctionStatisticsOfPlanCompletionStatus.setTotalNumberWaitingTimeForProduction((long) totaltotalNumberWaitingTimeForProduction);
                    productionFunctionStatisticsOfPlanCompletionStatus.setTotalNumberCompletionPeriodOfDoorFrameAssembly((long) totaltotalNumberCompletionPeriodOfDoorFrameAssembly);
                    productionFunctionStatisticsOfPlanCompletionStatus.setTotalNumberTrialCompletionPeriod((long) totaltotalNumberTrialCompletionPeriod);
                    productionFunctionStatisticsOfPlanCompletionStatus.setTotalNumberSpecialOperations((long) totaltotalNumberSpecialOperations);
                    productionFunctionStatisticsOfPlanCompletionStatus.setTotalNumberPrecisionCompletionPeriod((long) totaltotalNumberPrecisionCompletionPeriod);
                    productionFunctionStatisticsOfPlanCompletionStatus.setPlanRate((long) rate);
                    // 新增计算待排产时长
                    if (LaunchDate != null && AcceptanceDate != null&&LaunchDate.getTime() - AcceptanceDate.getTime()>0) {
                        long waitTimeForProduction = (LaunchDate.getTime() - AcceptanceDate.getTime())/ (24 * 60 * 60 * 1000);
                        productionFunctionStatisticsOfPlanCompletionStatus.setWaitingTimeForProduction(waitTimeForProduction);
                    }
                    // 新增计算生产周期
                    if (PlannedCompletionPeriod != null && LaunchDate != null&&PlannedCompletionPeriod.getTime() - LaunchDate.getTime()>0) {
                        long productionCycle = (PlannedCompletionPeriod.getTime() - LaunchDate.getTime())/ (24 * 60 * 60 * 1000);
                        productionFunctionStatisticsOfPlanCompletionStatus.setProductionCycle(String.valueOf(productionCycle));
                    }
                    // 新增统计超期天数
                    if (PrecisionCompletionPeriod != null && PlannedCompletionPeriod != null) {
                        if (PrecisionCompletionPeriod.before(PlannedCompletionPeriod)) {
                            // 精准完工期早于计划完工期，按计划完工
                            productionFunctionStatisticsOfPlanCompletionStatus.setOverdueDays(0L);
                        } else {
                            // 精准完工期晚于计划完工期，统计超期天数
                            long exceedingDays = (PrecisionCompletionPeriod.getTime() - PlannedCompletionPeriod.getTime()) / (24 * 60 * 60 * 1000);
                            productionFunctionStatisticsOfPlanCompletionStatus.setOverdueDays(exceedingDays);
                        }
                    }
                    result.put(carNumber, productionFunctionStatisticsOfPlanCompletionStatus);
                }else
                {System.out.println("车号已存在");}

                count1++;
            }
        //先删除数据库中所有快报
        List<ProductionFunctionStatisticsOfPlanCompletionStatus> list1 = selectProductionFunctionStatisticsOfPlanCompletionStatusList(productionFunctionStatisticsOfPlanCompletionStatus1);
        if (list1 != null&& list1.size()>0) {
            int x = 0;
            Long[] deleteId = new Long[list1.size()];
            while (x < list1.size()) {
                ProductionFunctionStatisticsOfPlanCompletionStatus value = list1.get(x);
                deleteId[x] = value.getMpcId();
                x++;
            }
            deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcIds(deleteId);
            System.out.println("删除成功");
        }
        //依次插入新生成的快报
//        Long id = 0L;
//        for (Map.Entry<String, ProductionFunctionStatisticsOfPlanCompletionStatus> entry : result.entrySet()){
//            System.out.println("-------------------");
//            ProductionFunctionStatisticsOfPlanCompletionStatus insertValue = entry.getValue();
//            insertValue.setMpcId(GenerateId.getNextId(id));
//            insertProductionFunctionStatisticsOfPlanCompletionStatus(insertValue);
//            id++;
//            System.out.println("插入成功");
//        }
        // 分批处理数据，每批2000条
        int batchSize = 4000;
        int totalSize = list1.size();
        for (int i = 0; i < totalSize; i += batchSize) {
            int end = Math.min(i + batchSize, totalSize);
            List<ProductionFunctionStatisticsOfPlanCompletionStatus> batchLedgers = list1.subList(i, end);

            // 过滤掉车号为null的数据
            batchLedgers.removeIf(ledger -> ledger.getCarNumber() == null);

            // 批量插入数据
            if (!batchLedgers.isEmpty()) {
                productionFunctionStatisticsOfPlanCompletionStatusMapper.insertBatch(batchLedgers);
            }
        }


        }


    /**
     * 查询计划完成情况
     * 
     * @param mpcId 计划完成情况主键
     * @return 计划完成情况
     */
    @Override
    public ProductionFunctionStatisticsOfPlanCompletionStatus selectProductionFunctionStatisticsOfPlanCompletionStatusByMpcId(Long mpcId)
    {
        return productionFunctionStatisticsOfPlanCompletionStatusMapper.selectProductionFunctionStatisticsOfPlanCompletionStatusByMpcId(mpcId);
    }

    /**
     * 查询计划完成情况列表
     * 
     * @param productionFunctionStatisticsOfPlanCompletionStatus 计划完成情况
     * @return 计划完成情况
     */
    @Override
    public List<ProductionFunctionStatisticsOfPlanCompletionStatus> selectProductionFunctionStatisticsOfPlanCompletionStatusList(ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus)
    {
        return productionFunctionStatisticsOfPlanCompletionStatusMapper.selectProductionFunctionStatisticsOfPlanCompletionStatusList(productionFunctionStatisticsOfPlanCompletionStatus);
    }

    /**
     * 新增计划完成情况
     * 
     * @param productionFunctionStatisticsOfPlanCompletionStatus 计划完成情况
     * @return 结果
     */
    @Override
    public int insertProductionFunctionStatisticsOfPlanCompletionStatus(ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus)
    {
        return productionFunctionStatisticsOfPlanCompletionStatusMapper.insertProductionFunctionStatisticsOfPlanCompletionStatus(productionFunctionStatisticsOfPlanCompletionStatus);
    }

    /**
     * 修改计划完成情况
     * 
     * @param productionFunctionStatisticsOfPlanCompletionStatus 计划完成情况
     * @return 结果
     */
    @Override
    public int updateProductionFunctionStatisticsOfPlanCompletionStatus(ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus)
    {
        return productionFunctionStatisticsOfPlanCompletionStatusMapper.updateProductionFunctionStatisticsOfPlanCompletionStatus(productionFunctionStatisticsOfPlanCompletionStatus);
    }

    /**
     * 批量删除计划完成情况
     * 
     * @param mpcIds 需要删除的计划完成情况主键
     * @return 结果
     */
    @Override
    public int deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcIds(Long[] mpcIds)
    {
        return productionFunctionStatisticsOfPlanCompletionStatusMapper.deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcIds(mpcIds);
    }

    /**
     * 删除计划完成情况信息
     * 
     * @param mpcId 计划完成情况主键
     * @return 结果
     */
    @Override
    public int deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcId(Long mpcId)
    {
        return productionFunctionStatisticsOfPlanCompletionStatusMapper.deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcId(mpcId);
    }
}
