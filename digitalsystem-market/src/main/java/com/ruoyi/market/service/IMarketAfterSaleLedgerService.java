package com.ruoyi.market.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.market.domain.MarketAfterSaleLedger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 售后台账导入Service接口
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
public interface IMarketAfterSaleLedgerService 
{

    /**
     * 导入excel
     *
     *
     *
     */
    public void importInterests(MultipartFile excelFile) throws IOException;

    /**
     * 查询售后台账导入
     * 
     * @param maslId 售后台账导入主键
     * @return 售后台账导入
     */
    public MarketAfterSaleLedger selectMarketAfterSaleLedgerByMaslId(Long maslId);

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
     * 批量删除售后台账导入
     * 
     * @param maslIds 需要删除的售后台账导入主键集合
     * @return 结果
     */
    public int deleteMarketAfterSaleLedgerByMaslIds(Long[] maslIds);

    /**
     * 删除售后台账导入信息
     * 
     * @param maslId 售后台账导入主键
     * @return 结果
     */
    public int deleteMarketAfterSaleLedgerByMaslId(Long maslId);
}
