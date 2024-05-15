package com.ruoyi.market.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品车台账Service接口
 * 
 * @author ruoyi
 * @date 2024-04-12
 */
public interface IMarketCommercialVehicleTableService 
{
    public int CVimportInterests(MultipartFile excelFile) throws IOException;
    /**
     * 查询商品车台账
     * 
     * @param mcvId 商品车台账主键
     * @return 商品车台账
     */
    public MarketCommercialVehicleTable selectMarketCommercialVehicleTableByMcvId(Long mcvId);

    /**
     * 查询商品车台账列表
     * 
     * @param marketCommercialVehicleTable 商品车台账
     * @return 商品车台账集合
     */
    public List<MarketCommercialVehicleTable> selectMarketCommercialVehicleTableList(MarketCommercialVehicleTable marketCommercialVehicleTable);

    public List<MarketCommercialVehicleTable> selectMarketCommercialVehicleTableList1();
    /**
     * 新增商品车台账
     * 
     * @param marketCommercialVehicleTable 商品车台账
     * @return 结果
     */
    public int insertMarketCommercialVehicleTable(MarketCommercialVehicleTable marketCommercialVehicleTable);

    /**
     * 修改商品车台账
     * 
     * @param marketCommercialVehicleTable 商品车台账
     * @return 结果
     */
    public int updateMarketCommercialVehicleTable(MarketCommercialVehicleTable marketCommercialVehicleTable);

    /**
     * 批量删除商品车台账
     * 
     * @param mcvIds 需要删除的商品车台账主键集合
     * @return 结果
     */
    public int deleteMarketCommercialVehicleTableByMcvIds(Long[] mcvIds);

    /**
     * 删除商品车台账信息
     * 
     * @param mcvId 商品车台账主键
     * @return 结果
     */
    public int deleteMarketCommercialVehicleTableByMcvId(Long mcvId);

}
