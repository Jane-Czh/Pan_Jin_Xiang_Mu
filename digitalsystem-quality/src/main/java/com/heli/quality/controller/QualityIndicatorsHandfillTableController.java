package com.heli.quality.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
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
import com.heli.quality.domain.QualityIndicatorsHandfillTable;
import com.heli.quality.service.IQualityIndicatorsHandfillTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * [质量]指标填报Controller
 *
 * @author ruoyi
 * @date 2024-04-26
 */
@Slf4j
@RestController
@RequestMapping("/quality/data/handfill")
public class QualityIndicatorsHandfillTableController extends BaseController {
    @Autowired
    private IQualityIndicatorsHandfillTableService qualityIndicatorsHandfillTableService;


    /**
     * @description: 获取最大年月的数据
     * @author: hong
     * @date: 2024/7/26 15:24
     */
    @PreAuthorize("@ss.hasPermi('quality:data:sum')")
    @PostMapping("/newData")
    public AjaxResult selectMaxMonthHandfill() {
        QualityIndicatorsHandfillTable infoMaxMonth = qualityIndicatorsHandfillTableService.selectMaxMonthHandfill();
        log.info("填报最大数据:{}", infoMaxMonth);
        return AjaxResult.success(infoMaxMonth);
    }

    /**
     * 查询[质量]指标填报列表
     */
    @PreAuthorize("@ss.hasPermi('quality:handFill:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityIndicatorsHandfillTable qualityIndicatorsHandfillTable) {
        startPage();
        List<QualityIndicatorsHandfillTable> list = qualityIndicatorsHandfillTableService.selectQualityIndicatorsHandfillTableList(qualityIndicatorsHandfillTable);
        return getDataTable(list);
    }


    /**
     * 获取[质量]指标填报详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:handFill:query')")
    @GetMapping(value = "/{qihfId}")
    public AjaxResult getInfo(@PathVariable("qihfId") Long qihfId) {
        return success(qualityIndicatorsHandfillTableService.selectQualityIndicatorsHandfillTableByQihfId(qihfId));
    }

    /**
     * 新增[质量]指标填报
     */
    @PreAuthorize("@ss.hasPermi('quality:handFill:add')")
    @Log(title = "[质量]指标填报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualityIndicatorsHandfillTable qualityIndicatorsHandfillTable) {
        logger.info(String.valueOf(qualityIndicatorsHandfillTable));
        if (qualityIndicatorsHandfillTableService.checkQualityFillingDataIsExisted(qualityIndicatorsHandfillTable.getYearAndMonth())){
            return AjaxResult.error("当月数据已填报");
        }
        qualityIndicatorsHandfillTable.setCreateBy(getUsername());
        return toAjax(qualityIndicatorsHandfillTableService.insertQualityIndicatorsHandfillTable(qualityIndicatorsHandfillTable));
    }

    /**
     * 修改[质量]指标填报
     */
    @PreAuthorize("@ss.hasPermi('quality:handFill:edit')")
    @Log(title = "[质量]指标填报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityIndicatorsHandfillTable qualityIndicatorsHandfillTable) {
        return toAjax(qualityIndicatorsHandfillTableService.updateQualityIndicatorsHandfillTable(qualityIndicatorsHandfillTable));
    }

    /**
     * 删除[质量]指标填报
     */
    @PreAuthorize("@ss.hasPermi('quality:handFill:remove')")
    @Log(title = "[质量]指标填报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{qihfIds}")
    public AjaxResult remove(@PathVariable Long[] qihfIds) {
        return toAjax(qualityIndicatorsHandfillTableService.deleteQualityIndicatorsHandfillTableByQihfIds(qihfIds));
    }
}
