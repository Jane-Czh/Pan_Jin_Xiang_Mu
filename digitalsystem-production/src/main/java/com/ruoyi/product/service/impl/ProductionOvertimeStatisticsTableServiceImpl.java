package com.ruoyi.product.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.domain.MarketFunctionComparisonDeliverydays;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.SplitDate;
import com.ruoyi.market.utils.getTime;
import com.ruoyi.product.domain.ProductionClockInForm;
import com.ruoyi.product.domain.ProductionFunctionStatisticsOfPlanCompletionStatus;
import com.ruoyi.product.domain.ProductionOvertimeApplicationForm;
import com.ruoyi.product.utils.HolidayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProductionOvertimeStatisticsTableMapper;
import com.ruoyi.product.domain.ProductionOvertimeStatisticsTable;
import com.ruoyi.product.service.IProductionOvertimeStatisticsTableService;

/**
 * 加班统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
@Service
public class ProductionOvertimeStatisticsTableServiceImpl implements IProductionOvertimeStatisticsTableService 
{
    @Autowired
    private ProductionOvertimeStatisticsTableMapper productionOvertimeStatisticsTableMapper;

    /**
     * 查询加班统计
     * 
     * @param postId 加班统计主键
     * @return 加班统计
     */
    @Override
    public ProductionOvertimeStatisticsTable selectProductionOvertimeStatisticsTableByPostId(Long postId)
    {
        return productionOvertimeStatisticsTableMapper.selectProductionOvertimeStatisticsTableByPostId(postId);
    }

    /**
     * 查询加班统计列表
     * 
     * @param productionOvertimeStatisticsTable 加班统计
     * @return 加班统计
     */
    @Override
    public List<ProductionOvertimeStatisticsTable> selectProductionOvertimeStatisticsTableList(ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable)
    {
        return productionOvertimeStatisticsTableMapper.selectProductionOvertimeStatisticsTableList(productionOvertimeStatisticsTable);
    }

    /**
     * 新增加班统计
     * 
     * @param productionOvertimeStatisticsTable 加班统计
     * @return 结果
     */
    @Override
    public int insertProductionOvertimeStatisticsTable(ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable)
    {
        return productionOvertimeStatisticsTableMapper.insertProductionOvertimeStatisticsTable(productionOvertimeStatisticsTable);
    }

    /**
     * 修改加班统计
     * 
     * @param productionOvertimeStatisticsTable 加班统计
     * @return 结果
     */
    @Override
    public int updateProductionOvertimeStatisticsTable(ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable)
    {
        return productionOvertimeStatisticsTableMapper.updateProductionOvertimeStatisticsTable(productionOvertimeStatisticsTable);
    }

    /**
     * 批量删除加班统计
     * 
     * @param postIds 需要删除的加班统计主键
     * @return 结果
     */
    @Override
    public int deleteProductionOvertimeStatisticsTableByPostIds(Long[] postIds)
    {
        return productionOvertimeStatisticsTableMapper.deleteProductionOvertimeStatisticsTableByPostIds(postIds);
    }

    /**
     * 删除加班统计信息
     * 
     * @param postId 加班统计主键
     * @return 结果
     */
    @Override
    public int deleteProductionOvertimeStatisticsTableByPostId(Long postId)
    {
        return productionOvertimeStatisticsTableMapper.deleteProductionOvertimeStatisticsTableByPostId(postId);
    }

    @Override
    public void Synchronization(List<ProductionClockInForm> list1, List<ProductionOvertimeApplicationForm> list2,ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable) throws ParseException {
        int count1 = 0;

//testAPI
//        String testdate1 = "2024-04-18";
//        String test1 = HolidayUtil.isWorkingDay(testdate1);
//        String testdate2 = "2024-06-09";
//        String test2 = HolidayUtil.isWorkingDay(testdate2);
//        String testdate3 = "2024-06-10";
//        String test3 = HolidayUtil.isWorkingDay(testdate3);
//        System.out.println("***********"+test1+"*****************");
//        System.out.println("***********"+test2+"*****************");
//        System.out.println("***********"+test3+"*****************");


        HashMap<String, ProductionOvertimeStatisticsTable> result = new HashMap<String, ProductionOvertimeStatisticsTable>();
//        Map<String, Map<String, ProductionOvertimeStatisticsTable>> attendanceMap = new HashMap<>();
//        Table<String,String,ProductionOvertimeStatisticsTable> table = HashBasedTable.create();

        // 获取当前日期
        LocalDateTime currentDateTime = LocalDateTime.now(); //获取当前时间.
        LocalDate currentDate = LocalDate.now();
        Date currentDateold = new Date();
        int year = getTime.getYear(currentDateTime);
        int month = getTime.getMonth(currentDateTime);
        int day = getTime.getDay(currentDateTime);

        long totalover = 0;

        //        先删除数据库中本月所统计的上个月数据
        List<ProductionOvertimeStatisticsTable> listnew = selectProductionOvertimeStatisticsTableList(productionOvertimeStatisticsTable);
        if (list1 != null&& listnew.size()>0){
            int x = 0;
            Long[] deleteId = new Long[listnew.size()];
            while (x < listnew.size()){
                ProductionOvertimeStatisticsTable value = listnew.get(x);
                if (SplitDate.splitDate(value.getCreateTime())[0] == year&&SplitDate.splitDate(value.getCreateTime())[1] == month)
                    deleteId[x] = value.getPostId();
                x++;
            }
            deleteProductionOvertimeStatisticsTableByPostIds(deleteId);
            System.out.println("删除成功");
        }

        while (count1<list1.size()){
            int count2 = 0;
            ProductionClockInForm productionClockInForm = list1.get(count1);
            //记录是否有加班申请
            int key = 0;
            String idnumber = productionClockInForm.getIdNumber();
            String name = productionClockInForm.getName();
            String gender = productionClockInForm.getGender();
            Date Firststart = productionClockInForm.getFirstTimeClockingInAtWork();
            //转换成String类型用于作为键
            String dateAsString = currentDate.toString();
            //获取第一次打卡年月日信息用于判断是否是节假日
            int Firststartyear = SplitDate.splitDate(Firststart)[0];
            int Firststartmonth = SplitDate.splitDate(Firststart)[1];
            int Firststartday = SplitDate.splitDate(Firststart)[2];
            String FirstStarttime = String.format("%04d-%02d-%02d", Firststartyear, Firststartmonth, Firststartday);
            //判断是否是节假日,节假日为2,周末为1,其他为0
            String ifovertime = HolidayUtil.isWorkingDay(FirstStarttime);
            if (ifovertime.equals("1")){
                ifovertime ="0";
            }



            Date Firstend = productionClockInForm.getFirstTimeClockingInAfterWork();
            Date Secondstart = productionClockInForm.getSecondTimeClockingInAtWork();
            Date Secondend = productionClockInForm.getSecondTimeClockingInAfterWork();
            Date Normalstart = productionClockInForm.getNormalWorkingHours();
            Date Normalend = productionClockInForm.getNormalClosingTime();
            while (count2<list2.size()){
                ProductionOvertimeApplicationForm productionOvertimeApplicationForm =list2.get(count2);
                String idnumberapp = productionOvertimeApplicationForm.getIdNumber();
                Date Firststartapp = productionOvertimeApplicationForm.getApplicationForOvertimeStartTime();
                // 打印每个判断条件的正确性
                boolean yearMatch = SplitDate.splitDate(Firststart)[0] == SplitDate.splitDate(Firststartapp)[0];
                boolean monthMatch = SplitDate.splitDate(Firststart)[1] == SplitDate.splitDate(Firststartapp)[1];
                boolean dayMatch = SplitDate.splitDate(Firststart)[2] == SplitDate.splitDate(Firststartapp)[2];

//                System.out.println("Year Match: " + yearMatch);
//                System.out.println(count2);
//                System.out.println(list2.size());
//                System.out.println("Month Match: " + monthMatch);
//                System.out.println("Day Match: " + dayMatch);
//                System.out.println(productionClockInForm);
//                System.out.println(productionOvertimeApplicationForm);
                if(idnumberapp.equals(idnumber)&&(SplitDate.splitDate(Firststart)[0]==SplitDate.splitDate(Firststartapp)[0])&&(SplitDate.splitDate(Firststart)[1]==SplitDate.splitDate(Firststartapp)[1])&&(SplitDate.splitDate(Firststart)[2]==SplitDate.splitDate(Firststartapp)[2])){
                    key=1;
                }
                System.out.println(SplitDate.splitDate(Firststart)[2]);
                System.out.println(SplitDate.splitDate(Firststartapp)[2]);
                count2++;
            }


            if(result.get(idnumber)==null){
                ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable1 = new ProductionOvertimeStatisticsTable();
                //新增数据
//                if(Firststart!=null&&Firstend!=null)
//                    productionOvertimeStatisticsTable1.set
                productionOvertimeStatisticsTable1.setCreateTime(currentDateold);//设置现在时间便于管理本月
                productionOvertimeStatisticsTable1.setName(name);
                productionOvertimeStatisticsTable1.setGender(gender);
                productionOvertimeStatisticsTable1.setIdNumber(idnumber);

                //判断是否出勤
                if(Firstend!=null){
                    productionOvertimeStatisticsTable1.setAttendanceSituation(Long.valueOf(1));
                    //计算加班时长
                    if (Secondend!=null&&key==1&&ifovertime.equals("0")){
                        totalover = (Secondend.getTime()-Normalend.getTime())/(1000 * 60 );
                        System.out.println("+++++++"+(Secondend.getTime()-Normalend.getTime())+"加班时长");
                        System.out.println("+++++++"+totalover+"加班时长");
                        if (productionOvertimeStatisticsTable1.getOvertimeDuration()==null){
                            productionOvertimeStatisticsTable1.setOvertimeDuration(0L);
                        }
                        productionOvertimeStatisticsTable1.setOvertimeDuration(productionOvertimeStatisticsTable1.getOvertimeDuration()+totalover+1);
                    }
                    if (Firstend!=null&&key==1&&ifovertime.equals("2")){
                        totalover = (Firstend.getTime()-Firststart.getTime())/(1000 * 60 );
                        if (productionOvertimeStatisticsTable1.getOvertimeDuration()==null){
                            productionOvertimeStatisticsTable1.setOvertimeDuration(0L);
                        }
                        productionOvertimeStatisticsTable1.setOvertimeDuration(productionOvertimeStatisticsTable1.getOvertimeDuration()+totalover+1);
                    }
                    if ((Secondend!=null||ifovertime.equals("2"))&&key==0){
                        productionOvertimeStatisticsTable1.setAbnormalSituation("未申请加班而有加班打卡记录");
                    }
                }
                //插入Table
                result.put(idnumber,productionOvertimeStatisticsTable1);
            }
            else {
                ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable1 = result.get(idnumber);
                //判断是否出勤
                if(Firstend!=null){
                    productionOvertimeStatisticsTable1.setAttendanceSituation(productionOvertimeStatisticsTable1.getAttendanceSituation());
                    //计算加班时长
                    if (Secondend!=null&&key==1&&ifovertime.equals("0")){
                        totalover = (Secondend.getTime()-Normalend.getTime())/(1000 * 60 );
                        if (productionOvertimeStatisticsTable1.getOvertimeDuration()==null){
                            productionOvertimeStatisticsTable1.setOvertimeDuration(0L);
                        }
                        productionOvertimeStatisticsTable1.setOvertimeDuration(productionOvertimeStatisticsTable1.getOvertimeDuration()+totalover+1);
                    }
                    if (Firstend!=null&&key==1&&ifovertime.equals("2")){
                        totalover = (Firstend.getTime()-Firststart.getTime())/(1000 * 60 );
                        if (productionOvertimeStatisticsTable1.getOvertimeDuration()==null){
                            productionOvertimeStatisticsTable1.setOvertimeDuration(0L);
                        }
                        productionOvertimeStatisticsTable1.setOvertimeDuration(productionOvertimeStatisticsTable1.getOvertimeDuration()+totalover+1);
                    }
                    if ((Secondend!=null||ifovertime.equals("2"))&&key==0){
                        productionOvertimeStatisticsTable1.setAbnormalSituation("未申请加班而有加班打卡记录");
                    }
                }
                //替换
                result.replace(idnumber,productionOvertimeStatisticsTable1);
            }

            count1++;
        }
        //依次插入新生成的加班情况
        Long id = 0L;
        for (Map.Entry<String, ProductionOvertimeStatisticsTable> entry : result.entrySet()){
            System.out.println("-------------------");
            ProductionOvertimeStatisticsTable insertValue = entry.getValue();
            insertValue.setPostId(GenerateId.getNextId(id));
            ProductionOvertimeStatisticsTable existingRecord = selectProductionOvertimeStatisticsTableByPostId(insertValue.getPostId());

            if (existingRecord != null) {
                // 处理已存在相同 mfcdId 的情况
                // 可以选择更新数据或者其他处理方式
                // 如果已存在相同 mfcdId 的数据，则向数据库中插入新数据
                Long lastId = selectLastId();// 获取一个新的唯一 mfcdId
//                System.out.println("lastid为"+lastId+"============");
                id = lastId+1;
//                System.out.println("id为"+id+"----------");
                insertValue.setPostId(id);  // 设置新的唯一 mfcdId
                insertProductionOvertimeStatisticsTable(insertValue); // 向数据库中插入带有新 mfcdId 的数据
            } else {
//                System.out.println("id为"+id+"----------");
                insertValue.setPostId(id);
                insertProductionOvertimeStatisticsTable(insertValue);
            }
//            insertValue.setMfcdId(GenerateId.getNextId(id));
//            insertProductionOvertimeStatisticsTable(insertValue);
            id++;
            System.out.println("插入成功");
        }

    }

//    @Override
//    public void Synchronization1(List<ProductionOvertimeApplicationForm> list2, ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable1) {
//        int count1 = 0;
//        HashMap<String, ProductionOvertimeStatisticsTable> result = new HashMap<String, ProductionOvertimeStatisticsTable>();
//        // 获取当前日期
//        LocalDateTime currentDateTime = LocalDateTime.now(); //获取当前时间.
//        LocalDate currentDate = LocalDate.now();
//        Date currentDateold = new Date();
//        int year = getTime.getYear(currentDateTime);
//        int month = getTime.getMonth(currentDateTime);
//        int day = getTime.getDay(currentDateTime);
//
//    }

    public Long selectLastId(){
        return productionOvertimeStatisticsTableMapper.selectLastId();
    }
}
