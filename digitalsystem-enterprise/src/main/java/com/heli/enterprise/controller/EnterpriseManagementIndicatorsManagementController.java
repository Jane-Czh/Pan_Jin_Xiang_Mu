package com.heli.enterprise.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
import com.heli.enterprise.service.IEnterpriseManagementIndicatorsManagementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 十一项管理指标
 * Controller
 *
 * @author hong
 * @date 2024-09-13
 */
@RestController
@RequestMapping("/enterprise/data/management")
public class EnterpriseManagementIndicatorsManagementController extends BaseController {
    @Autowired
    private IEnterpriseManagementIndicatorsManagementService enterpriseManagementIndicatorsManagementService;

    /**
     * 查询十一项管理指标
     * 列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:management:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
        startPage();
        List<EnterpriseManagementIndicatorsManagement> list = enterpriseManagementIndicatorsManagementService.selectEnterpriseManagementIndicatorsManagementList(enterpriseManagementIndicatorsManagement);
        return getDataTable(list);
    }

    /**
     * 导出十一项管理指标
     * 列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:management:export')")
    @Log(title = "十一项管理指标 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
        List<EnterpriseManagementIndicatorsManagement> list = enterpriseManagementIndicatorsManagementService.selectEnterpriseManagementIndicatorsManagementList(enterpriseManagementIndicatorsManagement);
        ExcelUtil<EnterpriseManagementIndicatorsManagement> util = new ExcelUtil<EnterpriseManagementIndicatorsManagement>(EnterpriseManagementIndicatorsManagement.class);
        util.exportExcel(response, list, "十一项管理指标数据");
    }

    /**
     * 获取十一项管理指标
     * 详细信息
     */
    @PreAuthorize("@ss.hasPermi('enterprise:management:query')")
    @GetMapping(value = "/{emId}")
    public AjaxResult getInfo(@PathVariable("emId") String emId) {
        return success(enterpriseManagementIndicatorsManagementService.selectEnterpriseManagementIndicatorsManagementByEmId(emId));
    }

    /**
     * 新增十一项管理指标
     */
    @PreAuthorize("@ss.hasPermi('enterprise:management:add')")
    @Log(title = "十一项管理指标 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
        if (enterpriseManagementIndicatorsManagementService.checkManagementDataIsExisted(enterpriseManagementIndicatorsManagement.getYearAndMonth())) {
            return AjaxResult.warn("当月数据已上传");
        }
        enterpriseManagementIndicatorsManagement.setCreateBy(getUsername());
        return toAjax(enterpriseManagementIndicatorsManagementService.insertEnterpriseManagementIndicatorsManagement(enterpriseManagementIndicatorsManagement));
    }

    /**
     * 修改十一项管理指标
     */
    @PreAuthorize("@ss.hasPermi('enterprise:management:edit')")
    @Log(title = "十一项管理指标 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
        return toAjax(enterpriseManagementIndicatorsManagementService.updateEnterpriseManagementIndicatorsManagement(enterpriseManagementIndicatorsManagement));
    }

    /**
     * 删除十一项管理指标
     */
    @PreAuthorize("@ss.hasPermi('enterprise:management:remove')")
    @Log(title = "十一项管理指标 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{emIds}")
    public AjaxResult remove(@PathVariable String[] emIds) {
        return toAjax(enterpriseManagementIndicatorsManagementService.deleteEnterpriseManagementIndicatorsManagementByEmIds(emIds));
    }
}


//package com.heli.enterprise.controller;
//
//import java.util.List;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ruoyi.common.utils.DateUtils;
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
//import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
//import com.heli.enterprise.service.IEnterpriseManagementIndicatorsManagementService;
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.common.core.page.TableDataInfo;
//
///**
// * 十一项管理指标
// * Controller
// *
// * @author hong
// * @date 2024-05-15
// */
//@RestController
//@RequestMapping("/enterprise/data/management")
//public class EnterpriseManagementIndicatorsManagementController extends BaseController {
//    @Autowired
//    private IEnterpriseManagementIndicatorsManagementService enterpriseManagementIndicatorsManagementService;
//
//    /**
//     * 查询十一项管理指标列表
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:management:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
//        startPage();
//        List<EnterpriseManagementIndicatorsManagement> list = enterpriseManagementIndicatorsManagementService.selectEnterpriseManagementIndicatorsManagementList(enterpriseManagementIndicatorsManagement);
//        return getDataTable(list);
//    }
//
//    /**
//     * 获取十一项管理指标详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:management:query')")
//    @GetMapping(value = "/{emId}")
//    public AjaxResult getInfo(@PathVariable("emId") Long emId) {
//        return success(enterpriseManagementIndicatorsManagementService.selectEnterpriseManagementIndicatorsManagementByEmId(emId));
//    }
//
//    /**
//     * 新增十一项管理指标
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:management:add')")
//    @Log(title = "十一项管理指标 ", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
//        if (enterpriseManagementIndicatorsManagementService.checkManagementDataIsExisted(enterpriseManagementIndicatorsManagement.getYearAndMonth())){
//            return AjaxResult.warn("当月数据已上传");
//        }
//        enterpriseManagementIndicatorsManagement.setCreateBy(getUsername());
//        return toAjax(enterpriseManagementIndicatorsManagementService.insertEnterpriseManagementIndicatorsManagement(enterpriseManagementIndicatorsManagement));
//    }
//
//    /**
//     * 修改十一项管理指标
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:management:edit')")
//    @Log(title = "十一项管理指标 ", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody EnterpriseManagementIndicatorsManagement enterpriseManagementIndicatorsManagement) {
//        enterpriseManagementIndicatorsManagement.setUpdateBy(getUsername());
//        return toAjax(enterpriseManagementIndicatorsManagementService.updateEnterpriseManagementIndicatorsManagement(enterpriseManagementIndicatorsManagement));
//    }
//
//    /**
//     * 删除十一项管理指标
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:management:remove')")
//    @Log(title = "十一项管理指标 ", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{emIds}")
//    public AjaxResult remove(@PathVariable Long[] emIds) {
//        return toAjax(enterpriseManagementIndicatorsManagementService.deleteEnterpriseManagementIndicatorsManagementByEmIds(emIds));
//    }
//}
