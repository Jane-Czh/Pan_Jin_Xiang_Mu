package com.heli.project.controller;

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
import com.heli.project.domain.UpdataRecode;
import com.heli.project.service.IUpdataRecodeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 更新记录Controller
 * 
 * @author teandron
 * @date 2024-07-28
 */
@RestController
@RequestMapping("/updata_recode/recode")
public class UpdataRecodeController extends BaseController
{
    @Autowired
    private IUpdataRecodeService updataRecodeService;

    /**
     * 查询更新记录列表
     */
    @PreAuthorize("@ss.hasPermi('updata_recode:recode:list')")
    @GetMapping("/list")
    public TableDataInfo list(UpdataRecode updataRecode)
    {
        startPage();
        List<UpdataRecode> list = updataRecodeService.selectUpdataRecodeList(updataRecode);
        return getDataTable(list);
    }

    /**
     * 导出更新记录列表
     */
    @PreAuthorize("@ss.hasPermi('updata_recode:recode:export')")
    @Log(title = "更新记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UpdataRecode updataRecode)
    {
        List<UpdataRecode> list = updataRecodeService.selectUpdataRecodeList(updataRecode);
        ExcelUtil<UpdataRecode> util = new ExcelUtil<UpdataRecode>(UpdataRecode.class);
        util.exportExcel(response, list, "更新记录数据");
    }

    /**
     * 获取更新记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('updata_recode:recode:query')")
    @GetMapping(value = "/{operId}")
    public AjaxResult getInfo(@PathVariable("operId") Long operId)
    {
        return success(updataRecodeService.selectUpdataRecodeByOperId(operId));
    }

    /**
     * 新增更新记录
     */
    @PreAuthorize("@ss.hasPermi('updata_recode:recode:add')")
    @Log(title = "更新记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UpdataRecode updataRecode)
    {
        return toAjax(updataRecodeService.insertUpdataRecode(updataRecode));
    }

    /**
     * 修改更新记录
     */
    @PreAuthorize("@ss.hasPermi('updata_recode:recode:edit')")
    @Log(title = "更新记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UpdataRecode updataRecode)
    {
        return toAjax(updataRecodeService.updateUpdataRecode(updataRecode));
    }

    /**
     * 删除更新记录
     */
    @PreAuthorize("@ss.hasPermi('updata_recode:recode:remove')")
    @Log(title = "更新记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{operIds}")
    public AjaxResult remove(@PathVariable Long[] operIds)
    {
        return toAjax(updataRecodeService.deleteUpdataRecodeByOperIds(operIds));
    }
}
