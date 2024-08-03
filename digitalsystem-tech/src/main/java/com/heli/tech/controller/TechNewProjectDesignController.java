package com.heli.tech.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.R;
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
import com.heli.tech.domain.TechNewProjectDesign;
import com.heli.tech.service.ITechNewProjectDesignService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 技术-项目计划完成统计Controller
 *
 * @author hong
 * @date 2024-08-03
 */
@Slf4j
@RestController
@RequestMapping("/tech/data/newProject")
public class TechNewProjectDesignController extends BaseController {
    @Autowired
    private ITechNewProjectDesignService techNewProjectDesignService;


    /**
     * @description: 检查当月数据是否上传
     * @author: hong
     * @date: 2024/8/2 17:14
     * @version: 1.0
     */
    @PostMapping("/check")
    public Boolean checkTechNewProjectDesignIsExisted(Date yearAndMonth) {
        Boolean status = techNewProjectDesignService.checkTechNewProjectDesignIsExisted(yearAndMonth);
        log.info("检查当月数据是否上传"+status);
        return status;
    }

    /**
     * @description: 非标订单导入
     * @author: hong
     * @date: 2024/8/2 13:58
     * @version: 1.0
     */
    @PreAuthorize("@ss.hasPermi('tech:newProject:import')")
    @PostMapping("/read")
    public R<String> simpleRead(Date yearAndMonth, MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {

            techNewProjectDesignService.readSalaryExcelToDB(multipartFile.getOriginalFilename(), inputStream, yearAndMonth);

            log.info("计算完毕");

            return R.ok("上传成功");
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }


    /**
     * 查询技术-项目计划完成统计列表
     */
    @PreAuthorize("@ss.hasPermi('tech:newProject:list')")
    @GetMapping("/list")
    public TableDataInfo list(TechNewProjectDesign techNewProjectDesign) {
        startPage();
        List<TechNewProjectDesign> list = techNewProjectDesignService.selectTechNewProjectDesignList(techNewProjectDesign);
        return getDataTable(list);
    }

    /**
     * 导出技术-项目计划完成统计列表
     */
    @PreAuthorize("@ss.hasPermi('tech:newProject:export')")
    @Log(title = "技术-项目计划完成统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TechNewProjectDesign techNewProjectDesign) {
        List<TechNewProjectDesign> list = techNewProjectDesignService.selectTechNewProjectDesignList(techNewProjectDesign);
        ExcelUtil<TechNewProjectDesign> util = new ExcelUtil<TechNewProjectDesign>(TechNewProjectDesign.class);
        util.exportExcel(response, list, "技术-项目计划完成统计数据");
    }

    /**
     * 获取技术-项目计划完成统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('tech:newProject:query')")
    @GetMapping(value = "/{tdId}")
    public AjaxResult getInfo(@PathVariable("tdId") Long tdId) {
        return success(techNewProjectDesignService.selectTechNewProjectDesignByTdId(tdId));
    }

    /**
     * 新增技术-项目计划完成统计
     */
    @PreAuthorize("@ss.hasPermi('tech:newProject:add')")
    @Log(title = "技术-项目计划完成统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TechNewProjectDesign techNewProjectDesign) {
        return toAjax(techNewProjectDesignService.insertTechNewProjectDesign(techNewProjectDesign));
    }

    /**
     * 修改技术-项目计划完成统计
     */
    @PreAuthorize("@ss.hasPermi('tech:newProject:edit')")
    @Log(title = "技术-项目计划完成统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TechNewProjectDesign techNewProjectDesign) {
        return toAjax(techNewProjectDesignService.updateTechNewProjectDesign(techNewProjectDesign));
    }

    /**
     * 删除技术-项目计划完成统计
     */
    @PreAuthorize("@ss.hasPermi('tech:newProject:remove')")
    @Log(title = "技术-项目计划完成统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tdIds}")
    public AjaxResult remove(@PathVariable Long[] tdIds) {
        return toAjax(techNewProjectDesignService.deleteTechNewProjectDesignByTdIds(tdIds));
    }
}
