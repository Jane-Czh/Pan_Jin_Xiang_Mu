package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.MarketAfterSaleLedger;

/**
 * 售后台账导入Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
public interface MarketAfterSaleLedgerMapper 
{
    /**
     * 查询售后台账导入
     * 
     * @param maslId 售后台账导入主键
     * @return 售后台账导入
     */
    public MarketAfterSaleLedger selectMarketAfterSaleLedgerByMaslId(Long maslId);

    //查询数据库最后一条数据id
    public Long selectLastId();

    /**
     * 查询售后台账导入列表
     * 
     * @param marketAfterSaleLedger 售后台账导入
     * @return 售后台账导入集合
     */
    public List<MarketAfterSaleLedger> selectMarketAfterSaleLedgerList(MarketAfterSaleLedger marketAfterSaleLedger);

    /**
     * 新增售后台账导入
     * 
     * @param marketAfterSaleLedger 售后台账导入
     * @return 结果
     */
    public int insertMarketAfterSaleLedger(MarketAfterSaleLedger marketAfterSaleLedger);

    /**
     * 修改售后台账导入
     * 
     * @param marketAfterSaleLedger 售后台账导入
     * @return 结果
     */
    public int updateMarketAfterSaleLedger(MarketAfterSaleLedger marketAfterSaleLedger);

    /**
     * 删除售后台账导入
     * 
     * @param maslId 售后台账导入主键
     * @return 结果
     */
    public int deleteMarketAfterSaleLedgerByMaslId(Long maslId);

    /**
     * 批量删除售后台账导入
     * 
     * @param maslIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketAfterSaleLedgerByMaslIds(Long[] maslIds);

    List<MarketAfterSaleLedger> selectMarketAfterSaleLedgerList1();
}
