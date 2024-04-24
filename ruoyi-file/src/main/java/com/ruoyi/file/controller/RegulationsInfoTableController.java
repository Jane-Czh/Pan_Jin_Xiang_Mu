package com.ruoyi.file.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.service.IRegulationsInfoTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
public class RegulationsInfoTableController extends BaseController
{
    @Autowired
    private IRegulationsInfoTableService regulationsInfoTableService;

    /**
     * 查询文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:list')")
    @GetMapping("/list")
    public TableDataInfo list(RegulationsInfoTable regulationsInfoTable)
    {
        startPage();
        List<RegulationsInfoTable> list = regulationsInfoTableService.selectRegulationsInfoTableList(regulationsInfoTable);
        return getDataTable(list);
    }

    /**
     * 导出文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:export')")
    @Log(title = "文件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RegulationsInfoTable regulationsInfoTable)
    {
        List<RegulationsInfoTable> list = regulationsInfoTableService.selectRegulationsInfoTableList(regulationsInfoTable);
        ExcelUtil<RegulationsInfoTable> util = new ExcelUtil<RegulationsInfoTable>(RegulationsInfoTable.class);
        util.exportExcel(response, list, "文件管理数据");
    }

    /**
     * 获取文件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:query')")
    @GetMapping(value = "/{regulationsId}")
    public AjaxResult getInfo(@PathVariable("regulationsId") Long regulationsId)
    {
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
        return toAjax(regulationsInfoTableService.insertRegulationsInfoTable(regulationsInfoTable));
    }

    /**
     * 修改文件管理
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:edit')")
    @Log(title = "文件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RegulationsInfoTable regulationsInfoTable)
    {
        return toAjax(regulationsInfoTableService.updateRegulationsInfoTable(regulationsInfoTable));
    }

    /**
     * 删除文件管理
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:remove')")
    @Log(title = "文件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{regulationsIds}")
    public AjaxResult remove(@PathVariable Long[] regulationsIds)
    {
        return toAjax(regulationsInfoTableService.deleteRegulationsInfoTableByRegulationsIds(regulationsIds));
    }

//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(@RequestParam("file") MultipartFile file,@RequestParam("fileName") String fileName, RegulationsInfoTable regulationsInfoTable) throws IOException
//    {
//        // 上传文件路径
//        String filePath = RuoYiConfig.getUploadPath();
//        // 上传并返回新文件名称
//        String fileNewName = FileUploadUtils.upload(filePath, file);
//        fileInfo.setFileName(fileName);
//        fileInfo.setFilePath(fileNewName);
//        return toAjax();
//    }

}
