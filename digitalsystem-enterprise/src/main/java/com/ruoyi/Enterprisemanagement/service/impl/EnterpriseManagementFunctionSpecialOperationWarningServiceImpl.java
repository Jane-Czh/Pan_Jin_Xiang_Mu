package com.ruoyi.Enterprisemanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;
import com.ruoyi.Enterprisemanagement.utils.EMExcelUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.utils.ExcelUtils;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.SplitDate;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Enterprisemanagement.mapper.EnterpriseManagementFunctionSpecialOperationWarningMapper;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementFunctionSpecialOperationWarningService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企管特种作业证到期预警Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
@Service
public class EnterpriseManagementFunctionSpecialOperationWarningServiceImpl implements IEnterpriseManagementFunctionSpecialOperationWarningService 
{
    @Autowired
    private EnterpriseManagementFunctionSpecialOperationWarningMapper enterpriseManagementFunctionSpecialOperationWarningMapper;

    /**
     * 查询企管特种作业证到期预警
     * 
     * @param emsoId 企管特种作业证到期预警主键
     * @return 企管特种作业证到期预警
     */
    @Override
    public EnterpriseManagementFunctionSpecialOperationWarning selectEnterpriseManagementFunctionSpecialOperationWarningByEmsoId(Long emsoId)
    {
        return enterpriseManagementFunctionSpecialOperationWarningMapper.selectEnterpriseManagementFunctionSpecialOperationWarningByEmsoId(emsoId);
    }

    /**
     * 查询企管特种作业证到期预警列表
     * 
     * @param enterpriseManagementFunctionSpecialOperationWarning 企管特种作业证到期预警
     * @return 企管特种作业证到期预警
     */
    @Override
    public List<EnterpriseManagementFunctionSpecialOperationWarning> selectEnterpriseManagementFunctionSpecialOperationWarningList(EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning)
    {
        return enterpriseManagementFunctionSpecialOperationWarningMapper.selectEnterpriseManagementFunctionSpecialOperationWarningList(enterpriseManagementFunctionSpecialOperationWarning);
    }

    /**
     * 新增企管特种作业证到期预警
     * 
     * @param enterpriseManagementFunctionSpecialOperationWarning 企管特种作业证到期预警
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementFunctionSpecialOperationWarning(EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning)
    {
        return enterpriseManagementFunctionSpecialOperationWarningMapper.insertEnterpriseManagementFunctionSpecialOperationWarning(enterpriseManagementFunctionSpecialOperationWarning);
    }

    /**
     * 修改企管特种作业证到期预警
     * 
     * @param enterpriseManagementFunctionSpecialOperationWarning 企管特种作业证到期预警
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementFunctionSpecialOperationWarning(EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning)
    {
        return enterpriseManagementFunctionSpecialOperationWarningMapper.updateEnterpriseManagementFunctionSpecialOperationWarning(enterpriseManagementFunctionSpecialOperationWarning);
    }

    /**
     * 批量删除企管特种作业证到期预警
     * 
     * @param emsoIds 需要删除的企管特种作业证到期预警主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoIds(Long[] emsoIds)
    {
        return enterpriseManagementFunctionSpecialOperationWarningMapper.deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoIds(emsoIds);
    }

    /**
     * 删除企管特种作业证到期预警信息
     * 
     * @param emsoId 企管特种作业证到期预警主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoId(Long emsoId)
    {
        return enterpriseManagementFunctionSpecialOperationWarningMapper.deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoId(emsoId);
    }

//    @Override
//    public int SOimportInterests(MultipartFile excelFile) throws IOException {
//        EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning;
//        InputStream is = null;
//        try {
//            List<EnterpriseManagementFunctionSpecialOperationWarning> enterpriseManagementFunctionSpecialOperationWarnings = EMExcelUtils.SOparseExcel(excelFile);
//            int i = 0;
//            while (i < enterpriseManagementFunctionSpecialOperationWarnings.size()){
//                enterpriseManagementFunctionSpecialOperationWarning = enterpriseManagementFunctionSpecialOperationWarnings.get(i);
//                Long lastid = selectLastId();
//                if(lastid == null){
//                    lastid = 0L;
//                }
//                Long EMSO_id = GenerateId.getNextId(lastid);
//                enterpriseManagementFunctionSpecialOperationWarning.setEmsoId(EMSO_id);
//                enterpriseManagementFunctionSpecialOperationWarning.setCreatedTime(getTime.getCurrentDate());
//                if (enterpriseManagementFunctionSpecialOperationWarning.getName() == null){
//                    continue;
//                }
//                enterpriseManagementFunctionSpecialOperationWarningMapper.insertEnterpriseManagementFunctionSpecialOperationWarning(enterpriseManagementFunctionSpecialOperationWarning);
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
    public void Synchronization(List<EnterpriseManagementSpecialOperationsManagementLedger> list, EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning1) {
        {
            int count1 = 0;
            HashMap<String, EnterpriseManagementFunctionSpecialOperationWarning> result = new HashMap<String, EnterpriseManagementFunctionSpecialOperationWarning>();
            // 获取当前日期
            Date currentDate = new Date();
            long twoMonthsInMillis = 2 * 30 * 24 * 60 * 60 * 1000;
            System.out.println(currentDate);  // 输出当前日期和时间

            // 遍历列表中的数据进行统计


            while (count1 < list.size()){
                EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementFunctionSpecialOperationWarning = list.get(count1);
                String IDnumber = enterpriseManagementFunctionSpecialOperationWarning.getIdNumber();//身份证
                String IDcard = enterpriseManagementFunctionSpecialOperationWarning.getIdCard();//证件号
                String Department = enterpriseManagementFunctionSpecialOperationWarning.getDepartment();//部门
                Date FirstExpiration = enterpriseManagementFunctionSpecialOperationWarning.getFirstExpiration(); //第一次到期
                Date SecondExpiration = enterpriseManagementFunctionSpecialOperationWarning.getSecondExpiration(); //第二次到期
                Date ThirdExpiration = enterpriseManagementFunctionSpecialOperationWarning.getThirdExpiration();//第三次到期

                //hash中没有当前证件数据，新建一个MarketStatisticsCompletionStatus类插入其中，分别给出其初始值
                if (result.get(IDcard) == null){
                    EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning2 = new EnterpriseManagementFunctionSpecialOperationWarning();
                    /*新建车号并将相关数据从商品车台账表导入到市场科计划完成情况统计表*/
                    enterpriseManagementFunctionSpecialOperationWarning2.setDepartment(Department);
                    enterpriseManagementFunctionSpecialOperationWarning2.setName(enterpriseManagementFunctionSpecialOperationWarning.getName());
                    enterpriseManagementFunctionSpecialOperationWarning2.setGender(enterpriseManagementFunctionSpecialOperationWarning.getGender());
                    enterpriseManagementFunctionSpecialOperationWarning2.setIssuingAuthority(enterpriseManagementFunctionSpecialOperationWarning.getIssuingAuthority());
                    enterpriseManagementFunctionSpecialOperationWarning2.setDocumentType(enterpriseManagementFunctionSpecialOperationWarning.getDocumentType());
                    enterpriseManagementFunctionSpecialOperationWarning2.setAssignmentCategory(enterpriseManagementFunctionSpecialOperationWarning.getAssignmentCategory());
                    enterpriseManagementFunctionSpecialOperationWarning2.setIdNumber(IDnumber);
                    enterpriseManagementFunctionSpecialOperationWarning2.setIdCard(enterpriseManagementFunctionSpecialOperationWarning.getIdCard());
                    enterpriseManagementFunctionSpecialOperationWarning2.setEvidenceCollectionTime(enterpriseManagementFunctionSpecialOperationWarning.getEvidenceCollectionTime());
                    enterpriseManagementFunctionSpecialOperationWarning2.setFirstExpiration(FirstExpiration);
                    enterpriseManagementFunctionSpecialOperationWarning2.setReexaminationFirstly(enterpriseManagementFunctionSpecialOperationWarning.getReexaminationFirstly());
                    enterpriseManagementFunctionSpecialOperationWarning2.setSecondExpiration(enterpriseManagementFunctionSpecialOperationWarning.getSecondExpiration());
                    enterpriseManagementFunctionSpecialOperationWarning2.setReexaminationSecondly(enterpriseManagementFunctionSpecialOperationWarning.getReexaminationSecondly());
                    enterpriseManagementFunctionSpecialOperationWarning2.setThirdExpiration(enterpriseManagementFunctionSpecialOperationWarning.getThirdExpiration());
                    enterpriseManagementFunctionSpecialOperationWarning2.setReexaminationThirdly(enterpriseManagementFunctionSpecialOperationWarning.getReexaminationThirdly());
                    //判断作业证是否快要到期，先从第三次到期时间对比currentDate.getTime()
                    if (ThirdExpiration!=null&&(ThirdExpiration.getTime()-currentDate.getTime())<=((long) 2 * 30 * 24 * 60 * 60 * 1000)&&(ThirdExpiration.getTime()-currentDate.getTime())>0){
                        enterpriseManagementFunctionSpecialOperationWarning2.setWarn("是");
                        result.put(String.valueOf(IDcard), enterpriseManagementFunctionSpecialOperationWarning2);
                        System.out.println(1);
                    }
                    else if (ThirdExpiration==null&&SecondExpiration!=null&&(SecondExpiration.getTime()-currentDate.getTime())<=((long) 2 * 30 * 24 * 60 * 60 * 1000)&&(SecondExpiration.getTime()-currentDate.getTime())>0) {
                        enterpriseManagementFunctionSpecialOperationWarning2.setWarn("是");
                        result.put(String.valueOf(IDcard), enterpriseManagementFunctionSpecialOperationWarning2);
                        System.out.println(2);
                    }
                    else if (ThirdExpiration==null&&SecondExpiration==null&&FirstExpiration!=null&&(FirstExpiration.getTime()-currentDate.getTime())<=((long) 2 * 30 * 24 * 60 * 60 * 1000)&&(FirstExpiration.getTime()-currentDate.getTime())>0) {
                        enterpriseManagementFunctionSpecialOperationWarning2.setWarn("是");
                        result.put(String.valueOf(IDcard), enterpriseManagementFunctionSpecialOperationWarning2);
                        System.out.println(currentDate.getTime()-FirstExpiration.getTime()+"=======");
                    }
                    else enterpriseManagementFunctionSpecialOperationWarning2.setWarn("否");
//                    if (ThirdExpiration!=null) {
//                        System.out.println("====" + ThirdExpiration.getTime() );
//                        System.out.println("====" + currentDate.getTime() );
//                        System.out.println("====" + (ThirdExpiration.getTime() - currentDate.getTime()));
//                        System.out.println("----" + (ThirdExpiration.getTime() - currentDate.getTime()));
//                    }
                    if (ThirdExpiration != null) {
                        System.out.println("====" + (ThirdExpiration.getTime() - currentDate.getTime()));
                        System.out.println("******"+twoMonthsInMillis);
                        System.out.println("ThirdExpiration is not null: true");
                        if ((ThirdExpiration.getTime() - currentDate.getTime()) <= twoMonthsInMillis) {
                            System.out.println("ThirdExpiration is within two months from now: true");
                            if ((ThirdExpiration.getTime() - currentDate.getTime()) > 0) {
                                System.out.println("ThirdExpiration is after the current date: true");
                                enterpriseManagementFunctionSpecialOperationWarning2.setWarn("是");
                                result.put(String.valueOf(IDcard), enterpriseManagementFunctionSpecialOperationWarning2);
                                System.out.println(1);
                            } else {
                                System.out.println("ThirdExpiration is after the current date: false");
                            }
                        } else {
                            System.out.println("ThirdExpiration is within two months from now: false");
                        }
                    } else {
                        System.out.println("ThirdExpiration is not null: false");
                    }

                    if(SecondExpiration!=null){
                        System.out.println("====" + SecondExpiration.getTime());
                    System.out.println("----"+(SecondExpiration.getTime()-currentDate.getTime()));}
                    if (FirstExpiration!=null){
                        System.out.println("====" + FirstExpiration.getTime());
                    System.out.println("===="+(FirstExpiration.getTime()-currentDate.getTime()));}


                }else
                {System.out.println("此证件已存在");}

                count1++;
            }
            //先删除数据库中所有数据
            List<EnterpriseManagementFunctionSpecialOperationWarning> list1 = selectEnterpriseManagementFunctionSpecialOperationWarningList(enterpriseManagementFunctionSpecialOperationWarning1);
            if (list1 != null&& list1.size()>0) {
                int x = 0;
                Long[] deleteId = new Long[list1.size()];
                while (x < list1.size()) {
                    EnterpriseManagementFunctionSpecialOperationWarning value = list1.get(x);
                    deleteId[x] = value.getEmsoId();
                    x++;
                }
                deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoIds(deleteId);
                System.out.println("删除成功");
            }
            //依次插入新生成的数据
            Long id = 0L;
            for (Map.Entry<String, EnterpriseManagementFunctionSpecialOperationWarning> entry : result.entrySet()){
                System.out.println("-------------------");
                EnterpriseManagementFunctionSpecialOperationWarning insertValue = entry.getValue();
                insertValue.setEmsoId(GenerateId.getNextId(id));
                insertEnterpriseManagementFunctionSpecialOperationWarning(insertValue);
                id++;
                System.out.println("插入成功");
                System.out.println(insertValue);
            }


        }
    }

    @Override
    public List<EnterpriseManagementFunctionSpecialOperationWarning> selectEnterpriseManagementFunctionSpecialOperationWarningList1() {
        return enterpriseManagementFunctionSpecialOperationWarningMapper.selectEnterpriseManagementFunctionSpecialOperationWarningList1();
    }

    private Long selectLastId() {
        return enterpriseManagementFunctionSpecialOperationWarningMapper.selectLastId();
    }
}
