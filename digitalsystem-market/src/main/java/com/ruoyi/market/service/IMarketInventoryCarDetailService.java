package com.ruoyi.market.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.market.domain.MarketInventoryCarDetail;
import org.springframework.web.multipart.MultipartFile;

/**
 * 库存车明细Service接口
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public interface IMarketInventoryCarDetailService 
{
    /**
     * 导入excel
     *
     *
     *
     */
    public void importInterests(MultipartFile excelFile) throws IOException;

    /**
     * 查询库存车明细
     * 
     * @param micdId 库存车明细主键
     * @return 库存车明细
     */
    public MarketInventoryCarDetail selectMarketInventoryCarDetailByMicdId(Long micdId);

    /**
     * 查询库存车明细列表
     * 
     * @param marketInventoryCarDetail 库存车明细
     * @return 库存车明细集合
     */
    public List<MarketInventoryCarDetail> selectMarketInventoryCarDetailList(MarketInventoryCarDetail marketInventoryCarDetail);

    /**
     * 新增库存车明细
     * 
     * @param marketInventoryCarDetail 库存车明细
     * @return 结果
     */
    public int insertMarketInventoryCarDetail(MarketInventoryCarDetail marketInventoryCarDetail);

    /**
     * 修改库存车明细
     * 
     * @param marketInventoryCarDetail 库存车明细
     * @return 结果
     */
    public int updateMarketInventoryCarDetail(MarketInventoryCarDetail marketInventoryCarDetail);

    /**
     * 批量删除库存车明细
     * 
     * @param micdIds 需要删除的库存车明细主键集合
     * @return 结果
     */
    public int deleteMarketInventoryCarDetailByMicdIds(Long[] micdIds);

    /**
     * 删除库存车明细信息
     * 
     * @param micdId 库存车明细主键
     * @return 结果
     */
    public int deleteMarketInventoryCarDetailByMicdId(Long micdId);
}
