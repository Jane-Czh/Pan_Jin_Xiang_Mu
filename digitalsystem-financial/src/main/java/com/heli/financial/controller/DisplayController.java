package com.heli.financial.controller;

import com.heli.financial.domain.DisplayEntity;
import com.heli.financial.service.DisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: hong
 * @CreateTime: 2024-04-01  12:19
 * @Description: TODO
 */
@RestController
@RequestMapping("/financial/display")
public class DisplayController extends BaseController {
    @Autowired
    private DisplayService displayService;

    @GetMapping("/list")
    public TableDataInfo list(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectDisplayData(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

}
