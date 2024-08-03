package com.heli.tech.mapper;




import com.heli.tech.domain.TechNonStandardOrder;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Tech_Non_Standard_OrderMapper接口
 *
 * @author hong
 * @date 2024-08-02
 */
public interface TechNonStandardOrderMapper {

    Boolean checkNonStandardOrderUploadStatus(@Param("yearAndMonth") Date yearAndMonth);

    public int deleteTechNonStandardOrderByMonth(@Param("yearAndMonth") Date yearAndMonth);



    void batchInsertNonStandardOrder(@Param("nonStandardOrderList") List<TechNonStandardOrder> nonStandardOrderList);
    //查询当月超时的数量
    public int countNonStandardOrderOvertimeNum(@Param("yearAndMonth") Date yearAndMonth);

    //统计非标订单平均天数
    BigDecimal countNonStandardOrderAvgDays(@Param("yearAndMonth") Date yearAndMonth);

    /**
     * 查询Tech_Non_Standard_Order
     *
     * @param tnId Tech_Non_Standard_Order主键
     * @return Tech_Non_Standard_Order
     */
    public TechNonStandardOrder selectTechNonStandardOrderByTnId(Long tnId);

    /**
     * 查询Tech_Non_Standard_Order列表
     *
     * @param techNonStandardOrder Tech_Non_Standard_Order
     * @return Tech_Non_Standard_Order集合
     */
    public List<TechNonStandardOrder> selectTechNonStandardOrderList(TechNonStandardOrder techNonStandardOrder);

    /**
     * 新增Tech_Non_Standard_Order
     *
     * @param techNonStandardOrder Tech_Non_Standard_Order
     * @return 结果
     */
    public int insertTechNonStandardOrder(TechNonStandardOrder techNonStandardOrder);

    /**
     * 修改Tech_Non_Standard_Order
     *
     * @param techNonStandardOrder Tech_Non_Standard_Order
     * @return 结果
     */
    public int updateTechNonStandardOrder(TechNonStandardOrder techNonStandardOrder);

    /**
     * 删除Tech_Non_Standard_Order
     *
     * @param tnId Tech_Non_Standard_Order主键
     * @return 结果
     */
    public int deleteTechNonStandardOrderByTnId(Long tnId);

    /**
     * 批量删除Tech_Non_Standard_Order
     *
     * @param tnIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTechNonStandardOrderByTnIds(Long[] tnIds);


}
