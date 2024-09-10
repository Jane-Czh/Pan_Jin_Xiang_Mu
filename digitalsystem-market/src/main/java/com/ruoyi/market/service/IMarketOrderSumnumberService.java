package com.ruoyi.market.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.market.domain.MarketOrderSumnumber;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
public interface IMarketOrderSumnumberService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MarketOrderSumnumber selectMarketOrderSumnumberById(Long id);

    public void importInterests(MultipartFile excelFile) throws IOException;
    /**
     * 查询【请填写功能名称】列表
     * 
     * @param marketOrderSumnumber 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MarketOrderSumnumber> selectMarketOrderSumnumberList(MarketOrderSumnumber marketOrderSumnumber);

    /**
     * 新增【请填写功能名称】
     * 
     * @param marketOrderSumnumber 【请填写功能名称】
     * @return 结果
     */
    public int insertMarketOrderSumnumber(MarketOrderSumnumber marketOrderSumnumber);

    /**
     * 修改【请填写功能名称】
     * 
     * @param marketOrderSumnumber 【请填写功能名称】
     * @return 结果
     */
    public int updateMarketOrderSumnumber(MarketOrderSumnumber marketOrderSumnumber);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteMarketOrderSumnumberByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMarketOrderSumnumberById(Long id);
}
