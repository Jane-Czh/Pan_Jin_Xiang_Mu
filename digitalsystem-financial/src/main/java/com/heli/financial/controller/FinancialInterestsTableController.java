package com.heli.financial.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.heli.financial.service.IFinancialDataService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.service.IFinancialInterestsTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务-利润Controller
 *
 * @author hong
 * @date 2024-03-31
 */
@RestController
@RequestMapping("/financial/data/interests")
public class FinancialInterestsTableController extends BaseController {
    @Autowired
    private IFinancialInterestsTableService financialInterestsTableService;
    @Autowired
    private IFinancialDataService financialDataService;


    /**
     * @description: 利润表excel导入接口
     * @author: hong
     * @date: 2024/3/31 13:42
     * @return: success
     */
//    @Log(title = "[财务]利润表表上传", businessType = BusinessType.INSERT)
//    @PostMapping("/import")
//    public AjaxResult importTable(MultipartFile excelFile) {
//        System.out.println(excelFile);
//        try {
//            financialInterestsTableService.importInterests(excelFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new ServiceException("excel上传失败");
//        }
//        return success();
//    }

//    @GetMapping("/selectByYearAndMonth")
//    public void selectByYearAndMonth(Date yearAndMonth){
//        System.out.println("controller"+yearAndMonth);
//        FinancialInterestsTable financialInterestsTable = financialInterestsTableService.selectFinancialInterestsTableByYearAndMonth(yearAndMonth);
//        System.out.println(financialInterestsTable);
//    }


    /**
     * 查询财务-利润列表
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancialInterestsTable financialInterestsTable) {
        startPage();
        List<FinancialInterestsTable> list = financialInterestsTableService.selectFinancialInterestsTableList(financialInterestsTable);
        return getDataTable(list);
    }

    /**
     * 获取财务-利润详细信息
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:query')")
    @GetMapping(value = "/{fiId}")
    public AjaxResult getInfo(@PathVariable("fiId") Long fiId) {
        return success(financialInterestsTableService.selectFinancialInterestsTableByFiId(fiId));
    }

    /**
     * 新增财务-利润
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:add')")
    @Log(title = "财务-利润", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancialInterestsTable financialInterestsTable) {
//        return toAjax(financialInterestsTableService.insertFinancialInterestsTable(financialInterestsTable));




        Date lastMonth = DateUtils.getLastMonth(financialInterestsTable.getYearAndMonth());


        if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth) && financialInterestsTableService.checkDataExists()) {
            return AjaxResult.error("上月利润表数据未填报");
        }

        if (financialInterestsTableService.checkInterestsDataIsExisted(financialInterestsTable.getYearAndMonth())) {
            return AjaxResult.error("当月利润表已上传");
        }
        int status = 0;

        status = financialInterestsTableService.insertFinancialInterestsTable(financialInterestsTable);

        if (status == 0) {
            return AjaxResult.error("利润表填报数据有误");
        }

        // 检查当月 和 上月文件是否上传完成，全部上传后开始计算
        if (financialDataService.checkDataUploadedForCurrentMonth(financialInterestsTable.getYearAndMonth())
                && financialDataService.checkDataUploadedForCurrentMonth(DateUtils.getLastMonth(financialInterestsTable.getYearAndMonth()))) {
            // 开始计算
            financialDataService.calculateCurrentMonthFinancialData(financialInterestsTable.getYearAndMonth());
        }

        return AjaxResult.success();


    }



    /**
     * 删除财务-利润
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:remove')")
    @Log(title = "财务-利润", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fiIds}")
    public AjaxResult remove(@PathVariable Long[] fiIds) {
        return toAjax(financialInterestsTableService.deleteFinancialInterestsTableByFiIds(fiIds));
    }
}

