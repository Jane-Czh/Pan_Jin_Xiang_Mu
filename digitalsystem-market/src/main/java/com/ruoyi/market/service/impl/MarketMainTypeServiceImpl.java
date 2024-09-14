package com.ruoyi.market.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.util.ListUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.domain.MarketSalesTableStorage;
import com.ruoyi.market.utils.ExcelUtils;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketMainTypeMapper;
import com.ruoyi.market.domain.MarketMainType;
import com.ruoyi.market.service.IMarketMainTypeService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 主要车型字典表Service业务层处理
 * 
 * @author Teandron
 * @date 2024-09-13
 */
@Service
public class MarketMainTypeServiceImpl implements IMarketMainTypeService 
{

    private static final int BATCH_COUNT = 500; // 批处理数量
    //缓存一批数据
    private List<MarketMainType> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Autowired
    private MarketMainTypeMapper marketMainTypeMapper;

    /**
     * @description: 利润表导入
     * @author: hong
     * @date: 2024/3/31 13:50
     * @param: excel文件
     * @return: 影响的行数
     **/
    @Override
    public int importInterests(MultipartFile excelFile) throws IOException {
        MarketMainType marketMainType;

        List<MarketMainType> marketMainTypes = ExcelUtils.parseExcel2MarketMainType(excelFile);

        marketMainTypeMapper.deleteAll();

        int i = 0;
        while (i < marketMainTypes.size()){
            marketMainType = marketMainTypes.get(i);
            cachedDataList.add(marketMainType);
            if (cachedDataList.size() >= BATCH_COUNT) {
                marketMainTypeMapper.batchInsert(cachedDataList);
                cachedDataList.clear();
                System.out.println("插入一轮");
            }
            i++;
        }

        if (cachedDataList.size() > 0){
            marketMainTypeMapper.batchInsert(cachedDataList);
            cachedDataList.clear();
        }

        return 0;
    }

    /**
     * 查询主要车型字典表
     * 
     * @param id 主要车型字典表主键
     * @return 主要车型字典表
     */
    @Override
    public MarketMainType selectMarketMainTypeById(Long id)
    {
        return marketMainTypeMapper.selectMarketMainTypeById(id);
    }

    /**
     * 查询主要车型字典表列表
     * 
     * @param marketMainType 主要车型字典表
     * @return 主要车型字典表
     */
    @Override
    public List<MarketMainType> selectMarketMainTypeList(MarketMainType marketMainType)
    {
        return marketMainTypeMapper.selectMarketMainTypeList(marketMainType);
    }

    /**
     * 新增主要车型字典表
     * 
     * @param marketMainType 主要车型字典表
     * @return 结果
     */
    @Override
    public int insertMarketMainType(MarketMainType marketMainType)
    {
        return marketMainTypeMapper.insertMarketMainType(marketMainType);
    }

    /**
     * 修改主要车型字典表
     * 
     * @param marketMainType 主要车型字典表
     * @return 结果
     */
    @Override
    public int updateMarketMainType(MarketMainType marketMainType)
    {
        return marketMainTypeMapper.updateMarketMainType(marketMainType);
    }

    /**
     * 批量删除主要车型字典表
     * 
     * @param ids 需要删除的主要车型字典表主键
     * @return 结果
     */
    @Override
    public int deleteMarketMainTypeByIds(Long[] ids)
    {
        return marketMainTypeMapper.deleteMarketMainTypeByIds(ids);
    }

    /**
     * 删除主要车型字典表信息
     * 
     * @param id 主要车型字典表主键
     * @return 结果
     */
    @Override
    public int deleteMarketMainTypeById(Long id)
    {
        return marketMainTypeMapper.deleteMarketMainTypeById(id);
    }
}
