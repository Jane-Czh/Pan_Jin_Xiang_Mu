package com.ruoyi.file.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 flow_node
 *
 * @author ruoyi
 * @date 2024-07-28
 */
public class FlowNode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 项目ID */
    @Excel(name = "项目ID")
    private String projectId;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 坐标 */
    @Excel(name = "坐标")
    private String left;

    /** 坐标 */
    @Excel(name = "坐标")
    private String top;

    /** 图标 */
    @Excel(name = "图标")
    private String ico;

    /** 状态/绑定表单 */
    @Excel(name = "状态/绑定表单")
    private String state;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public String getProjectId()
    {
        return projectId;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setLeft(String left)
    {
        this.left = left;
    }

    public String getLeft()
    {
        return left;
    }
    public void setTop(String top)
    {
        this.top = top;
    }

    public String getTop()
    {
        return top;
    }
    public void setIco(String ico)
    {
        this.ico = ico;
    }

    public String getIco()
    {
        return ico;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("projectId", getProjectId())
                .append("type", getType())
                .append("name", getName())
                .append("left", getLeft())
                .append("top", getTop())
                .append("ico", getIco())
                .append("state", getState())
                .toString();
    }
}