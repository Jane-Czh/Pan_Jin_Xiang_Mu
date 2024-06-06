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
     * 新增[企业管理]指标月度填报数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:monthly:add')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.INSERT)
    @PostMapping("/monthly")
    @Transactional
    public AjaxResult add(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {

        System.out.println(enterpriseManagementMonthlyData);


        if (enterpriseManagementMonthlyDataService.checkEMEmployeesDataIsExisted(enterpriseManagementMonthlyData.getYearAndMonth())) {
            return AjaxResult.error("当月数据已填报");
        }
        if (!enterpriseManagementMonthlyDataService.checkEMEmployeesDataIsExisted(DateUtils.getLastMonth(enterpriseManagementMonthlyData.getYearAndMonth())) &&
            enterpriseManagementMonthlyDataService.checkEMMonthlyDataIsExisted()) {
            return AjaxResult.error("上月数据未填报");
        }
        enterpriseManagementMonthlyData.setCreateBy(getUsername());

//        enterpriseManagementMonthlyDataService.insertEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData);
        enterpriseManagementMonthlyDataService.insertMonthlyFillingDataByMonth(enterpriseManagementMonthlyData);
        enterpriseManagementMonthlyDataService.calculateHandFillIndicators(enterpriseManagementMonthlyData.getYearAndMonth());

        return AjaxResult.success();
    }


    /**
     * 新增[企业管理]指标年度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:annual:add')")
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
    @PreAuthorize("@ss.hasPermi('enterprise:salary:import')")
    @PostMapping("/salary")
    public R<String> simpleRead(Date yearAndMonth, MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {

            enterpriseManagementSalaryTableService.readSalaryExcelToDB(multipartFile.getOriginalFilename(), inputStream, getUsername());

            enterpriseManagementMonthlyDataService.calculateSalaryTableIndicators(yearAndMonth);
            return R.ok("上传成功");
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }


}
