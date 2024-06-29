package com.ruoyi.ef.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.ef.entity.NodeEntity;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author liuchengbiao
 * @date 2020-05-22 21:28
 */

public interface INodeService extends IService<NodeEntity> {
    /**
     * 根据项目ID查询所有的节点
     *
     * @param projectId
     * @return
     */
    List<NodeEntity> queryByProjectId(String projectId);

    void deleteByProjectId(String projectId);

    /**
     * 更改坐标
     *
     * @param nodeId
     * @param left
     * @param top
     */
    void changeSite(String nodeId, String left, String top);

    /**
     * 删除
     *
     * @param nodeId
     */
    void delete(String nodeId);
}
