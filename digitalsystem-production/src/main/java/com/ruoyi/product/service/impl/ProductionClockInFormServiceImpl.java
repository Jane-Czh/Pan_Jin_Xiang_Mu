package com.ruoyi.product.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketFunctionComparisonDeliverydays;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.SplitDate;
import com.ruoyi.market.utils.getTime;
import com.ruoyi.product.domain.ProductionOvertimeStatisticsTable;
import com.ruoyi.product.domain.ProuctionFunctionQualifiedRate;
import com.ruoyi.product.domain.WorkTimeData;
import com.ruoyi.product.utils.ExcelUtilsPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProductionClockInFormMapper;
import com.ruoyi.product.domain.ProductionClockInForm;
import com.ruoyi.product.service.IProductionClockInFormService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工打卡表Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
@Service
public class ProductionClockInFormServiceImpl implements IProductionClockInFormService 
{
    @Autowired
    private ProductionClockInFormMapper productionClockInFormMapper;

    /**
     * 查询员工打卡表
     * 
     * @param pcifId 员工打卡表主键
     * @return 员工打卡表
     */
    @Override
    public ProductionClockInForm selectProductionClockInFormByPcifId(Long pcifId)
    {
        return productionClockInFormMapper.selectProductionClockInFormByPcifId(pcifId);
    }

    /**
     * 查询员工打卡表列表
     * 
     * @param productionClockInForm 员工打卡表
     * @return 员工打卡表
     */
    @Override
    public List<ProductionClockInForm> selectProductionClockInFormList(ProductionClockInForm productionClockInForm)
    {
        return productionClockInFormMapper.selectProductionClockInFormList(productionClockInForm);
    }

    @Override
    public List<ProductionClockInForm> selectProductionClockInFormList1() {
        return productionClockInFormMapper.selectProductionClockInFormList1();
    }


    /**
     * 新增员工打卡表
     * 
     * @param productionClockInForm 员工打卡表
     * @return 结果
     */
    @Override
    public int insertProductionClockInForm(ProductionClockInForm productionClockInForm)
    {
        return productionClockInFormMapper.insertProductionClockInForm(productionClockInForm);
    }

    /**
     * 修改员工打卡表
     * 
     * @param productionClockInForm 员工打卡表
     * @return 结果
     */
    @Override
    public int updateProductionClockInForm(ProductionClockInForm productionClockInForm)
    {
//        ProductionClockInForm productionClockInForm1 = new ProductionClockInForm();
        return productionClockInFormMapper.updateProductionClockInForm(productionClockInForm);
    }

    /**
     * 批量删除员工打卡表
     * 
     * @param pcifIds 需要删除的员工打卡表主键
     * @return 结果
     */
    @Override
    public int deleteProductionClockInFormByPcifIds(Long[] pcifIds)
    {
        return productionClockInFormMapper.deleteProductionClockInFormByPcifIds(pcifIds);
    }

    /**
     * 删除员工打卡表信息
     * 
     * @param pcifId 员工打卡表主键
     * @return 结果
     */
    @Override
    public int deleteProductionClockInFormByPcifId(Long pcifId)
    {
        return productionClockInFormMapper.deleteProductionClockInFormByPcifId(pcifId);
    }

    @Override
    public int importInterests(MultipartFile excelFile) throws IOException {
        ProductionClockInForm productionClockInForm;
        InputStream is = null;
        try {
            List<ProductionClockInForm> productionClockInForms = ExcelUtilsPro.parseExcelform(excelFile);
            int i = 0;
            while (i < productionClockInForms.size()){
                productionClockInForm = productionClockInForms.get(i);
                Long lastid = selectLastId();
                if(lastid == null){
                    lastid = 0L;
                }
                long PQR_id = GenerateId.getNextId(lastid);
                productionClockInForm.setPcifId(PQR_id);
                if (productionClockInForm.getIdNumber() == null){
                    continue;
                }
                productionClockInFormMapper.insertProductionClockInForm(productionClockInForm);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return 0;
    }

    @Override
    public void updateTime(List<ProductionClockInForm> list1, WorkTimeData workTimeData) throws ParseException {
//        System.out.println("============"+workTimeData.getStartTime()+"=============");
//        System.out.println("------------"+workTimeData.getEndTime()+"-------------");
        int count1 = 0;
        HashMap<String, ProductionClockInForm> result = new HashMap<String, ProductionClockInForm>();
        // 获取当前日期
        LocalDateTime currentDateTime = LocalDateTime.now(); //获取当前时间.
        LocalDate currentDate = LocalDate.now();
        Date currentDateold = new Date();
        int year = getTime.getYear(currentDateTime);
        int month = getTime.getMonth(currentDateTime);
        int day = getTime.getDay(currentDateTime);




        while (count1<list1.size()){
            ProductionClockInForm productionClockInForm = list1.get(count1);
//            ProductionClockInForm productionClockInForm1 = new ProductionClockInForm();
//            Long ID = productionClockInForm.getPcifId();
            String idnumber = productionClockInForm.getIdNumber();
            String name = productionClockInForm.getName();
            String gender = productionClockInForm.getGender();
            Date Firststart = productionClockInForm.getFirstTimeClockingInAtWork();
            // 获取 Firststart 的年月日
            LocalDate firstStartDate = Firststart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date Firstend = productionClockInForm.getFirstTimeClockingInAfterWork();
            // 获取 Firstend 的年月日
            LocalDate firstEndDate = Firstend.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date Secondstart = productionClockInForm.getSecondTimeClockingInAtWork();
            Date Secondend = productionClockInForm.getSecondTimeClockingInAfterWork();
            Date Normalstartstr = workTimeData.getStartTime();
            // 获取 workTimeData.getStartTime() 的时分秒
            LocalDateTime workStartDateTime = Instant.ofEpochMilli(workTimeData.getStartTime().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//            Date startDate = dateFormat.parse(Normalstartstr);
            Date Normalend = workTimeData.getEndTime();
            // 获取 workTimeData.getEndTime() 的时分秒
            LocalDateTime workEndTime = Instant.ofEpochMilli(Normalend.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            // 使用 LocalDate 的年月日和 LocalTime 的时分秒创建新的 LocalDateTime 对象
            LocalDateTime combinedStartDateTime = workStartDateTime.with(firstStartDate);
            // 使用 LocalDate 的年月日和 LocalTime 的时分秒创建新的 LocalDateTime 对象
            LocalDateTime combinedDateTime = workEndTime.with(firstEndDate);
            // 将新的 LocalDateTime 对象转换为带有时区的 ZonedDateTime 对象
            ZonedDateTime zonedStartDateTime = combinedStartDateTime.atZone(ZoneId.systemDefault());
            // 将新的 LocalDateTime 对象转换为带有时区的 ZonedDateTime 对象
            ZonedDateTime zonedDateTime = combinedDateTime.atZone(ZoneId.systemDefault());
            // 将 ZonedDateTime 对象转换为 Instant，然后将其转换为 Date 类型的 Normalstartstr
            Date newNormalstartstr = Date.from(zonedStartDateTime.toInstant());
            // 将 ZonedDateTime 对象转换为 Instant，然后将其转换为 Date 类型的 Normalend
            Date newNormalend = Date.from(zonedDateTime.toInstant());

            System.out.println("============"+Normalstartstr+"=============");
            System.out.println("------------"+newNormalend+"-------------");
            productionClockInForm.setNormalWorkingHours(newNormalstartstr);
            productionClockInForm.setNormalClosingTime(newNormalend);
//            productionClockInForm1.setPcifId(ID);
//            productionClockInForm1.setIdNumber(idnumber);
//            productionClockInForm1.setName(name);
//            productionClockInForm1.setGender(gender);
//            productionClockInForm1.setFirstTimeClockingInAtWork(Firststart);
//            productionClockInForm1.setFirstTimeClockingInAfterWork(Firstend);
//            productionClockInForm1.setSecondTimeClockingInAtWork(Secondstart);
//            productionClockInForm1.setSecondTimeClockingInAfterWork(Secondend);
//            productionClockInForm1.setNormalWorkingHours(Normalstartstr);
//            productionClockInForm1.setNormalClosingTime(enddate);
            result.put(idnumber,productionClockInForm);
            System.out.println("------------"+result+"-------------");
            count1++;
        }

//        //        先删除数据库中本月的所有数据
//        List<ProductionOvertimeStatisticsTable> listnew = selectProductionClockInFormList(pro);
//        if (list1 != null&& listnew.size()>0){
//            int x = 0;
//            Long[] deleteId = new Long[listnew.size()];
//            while (x < listnew.size()){
//                ProductionOvertimeStatisticsTable value = listnew.get(x);
//                if (SplitDate.splitDate(value.getCreateTime())[0] == year&&SplitDate.splitDate(value.getCreateTime())[1] == month)
//                    deleteId[x] = value.getPostId();
//                x++;
//            }
//            deleteProductionOvertimeStatisticsTableByPostIds(deleteId);
//            System.out.println("删除成功");
//        }


        if (list1 != null){
            int x = 0;
            Long[] deleteId = new Long[list1.size()];
            while (x < list1.size()){
                ProductionClockInForm value = list1.get(x);
                    deleteId[x] = value.getPcifId();
                x++;
            }
            deleteProductionClockInFormByPcifIds(deleteId);
            System.out.println("删除成功");
        }
        Long id = 0L;
        for (Map.Entry<String, ProductionClockInForm> entry : result.entrySet()){
            System.out.println("-------------------");
            ProductionClockInForm insertValue = entry.getValue();
            insertValue.setPcifId(GenerateId.getNextId(id));
            ProductionClockInForm existingRecord = selectProductionClockInFormByPcifId(insertValue.getPcifId());
            System.out.println(insertValue.getPcifId()+"-----------");
            System.out.println(existingRecord+"*************");
            if (existingRecord != null) {
                // 处理已存在相同 mfcdId 的情况
                // 可以选择更新数据或者其他处理方式
                // 如果已存在相同 mfcdId 的数据，则向数据库中插入新数据
                Long lastId = selectLastId();// 获取一个新的唯一 mfcdId
                System.out.println("lastid为"+lastId+"============");
                id = lastId+1;
                System.out.println("id为"+id+"----------");
                insertValue.setPcifId(id);  // 设置新的唯一 mfcdId
                insertProductionClockInForm(insertValue); // 向数据库中插入带有新 mfcdId 的数据
            } else {
                System.out.println("id为"+id+"----------");
                insertValue.setPcifId(id);
                insertProductionClockInForm(insertValue);
            }
//            insertValue.setMfcdId(GenerateId.getNextId(id));
//            insertMarketFunctionComparisonDeliverydays(insertValue);
            id++;
            System.out.println("插入成功");
        }
    }

//    @Override
//    public void updatetime(List<ProductionClockInForm> lsit, String combinedDateTime) {
//        int count1 = 0;
//        while (count1<lsit.size()){
//            ProductionClockInForm productionClockInForm = new ProductionClockInForm();
//
//        }
//    }



    //查询数据库最后一条数据id
    public Long selectLastId(){
        return productionClockInFormMapper.selectLastId();
    }


}
