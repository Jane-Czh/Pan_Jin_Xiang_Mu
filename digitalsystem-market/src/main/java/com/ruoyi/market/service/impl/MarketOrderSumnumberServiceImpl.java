package com.ruoyi.market.service.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketOrderSumnumberMapper;
import com.ruoyi.market.domain.MarketOrderSumnumber;
import com.ruoyi.market.service.IMarketOrderSumnumberService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@Service
public class MarketOrderSumnumberServiceImpl implements IMarketOrderSumnumberService 
{
    @Autowired
    private MarketOrderSumnumberMapper marketOrderSumnumberMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public MarketOrderSumnumber selectMarketOrderSumnumberById(Long id)
    {
        return marketOrderSumnumberMapper.selectMarketOrderSumnumberById(id);
    }

    @Override
    public void importInterests(MultipartFile excelFile) throws IOException {
        System.out.println("导入订单总数表");
        return ;
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param marketOrderSumnumber 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<MarketOrderSumnumber> selectMarketOrderSumnumberList(MarketOrderSumnumber marketOrderSumnumber)
    {
        return marketOrderSumnumberMapper.selectMarketOrderSumnumberList(marketOrderSumnumber);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param marketOrderSumnumber 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertMarketOrderSumnumber(MarketOrderSumnumber marketOrderSumnumber)
    {
        return marketOrderSumnumberMapper.insertMarketOrderSumnumber(marketOrderSumnumber);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param marketOrderSumnumber 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateMarketOrderSumnumber(MarketOrderSumnumber marketOrderSumnumber)
    {
        return marketOrderSumnumberMapper.updateMarketOrderSumnumber(marketOrderSumnumber);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMarketOrderSumnumberByIds(Long[] ids)
    {
        return marketOrderSumnumberMapper.deleteMarketOrderSumnumberByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMarketOrderSumnumberById(Long id)
    {
        return marketOrderSumnumberMapper.deleteMarketOrderSumnumberById(id);
    }
}
