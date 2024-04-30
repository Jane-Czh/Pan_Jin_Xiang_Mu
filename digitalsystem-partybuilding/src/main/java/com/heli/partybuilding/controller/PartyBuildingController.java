package com.heli.partybuilding.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.heli.partybuilding.domain.PartyBuilding;
import com.heli.partybuilding.service.IPartyBuildingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * @description: [党建]指标填报Controller
 * @author: hong
 * @date: 2024/4/9 15:31
 **/
@RestController
@RequestMapping("/partybuilding")
public class PartyBuildingController extends BaseController {
    @Autowired
    private IPartyBuildingService partyBuildingService;


    /**
     * @description: 按年查询党建数据
     * @author: hong
     * @date: 2024/4/9 15:37
     * @param:
     * @return:
     **/
    @GetMapping("/display/{year}")
    public TableDataInfo list(@PathVariable("year") int year) {
        List<PartyBuilding> list = partyBuildingService.selectPartyBuildingListByYear(year);
        return getDataTable(list);
    }

    /**
     * 查询[党建]指标填报列表
     */
    @PreAuthorize("@ss.hasPermi('partybuilding:data:list')")
    @GetMapping("/data/list")
    public TableDataInfo list(PartyBuilding partyBuilding) {
        startPage();
        List<PartyBuilding> list = partyBuildingService.selectPartyBuildingList(partyBuilding);
        return getDataTable(list);
    }

    /**
     * 导出[党建]指标填报列表
     */
    @PreAuthorize("@ss.hasPermi('partybuilding:data:export')")
    @Log(title = "[党建]指标填报", businessType = BusinessType.EXPORT)
    @PostMapping("/data/export")
    public void export(HttpServletResponse response, PartyBuilding partyBuilding) {
        List<PartyBuilding> list = partyBuildingService.selectPartyBuildingList(partyBuilding);
        ExcelUtil<PartyBuilding> util = new ExcelUtil<PartyBuilding>(PartyBuilding.class);
        util.exportExcel(response, list, "[党建]指标填报数据");
    }

    /**
     * 获取[党建]指标填报详细信息
     */
    @PreAuthorize("@ss.hasPermi('partybuilding:data:query')")
    @GetMapping(value = "/data/{pbId}")
    public AjaxResult getInfo(@PathVariable("pbId") String pbId) {
        return success(partyBuildingService.selectPartyBuildingByPbId(pbId));
    }

    /**
     * 新增[党建]指标填报
     */
    @PreAuthorize("@ss.hasPermi('partybuilding:data:add')")
    @Log(title = "[党建]指标填报", businessType = BusinessType.INSERT)
    @PostMapping("/data")
    public AjaxResult add(@RequestBody PartyBuilding partyBuilding) {
        if (partyBuildingService.checkPartyBuildingDataIsExisted(partyBuilding.getYearAndMonth())){
            return AjaxResult.error("当月党建排名数据已填报");
        }
        return toAjax(partyBuildingService.insertPartyBuilding(partyBuilding));
    }

    /**
     * 修改[党建]指标填报
     */
    @PreAuthorize("@ss.hasPermi('partybuilding:data:edit')")
    @Log(title = "[党建]指标填报", businessType = BusinessType.UPDATE)
    @PutMapping("/data")
    public AjaxResult edit(@RequestBody PartyBuilding partyBuilding) {
        return toAjax(partyBuildingService.updatePartyBuilding(partyBuilding));
    }

    /**
     * 删除[党建]指标填报
     */
    @PreAuthorize("@ss.hasPermi('partybuilding:data:remove')")
    @Log(title = "[党建]指标填报", businessType = BusinessType.DELETE)
    @DeleteMapping("/data/{pbIds}")
    public AjaxResult remove(@PathVariable String[] pbIds) {
        return toAjax(partyBuildingService.deletePartyBuildingByPbIds(pbIds));
    }
}
