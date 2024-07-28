package com.heli.safety.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 重点设备字典对象 Safety_EP_Key_Equipment_index
 *
 * @author hong
 * @date 2024-05-31
 */
public class SafetyEpKeyEquipmentIndex extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long skId;

    /**
     * 重点设备编号
     */
//    @Excel(name = "重点设备编号")
    @ExcelProperty(value = "设备编号")
    private String seKeyEquipmentId;

    /**
     * 名称
     */
//    @Excel(name = "名称")
    @ExcelProperty(value = "设备名称")
    private String keyEquipmentName;

    /**
     * 数量
     */
//    @Excel(name = "数量")
    @ExcelProperty(value = "设备数量")
    private Long keyEquipmentNumber;

    public void setSkId(Long skId) {
        this.skId = skId;
    }

    public Long getSkId() {
        return skId;
    }

    public void setSeKeyEquipmentId(String seKeyEquipmentId) {
        this.seKeyEquipmentId = seKeyEquipmentId;
    }

    public String getSeKeyEquipmentId() {
        return seKeyEquipmentId;
    }

    public void setKeyEquipmentName(String keyEquipmentName) {
        this.keyEquipmentName = keyEquipmentName;
    }

    public String getKeyEquipmentName() {
        return keyEquipmentName;
    }

    public void setKeyEquipmentNumber(Long keyEquipmentNumber) {
        this.keyEquipmentNumber = keyEquipmentNumber;
    }

    public Long getKeyEquipmentNumber() {
        return keyEquipmentNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("skId", getSkId())
                .append("seKeyEquipmentId", getSeKeyEquipmentId())
                .append("keyEquipmentName", getKeyEquipmentName())
                .append("keyEquipmentNumber", getKeyEquipmentNumber())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

//public class SafetyEpKeyEquipmentIndex extends BaseEntity {
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * id
//     */
//    private Long skId;
//
//    /**
//     * 重点设备编号
//     */
//    @Excel(name = "重点设备编号")
//    private String seKeyEquipmentId;
//
//    public void setSkId(Long skId) {
//        this.skId = skId;
//    }
//
//    public Long getSkId() {
//        return skId;
//    }
//
//    public void setSeKeyEquipmentId(String seKeyEquipmentId) {
//        this.seKeyEquipmentId = seKeyEquipmentId;
//    }
//
//    public String getSeKeyEquipmentId() {
//        return seKeyEquipmentId;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
//                .append("skId", getSkId())
//                .append("seKeyEquipmentId", getSeKeyEquipmentId())
//                .append("createBy", getCreateBy())
//                .append("createTime", getCreateTime())
//                .append("updateBy", getUpdateBy())
//                .append("updateTime", getUpdateTime())
//                .toString();
//    }
//}
