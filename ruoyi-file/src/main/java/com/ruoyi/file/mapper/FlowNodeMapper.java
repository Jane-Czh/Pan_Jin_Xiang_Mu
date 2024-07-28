package com.ruoyi.file.mapper;


import java.util.List;
import com.ruoyi.file.domain.FlowNode;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-28
 */
public interface FlowNodeMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public List<FlowNode> selectFlowNodeById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param flowNode 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FlowNode> selectFlowNodeList(FlowNode flowNode);

    /**
     * 新增【请填写功能名称】
     *
     * @param flowNode 【请填写功能名称】
     * @return 结果
     */
    public int insertFlowNode(FlowNode flowNode);

    /**
     * 修改【请填写功能名称】
     *
     * @param flowNode 【请填写功能名称】
     * @return 结果
     */
    public int updateFlowNode(FlowNode flowNode);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteFlowNodeById(String id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFlowNodeByIds(String[] ids);
}

