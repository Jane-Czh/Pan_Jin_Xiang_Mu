package com.ruoyi.file.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.entity.regulationImplementationRateRespondEntity;
import com.ruoyi.file.entity.regulationRespondEntity;
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
import com.ruoyi.file.domain.RegulationsImplementationTable;
import com.ruoyi.file.service.IRegulationsImplementationTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 制度执行率Controller
 *
 * @author ruoyi
 * @date 2024-05-30
 */
@RestController
@RequestMapping("/file/regulationsImplementationRate")
public class RegulationsImplementationTableController extends BaseController
{
    @Autowired
    private IRegulationsImplementationTableService regulationsImplementationTableService;

    /**
     * 查询制度执行率列表
     */
    @PreAuthorize("@ss.hasPermi('file:regulationsImplementationRate:list')")
    @GetMapping("/list")
    public TableDataInfo list(RegulationsImplementationTable regulationsImplementationTable)
    {
        startPage();
        List<RegulationsImplementationTable> list = regulationsImplementationTableService.selectRegulationsImplementationTableList(regulationsImplementationTable);
        return getDataTable(list);
    }

    /**
     * 导出制度执行率列表
     */
    @PreAuthorize("@ss.hasPermi('file:regulationsImplementationRate:export')")
    @Log(title = "制度执行率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RegulationsImplementationTable regulationsImplementationTable)
    {
        List<RegulationsImplementationTable> list = regulationsImplementationTableService.selectRegulationsImplementationTableList(regulationsImplementationTable);
        ExcelUtil<RegulationsImplementationTable> util = new ExcelUtil<RegulationsImplementationTable>(RegulationsImplementationTable.class);
        util.exportExcel(response, list, "制度执行率数据");
    }

    /**
     * 获取制度执行率详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:regulationsImplementationRate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(regulationsImplementationTableService.selectRegulationsImplementationTableById(id));
    }

    /**
     * 新增制度执行率
     */
    @PreAuthorize("@ss.hasPermi('file:regulationsImplementationRate:add')")
    @Log(title = "制度执行率", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RegulationsImplementationTable regulationsImplementationTable)
    {
        return toAjax(regulationsImplementationTableService.insertRegulationsImplementationTable(regulationsImplementationTable));
    }

    /**
     * 修改制度执行率
     */
    @PreAuthorize("@ss.hasPermi('file:regulationsImplementationRate:edit')")
    @Log(title = "制度执行率", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RegulationsImplementationTable regulationsImplementationTable)
    {
        return toAjax(regulationsImplementationTableService.updateRegulationsImplementationTable(regulationsImplementationTable));
    }

    /**
     * 删除制度执行率
     */
    @PreAuthorize("@ss.hasPermi('file:regulationsImplementationRate:remove')")
    @Log(title = "制度执行率", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(regulationsImplementationTableService.deleteRegulationsImplementationTableByIds(ids));
    }

    /**
     * 指标：制度修订频率
     */
    @PostMapping("/implementationRateCounts")
    public List<regulationImplementationRateRespondEntity> implementationRateCounts(@RequestBody RegulationsImplementationTable table) {
        System.out.println("制度执行率");
        System.out.println("time start ==>" + table.getStartTime() + "// " + table.getEndTime());
        List<regulationImplementationRateRespondEntity> list = regulationsImplementationTableService.countImplementationRate(table.getStartTime(),table.getEndTime(),table);
        System.out.println("list11111=>" + list);
        return list;
    }
}
