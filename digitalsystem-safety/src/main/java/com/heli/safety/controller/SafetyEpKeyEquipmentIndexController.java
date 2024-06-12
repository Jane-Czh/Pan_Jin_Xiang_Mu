package com.heli.safety.controller;

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
import com.heli.safety.domain.SafetyEpKeyEquipmentIndex;
import com.heli.safety.service.ISafetyEpKeyEquipmentIndexService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 重点设备字典Controller
 *
 * @author hong
 * @date 2024-05-31
 */
@RestController
@RequestMapping("/safety/data/dictionary")
public class SafetyEpKeyEquipmentIndexController extends BaseController {
    @Autowired
    private ISafetyEpKeyEquipmentIndexService safetyEpKeyEquipmentIndexService;

    /**
     * 查询重点设备字典列表
     */
    @PreAuthorize("@ss.hasPermi('safety:dictionary:list')")
    @GetMapping("/list")
    public TableDataInfo list(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
        startPage();
        List<SafetyEpKeyEquipmentIndex> list = safetyEpKeyEquipmentIndexService.selectSafetyEpKeyEquipmentIndexList(safetyEpKeyEquipmentIndex);
        return getDataTable(list);
    }

    /**
     * 获取重点设备字典详细信息
     */
    @PreAuthorize("@ss.hasPermi('safety:dictionary:query')")
    @GetMapping(value = "/{skId}")
    public AjaxResult getInfo(@PathVariable("skId") Long skId) {
        return success(safetyEpKeyEquipmentIndexService.selectSafetyEpKeyEquipmentIndexBySkId(skId));
    }

    /**
     * 新增重点设备字典
     */
    @PreAuthorize("@ss.hasPermi('safety:dictionary:add')")
    @Log(title = "重点设备字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
        if(safetyEpKeyEquipmentIndexService.checkSafetyEpKeyEquipmentIndexIsExisted(safetyEpKeyEquipmentIndex.getSeKeyEquipmentId())){
            return AjaxResult.error("该设备已存在");
        }
        safetyEpKeyEquipmentIndex.setCreateBy(getUsername());
        return toAjax(safetyEpKeyEquipmentIndexService.insertSafetyEpKeyEquipmentIndex(safetyEpKeyEquipmentIndex));
    }

    /**
     * 修改重点设备字典
     */
    @PreAuthorize("@ss.hasPermi('safety:dictionary:edit')")
    @Log(title = "重点设备字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
        safetyEpKeyEquipmentIndex.setUpdateBy(getUsername());
        return toAjax(safetyEpKeyEquipmentIndexService.updateSafetyEpKeyEquipmentIndex(safetyEpKeyEquipmentIndex));
    }

    /**
     * 删除重点设备字典
     */
    @PreAuthorize("@ss.hasPermi('safety:dictionary:remove')")
    @Log(title = "重点设备字典", businessType = BusinessType.DELETE)
    @DeleteMapping("/{skIds}")
    public AjaxResult remove(@PathVariable Long[] skIds) {
        return toAjax(safetyEpKeyEquipmentIndexService.deleteSafetyEpKeyEquipmentIndexBySkIds(skIds));
    }
}
