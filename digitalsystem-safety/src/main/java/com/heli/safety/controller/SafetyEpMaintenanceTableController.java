package com.heli.safety.controller;

import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.heli.safety.domain.SafetyEpMaintenanceTable;
import com.heli.safety.service.ISafetyEpMaintenanceTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 安环科
 * 维修数据Controller
 *
 * @author ruoyi
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/safety/importTable")
public class SafetyEpMaintenanceTableController extends BaseController {
    @Autowired
    private ISafetyEpMaintenanceTableService safetyEpMaintenanceTableService;
    private static final Logger log = LoggerFactory.getLogger(SafetyEpMaintenanceTableController.class);


    /**
     * @description: 上传 商品车台账excel，并存入数据库
     * @author: hong
     * @date: 2024/4/11 16:38
     *
     * @return*/
    @PostMapping("/simpleRead")
    public R<String> simpleRead(@RequestParam(value = "multipartFile") MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            return safetyEpMaintenanceTableService.readSafetyEpMaintenanceTableToDB(multipartFile.getOriginalFilename(), inputStream);
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }




    /**
     * 查询安环科维修数据列表
     */
    @PreAuthorize("@ss.hasPermi('safety:importTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(SafetyEpMaintenanceTable safetyEpMaintenanceTable) {
        startPage();
        List<SafetyEpMaintenanceTable> list = safetyEpMaintenanceTableService.selectSafetyEpMaintenanceTableList(safetyEpMaintenanceTable);
        return getDataTable(list);
    }

    /**
     * 导出安环科维修数据列表
     */
    @PreAuthorize("@ss.hasPermi('safety:importTable:export')")
    @Log(title = "安环科 维修数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafetyEpMaintenanceTable safetyEpMaintenanceTable) {
        List<SafetyEpMaintenanceTable> list = safetyEpMaintenanceTableService.selectSafetyEpMaintenanceTableList(safetyEpMaintenanceTable);
        ExcelUtil<SafetyEpMaintenanceTable> util = new ExcelUtil<SafetyEpMaintenanceTable>(SafetyEpMaintenanceTable.class);
        util.exportExcel(response, list, "安环科维修数据数据");
    }

    /**
     * 获取安环科维修数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('safety:importTable:query')")
    @GetMapping(value = "/{semId}")
    public AjaxResult getInfo(@PathVariable("semId") String semId) {
        return success(safetyEpMaintenanceTableService.selectSafetyEpMaintenanceTableBySemId(semId));
    }

    /**
     * 新增安环科维修数据
     */
    @PreAuthorize("@ss.hasPermi('safety:importTable:add')")
    @Log(title = "安环科 维修数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SafetyEpMaintenanceTable safetyEpMaintenanceTable) {
        return toAjax(safetyEpMaintenanceTableService.insertSafetyEpMaintenanceTable(safetyEpMaintenanceTable));
    }

    /**
     * 修改安环科维修数据
     */
    @PreAuthorize("@ss.hasPermi('safety:importTable:edit')")
    @Log(title = "安环科维修数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafetyEpMaintenanceTable safetyEpMaintenanceTable) {
        return toAjax(safetyEpMaintenanceTableService.updateSafetyEpMaintenanceTable(safetyEpMaintenanceTable));
    }

    /**
     * 删除安环科维修数据
     */
    @PreAuthorize("@ss.hasPermi('safety:importTable:remove')")
    @Log(title = "安环科维修数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{semIds}")
    public AjaxResult remove(@PathVariable String[] semIds) {
        return toAjax(safetyEpMaintenanceTableService.deleteSafetyEpMaintenanceTableBySemIds(semIds));
    }
}
