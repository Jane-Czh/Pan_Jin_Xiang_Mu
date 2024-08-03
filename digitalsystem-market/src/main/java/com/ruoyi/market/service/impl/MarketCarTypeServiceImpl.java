package com.ruoyi.market.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.util.ListUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketInventoryCarDetail;
import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.domain.MarketSalesTableStorage;
import com.ruoyi.market.utils.ExcelUtils;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketCarTypeMapper;
import com.ruoyi.market.domain.MarketCarType;
import com.ruoyi.market.service.IMarketCarTypeService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 主要车型维护Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
@Service
public class MarketCarTypeServiceImpl implements IMarketCarTypeService 
{
    @Autowired
    private MarketCarTypeMapper marketCarTypeMapper;

    private static final int BATCH_COUNT = 5000; // 批处理数量
    //缓存一批数据
    private List<MarketCarType> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public int importInterests(MultipartFile excelFile) throws IOException {
        MarketCarType marketCarType = new MarketCarType();

        InputStream is = null;
        try {
            List<MarketCarType> marketCarTypes = ExcelUtils.parseExcel2MarketCarType(excelFile);

            int i = 0;
            while (i < marketCarTypes.size()){
                marketCarType = marketCarTypes.get(i);
//                insertMarketCarType(marketCarType);
                cachedDataList.add(marketCarType);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    marketCarTypeMapper.batchInsert(cachedDataList);
                    cachedDataList.clear();
                    System.out.println("插入一轮");
                }
                i++;
            }
            if (cachedDataList.size() > 0){
                marketCarTypeMapper.batchInsert(cachedDataList);
                cachedDataList.clear();
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
        return marketCarTypeMapper.selectLastId();
    }
    /**
     * 查询主要车型维护
     * 
     * @param mctId 主要车型维护主键
     * @return 主要车型维护
     */
    @Override
    public MarketCarType selectMarketCarTypeByMctId(Long mctId)
    {
        return marketCarTypeMapper.selectMarketCarTypeByMctId(mctId);
    }

    /**
     * 查询主要车型维护列表
     * 
     * @param marketCarType 主要车型维护
     * @return 主要车型维护
     */
    @Override
    public List<MarketCarType> selectMarketCarTypeList(MarketCarType marketCarType)
    {
        return marketCarTypeMapper.selectMarketCarTypeList(marketCarType);
    }

    /**
     * 新增主要车型维护
     * 
     * @param marketCarType 主要车型维护
     * @return 结果
     */
    @Override
    public int insertMarketCarType(MarketCarType marketCarType)
    {
        Long lastid = selectLastId();
        if(lastid == null){
            lastid = 0L;
        }
        Long MCT_ID = GenerateId.getNextId(lastid);
        marketCarType.setMctId(MCT_ID);
        return marketCarTypeMapper.insertMarketCarType(marketCarType);
    }

    /**
     * 修改主要车型维护
     * 
     * @param marketCarType 主要车型维护
     * @return 结果
     */
    @Override
    public int updateMarketCarType(MarketCarType marketCarType)
    {
        return marketCarTypeMapper.updateMarketCarType(marketCarType);
    }

    /**
     * 批量删除主要车型维护
     * 
     * @param mctIds 需要删除的主要车型维护主键
     * @return 结果
     */
    @Override
    public int deleteMarketCarTypeByMctIds(Long[] mctIds)
    {
        return marketCarTypeMapper.deleteMarketCarTypeByMctIds(mctIds);
    }

    /**
     * 删除主要车型维护信息
     * 
     * @param mctId 主要车型维护主键
     * @return 结果
     */
    @Override
    public int deleteMarketCarTypeByMctId(Long mctId)
    {
        return marketCarTypeMapper.deleteMarketCarTypeByMctId(mctId);
    }
}
