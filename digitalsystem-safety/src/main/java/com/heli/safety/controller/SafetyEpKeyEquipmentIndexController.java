package com.heli.safety.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import org.springframework.web.multipart.MultipartFile;

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
    private static final Logger log = LoggerFactory.getLogger(SafetyEpKeyEquipmentIndexController.class);

    /**
     * @description: 上传 重点设备字典excel，并存入数据库
     * @author: hong
     * @date: 2024/7/28 16:38
     */
    @PreAuthorize("@ss.hasPermi('safety:dictionary:import')")
    @PostMapping("/import")
    @Transactional
    public R<String> simpleRead(MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            return safetyEpKeyEquipmentIndexService.readSafetyEpKeyEquipmentTableToDB (multipartFile.getOriginalFilename(), inputStream,getUsername());
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }

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
     * 导出重点设备字典列表
     */
    @PreAuthorize("@ss.hasPermi('safety:dictionary:export')")
    @Log(title = "重点设备字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
        List<SafetyEpKeyEquipmentIndex> list = safetyEpKeyEquipmentIndexService.selectSafetyEpKeyEquipmentIndexList(safetyEpKeyEquipmentIndex);
        ExcelUtil<SafetyEpKeyEquipmentIndex> util = new ExcelUtil<SafetyEpKeyEquipmentIndex>(SafetyEpKeyEquipmentIndex.class);
        util.exportExcel(response, list, "重点设备字典数据");
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
        return toAjax(safetyEpKeyEquipmentIndexService.insertSafetyEpKeyEquipmentIndex(safetyEpKeyEquipmentIndex));
    }

    /**
     * 修改重点设备字典
     */
    @PreAuthorize("@ss.hasPermi('safety:dictionary:edit')")
    @Log(title = "重点设备字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
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


//@RestController
//@RequestMapping("/safety/data/dictionary")
//public class SafetyEpKeyEquipmentIndexController extends BaseController {
//    @Autowired
//    private ISafetyEpKeyEquipmentIndexService safetyEpKeyEquipmentIndexService;
//
//    /**
//     * 查询重点设备字典列表
//     */
//    @PreAuthorize("@ss.hasPermi('safety:dictionary:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
//        startPage();
//        List<SafetyEpKeyEquipmentIndex> list = safetyEpKeyEquipmentIndexService.selectSafetyEpKeyEquipmentIndexList(safetyEpKeyEquipmentIndex);
//        return getDataTable(list);
//    }
//
//    /**
//     * 获取重点设备字典详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('safety:dictionary:query')")
//    @GetMapping(value = "/{skId}")
//    public AjaxResult getInfo(@PathVariable("skId") Long skId) {
//        return success(safetyEpKeyEquipmentIndexService.selectSafetyEpKeyEquipmentIndexBySkId(skId));
//    }
//
//    /**
//     * 新增重点设备字典
//     */
//    @PreAuthorize("@ss.hasPermi('safety:dictionary:add')")
//    @Log(title = "重点设备字典", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
//        if(safetyEpKeyEquipmentIndexService.checkSafetyEpKeyEquipmentIndexIsExisted(safetyEpKeyEquipmentIndex.getSeKeyEquipmentId())){
//            return AjaxResult.error("该设备已存在");
//        }
//        safetyEpKeyEquipmentIndex.setCreateBy(getUsername());
//        return toAjax(safetyEpKeyEquipmentIndexService.insertSafetyEpKeyEquipmentIndex(safetyEpKeyEquipmentIndex));
//    }
//
//    /**
//     * 修改重点设备字典
//     */
//    @PreAuthorize("@ss.hasPermi('safety:dictionary:edit')")
//    @Log(title = "重点设备字典", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
//        safetyEpKeyEquipmentIndex.setUpdateBy(getUsername());
//        return toAjax(safetyEpKeyEquipmentIndexService.updateSafetyEpKeyEquipmentIndex(safetyEpKeyEquipmentIndex));
//    }
//
//    /**
//     * 删除重点设备字典
//     */
//    @PreAuthorize("@ss.hasPermi('safety:dictionary:remove')")
//    @Log(title = "重点设备字典", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{skIds}")
//    public AjaxResult remove(@PathVariable Long[] skIds) {
//        return toAjax(safetyEpKeyEquipmentIndexService.deleteSafetyEpKeyEquipmentIndexBySkIds(skIds));
//    }
//}
