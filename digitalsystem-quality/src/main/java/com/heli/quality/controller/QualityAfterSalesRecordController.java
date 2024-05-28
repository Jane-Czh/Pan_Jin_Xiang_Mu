package com.heli.quality.controller;

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
import com.heli.quality.domain.QualityAfterSalesRecord;
import com.heli.quality.service.IQualityAfterSalesRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 售后台账部分字段Controller
 *
 * @author hong
 * @date 2024-05-16
 */
@RestController
@RequestMapping("/quality/data/after-sales")
public class QualityAfterSalesRecordController extends BaseController {
    @Autowired
    private IQualityAfterSalesRecordService qualityAfterSalesRecordService;

    private static final Logger log = LoggerFactory.getLogger(QualityAfterSalesRecordController.class);


    /**
     * @return
     * @description: 上传工资表，存入数据库,计算相关数据
     * @author: hong
     * @date: 2024/4/11 16:38
     */
    @PostMapping("/read")
    public R<String> simpleRead(Date yearAndMonth, MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {

            qualityAfterSalesRecordService.readSalesAfterExcelToDB(multipartFile.getOriginalFilename(), inputStream, yearAndMonth);

            return R.ok("上传成功");
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }

    /**
     * 查询售后台账部分字段列表
     */
//    @PreAuthorize("@ss.hasPermi('quality:salesafter:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(QualityAfterSalesRecord qualityAfterSalesRecord) {
//        startPage();
//        List<QualityAfterSalesRecord> list = qualityAfterSalesRecordService.selectQualityAfterSalesRecordList(qualityAfterSalesRecord);
//        return getDataTable(list);
//    }

    /**
     * 导出售后台账部分字段列表
     */
//    @PreAuthorize("@ss.hasPermi('quality:salesafter:export')")
//    @Log(title = "售后台账部分字段", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, QualityAfterSalesRecord qualityAfterSalesRecord) {
//        List<QualityAfterSalesRecord> list = qualityAfterSalesRecordService.selectQualityAfterSalesRecordList(qualityAfterSalesRecord);
//        ExcelUtil<QualityAfterSalesRecord> util = new ExcelUtil<QualityAfterSalesRecord>(QualityAfterSalesRecord.class);
//        util.exportExcel(response, list, "售后台账部分字段数据");
//    }

    /**
     * 获取售后台账部分字段详细信息
     */
//    @PreAuthorize("@ss.hasPermi('quality:salesafter:query')")
//    @GetMapping(value = "/{qaId}")
//    public AjaxResult getInfo(@PathVariable("qaId") Long qaId) {
//        return success(qualityAfterSalesRecordService.selectQualityAfterSalesRecordByQaId(qaId));
//    }

    /**
     * 新增售后台账部分字段
     */
//    @PreAuthorize("@ss.hasPermi('quality:salesafter:add')")
//    @Log(title = "售后台账部分字段", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody QualityAfterSalesRecord qualityAfterSalesRecord) {
//        return toAjax(qualityAfterSalesRecordService.insertQualityAfterSalesRecord(qualityAfterSalesRecord));
//    }

    /**
     * 修改售后台账部分字段
     */
//    @PreAuthorize("@ss.hasPermi('quality:salesafter:edit')")
//    @Log(title = "售后台账部分字段", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody QualityAfterSalesRecord qualityAfterSalesRecord) {
//        return toAjax(qualityAfterSalesRecordService.updateQualityAfterSalesRecord(qualityAfterSalesRecord));
//    }

    /**
     * 删除售后台账部分字段
     */
//    @PreAuthorize("@ss.hasPermi('quality:salesafter:remove')")
//    @Log(title = "售后台账部分字段", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{qaIds}")
//    public AjaxResult remove(@PathVariable Long[] qaIds) {
//        return toAjax(qualityAfterSalesRecordService.deleteQualityAfterSalesRecordByQaIds(qaIds));
//    }
}
