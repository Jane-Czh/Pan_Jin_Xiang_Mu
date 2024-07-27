package com.ruoyi.ef.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.ruoyi.ef.entity.NodeEntity;

import com.ruoyi.ef.service.INodeService;
import com.ruoyi.ef.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @author liuchengbiao
 * @date 2020-05-23 20:34
 */
@RestController
@RequestMapping("/node")
public class NodeController extends SuperController<NodeEntity> {

    @Autowired
    private INodeService nodeService;

    @GetMapping("/{id}")
    public R save(@PathVariable("id") String id) {
        NodeEntity nodeEntity = nodeService.getById(id);
        return success(nodeEntity);
    }

    /**
     * 新增
     *
     * @param node
     * @return
     */
    @PostMapping("/saveNode")
    public R save(@RequestBody NodeEntity node) {

//        System.out.println("save node===>"+node);

        // 处理state -> 绑定的[制度文件ids] -> 其序列化和反序列化
        if (!node.getState().equals("no")) {
            String stateStr = node.getState();
            // 检查并移除首尾双引号
            if (stateStr.startsWith("\"") && stateStr.endsWith("\"")) {
                stateStr = stateStr.substring(1, stateStr.length() - 1);
            }
            List<Integer> list = JSONObject.parseArray(stateStr, Integer.class);
            node.setState(list.toString());
        }

        // 处理type -> 绑定的[表单文件ids] -> 同上
        if (!node.getType().equals("no")) {
            String typeStr = node.getType();
            // 检查并移除首尾双引号
            if (typeStr.startsWith("\"") && typeStr.endsWith("\"")) {
                typeStr = typeStr.substring(1, typeStr.length() - 1);
            }
            List<Integer> list2 = JSONObject.parseArray(typeStr, Integer.class);
            node.setType(list2.toString());
        }

        System.out.println("node date==>"+node.getDate());

//        System.out.println("new node==>"+node);
        nodeService.save(node);
        return success();
    }

    /**
     * 更新
     *
     * @param node
     * @return
     */
    @PutMapping
    public R update(@RequestBody NodeEntity node) {
        nodeService.updateById(node);
        return success();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String id) {
        nodeService.delete(id);
        return success();
    }

    /**
     * 更改位置坐标
     *
     * @param node
     * @return
     */
    @PutMapping("/changeSite")
    public R changeSite(@RequestBody NodeEntity node) {
        nodeService.changeSite(node.getId(), node.getLeft(), node.getTop());
        return success();
    }
}
