package com.ruoyi.file.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.file.util.Word2PdfAsposeUtil;
import com.ruoyi.file.Word2PdfAsposeUtil;
import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.entity.regulationRespondEntity;
import com.ruoyi.file.service.IRegulationsInfoTableService;
import com.ruoyi.file.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * 指标：制度修订频率
     */
    @PostMapping("/revisionCounts")
    public List<regulationRespondEntity> revisionCounts(@RequestBody RegulationsInfoTable time) {
        System.out.println("制度指标频率");
        System.out.println("time start ==>" + time.getStartTime() + "// " + time.getEndTime());
        List<regulationRespondEntity> list = regulationsInfoTableService.selectRevisionFrequency(time.getStartTime(),time.getEndTime(),time.getRegulationsId());
        System.out.println("list11111=>" + list);
        return list;
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



//
//    @PostMapping("/convert")
//    public void previewFile(@RequestParam String inPath,@RequestParam String fileName,HttpServletResponse response)throws IOException {
//
//        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//        if("pdf".equalsIgnoreCase(suffix)){
//            String type = new MimetypesFileTypeMap().getContentType(fileName);
//            response.setHeader("Content-type",type);
//            String newFileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
//            // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
//            response.setHeader("Content-Disposition", "attachment;filename=" + newFileName);
//            FileUtil.downloadFile(fileName,inPath,response);
//
//        }
//
//        String filePath = inPath + fileName.substring(0,fileName.lastIndexOf(".")) + ".pdf";
//        if( FileUtil.checkFileExist(filePath)){
//            FileUtil.previewFile1(filePath,response);
//        }else {
//            String newFilePath=null;
//
//                //DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF
//                newFilePath = FileUtil.doc2pdf(fileName, inPath);
//
//
//
//            if(StringUtils.isNotBlank(newFilePath)){
//                FileUtil.previewFile1(newFilePath,response);
//            }
//        }
//
//
//
//    }

}


