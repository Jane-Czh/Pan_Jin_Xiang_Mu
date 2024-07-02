package com.ruoyi.ef.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.ef.entity.ModelEntity;
import com.ruoyi.ef.service.IModelService;
import com.ruoyi.ef.vo.R;
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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-07-01
 */
@RestController
@RequestMapping("/ef/model")
public class FlowModelController extends BaseController {
    @Autowired
    private IModelService iModelService;


    /**
     * 将创建的流程模型保存到数据库中
     * @param modelEntity
     * @return
     */
    @PreAuthorize("@ss.hasPermi('process:ef:addModel')")
    @PostMapping("/save")
    public String add(@RequestBody ModelEntity modelEntity) {
        System.out.println("modelEntity===============:"+modelEntity);
        int i = iModelService.insertModel(modelEntity);
        System.out.println("i======="+i);
        return "success";
    }


    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('ef:model:list')")
    @GetMapping("/list")
    public List<ModelEntity> list(ModelEntity modelEntity) {
        startPage();
        List<ModelEntity> lists = iModelService.selectModelList(modelEntity);
        return lists;
    }


    /**
     * 删除 指定id的用户自定义流程模板
     */
    @PreAuthorize("@ss.hasPermi('process:ef:deleteModel')")
    @DeleteMapping("/delete/{ids}")
    public String delete(@PathVariable("ids") String ids) {
        System.out.println("删除的id为" + ids);
        iModelService.deleteModelById(ids);
        //接口层同时删除了  与流程project相关的node、line
        return "success";

    }
}
