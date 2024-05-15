package com.ruoyi.Enterprisemanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.utils.EMExcelUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Enterprisemanagement.mapper.EnterpriseManagementFunctionLaborReminderMapper;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionLaborReminder;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementFunctionLaborReminderService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企管劳动合同到期提醒Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-23
 */
@Service
public class EnterpriseManagementFunctionLaborReminderServiceImpl implements IEnterpriseManagementFunctionLaborReminderService
{
    @Autowired
    private EnterpriseManagementFunctionLaborReminderMapper enterpriseManagementFunctionLaborReminderMapper;

    /**
     * 查询企管劳动合同到期提醒
     *
     * @param emlwId 企管劳动合同到期提醒主键
     * @return 企管劳动合同到期提醒
     */
    @Override
    public EnterpriseManagementFunctionLaborReminder selectEnterpriseManagementFunctionLaborReminderByEmlwId(Long emlwId)
    {
        return enterpriseManagementFunctionLaborReminderMapper.selectEnterpriseManagementFunctionLaborReminderByEmlwId(emlwId);
    }

    /**
     * 查询企管劳动合同到期提醒列表
     *
     * @param enterpriseManagementFunctionLaborReminder 企管劳动合同到期提醒
     * @return 企管劳动合同到期提醒
     */
    @Override
    public List<EnterpriseManagementFunctionLaborReminder> selectEnterpriseManagementFunctionLaborReminderList(EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder)
    {
        return enterpriseManagementFunctionLaborReminderMapper.selectEnterpriseManagementFunctionLaborReminderList(enterpriseManagementFunctionLaborReminder);
    }

    /**
     * 新增企管劳动合同到期提醒
     *
     * @param enterpriseManagementFunctionLaborReminder 企管劳动合同到期提醒
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementFunctionLaborReminder(EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder)
    {
        return enterpriseManagementFunctionLaborReminderMapper.insertEnterpriseManagementFunctionLaborReminder(enterpriseManagementFunctionLaborReminder);
    }

    /**
     * 修改企管劳动合同到期提醒
     *
     * @param enterpriseManagementFunctionLaborReminder 企管劳动合同到期提醒
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementFunctionLaborReminder(EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder)
    {
        return enterpriseManagementFunctionLaborReminderMapper.updateEnterpriseManagementFunctionLaborReminder(enterpriseManagementFunctionLaborReminder);
    }

    /**
     * 批量删除企管劳动合同到期提醒
     *
     * @param emlwIds 需要删除的企管劳动合同到期提醒主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementFunctionLaborReminderByEmlwIds(Long[] emlwIds)
    {
        return enterpriseManagementFunctionLaborReminderMapper.deleteEnterpriseManagementFunctionLaborReminderByEmlwIds(emlwIds);
    }

    /**
     * 删除企管劳动合同到期提醒信息
     *
     * @param emlwId 企管劳动合同到期提醒主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementFunctionLaborReminderByEmlwId(Long emlwId)
    {
        return enterpriseManagementFunctionLaborReminderMapper.deleteEnterpriseManagementFunctionLaborReminderByEmlwId(emlwId);
    }

//    @Override
//    public int LRimportInterests(MultipartFile excelFile) throws IOException {
//        EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder;
//        InputStream is = null;
//        try {
//            List<EnterpriseManagementFunctionLaborReminder> enterpriseManagementFunctionLaborReminders = EMExcelUtils.LRparseExcel(excelFile);
//            int i = 0;
//            while (i < enterpriseManagementFunctionLaborReminders.size()){
//                enterpriseManagementFunctionLaborReminder = enterpriseManagementFunctionLaborReminders.get(i);
//                Long lastid = selectLastId();
//                if(lastid == null){
//                    lastid = 0L;
//                }
//                Long EMLW_id = GenerateId.getNextId(lastid);
//                enterpriseManagementFunctionLaborReminder.setEmlwId(EMLW_id);
//                if (enterpriseManagementFunctionLaborReminder.getName() == null){
//                    continue;
//                }
//                enterpriseManagementFunctionLaborReminderMapper.insertEnterpriseManagementFunctionLaborReminder(enterpriseManagementFunctionLaborReminder);
//                i++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new ServiceException("excel解析失败");
//        } finally {
//            if (is != null) {
//                is.close();
//            }
//        }
//
//        return 0;
//    }

    @Override
    public List<EnterpriseManagementFunctionLaborReminder> selectEnterpriseManagementFunctionLaborReminderList1() {
        return enterpriseManagementFunctionLaborReminderMapper.selectEnterpriseManagementFunctionLaborReminderList1();
    }

    @Override
    public void Synchronization(List<EnterpriseManagementLaborContractLedger> list, EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder1) {
        int count1 = 0;
        HashMap<String, EnterpriseManagementFunctionLaborReminder> result = new HashMap<String, EnterpriseManagementFunctionLaborReminder>();
        // 获取当前日期
        Date currentDate = new Date();
        long noeMonthsInMillis = 1 * 30 * 24 * 60 * 60 * 1000;
        System.out.println(currentDate);  // 输出当前日期和时间

        // 遍历列表中的数据进行统计


        while (count1 < list.size()){
            EnterpriseManagementLaborContractLedger enterpriseManagementFunctionLaborReminder = list.get(count1);
            String IDnumber = enterpriseManagementFunctionLaborReminder.getIdNumber();//身份证
            String Name = enterpriseManagementFunctionLaborReminder.getName();//姓名
            long Number = enterpriseManagementFunctionLaborReminder.getNumber();//序号
            String Department = enterpriseManagementFunctionLaborReminder.getDocument();//部门
            String stats = enterpriseManagementFunctionLaborReminder.getEmploymentStatus();//就职状态
            String Phone = enterpriseManagementFunctionLaborReminder.getPhoneNumber();//电话号码
            Date FirstStart = enterpriseManagementFunctionLaborReminder.getFirstContractPeriodStart(); //第一次开始
            Date FirstEnd  = enterpriseManagementFunctionLaborReminder.getFirstContractPeriodEnd(); //第一次结束
            String Firststatus = enterpriseManagementFunctionLaborReminder.getContractStatusFirstly();//第一次情况
            Date SecondStart = enterpriseManagementFunctionLaborReminder.getSecondContractPeriodStart();//第二次开始
            Date SecondEnd = enterpriseManagementFunctionLaborReminder.getSecondContractPeriodEnd();//第二次结束
            String Secondstatus = enterpriseManagementFunctionLaborReminder.getContractStatusSecondly();//第二次情况
            Date ThirdStart = enterpriseManagementFunctionLaborReminder.getThirdContractPeriodStart();//第三次开始
            Date ThirdEnd = enterpriseManagementFunctionLaborReminder.getThirdContractPeriodEnd();//第三次结束
            String Thirdstatus = enterpriseManagementFunctionLaborReminder.getContractStatusThirdly();//第三次情况

            //hash中没有当前证件数据，新建一个MarketStatisticsCompletionStatus类插入其中，分别给出其初始值
            if (result.get(IDnumber) == null){
                EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder2 = new EnterpriseManagementFunctionLaborReminder();
                /*新建车号并将相关数据从商品车台账表导入到市场科计划完成情况统计表*/
                enterpriseManagementFunctionLaborReminder2.setNumber(Number);
                enterpriseManagementFunctionLaborReminder2.setName(Name);
                enterpriseManagementFunctionLaborReminder2.setDocument(Department);
                enterpriseManagementFunctionLaborReminder2.setEmploymentStatus(stats);
                enterpriseManagementFunctionLaborReminder2.setIdNumber(IDnumber);
                enterpriseManagementFunctionLaborReminder2.setPhoneNumber(Phone);
                enterpriseManagementFunctionLaborReminder2.setFirstContractPeriodStart(FirstStart);
                enterpriseManagementFunctionLaborReminder2.setFirstContractPeriodEnd(FirstEnd);
                enterpriseManagementFunctionLaborReminder2.setContractStatusFirstly(Firststatus);
                enterpriseManagementFunctionLaborReminder2.setSecondContractPeriodStart(SecondStart);
                enterpriseManagementFunctionLaborReminder2.setSecondContractPeriodEnd(SecondEnd);
                enterpriseManagementFunctionLaborReminder2.setContractStatusSecondly(Secondstatus);
                enterpriseManagementFunctionLaborReminder2.setThirdContractPeriodStart(ThirdStart);
                enterpriseManagementFunctionLaborReminder2.setThirdContractPeriodEnd(ThirdEnd);
                enterpriseManagementFunctionLaborReminder2.setContractStatusThirdly(Thirdstatus);
                //判断作业证是否快要到期，先从第三次到期时间对比
//                if (ThirdExpiration!=null&&(currentDate.getTime()-ThirdExpiration.getTime())<=twoMonthsInMillis){
//                    enterpriseManagementFunctionLaborReminder2.setWarn("是");
//                    result.put(IDcard, enterpriseManagementFunctionSpecialOperationWarning2);
//                    System.out.println(1);
//                }
//                else if (ThirdExpiration==null&&SecondExpiration!=null&&(currentDate.getTime()-SecondExpiration.getTime())<=twoMonthsInMillis) {
//                    enterpriseManagementFunctionSpecialOperationWarning2.setWarn("是");
//                    result.put(IDcard, enterpriseManagementFunctionSpecialOperationWarning2);
//                    System.out.println(2);
//                }
//                else if (ThirdExpiration==null&&SecondExpiration==null&&FirstExpiration!=null&&(currentDate.getTime()-FirstExpiration.getTime())<=twoMonthsInMillis) {
//                    enterpriseManagementFunctionSpecialOperationWarning2.setWarn("是");
//                    result.put(IDcard, enterpriseManagementFunctionSpecialOperationWarning2);
//                    System.out.println(3);
//                }
//                else enterpriseManagementFunctionSpecialOperationWarning2.setWarn("否");
                System.out.println("--------------");
                System.out.println(Thirdstatus==null);
                System.out.println(Secondstatus==null);
                System.out.println(Firststatus!=null);
                System.out.println(FirstEnd!=null);
                System.out.println(Thirdstatus==null&&Secondstatus==null&&Firststatus!=null&&FirstEnd!=null);
                System.out.printf(String.valueOf((FirstEnd.getTime()-currentDate.getTime())<(((long) 30 * 24 * 60 * 60 * 1000))));
                System.out.println("---------------");
                System.out.println(((long) 30 * 24 * 60 * 60 * 1000)-(FirstEnd.getTime()-currentDate.getTime()));
                System.out.println(1 * 30 * 24 * 60 * 60 * 1000-644331294);
                System.out.println(FirstEnd.getTime()-currentDate.getTime());
                System.out.println("-------------------");
                if (Thirdstatus==null&&Secondstatus!=null&&SecondEnd!=null&&(SecondEnd.getTime()-currentDate.getTime())<((long) 30 * 24 * 60 * 60 * 1000)&&(SecondEnd.getTime()-currentDate.getTime())>0){
                    enterpriseManagementFunctionLaborReminder2.setWarn("是");
                    result.put(IDnumber,enterpriseManagementFunctionLaborReminder2);
                } else if (Thirdstatus==null&&Secondstatus==null&&Firststatus!=null&&FirstEnd!=null&&(FirstEnd.getTime()-currentDate.getTime())<((long) 30 * 24 * 60 * 60 * 1000)&&(FirstEnd.getTime()-currentDate.getTime())>0){
                    enterpriseManagementFunctionLaborReminder2.setWarn("是");
                    result.put(IDnumber,enterpriseManagementFunctionLaborReminder2);
                    System.out.println("Cahrucehgngong*********************************");
                }
                else {enterpriseManagementFunctionLaborReminder2.setWarn("否");}
                System.out.println(enterpriseManagementFunctionLaborReminder2);

            }else
            {System.out.println("此人已存在");}

            count1++;
        }
        //先删除数据库中所有数据
        List<EnterpriseManagementFunctionLaborReminder> list1 = selectEnterpriseManagementFunctionLaborReminderList(enterpriseManagementFunctionLaborReminder1);
        if (list1 != null&& list1.size()>0) {
            int x = 0;
            Long[] deleteId = new Long[list1.size()];
            while (x < list1.size()) {
                EnterpriseManagementFunctionLaborReminder value = list1.get(x);
                deleteId[x] = value.getEmlwId();
                x++;
            }
            deleteEnterpriseManagementFunctionLaborReminderByEmlwIds(deleteId);
            System.out.println("删除成功");
        }
        //依次插入新生成的数据
        Long id = 0L;
        for (Map.Entry<String, EnterpriseManagementFunctionLaborReminder> entry : result.entrySet()){
            System.out.println("-------------------");
            EnterpriseManagementFunctionLaborReminder insertValue = entry.getValue();
            insertValue.setEmlwId(GenerateId.getNextId(id));
            insertEnterpriseManagementFunctionLaborReminder(insertValue);
            id++;
            System.out.println("插入成功");
            System.out.println(insertValue);
        }


    }

    private Long selectLastId() {
        return enterpriseManagementFunctionLaborReminderMapper.selectLastId();
    }
}
