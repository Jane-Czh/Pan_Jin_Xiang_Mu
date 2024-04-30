package com.heli.enterprise.controller;

import com.heli.enterprise.service.IEnterpriseManagementDisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.DisplayEntity;
import com.ruoyi.common.core.domain.entity.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/enterprise/display")
public class EnterpriseManagementDisplayController extends BaseController{

    @Autowired
    private IEnterpriseManagementDisplayService enterpriseManagementDisplayService;

    @PostMapping("/employeesNumber")
    public TableDataInfo employeesNumber(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = enterpriseManagementDisplayService.selectEmployeesNumber(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    @PostMapping("/employeesAVGMonthlyNumber")
    public TableDataInfo employeesAVGMonthlyNumber(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = enterpriseManagementDisplayService.selectEmployeesAVGMonthlyNumber(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

}

