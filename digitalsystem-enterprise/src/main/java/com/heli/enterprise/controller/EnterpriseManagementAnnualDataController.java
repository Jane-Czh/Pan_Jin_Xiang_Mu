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
import com.heli.enterprise.domain.EnterpriseManagementAnnualData;
import com.heli.enterprise.service.IEnterpriseManagementAnnualDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * [企业管理]指标年度数据Controller
 *
 * @author hong
 * @date 2024-04-27
 */
@RestController
@RequestMapping("/enterprise/data/annual")
public class EnterpriseManagementAnnualDataController extends BaseController {
    @Autowired
    private IEnterpriseManagementAnnualDataService enterpriseManagementAnnualDataService;

    /**
     * 查询[企业管理]指标年度数据列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:annual:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementAnnualData enterpriseManagementAnnualData) {
        startPage();
        List<EnterpriseManagementAnnualData> list = enterpriseManagementAnnualDataService.selectEnterpriseManagementAnnualDataList(enterpriseManagementAnnualData);
        return getDataTable(list);
    }

    /**
     * 获取[企业管理]指标年度数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('enterprise:annual:query')")
    @GetMapping(value = "/{emasId}")
    public AjaxResult getInfo(@PathVariable("emasId") Long emasId) {
        return success(enterpriseManagementAnnualDataService.selectEnterpriseManagementAnnualDataByEmasId(emasId));
    }

//    /**
//     * 新增[企业管理]指标年度数据
//     */
//    @PreAuthorize("@ss.hasPermi('enterprise:data:add')")
//    @Log(title = "[企业管理]指标年度数据", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody EnterpriseManagementAnnualData enterpriseManagementAnnualData) {
//        return toAjax(enterpriseManagementAnnualDataService.insertEnterpriseManagementAnnualData(enterpriseManagementAnnualData));
//    }

    /**
     * 修改[企业管理]指标年度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:annual:edit')")
    @Log(title = "[企业管理]指标年度数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementAnnualData enterpriseManagementAnnualData) {
        enterpriseManagementAnnualData.setUpdateBy(getUsername());
        return toAjax(enterpriseManagementAnnualDataService.updateEnterpriseManagementAnnualData(enterpriseManagementAnnualData));
    }

    /**
     * 删除[企业管理]指标年度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:annual:remove')")
    @Log(title = "[企业管理]指标年度数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{emasIds}")
    public AjaxResult remove(@PathVariable Long[] emasIds) {
        return toAjax(enterpriseManagementAnnualDataService.deleteEnterpriseManagementAnnualDataByEmasIds(emasIds));
    }
}
