package com.ruoyi.ef.controller;

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
import com.ruoyi.system.domain.AugustDepartments;
import com.ruoyi.system.service.IAugustDepartmentsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-08-01
 */
@RestController
@RequestMapping("/system/departments")
public class AugustDepartmentsController extends BaseController
{
    @Autowired
    private IAugustDepartmentsService augustDepartmentsService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:departments:list')")
    @GetMapping("/list")
    public TableDataInfo list(AugustDepartments augustDepartments)
    {
        startPage();
        List<AugustDepartments> list = augustDepartmentsService.selectAugustDepartmentsList(augustDepartments);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:departments:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AugustDepartments augustDepartments)
    {
        List<AugustDepartments> list = augustDepartmentsService.selectAugustDepartmentsList(augustDepartments);
        ExcelUtil<AugustDepartments> util = new ExcelUtil<AugustDepartments>(AugustDepartments.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:departments:query')")
    @GetMapping(value = "/{depId}")
    public AjaxResult getInfo(@PathVariable("depId") Long depId)
    {
        return success(augustDepartmentsService.selectAugustDepartmentsByDepId(depId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:departments:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AugustDepartments augustDepartments)
    {
        return toAjax(augustDepartmentsService.insertAugustDepartments(augustDepartments));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:departments:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AugustDepartments augustDepartments)
    {
        return toAjax(augustDepartmentsService.updateAugustDepartments(augustDepartments));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:departments:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{depIds}")
    public AjaxResult remove(@PathVariable Long[] depIds)
    {
        return toAjax(augustDepartmentsService.deleteAugustDepartmentsByDepIds(depIds));
    }
}
