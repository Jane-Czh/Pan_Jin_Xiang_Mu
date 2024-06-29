package com.heli.enterprise.controller;

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
import com.heli.enterprise.domain.EnterpriseManagementSalaryTable;
import com.heli.enterprise.service.IEnterpriseManagementSalaryTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工资-部分字段Controller
 *
 * @author hong
 * @date 2024-05-07
 */
@RestController
@RequestMapping("/enterprise/salaryTable")
public class EnterpriseManagementSalaryTableController extends BaseController {

    @Autowired
    private IEnterpriseManagementSalaryTableService enterpriseManagementSalaryTableService;

    private static final Logger log = LoggerFactory.getLogger(EnterpriseManagementSalaryTableController.class);


    /**
     * @description: 上传 商品车台账excel，并存入数据库
     * @author: hong
     * @date: 2024/4/11 16:38
     *
     * @return*/
//    @PostMapping("/simpleRead")
//    public R<String> simpleRead(@RequestParam(value = "multipartFile") MultipartFile multipartFile) {
//
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            log.info("1233333333");
//            return enterpriseManagementSalaryTableService.readSalaryExcelToDB(multipartFile.getOriginalFilename(), inputStream);
//        } catch (Exception e) {
//            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
//            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
//        }
//    }




    /**
     * 查询工资-部分字段列表
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:salaryTable:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(EnterpriseManagementSalaryTable enterpriseManagementSalaryTable) {
//        startPage();
//        List<EnterpriseManagementSalaryTable> list = enterpriseManagementSalaryTableService.selectEnterpriseManagementSalaryTableList(enterpriseManagementSalaryTable);
//        return getDataTable(list);
//    }

    /**
     * 导出工资-部分字段列表
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:salaryTable:export')")
//    @Log(title = "工资-部分字段", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, EnterpriseManagementSalaryTable enterpriseManagementSalaryTable) {
//        List<EnterpriseManagementSalaryTable> list = enterpriseManagementSalaryTableService.selectEnterpriseManagementSalaryTableList(enterpriseManagementSalaryTable);
//        ExcelUtil<EnterpriseManagementSalaryTable> util = new ExcelUtil<EnterpriseManagementSalaryTable>(EnterpriseManagementSalaryTable.class);
//        util.exportExcel(response, list, "工资-部分字段数据");
//    }

    /**
     * 获取工资-部分字段详细信息
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:salaryTable:query')")
//    @GetMapping(value = "/{esId}")
//    public AjaxResult getInfo(@PathVariable("esId") Long esId) {
//        return success(enterpriseManagementSalaryTableService.selectEnterpriseManagementSalaryTableByEsId(esId));
//    }

    /**
     * 新增工资-部分字段
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:salaryTable:add')")
//    @Log(title = "工资-部分字段", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody EnterpriseManagementSalaryTable enterpriseManagementSalaryTable) {
//        return toAjax(enterpriseManagementSalaryTableService.insertEnterpriseManagementSalaryTable(enterpriseManagementSalaryTable));
//    }

    /**
     * 修改工资-部分字段
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:salaryTable:edit')")
//    @Log(title = "工资-部分字段", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody EnterpriseManagementSalaryTable enterpriseManagementSalaryTable) {
//        return toAjax(enterpriseManagementSalaryTableService.updateEnterpriseManagementSalaryTable(enterpriseManagementSalaryTable));
//    }

    /**
     * 删除工资-部分字段
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:salaryTable:remove')")
//    @Log(title = "工资-部分字段", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{esIds}")
//    public AjaxResult remove(@PathVariable Long[] esIds) {
//        return toAjax(enterpriseManagementSalaryTableService.deleteEnterpriseManagementSalaryTableByEsIds(esIds));
//    }
}
