package com.heli.supply.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料类别字典存储对象 supply_material_category_dictionary_table
 * 
 * @author Teandron
 * @date 2024-07-29
 */
public class SupplyMaterialCategoryDictionaryTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long smcdId;

    /** 物料号 */
    @Excel(name = "物料号")
    private String materialNumber;

    /** 物料类别 */
    @Excel(name = "物料类别")
    private String materialClass;

    public void setSmcdId(Long smcdId) 
    {
        this.smcdId = smcdId;
    }

    public Long getSmcdId() 
    {
        return smcdId;
    }
    public void setMaterialNumber(String materialNumber) 
    {
        this.materialNumber = materialNumber;
    }

    public String getMaterialNumber() 
    {
        return materialNumber;
    }
    public void setMaterialClass(String materialClass) 
    {
        this.materialClass = materialClass;
    }

    public String getMaterialClass() 
    {
        return materialClass;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("smcdId", getSmcdId())
            .append("materialNumber", getMaterialNumber())
            .append("materialClass", getMaterialClass())
            .toString();
    }
}
