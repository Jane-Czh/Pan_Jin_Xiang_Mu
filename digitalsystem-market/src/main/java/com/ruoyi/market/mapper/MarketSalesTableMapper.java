package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.MarketSalesTable;
import org.apache.ibatis.annotations.Param;

/**
 * 市场科销售台账Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public interface MarketSalesTableMapper 
{
    /**
     * 查询市场科销售台账
     * 
     * @param msId 市场科销售台账主键
     * @return 市场科销售台账
     */
    public MarketSalesTable selectMarketSalesTableByMsId(Long msId);

    /**
     * 查询市场科销售台账列表
     * 
     * @param marketSalesTable 市场科销售台账
     * @return 市场科销售台账集合
     */
    public List<MarketSalesTable> selectMarketSalesTableList(MarketSalesTable marketSalesTable);

    //无参数方法
    public List<MarketSalesTable> selectMarketSalesTableList1();

    //查询数据库最后一条数据id
    public Long selectLastId();
    /**
     * 新增市场科销售台账
     * 
     * @param marketSalesTable 市场科销售台账
     * @return 结果
     */
    public int insertMarketSalesTable(MarketSalesTable marketSalesTable);

    /**
     * 修改市场科销售台账
     * 
     * @param marketSalesTable 市场科销售台账
     * @return 结果
     */
    public int updateMarketSalesTable(MarketSalesTable marketSalesTable);

    /**
     * 删除市场科销售台账
     * 
     * @param msId 市场科销售台账主键
     * @return 结果
     */
    public int deleteMarketSalesTableByMsId(Long msId);

    /**
     * 批量删除市场科销售台账
     * 
     * @param msIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketSalesTableByMsIds(Long[] msIds);

    public void batchInsert(@Param("list") List<MarketSalesTable> marketSalesTable);
}
