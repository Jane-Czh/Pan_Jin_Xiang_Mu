package com.ruoyi.market.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 主要车型维护对象 Market_Car_Type
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
public class MarketCarType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long mctId;

    /** 类别 */
    @Excel(name = "类别")
    private String Category;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 关系 */
    @Excel(name = "关系")
    private String Relation;

    public void setMctId(Long mctId) 
    {
        this.mctId = mctId;
    }

    public Long getMctId() 
    {
        return mctId;
    }
    public void setCategory(String Category) 
    {
        this.Category = Category;
    }

    public String getCategory() 
    {
        return Category;
    }
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setRelation(String Relation) 
    {
        this.Relation = Relation;
    }

    public String getRelation() 
    {
        return Relation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mctId", getMctId())
            .append("Category", getCategory())
            .append("vehicleModel", getVehicleModel())
            .append("Relation", getRelation())
            .toString();
    }
}
