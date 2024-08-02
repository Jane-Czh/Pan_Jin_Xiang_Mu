package com.heli.quality.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.heli.quality.domain.QualityIndicatorsMetrics;
import com.ruoyi.common.exception.ServiceException;
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
import com.heli.quality.domain.QualityInspectionRecord;
import com.heli.quality.service.IQualityInspectionRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 质检部分字段Controller
 *
 * @author ruoyi
 * @date 2024-05-16
 */
@Slf4j
@RestController
@RequestMapping("/quality/data/inspection")
public class QualityInspectionRecordController extends BaseController {
    @Autowired
    private IQualityInspectionRecordService qualityInspectionRecordService;


    /**
     * @description: 获取最大年月的数据
     * @author: hong
     * @date: 2024/7/26 15:24
     */
    @PreAuthorize("@ss.hasPermi('quality:data:sum')")
    @PostMapping("/newData")
    public AjaxResult selectMaxMonthMetrics() {
        QualityInspectionRecord infoMaxMonth = qualityInspectionRecordService.selectMaxMonthInspection();
        log.info("infoMaxMonth:{}", infoMaxMonth);
        return AjaxResult.success(infoMaxMonth);
    }


    @PostMapping("/read")
    public AjaxResult importTable(Date yearAndMonth, MultipartFile excelFile) {
        //检查当月数据是否上传
        System.out.println(excelFile.getOriginalFilename());
        System.out.println(yearAndMonth);
        if (qualityInspectionRecordService.checkQualityInspectionTableIsExisted(yearAndMonth)) {
            return error("当月数据已上传");
        }
        try {
            qualityInspectionRecordService.importQualityInspectionTable(excelFile, yearAndMonth, getUsername());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }


    /**
     * 查询质检部分字段列表
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityInspectionRecord qualityInspectionRecord) {
        startPage();
        List<QualityInspectionRecord> list = qualityInspectionRecordService.selectQualityInspectionRecordList(qualityInspectionRecord);
        return getDataTable(list);
    }


    /**
     * 获取质检部分字段详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:query')")
    @GetMapping(value = "/{qiId}")
    public AjaxResult getInfo(@PathVariable("qiId") Long qiId) {
        return success(qualityInspectionRecordService.selectQualityInspectionRecordByQiId(qiId));
    }

    /**
     * 新增质检部分字段
     */
//    @PreAuthorize("@ss.hasPermi('quality:inspection:add')")
//    @Log(title = "质检部分字段", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody QualityInspectionRecord qualityInspectionRecord) {
//        return toAjax(qualityInspectionRecordService.insertQualityInspectionRecord(qualityInspectionRecord));
//    }

    /**
     * 修改质检部分字段
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:edit')")
    @Log(title = "质检部分字段", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityInspectionRecord qualityInspectionRecord) {
        qualityInspectionRecord.setUpdateBy(getUsername());
        return toAjax(qualityInspectionRecordService.updateQualityInspectionRecord(qualityInspectionRecord));
    }

    /**
     * 删除质检部分字段
     */
    @PreAuthorize("@ss.hasPermi('quality:inspection:remove')")
    @Log(title = "质检部分字段", businessType = BusinessType.DELETE)
    @DeleteMapping("/{qiIds}")
    public AjaxResult remove(@PathVariable Long[] qiIds) {
        return toAjax(qualityInspectionRecordService.deleteQualityInspectionRecordByQiIds(qiIds));
    }
}
