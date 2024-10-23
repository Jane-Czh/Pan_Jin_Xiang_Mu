package com.heli.enterprise.controller;

import com.heli.enterprise.domain.EnterpriseManagementAnnualData;

import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import com.heli.enterprise.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/enterprise/data")
public class EnterpriseManagementDataController extends BaseController {

    @Autowired
    private IEnterpriseManagementAnnualDataService enterpriseManagementAnnualDataService;
    @Autowired
    private IEnterpriseManagementMonthlyDataService enterpriseManagementMonthlyDataService;
    @Autowired
    private IEnterpriseManagementSalaryTableService enterpriseManagementSalaryTableService;

    private static final Logger log = LoggerFactory.getLogger(EnterpriseManagementDataController.class);

    /**
     * 更新-指标月度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:monthly:calculation')")
    @PostMapping("/calculation")
    @Transactional
    public AjaxResult calculation() {
        int i = enterpriseManagementMonthlyDataService.calculationAllData();
        return AjaxResult.success(i);
    }

    /**
     * 新增[企业管理]指标月度填报数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:monthly:add')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.INSERT)
    @PostMapping("/monthly")
    @Transactional
    public AjaxResult add(@RequestBody EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {

        System.out.println(enterpriseManagementMonthlyData);

        EnterpriseManagementMonthlyData monthlyData = new EnterpriseManagementMonthlyData();
        monthlyData.setYearAndMonth(enterpriseManagementMonthlyData.getYearAndMonth());
        List<EnterpriseManagementMonthlyData> list = enterpriseManagementMonthlyDataService.selectEnterpriseManagementMonthlyDataList(monthlyData);

        log.info("查询到的数据：" + list);

        enterpriseManagementMonthlyData.setCreateBy(getUsername());
        if (Objects.nonNull(list) && list.size() > 0) {
            enterpriseManagementMonthlyData.setEsId(list.get(0).getEsId());
            enterpriseManagementMonthlyDataService.updateEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData);
        }  else {
            enterpriseManagementMonthlyDataService.insertMonthlyFillingDataByMonth(enterpriseManagementMonthlyData);
        }

//        enterpriseManagementMonthlyDataService.calculateEmployeesNumber(enterpriseManagementMonthlyData.getYearAndMonth());
        enterpriseManagementMonthlyDataService.calculateSalaryFillNumber(enterpriseManagementMonthlyData.getYearAndMonth());

        return AjaxResult.success();
    }


    /**
     * 新增[企业管理]指标年度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:annual:add')")
    @Log(title = "[企业管理]指标年度数据", businessType = BusinessType.INSERT)
    @PostMapping("/annual")
    public AjaxResult add(@RequestBody EnterpriseManagementAnnualData enterpriseManagementAnnualData) {
        if (enterpriseManagementAnnualDataService.checkEMAnnualDataIsExisted(enterpriseManagementAnnualData.getNaturalYear())) {
            return AjaxResult.error("年度数据已填报");
        }
        enterpriseManagementAnnualData.setCreateBy(getUsername());


        return toAjax(enterpriseManagementAnnualDataService.insertEnterpriseManagementAnnualData(enterpriseManagementAnnualData));
    }


    /**
     * @return
     * @description: 上传工资表，存入数据库,计算相关数据
     * @author: hong
     * @date: 2024/4/11 16:38
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:salary:import')")
    @PostMapping("/salary")
    public R<String> simpleRead(Date yearAndMonth, MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {
            enterpriseManagementSalaryTableService.clearSalaryTableAllInfo();

            enterpriseManagementSalaryTableService.readSalaryExcelToDB(multipartFile.getOriginalFilename(), inputStream, getUsername());
            enterpriseManagementMonthlyDataService.statisticsSalaryTableIndicators(yearAndMonth);
            enterpriseManagementMonthlyDataService.calculateMonthlyDataSalary(yearAndMonth);
//            enterpriseManagementMonthlyDataService.calculateSalaryTableIndicators(yearAndMonth);
            log.info("计算完毕，清空数据库");
            enterpriseManagementSalaryTableService.clearSalaryTableAllInfo();
            return R.ok("上传成功");
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }


}
