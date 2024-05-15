package com.heli.safety.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.heli.safety.service.ISafetyEpMaintenanceTableService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.heli.safety.domain.SafetyEp;
import com.heli.safety.service.ISafetyEpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * [安全环保]指标填报Controller
 *
 * @author ruoyi
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/safety/data")
public class SafetyEpDataController extends BaseController {
    @Autowired
    private ISafetyEpService safetyEpService;
    @Autowired
    private ISafetyEpMaintenanceTableService safetyEpMaintenanceTableService;

    private static final Logger log = LoggerFactory.getLogger(SafetyEpDataController.class);




    /**
     * @return
     * @description: 上传 维修数据表excel，并存入数据库
     * @author: hong
     * @date: 2024/4/11 16:38
     */
    @PostMapping("/importTable")
    public R<String> simpleRead(Date yearAndMonth, @RequestParam(value = "multipartFile") MultipartFile multipartFile) {

        //检查当月数据是否上传
        if (safetyEpMaintenanceTableService.checkSafetyEpMaintenanceTableIsExisted(yearAndMonth)){
            return R.fail("当月数据已上传");
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            return safetyEpMaintenanceTableService.readSafetyEpMaintenanceTableToDB(multipartFile.getOriginalFilename(), inputStream, yearAndMonth);
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }



    }


    /**
     * 新增[安全环保]-指标填报
     */
    @PreAuthorize("@ss.hasPermi('safety:data:add')")
    @Log(title = "[安全环保]指标填报", businessType = BusinessType.INSERT)
    @PostMapping("/fillingData")
    public AjaxResult add(@RequestBody SafetyEp safetyEp) {
        if(safetyEpService.checkSafetyFillingDataIsExisted(safetyEp.getYearAndMonth()))
            return AjaxResult.error("当月数据已填报");

        safetyEp.setCreateBy(getUsername());
        safetyEp.setCreateTime(DateUtils.getNowDate());

        return toAjax(safetyEpService.InsertOrUpdateSafetyEp(safetyEp));
    }





    /**
     * 修改[安全环保]指标填报
     */
    @PreAuthorize("@ss.hasPermi('safety:data:edit')")
    @Log(title = "[安全环保]-已有指标修改", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafetyEp safetyEp) {
        return toAjax(safetyEpService.InsertOrUpdateSafetyEp(safetyEp));
    }








    /**
     * 查询[安全环保]指标填报列表
     */
    @PreAuthorize("@ss.hasPermi('safety:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(SafetyEp safetyEp) {
        startPage();
        List<SafetyEp> list = safetyEpService.selectSafetyEpList(safetyEp);
        return getDataTable(list);
    }

    /**
     * 导出[安全环保]指标填报列表
     */
    @PreAuthorize("@ss.hasPermi('safety:data:export')")
    @Log(title = "[安全环保]指标填报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafetyEp safetyEp) {
        List<SafetyEp> list = safetyEpService.selectSafetyEpList(safetyEp);
        ExcelUtil<SafetyEp> util = new ExcelUtil<SafetyEp>(SafetyEp.class);
        util.exportExcel(response, list, "[安全环保]指标填报数据");
    }

    /**
     * 获取[安全环保]指标填报详细信息
     */
    @PreAuthorize("@ss.hasPermi('safety:data:query')")
    @GetMapping(value = "/{safetyEpId}")
    public AjaxResult getInfo(@PathVariable("safetyEpId") Long safetyEpId) {
        return success(safetyEpService.selectSafetyEpBySafetyEpId(safetyEpId));
    }



    /**
     * 删除[安全环保]指标填报
     */
    @PreAuthorize("@ss.hasPermi('safety:data:remove')")
    @Log(title = "[安全环保]指标填报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{safetyEpIds}")
    public AjaxResult remove(@PathVariable Long[] safetyEpIds) {
        return toAjax(safetyEpService.deleteSafetyEpBySafetyEpIds(safetyEpIds));
    }





}
