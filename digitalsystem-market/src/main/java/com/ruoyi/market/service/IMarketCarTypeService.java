package com.ruoyi.market.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.market.domain.MarketCarType;
import com.ruoyi.market.domain.MarketSalesTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 主要车型维护Service接口
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
public interface IMarketCarTypeService 
{

    /**
     * 导入市场科车型维护表
     *
     *
     *
     */
    public int importInterests(MultipartFile excelFile) throws IOException;



    /**
     * 查询主要车型维护
     * 
     * @param mctId 主要车型维护主键
     * @return 主要车型维护
     */
    public MarketCarType selectMarketCarTypeByMctId(Long mctId);


    /**
     * 无参数查询主要车型维护
     */
    public List<MarketCarType> selectMarketCarTypeList1();

    /**
     * 查询主要车型维护列表
     * 
     * @param marketCarType 主要车型维护
     * @return 主要车型维护集合
     */
    public List<MarketCarType> selectMarketCarTypeList(MarketCarType marketCarType);

    /**
     * 新增主要车型维护
     * 
     * @param marketCarType 主要车型维护
     * @return 结果
     */
    public int insertMarketCarType(MarketCarType marketCarType);

    /**
     * 修改主要车型维护
     * 
     * @param marketCarType 主要车型维护
     * @return 结果
     */
    public int updateMarketCarType(MarketCarType marketCarType);

    /**
     * 批量删除主要车型维护
     * 
     * @param mctIds 需要删除的主要车型维护主键集合
     * @return 结果
     */
    public int deleteMarketCarTypeByMctIds(Long[] mctIds);

    /**
     * 删除主要车型维护信息
     * 
     * @param mctId 主要车型维护主键
     * @return 结果
     */
    public int deleteMarketCarTypeByMctId(Long mctId);
}
