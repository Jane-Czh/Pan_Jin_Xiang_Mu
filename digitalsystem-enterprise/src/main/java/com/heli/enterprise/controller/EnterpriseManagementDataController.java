package com.heli.enterprise.controller;

import com.heli.enterprise.domain.EnterpriseManagementAnnualData;
import com.heli.enterprise.domain.EnterpriseManagementEmployeesData;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsSalaryData;

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

@RestController
@RequestMapping("/enterprise/data")
public class EnterpriseManagementDataController extends BaseController {

    @Autowired
    private IEnterpriseManagementAnnualDataService enterpriseManagementAnnualDataService;
    @Autowired
    private IEnterpriseManagementMonthlyDataService enterpriseManagementMonthlyDataService;
    @Autowired
    private IEnterpriseManagementIndicatorsSalaryDataService enterpriseManagementIndicatorsSalaryDataService;
    @Autowired
    private IEnterpriseManagementSalaryTableService enterpriseManagementSalaryTableService;


    private static final Logger log = LoggerFactory.getLogger(EnterpriseManagementDataController.class);


//    /**
//     * 新增[企业管理]指标月度填报数据
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:Data:add')")
//    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.INSERT)
//    @PostMapping("monthly")
//    @Transactional
//    public AjaxResult add(EnterpriseManagementEmployeesData enterpriseManagementMonthlyData, EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData) {
//
//        System.out.println(enterpriseManagementMonthlyData);
//        System.out.println(enterpriseManagementIndicatorsSalaryData);
//
//        if (!enterpriseManagementMonthlyDataService.checkEMEmployeesDataIsExisted(DateUtils.getLastMonth(enterpriseManagementMonthlyData.getYearAndMonth()))) {
//            return AjaxResult.error("上月数据未填报，请有序填报");
//        }
//        if (enterpriseManagementMonthlyDataService.checkEMEmployeesDataIsExisted(enterpriseManagementMonthlyData.getYearAndMonth())) {
//            return AjaxResult.error("当月数据已填报");
//        }
//        enterpriseManagementMonthlyData.setCreateBy(getUsername());
//        enterpriseManagementIndicatorsSalaryData.setCreateBy(getUsername());
//        enterpriseManagementIndicatorsSalaryDataService.insertEnterpriseManagementIndicatorsSalaryData(enterpriseManagementIndicatorsSalaryData);
//        return toAjax(enterpriseManagementMonthlyDataService.insertEnterpriseManagementEmployeesData(enterpriseManagementMonthlyData));
//    }

    /**
     * 新增[企业管理]指标月度填报数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:Data:add')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.INSERT)
    @PostMapping("monthly")
    @Transactional
    public AjaxResult add(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {

        System.out.println(enterpriseManagementMonthlyData);


//        if (!enterpriseManagementMonthlyDataService.checkEMEmployeesDataIsExisted(DateUtils.getLastMonth(enterpriseManagementMonthlyData.getYearAndMonth()))) {
//            return AjaxResult.error("上月数据未填报，请有序填报");
//        }
//        if (enterpriseManagementMonthlyDataService.checkEMEmployeesDataIsExisted(enterpriseManagementMonthlyData.getYearAndMonth())) {
//            return AjaxResult.error("当月数据已填报");
//        }
        enterpriseManagementMonthlyData.setCreateBy(getUsername());

//        enterpriseManagementMonthlyDataService.insertEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData);
        enterpriseManagementMonthlyDataService.insertMonthlyFillingDataByMonth(enterpriseManagementMonthlyData);
        enterpriseManagementMonthlyDataService.calculateHandFillIndicators(enterpriseManagementMonthlyData.getYearAndMonth());

        return AjaxResult.success();
    }


    /**
     * 新增[企业管理]指标年度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:data:add')")
    @Log(title = "[企业管理]指标年度数据", businessType = BusinessType.INSERT)
    @PostMapping("/annual")
    public AjaxResult add(EnterpriseManagementAnnualData enterpriseManagementAnnualData) {
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
    @PostMapping("/salary")
    public R<String> simpleRead(Date yearAndMonth, MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {

            enterpriseManagementSalaryTableService.readSalaryExcelToDB(multipartFile.getOriginalFilename(), inputStream);

            enterpriseManagementMonthlyDataService.calculateSalaryTableIndicators(yearAndMonth);
            return R.ok("上传成功");
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }


}
