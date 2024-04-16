package com.ruoyi.market.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.domain.MarketSalesTableStorage;
import com.ruoyi.market.utils.ExcelUtils;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketAfterSaleLedgerMapper;
import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.service.IMarketAfterSaleLedgerService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 售后台账导入Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
@Service
public class MarketAfterSaleLedgerServiceImpl implements IMarketAfterSaleLedgerService 
{
    @Autowired
    private MarketAfterSaleLedgerMapper marketAfterSaleLedgerMapper;


    @Override
    public void importInterests(MultipartFile excelFile) throws IOException {
        MarketAfterSaleLedger marketAfterSaleLedger;
        InputStream is = null;

        try {
            List<MarketAfterSaleLedger> marketAfterSaleLedgers = ExcelUtils.parseExcel2MarketAfterSaleLedger(excelFile);

            int i = 0;
            while (i < marketAfterSaleLedgers.size()){
                marketAfterSaleLedger = marketAfterSaleLedgers.get(i);
                Long lastid = selectLastId();
                if(lastid == null){
                    lastid = 0L;
                }
                Long MASL_ID = GenerateId.getNextId(lastid);
                marketAfterSaleLedger.setMaslId(MASL_ID);
                if (marketAfterSaleLedger.getVehicleModel() == null || marketAfterSaleLedger.getFeedbackDate() == null ){
                    continue;
                }
                marketAfterSaleLedgerMapper.insertMarketAfterSaleLedger(marketAfterSaleLedger);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return marketAfterSaleLedgerMapper.selectLastId();
    }

    /**
     * 查询售后台账导入
     * 
     * @param maslId 售后台账导入主键
     * @return 售后台账导入
     */
    @Override
    public MarketAfterSaleLedger selectMarketAfterSaleLedgerByMaslId(Long maslId)
    {
        return marketAfterSaleLedgerMapper.selectMarketAfterSaleLedgerByMaslId(maslId);
    }

    /**
     * 查询售后台账导入列表
     * 
     * @param marketAfterSaleLedger 售后台账导入
     * @return 售后台账导入
     */
    @Override
    public List<MarketAfterSaleLedger> selectMarketAfterSaleLedgerList(MarketAfterSaleLedger marketAfterSaleLedger)
    {
        return marketAfterSaleLedgerMapper.selectMarketAfterSaleLedgerList(marketAfterSaleLedger);
    }

    /**
     * 新增售后台账导入
     * 
     * @param marketAfterSaleLedger 售后台账导入
     * @return 结果
     */
    @Override
    public int insertMarketAfterSaleLedger(MarketAfterSaleLedger marketAfterSaleLedger)
    {
        return marketAfterSaleLedgerMapper.insertMarketAfterSaleLedger(marketAfterSaleLedger);
    }

    /**
     * 修改售后台账导入
     * 
     * @param marketAfterSaleLedger 售后台账导入
     * @return 结果
     */
    @Override
    public int updateMarketAfterSaleLedger(MarketAfterSaleLedger marketAfterSaleLedger)
    {
        return marketAfterSaleLedgerMapper.updateMarketAfterSaleLedger(marketAfterSaleLedger);
    }

    /**
     * 批量删除售后台账导入
     * 
     * @param maslIds 需要删除的售后台账导入主键
     * @return 结果
     */
    @Override
    public int deleteMarketAfterSaleLedgerByMaslIds(Long[] maslIds)
    {
        return marketAfterSaleLedgerMapper.deleteMarketAfterSaleLedgerByMaslIds(maslIds);
    }

    /**
     * 删除售后台账导入信息
     * 
     * @param maslId 售后台账导入主键
     * @return 结果
     */
    @Override
    public int deleteMarketAfterSaleLedgerByMaslId(Long maslId)
    {
        return marketAfterSaleLedgerMapper.deleteMarketAfterSaleLedgerByMaslId(maslId);
    }
}
