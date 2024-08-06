package com.ruoyi.Enterprisemanagement.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementPersonnelRoster;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementPersonnelRosterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工花名单导入Controller
 * 
 * @author ruoyi
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/Enterprisemanagement/roster")
public class EnterpriseManagementPersonnelRosterController extends BaseController
{
    @Autowired
    private IEnterpriseManagementPersonnelRosterService enterpriseManagementPersonnelRosterService;
    /*表单导入功能*/
//    @Log(title = "[市场]员工花名册", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:roster:import')")
    @PostMapping("/PRimport")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        System.out.println("excelFile" + excelFile);
        try {

            enterpriseManagementPersonnelRosterService.PRimportInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }

    /**
     * 查询员工花名单导入列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:roster:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster)
    {
        startPage();
        List<EnterpriseManagementPersonnelRoster> list = enterpriseManagementPersonnelRosterService.selectEnterpriseManagementPersonnelRosterList(enterpriseManagementPersonnelRoster);
        return getDataTable(list);
    }

    /**
     * 导出员工花名单导入列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:roster:export')")
    @Log(title = "员工花名单导入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster)
    {
        List<EnterpriseManagementPersonnelRoster> list = enterpriseManagementPersonnelRosterService.selectEnterpriseManagementPersonnelRosterList(enterpriseManagementPersonnelRoster);
        ExcelUtil<EnterpriseManagementPersonnelRoster> util = new ExcelUtil<EnterpriseManagementPersonnelRoster>(EnterpriseManagementPersonnelRoster.class);
        util.exportExcel(response, list, "员工花名单导入数据");
    }

    /**
     * 获取员工花名单导入详细信息
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:roster:query')")
    @GetMapping(value = "/{emprId}")
    public AjaxResult getInfo(@PathVariable("emprId") Long emprId)
    {
        return success(enterpriseManagementPersonnelRosterService.selectEnterpriseManagementPersonnelRosterByEmprId(emprId));
    }

    /**
     * 新增员工花名单导入
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:roster:add')")
    @Log(title = "员工花名单导入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster)
    {
        return toAjax(enterpriseManagementPersonnelRosterService.insertEnterpriseManagementPersonnelRoster(enterpriseManagementPersonnelRoster));
    }

    /**
     * 修改员工花名单导入
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:roster:edit')")
    @Log(title = "员工花名单导入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster)
    {
        return toAjax(enterpriseManagementPersonnelRosterService.updateEnterpriseManagementPersonnelRoster(enterpriseManagementPersonnelRoster));
    }

    /**
     * 删除员工花名单导入
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:roster:remove')")
    @Log(title = "员工花名单导入", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emprIds}")
    public AjaxResult remove(@PathVariable Long[] emprIds)
    {
        return toAjax(enterpriseManagementPersonnelRosterService.deleteEnterpriseManagementPersonnelRosterByEmprIds(emprIds));
    }
}
