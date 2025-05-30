package com.heli.enterprise.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.heli.enterprise.service.IEnterpriseManagementIndicatorsDailyClearingSettlementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 日清日结
 * Controller
 *
 * @author hong
 * @date 2024-09-12
 */
@Slf4j
@RestController
@RequestMapping("/enterprise/data/dailyclear")
public class EnterpriseManagementIndicatorsDailyClearingSettlementController extends BaseController {
    @Autowired
    private IEnterpriseManagementIndicatorsDailyClearingSettlementService enterpriseManagementIndicatorsDailyClearingSettlementService;

    /**
     * @description: 上传日清日结表，存入数据库
     * @author: hong
     * @date: 2024/10/8 14:27
     * @version: 1.0
     */
    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:import')")
    @PostMapping("/read")
    public R<String> simpleRead(Date yearAndMonth, MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            enterpriseManagementIndicatorsDailyClearingSettlementService.readSalaryExcelToDB(multipartFile.getOriginalFilename(), inputStream, yearAndMonth);
            return R.ok("上传成功");
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }

    /**
     * 查询日清日结
     * 列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
        startPage();
        List<EnterpriseManagementIndicatorsDailyClearingSettlement> list = enterpriseManagementIndicatorsDailyClearingSettlementService.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(enterpriseManagementIndicatorsDailyClearingSettlement);
        return getDataTable(list);
    }

    /**
     * 导出日清日结
     * 列表
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:export')")
//    @Log(title = "日清日结 ", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
//        List<EnterpriseManagementIndicatorsDailyClearingSettlement> list = enterpriseManagementIndicatorsDailyClearingSettlementService.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(enterpriseManagementIndicatorsDailyClearingSettlement);
//        ExcelUtil<EnterpriseManagementIndicatorsDailyClearingSettlement> util = new ExcelUtil<EnterpriseManagementIndicatorsDailyClearingSettlement>(EnterpriseManagementIndicatorsDailyClearingSettlement.class);
//        util.exportExcel(response, list, "日清日结数据");
//    }

    /**
     * 获取日清日结
     * 详细信息
     */
    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:query')")
    @GetMapping(value = "/{edId}")
    public AjaxResult getInfo(@PathVariable("edId") String edId) {
        return success(enterpriseManagementIndicatorsDailyClearingSettlementService.selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(edId));
    }

    /**
     * 新增日清日结
     */
    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:add')")
    @Log(title = "日清日结 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
        EnterpriseManagementIndicatorsDailyClearingSettlement dailyClear = new EnterpriseManagementIndicatorsDailyClearingSettlement();
        dailyClear.setYearAndMonth(enterpriseManagementIndicatorsDailyClearingSettlement.getYearAndMonth());
        dailyClear.setFlag(enterpriseManagementIndicatorsDailyClearingSettlement.getFlag());

        if (enterpriseManagementIndicatorsDailyClearingSettlementService.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(dailyClear).size() > 0) {
            return AjaxResult.warn("当月数据已上传");
        }
        enterpriseManagementIndicatorsDailyClearingSettlement.setCreateBy(getUsername());
        return toAjax(enterpriseManagementIndicatorsDailyClearingSettlementService.insertEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement));
    }

    /**
     * 修改日清日结
     */
    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:edit')")
//    @Log(title = "日清日结 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
        return toAjax(enterpriseManagementIndicatorsDailyClearingSettlementService.updateEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement));
    }

    /**
     * 删除日清日结
     */
    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:remove')")
    @Log(title = "日清日结 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{edIds}")
    public AjaxResult remove(@PathVariable String[] edIds) {
        return toAjax(enterpriseManagementIndicatorsDailyClearingSettlementService.deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(edIds));
    }
}


//package com.heli.enterprise.controller;
//
//import java.util.List;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.ruoyi.common.annotation.Log;
//import com.ruoyi.common.core.controller.BaseController;
//import com.ruoyi.common.core.domain.AjaxResult;
//import com.ruoyi.common.enums.BusinessType;
//import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
//import com.heli.enterprise.service.IEnterpriseManagementIndicatorsDailyClearingSettlementService;
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.common.core.page.TableDataInfo;
//
///**
// * 日清日结 Controller
// * @author hong
// * @date 2024-05-15
// */
//@RestController
//@RequestMapping("/enterprise/data/dailyclear")
//public class EnterpriseManagementIndicatorsDailyClearingSettlementController extends BaseController {
//    @Autowired
//    private IEnterpriseManagementIndicatorsDailyClearingSettlementService enterpriseManagementIndicatorsDailyClearingSettlementService;
//
//    /**
//     * 查询日清日结列表
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
//        startPage();
//        List<EnterpriseManagementIndicatorsDailyClearingSettlement> list = enterpriseManagementIndicatorsDailyClearingSettlementService.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(enterpriseManagementIndicatorsDailyClearingSettlement);
//        return getDataTable(list);
//    }
//
//    /**
//     * 获取日清日结详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:query')")
//    @GetMapping(value = "/{edId}")
//    public AjaxResult getInfo(@PathVariable("edId") Long edId) {
//        return success(enterpriseManagementIndicatorsDailyClearingSettlementService.selectEnterpriseManagementIndicatorsDailyClearingSettlementByEdId(edId));
//    }
//
//    /**
//     * 新增日清日结
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:add')")
//    @Log(title = "日清日结 ", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
//        System.out.println('1');
//        if (enterpriseManagementIndicatorsDailyClearingSettlementService.checkDailyClearingDataIsExisted(enterpriseManagementIndicatorsDailyClearingSettlement.getYearAndMonth())){
//            return AjaxResult.warn("当月数据已上传");
//        }
//        enterpriseManagementIndicatorsDailyClearingSettlement.setCreateBy(getUsername());
//        return toAjax(enterpriseManagementIndicatorsDailyClearingSettlementService.insertEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement));
//    }
//
//    /**
//     * 修改日清日结
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:edit')")
////    @Log(title = "日清日结 ", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody EnterpriseManagementIndicatorsDailyClearingSettlement enterpriseManagementIndicatorsDailyClearingSettlement) {
//        enterpriseManagementIndicatorsDailyClearingSettlement.setUpdateBy(getUsername());
//        return toAjax(enterpriseManagementIndicatorsDailyClearingSettlementService.updateEnterpriseManagementIndicatorsDailyClearingSettlement(enterpriseManagementIndicatorsDailyClearingSettlement));
//    }
//
//    /**
//     * 删除日清日结
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:dailyclear:remove')")
////    @Log(title = "日清日结 ", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{edIds}")
//    public AjaxResult remove(@PathVariable Long[] edIds) {
//        return toAjax(enterpriseManagementIndicatorsDailyClearingSettlementService.deleteEnterpriseManagementIndicatorsDailyClearingSettlementByEdIds(edIds));
//    }
//}
