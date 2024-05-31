package com.ruoyi.file.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.file.Word2PdfAsposeUtil;
import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.service.IRegulationsInfoTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件管理Controller
 * 
 * @author ruoyi
 * @date 2024-04-09
 */
@RestController
@RequestMapping("/file/filemanagement")
public class RegulationsInfoTableController extends BaseController {
    @Autowired
    private IRegulationsInfoTableService regulationsInfoTableService;

    /**
     * 查询文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:list')")
    @GetMapping("/list")
    public TableDataInfo list(RegulationsInfoTable regulationsInfoTable) {
        startPage();
        List<RegulationsInfoTable> list = regulationsInfoTableService.selectRegulationsInfoTableList(regulationsInfoTable);
        return getDataTable(list);
    }



    /**
     * 查询历史版本文件列表
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:list')")
    @GetMapping(value = "/history/{currentId}")
    public List<RegulationsInfoTable> getRegulationsHistory(@PathVariable Long currentId) {
//        startPage();
        System.out.println("123 currentId" + currentId);
        List<RegulationsInfoTable> list = regulationsInfoTableService.getRegulationsHistory(currentId);
        return list;
    }

    /**
     * 导出文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:export')")
    @Log(title = "文件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RegulationsInfoTable regulationsInfoTable) {
        List<RegulationsInfoTable> list = regulationsInfoTableService.selectRegulationsInfoTableList(regulationsInfoTable);
        ExcelUtil<RegulationsInfoTable> util = new ExcelUtil<RegulationsInfoTable>(RegulationsInfoTable.class);
        util.exportExcel(response, list, "文件管理数据");
    }

    /**
     * 获取文件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:query')")
    @GetMapping(value = "/{regulationsId}")
    public AjaxResult getInfo(@PathVariable("regulationsId") Long regulationsId) {
        return success(regulationsInfoTableService.selectRegulationsInfoTableByRegulationsId(regulationsId));
    }

    /**
     * 新增文件管理
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:add')")
    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RegulationsInfoTable regulationsInfoTable)
    {
        return success(regulationsInfoTableService.insertRegulationsInfoTable(regulationsInfoTable));
    }

    /**
     * 修改文件管理
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:edit')")
    @Log(title = "文件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RegulationsInfoTable regulationsInfoTable) {
        return toAjax(regulationsInfoTableService.updateRegulationsInfoTable(regulationsInfoTable));
    }

    /**
     * 删除文件管理
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:remove')")
    @Log(title = "文件管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{regulationsIds}")
    public AjaxResult remove(@PathVariable Long[] regulationsIds) {
        return toAjax(regulationsInfoTableService.deleteRegulationsInfoTableByRegulationsIds(regulationsIds));
    }

    /**
     * word转pdf
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:list')")
    @PostMapping("/convert")
    public ResponseEntity<String> wordToPdf(@RequestParam String inPath,@RequestParam String outPath) {

        // 调用 Word2PdfAsposeUtil 类的方法进行 Word 转 PDF 操作
        boolean success = Word2PdfAsposeUtil.doc2pdf(inPath, outPath);
        if (success) {
            System.out.println("转换成功");
            return ResponseEntity.ok("Word 转 PDF 成功");
        } else {
            System.out.println("转换失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Word 转 PDF 失败");
        }
    }
}


