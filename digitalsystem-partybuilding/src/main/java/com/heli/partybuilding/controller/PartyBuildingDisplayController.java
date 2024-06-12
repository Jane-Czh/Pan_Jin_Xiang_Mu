package com.heli.partybuilding.controller;

import com.heli.partybuilding.service.IPartyBuildingDisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.partybuilding.controller
 * @Author: hong
 * @CreateTime: 2024-04-26  15:23
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/partybuilding/display")
public class PartyBuildingDisplayController extends BaseController {

    @Autowired
    private IPartyBuildingDisplayService partyBuildingDisplayService;

    /**
     * 当月在制品存货额 指标27
     */

    @PreAuthorize("@ss.hasPermi('partybuilding:display:rank')")
    @PostMapping("/rank")
    public TableDataInfo partyBuildingRank(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = partyBuildingDisplayService.selectPartyBuildingRank(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

}
