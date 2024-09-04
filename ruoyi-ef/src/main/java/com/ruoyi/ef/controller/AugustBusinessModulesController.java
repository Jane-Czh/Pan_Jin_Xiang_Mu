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
import com.ruoyi.system.domain.AugustBusinessModules;
import com.ruoyi.system.service.IAugustBusinessModulesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-08-01
 */
@RestController
@RequestMapping("/system/modules")
public class AugustBusinessModulesController extends BaseController
{
    @Autowired
    private IAugustBusinessModulesService augustBusinessModulesService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:modules:list')")
    @GetMapping("/list")
    public TableDataInfo list(AugustBusinessModules augustBusinessModules)
    {
        startPage();
        List<AugustBusinessModules> list = augustBusinessModulesService.selectAugustBusinessModulesList(augustBusinessModules);
        return getDataTable(list);
    }

    /**
     * 无权限版本
     * @param augustBusinessModules
     * @return
     */
    @GetMapping("/list/list")
    public TableDataInfo list2(AugustBusinessModules augustBusinessModules)
    {
        startPage();
        List<AugustBusinessModules> list = augustBusinessModulesService.selectAugustBusinessModulesList(augustBusinessModules);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:modules:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AugustBusinessModules augustBusinessModules)
    {
        List<AugustBusinessModules> list = augustBusinessModulesService.selectAugustBusinessModulesList(augustBusinessModules);
        ExcelUtil<AugustBusinessModules> util = new ExcelUtil<AugustBusinessModules>(AugustBusinessModules.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:modules:query')")
    @GetMapping(value = "/{bmId}")
    public AjaxResult getInfo(@PathVariable("bmId") Long bmId)
    {
        return success(augustBusinessModulesService.selectAugustBusinessModulesByBmId(bmId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:modules:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AugustBusinessModules augustBusinessModules)
    {
        return toAjax(augustBusinessModulesService.insertAugustBusinessModules(augustBusinessModules));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:modules:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AugustBusinessModules augustBusinessModules)
    {
        return toAjax(augustBusinessModulesService.updateAugustBusinessModules(augustBusinessModules));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:modules:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bmIds}")
    public AjaxResult remove(@PathVariable Long[] bmIds)
    {
        return toAjax(augustBusinessModulesService.deleteAugustBusinessModulesByBmIds(bmIds));
    }
}
