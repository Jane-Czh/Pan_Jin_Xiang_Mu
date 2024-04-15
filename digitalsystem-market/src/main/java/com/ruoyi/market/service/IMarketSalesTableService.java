package com.ruoyi.market.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.market.domain.MarketSalesTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 市场科销售台账Service接口
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public interface IMarketSalesTableService 
{

    /**
     * 导入市场科销售台账
     *
     *
     *
     */
    public int importInterests(MultipartFile excelFile) throws IOException;

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

    public List<MarketSalesTable> selectMarketSalesTableList1();


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
     * 批量删除市场科销售台账
     * 
     * @param msIds 需要删除的市场科销售台账主键集合
     * @return 结果
     */
    public int deleteMarketSalesTableByMsIds(Long[] msIds);

    /**
     * 删除市场科销售台账信息
     * 
     * @param msId 市场科销售台账主键
     * @return 结果
     */
    public int deleteMarketSalesTableByMsId(Long msId);
}
