package com.ruoyi.market.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.mapper.MarketSalesTableMapper;
import com.ruoyi.market.service.IMarketSalesTableService;
import com.ruoyi.market.utils.*;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 市场科销售台账Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Service
public class MarketSalesTableServiceImpl implements IMarketSalesTableService 
{
    @Autowired
    private MarketSalesTableMapper marketSalesTableMapper;


    /**
     * @description: 利润表导入
     * @author: hong
     * @date: 2024/3/31 13:50
     * @param: excel文件
     * @return: 影响的行数
     **/
    @Override
    public int importInterests(MultipartFile excelFile) throws IOException {
        MarketSalesTable marketSalesTable;
        InputStream is = null;
        try {
            List<MarketSalesTable> marketSalesTables = ExcelUtils.parseExcel(excelFile);
            int i = 0;
            while (i < marketSalesTables.size()){
                marketSalesTable = marketSalesTables.get(i);
                Long lastid = selectLastId();
                if(lastid == null){
                    lastid = 0L;
                }
                Long MS_id = GenerateId.getNextId(lastid);
                marketSalesTable.setMsId(MS_id);
                marketSalesTable.setCreatedTime(getTime.getCurrentDate());
                if (marketSalesTable.getBranch() == null){
                    continue;
                }
                marketSalesTableMapper.insertMarketSalesTable(marketSalesTable);
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
        return 0;
    }

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return marketSalesTableMapper.selectLastId();
    }

    /**
     * 查询市场科销售台账
     * 
     * @param msId 市场科销售台账主键
     * @return 市场科销售台账
     */
    @Override
    public MarketSalesTable selectMarketSalesTableByMsId(Long msId)
    {
        return marketSalesTableMapper.selectMarketSalesTableByMsId(msId);
    }

    /**
     * 查询市场科销售台账列表
     * 
     * @param marketSalesTable 市场科销售台账
     * @return 市场科销售台账
     */
    @Override
    public List<MarketSalesTable> selectMarketSalesTableList(MarketSalesTable marketSalesTable)
    {
        return marketSalesTableMapper.selectMarketSalesTableList(marketSalesTable);
    }

    /**
     * 查询市场科销售台账列表
     *
//     * @param marketSalesTable 市场科销售台账
     * @return 市场科销售台账
     */
    @Override
    public List<MarketSalesTable> selectMarketSalesTableList1()
    {
        return marketSalesTableMapper.selectMarketSalesTableList1();
    }


    /**
     * 新增市场科销售台账
     * 
     * @param marketSalesTable 市场科销售台账
     * @return 结果
     */
    @Override
    public int insertMarketSalesTable(MarketSalesTable marketSalesTable)
    {
        return marketSalesTableMapper.insertMarketSalesTable(marketSalesTable);
    }

    /**
     * 修改市场科销售台账
     * 
     * @param marketSalesTable 市场科销售台账
     * @return 结果
     */
    @Override
    public int updateMarketSalesTable(MarketSalesTable marketSalesTable)
    {
        return marketSalesTableMapper.updateMarketSalesTable(marketSalesTable);
    }

    /**
     * 批量删除市场科销售台账
     * 
     * @param msIds 需要删除的市场科销售台账主键
     * @return 结果
     */
    @Override
    public int deleteMarketSalesTableByMsIds(Long[] msIds)
    {
        return marketSalesTableMapper.deleteMarketSalesTableByMsIds(msIds);
    }

    /**
     * 删除市场科销售台账信息
     * 
     * @param msId 市场科销售台账主键
     * @return 结果
     */
    @Override
    public int deleteMarketSalesTableByMsId(Long msId)
    {
        return marketSalesTableMapper.deleteMarketSalesTableByMsId(msId);
    }
}
