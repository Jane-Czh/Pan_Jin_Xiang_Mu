package com.heli.tech.service;



import com.heli.tech.domain.TechNonStandardOrder;
import com.ruoyi.common.core.domain.R;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Tech_Non_Standard_OrderService接口
 *
 * @author hong
 * @date 2024-08-02
 */
public interface ITechNonStandardOrderService {

    public int deleteTechNonStandardOrderByMonth(@Param("yearAndMonth") Date yearAndMonth);


    Boolean checkNonStandardOrderUploadStatus(Date yearAndMonth);


    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream , Date yearAndMonth) ;
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
     * 批量删除Tech_Non_Standard_Order
     *
     * @param tnIds 需要删除的Tech_Non_Standard_Order主键集合
     * @return 结果
     */
    public int deleteTechNonStandardOrderByTnIds(Long[] tnIds);

    /**
     * 删除Tech_Non_Standard_Order信息
     *
     * @param tnId Tech_Non_Standard_Order主键
     * @return 结果
     */
    public int deleteTechNonStandardOrderByTnId(Long tnId);
}
