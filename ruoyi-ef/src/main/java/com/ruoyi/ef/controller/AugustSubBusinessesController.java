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
import com.ruoyi.system.domain.AugustSubBusinesses;
import com.ruoyi.system.service.IAugustSubBusinessesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-08-01
 */
@RestController
@RequestMapping("/system/businesses")
public class AugustSubBusinessesController extends BaseController
{
    @Autowired
    private IAugustSubBusinessesService augustSubBusinessesService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:businesses:list')")
    @GetMapping("/list")
    public TableDataInfo list(AugustSubBusinesses augustSubBusinesses)
    {
        startPage();
        List<AugustSubBusinesses> list = augustSubBusinessesService.selectAugustSubBusinessesList(augustSubBusinesses);
        return getDataTable(list);
    }


    //无权限版本
    @GetMapping("/list/list")
    public TableDataInfo list2(AugustSubBusinesses augustSubBusinesses)
    {
        startPage();
        List<AugustSubBusinesses> list = augustSubBusinessesService.selectAugustSubBusinessesList(augustSubBusinesses);
        return getDataTable(list);
    }


    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:businesses:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AugustSubBusinesses augustSubBusinesses)
    {
        List<AugustSubBusinesses> list = augustSubBusinessesService.selectAugustSubBusinessesList(augustSubBusinesses);
        ExcelUtil<AugustSubBusinesses> util = new ExcelUtil<AugustSubBusinesses>(AugustSubBusinesses.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:businesses:query')")
    @GetMapping(value = "/{subbId}")
    public AjaxResult getInfo(@PathVariable("subbId") Long subbId)
    {
        return success(augustSubBusinessesService.selectAugustSubBusinessesBySubbId(subbId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:businesses:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AugustSubBusinesses augustSubBusinesses)
    {
        return toAjax(augustSubBusinessesService.insertAugustSubBusinesses(augustSubBusinesses));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:businesses:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AugustSubBusinesses augustSubBusinesses)
    {
        return toAjax(augustSubBusinessesService.updateAugustSubBusinesses(augustSubBusinesses));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:businesses:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{subbIds}")
    public AjaxResult remove(@PathVariable Long[] subbIds)
    {
        return toAjax(augustSubBusinessesService.deleteAugustSubBusinessesBySubbIds(subbIds));
    }
}
