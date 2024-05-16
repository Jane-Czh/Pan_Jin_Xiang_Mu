package com.ruoyi.Enterprisemanagement.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionLaborReminder;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementPersonnelRoster;
import com.ruoyi.market.utils.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Enterprisemanagement.mapper.EnterpriseManagementFunctionEmployeeStatisticsMapper;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionEmployeeStatistics;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementFunctionEmployeeStatisticsService;

/**
 * 员工统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-06
 */
@Service
public class EnterpriseManagementFunctionEmployeeStatisticsServiceImpl implements IEnterpriseManagementFunctionEmployeeStatisticsService 
{
    @Autowired
    private EnterpriseManagementFunctionEmployeeStatisticsMapper enterpriseManagementFunctionEmployeeStatisticsMapper;

    /**
     * 查询员工统计
     * 
     * @param emesId 员工统计主键
     * @return 员工统计
     */
    @Override
    public EnterpriseManagementFunctionEmployeeStatistics selectEnterpriseManagementFunctionEmployeeStatisticsByEmesId(Long emesId)
    {
        return enterpriseManagementFunctionEmployeeStatisticsMapper.selectEnterpriseManagementFunctionEmployeeStatisticsByEmesId(emesId);
    }

    /**
     * 查询员工统计列表
     * 
     * @param enterpriseManagementFunctionEmployeeStatistics 员工统计
     * @return 员工统计
     */
    @Override
    public List<EnterpriseManagementFunctionEmployeeStatistics> selectEnterpriseManagementFunctionEmployeeStatisticsList(EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics)
    {
        return enterpriseManagementFunctionEmployeeStatisticsMapper.selectEnterpriseManagementFunctionEmployeeStatisticsList(enterpriseManagementFunctionEmployeeStatistics);
    }

    /**
     * 新增员工统计
     * 
     * @param enterpriseManagementFunctionEmployeeStatistics 员工统计
     * @return 结果
     */
    @Override
    public int insertEnterpriseManagementFunctionEmployeeStatistics(EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics)
    {
        return enterpriseManagementFunctionEmployeeStatisticsMapper.insertEnterpriseManagementFunctionEmployeeStatistics(enterpriseManagementFunctionEmployeeStatistics);
    }

    /**
     * 修改员工统计
     * 
     * @param enterpriseManagementFunctionEmployeeStatistics 员工统计
     * @return 结果
     */
    @Override
    public int updateEnterpriseManagementFunctionEmployeeStatistics(EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics)
    {
        return enterpriseManagementFunctionEmployeeStatisticsMapper.updateEnterpriseManagementFunctionEmployeeStatistics(enterpriseManagementFunctionEmployeeStatistics);
    }

    /**
     * 批量删除员工统计
     * 
     * @param emesIds 需要删除的员工统计主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementFunctionEmployeeStatisticsByEmesIds(Long[] emesIds)
    {
        return enterpriseManagementFunctionEmployeeStatisticsMapper.deleteEnterpriseManagementFunctionEmployeeStatisticsByEmesIds(emesIds);
    }

    /**
     * 删除员工统计信息
     * 
     * @param emesId 员工统计主键
     * @return 结果
     */
    @Override
    public int deleteEnterpriseManagementFunctionEmployeeStatisticsByEmesId(Long emesId)
    {
        return enterpriseManagementFunctionEmployeeStatisticsMapper.deleteEnterpriseManagementFunctionEmployeeStatisticsByEmesId(emesId);
    }

    @Override
    public void Synchronization(List<EnterpriseManagementPersonnelRoster> list, EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics1) {
        int count1 = 0;
        int count2 = 0;
        LinkedHashMap<String,EnterpriseManagementFunctionEmployeeStatistics> result = new LinkedHashMap<>();
        long totalNumberOfInterns = 0;//实习生总数
        long totalNumberOfLaborDispatch =0;//劳动派遣总数
        long totalNumberOfContracted = 0;//合同工总数
        while (count2<list.size()){
            EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster1 = list.get(count2);
            String Employegroup = enterpriseManagementPersonnelRoster1.getEmployeeGroup();//员工组
            //判断是否为实习生
            if(Employegroup != null &&"实习生".equals(Employegroup)){
                totalNumberOfInterns++;
            }
            //判断是否为劳务派遣
            if(Employegroup != null &&"劳务派遣".equals(Employegroup)){
                totalNumberOfLaborDispatch++;
            }
            //判断是否为合同员工
            if(Employegroup != null &&"合同制".equals(Employegroup)){
                totalNumberOfContracted++;
            }
            count2++;
        }
        while (count1 < list.size()){
            EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster = list.get(count1);
            String Name = enterpriseManagementPersonnelRoster.getName();//姓名
            String Employeeid = enterpriseManagementPersonnelRoster.getEmployeeId();//员工编号
            String gender = enterpriseManagementPersonnelRoster.getGender();//性别
            String Employegroup = enterpriseManagementPersonnelRoster.getEmployeeGroup();//员工组
//            System.out.println(enterpriseManagementPersonnelRoster);

            //hash中没有当前数据，新建一个MarketStatisticsCompletionStatus类插入其中，分别给出其初始值
            if (result.get(Employeeid)==null){
                EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics2 = new EnterpriseManagementFunctionEmployeeStatistics();
                /*新建员工编号并将相关数据从花名单导入到员工统计表*/
                enterpriseManagementFunctionEmployeeStatistics2.setEmployeeId(Employeeid);
                enterpriseManagementFunctionEmployeeStatistics2.setName(Name);
                enterpriseManagementFunctionEmployeeStatistics2.setGender(gender);
                enterpriseManagementFunctionEmployeeStatistics2.setEmployeeGroup(Employegroup);
                enterpriseManagementFunctionEmployeeStatistics2.setTotalNumberOfInterns(totalNumberOfInterns);
                enterpriseManagementFunctionEmployeeStatistics2.setTtotalNumberOfLaborDispatch(totalNumberOfLaborDispatch);
                enterpriseManagementFunctionEmployeeStatistics2.setTotalNumberOfContracted(totalNumberOfContracted);
                result.put(Employeeid, enterpriseManagementFunctionEmployeeStatistics2);
            }

            System.out.println(Name);
            count1++;

        }

        //先删除数据库中所有人员统计数据
        List<EnterpriseManagementFunctionEmployeeStatistics> list1 = selectEnterpriseManagementFunctionEmployeeStatisticsList(enterpriseManagementFunctionEmployeeStatistics1);
        if (list1 != null&& list1.size()>0) {
            int x = 0;
            Long[] deleteId = new Long[list1.size()];
            while (x < list1.size()) {
                EnterpriseManagementFunctionEmployeeStatistics value = list1.get(x);
                deleteId[x] = value.getEmesId();
                x++;
            }
            deleteEnterpriseManagementFunctionEmployeeStatisticsByEmesIds(deleteId);
            System.out.println("删除成功");
        }
        //依次插入新生成的数据
        Long id = 0L;
        for (Map.Entry<String, EnterpriseManagementFunctionEmployeeStatistics> entry : result.entrySet()){
            System.out.println("-------------------");
            EnterpriseManagementFunctionEmployeeStatistics insertValue = entry.getValue();
            insertValue.setEmesId(GenerateId.getNextId(id));
            insertEnterpriseManagementFunctionEmployeeStatistics(insertValue);
            id++;
            System.out.println("插入成功");
        }
    }
}
