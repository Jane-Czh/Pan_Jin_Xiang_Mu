package com.ruoyi.file.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.FlowNodeMapper;
import com.ruoyi.file.domain.FlowNode;
import com.ruoyi.file.service.IFlowNodeService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-28
 */
@Service
public class FlowNodeServiceImpl implements IFlowNodeService
{
    @Autowired
    private FlowNodeMapper flowNodeMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public List<FlowNode> selectFlowNodeById(String id)
    {
        return flowNodeMapper.selectFlowNodeById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param flowNode 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FlowNode> selectFlowNodeList(FlowNode flowNode)
    {
        return flowNodeMapper.selectFlowNodeList(flowNode);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param flowNode 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFlowNode(FlowNode flowNode)
    {
        return flowNodeMapper.insertFlowNode(flowNode);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param flowNode 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFlowNode(FlowNode flowNode)
    {
        return flowNodeMapper.updateFlowNode(flowNode);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFlowNodeByIds(String[] ids)
    {
        return flowNodeMapper.deleteFlowNodeByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteFlowNodeById(String id)
    {
        return flowNodeMapper.deleteFlowNodeById(id);
    }
}

