package com.heli.quality.controller;

import com.heli.quality.service.IQualityDisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.quality.controller
 * @Author: hong
 * @CreateTime: 2024-04-26  14:29
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/quality/display")
public class QualityDisplayController extends BaseController {
    @Autowired
    private IQualityDisplayService qualityDisplayService;

    /**
     * 质量考核季度排名 指标63
     */
    @PostMapping("/quarterlyRank")
    public TableDataInfo quarterlyRank(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectQuarterlyRank(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 平均无故障时间 指标64
     */
    @PostMapping("/meantimeWithoutFailure")
    public TableDataInfo meantimeWithoutFailure(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectMeantimeWithoutFailure(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

}
