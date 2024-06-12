package com.ruoyi.Technology.controller;

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
import com.ruoyi.Technology.domain.TechnologyFunctionChangeorder;
import com.ruoyi.Technology.service.ITechnologyFunctionChangeorderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 变更单留存Controller
 * 
 * @author ruoyi
 * @date 2024-06-09
 */
@RestController
@RequestMapping("/Technology/Changeorder")
public class TechnologyFunctionChangeorderController extends BaseController
{
    @Autowired
    private ITechnologyFunctionChangeorderService technologyFunctionChangeorderService;

    /**
     * 查询变更单留存列表
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(TechnologyFunctionChangeorder technologyFunctionChangeorder)
    {
        startPage();
        List<TechnologyFunctionChangeorder> list = technologyFunctionChangeorderService.selectTechnologyFunctionChangeorderList(technologyFunctionChangeorder);
        return getDataTable(list);
    }

    /**
     * 导出变更单留存列表
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:export')")
    @Log(title = "变更单留存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TechnologyFunctionChangeorder technologyFunctionChangeorder)
    {
        List<TechnologyFunctionChangeorder> list = technologyFunctionChangeorderService.selectTechnologyFunctionChangeorderList(technologyFunctionChangeorder);
        ExcelUtil<TechnologyFunctionChangeorder> util = new ExcelUtil<TechnologyFunctionChangeorder>(TechnologyFunctionChangeorder.class);
        util.exportExcel(response, list, "变更单留存数据");
    }

    /**
     * 获取变更单留存详细信息
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:query')")
    @GetMapping(value = "/{tfcoId}")
    public AjaxResult getInfo(@PathVariable("tfcoId") Long tfcoId)
    {
        return success(technologyFunctionChangeorderService.selectTechnologyFunctionChangeorderByTfcoId(tfcoId));
    }

    /**
     * 新增变更单留存
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:add')")
    @Log(title = "变更单留存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TechnologyFunctionChangeorder technologyFunctionChangeorder)
    {
        return toAjax(technologyFunctionChangeorderService.insertTechnologyFunctionChangeorder(technologyFunctionChangeorder));
    }

    /**
     * 修改变更单留存
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:edit')")
    @Log(title = "变更单留存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TechnologyFunctionChangeorder technologyFunctionChangeorder)
    {
        return toAjax(technologyFunctionChangeorderService.updateTechnologyFunctionChangeorder(technologyFunctionChangeorder));
    }

    /**
     * 删除变更单留存
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:remove')")
    @Log(title = "变更单留存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tfcoIds}")
    public AjaxResult remove(@PathVariable Long[] tfcoIds)
    {
        return toAjax(technologyFunctionChangeorderService.deleteTechnologyFunctionChangeorderByTfcoIds(tfcoIds));
    }
}
