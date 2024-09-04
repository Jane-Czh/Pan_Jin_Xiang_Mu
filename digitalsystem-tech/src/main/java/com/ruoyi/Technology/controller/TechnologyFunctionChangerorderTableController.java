package com.ruoyi.Technology.controller;

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
import com.ruoyi.Technology.domain.TechnologyFunctionChangerorderTable;
import com.ruoyi.Technology.service.ITechnologyFunctionChangerorderTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 变更单留存Controller
 * 
 * @author ruoyi
 * @date 2024-08-02
 */
@RestController
@RequestMapping("/Technology/Changeorder")
public class TechnologyFunctionChangerorderTableController extends BaseController
{
    @Autowired
    private ITechnologyFunctionChangerorderTableService technologyFunctionChangerorderTableService;



    @Log(title = "[技术]表上传", businessType = BusinessType.INSERT)
    @PostMapping("/FOimport")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        System.out.println("excelFile" + excelFile);
        try {
            technologyFunctionChangerorderTableService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }
    /**
     * 查询变更单留存列表
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable)
    {
        startPage();
        List<TechnologyFunctionChangerorderTable> list = technologyFunctionChangerorderTableService.selectTechnologyFunctionChangerorderTableList(technologyFunctionChangerorderTable);
        return getDataTable(list);
    }

    /**
     * 导出变更单留存列表
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:export')")
    @Log(title = "变更单留存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable)
    {
        List<TechnologyFunctionChangerorderTable> list = technologyFunctionChangerorderTableService.selectTechnologyFunctionChangerorderTableList(technologyFunctionChangerorderTable);
        ExcelUtil<TechnologyFunctionChangerorderTable> util = new ExcelUtil<TechnologyFunctionChangerorderTable>(TechnologyFunctionChangerorderTable.class);
        util.exportExcel(response, list, "变更单留存数据");
    }

    /**
     * 获取变更单留存详细信息
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:query')")
    @GetMapping(value = "/{changetableId}")
    public AjaxResult getInfo(@PathVariable("changetableId") Long changetableId)
    {
        return success(technologyFunctionChangerorderTableService.selectTechnologyFunctionChangerorderTableByChangetableId(changetableId));
    }

    /**
     * 新增变更单留存
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:add')")
    @Log(title = "变更单留存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable)
    {
        return toAjax(technologyFunctionChangerorderTableService.insertTechnologyFunctionChangerorderTable(technologyFunctionChangerorderTable));
    }

    /**
     * 修改变更单留存
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:edit')")
    @Log(title = "变更单留存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable)
    {
        return toAjax(technologyFunctionChangerorderTableService.updateTechnologyFunctionChangerorderTable(technologyFunctionChangerorderTable));
    }

    /**
     * 删除变更单留存
     */
    @PreAuthorize("@ss.hasPermi('Technology:Changeorder:remove')")
    @Log(title = "变更单留存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{changetableIds}")
    public AjaxResult remove(@PathVariable Long[] changetableIds)
    {
        return toAjax(technologyFunctionChangerorderTableService.deleteTechnologyFunctionChangerorderTableByChangetableIds(changetableIds));
    }
}
