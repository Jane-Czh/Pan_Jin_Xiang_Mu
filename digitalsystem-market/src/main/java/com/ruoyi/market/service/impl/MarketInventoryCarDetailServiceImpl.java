package com.ruoyi.market.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.alibaba.excel.util.ListUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketFunctionQuickReport;
import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.utils.ExcelUtils;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketInventoryCarDetailMapper;
import com.ruoyi.market.domain.MarketInventoryCarDetail;
import com.ruoyi.market.service.IMarketInventoryCarDetailService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 库存车明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
@Service
public class MarketInventoryCarDetailServiceImpl implements IMarketInventoryCarDetailService 
{
    @Autowired
    private MarketInventoryCarDetailMapper marketInventoryCarDetailMapper;


    private static final int BATCH_COUNT = 500; // 批处理数量
    //缓存一批数据
    private List<MarketInventoryCarDetail> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 导入excel
     *
     *
     *
     */
    @Override
    public void importInterests(MultipartFile excelFile) throws IOException{
        MarketInventoryCarDetail marketInventoryCarDetail = new MarketInventoryCarDetail();
        InputStream is = null;
        try {

            //获取表格数据
            List<MarketInventoryCarDetail> marketInventoryCarDetails = ExcelUtils.parseExcel2MarketInventoryCarDetail(excelFile);

            //先删除数据库中所有数据
//            List<MarketInventoryCarDetail> need_delete = selectMarketInventoryCarDetailList(marketInventoryCarDetail);
//            if (need_delete.size() > 0){
//                //有需要删除的内容
//                int x = 0;
//                Long[] deleteId = new Long[need_delete.size()];
//                while (x < need_delete.size()){
//                    MarketInventoryCarDetail value = need_delete.get(x);
//                    deleteId[x] = value.getMicdId();
//                    x++;
//                }
//                deleteMarketInventoryCarDetailByMicdIds(deleteId);
//
//            }
            marketInventoryCarDetailMapper.deleteAll();
            System.out.println("删除成功");

            //插入
            int i = 0;
            while (i < marketInventoryCarDetails.size()){
                marketInventoryCarDetail = marketInventoryCarDetails.get(i);
                cachedDataList.add(marketInventoryCarDetail);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    marketInventoryCarDetailMapper.batchInsert(cachedDataList);
                    cachedDataList.clear();
                    System.out.println("插入一轮");
                }
//                marketInventoryCarDetailMapper.insertMarketInventoryCarDetail(marketInventoryCarDetail);
                i++;
            }
            if (cachedDataList.size() > 0){
                marketInventoryCarDetailMapper.batchInsert(cachedDataList);
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
    };

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return marketInventoryCarDetailMapper.selectLastId();
    }

    /**
     * 查询库存车明细
     * 
     * @param micdId 库存车明细主键
     * @return 库存车明细
     */
    @Override
    public MarketInventoryCarDetail selectMarketInventoryCarDetailByMicdId(Long micdId)
    {
        return marketInventoryCarDetailMapper.selectMarketInventoryCarDetailByMicdId(micdId);
    }

    /**
     * 查询库存车明细列表
     * 
     * @param marketInventoryCarDetail 库存车明细
     * @return 库存车明细
     */
    @Override
    public List<MarketInventoryCarDetail> selectMarketInventoryCarDetailList(MarketInventoryCarDetail marketInventoryCarDetail)
    {
        return marketInventoryCarDetailMapper.selectMarketInventoryCarDetailList(marketInventoryCarDetail);
    }

    /**
     * 新增库存车明细
     * 
     * @param marketInventoryCarDetail 库存车明细
     * @return 结果
     */
    @Override
    public int insertMarketInventoryCarDetail(MarketInventoryCarDetail marketInventoryCarDetail)
    {
        Long lastid = selectLastId();
        if(lastid == null){
            lastid = 0L;
        }
        Long MicdId = GenerateId.getNextId(lastid);
        marketInventoryCarDetail.setMicdId(MicdId);
        return marketInventoryCarDetailMapper.insertMarketInventoryCarDetail(marketInventoryCarDetail);
    }

    /**
     * 修改库存车明细
     * 
     * @param marketInventoryCarDetail 库存车明细
     * @return 结果
     */
    @Override
    public int updateMarketInventoryCarDetail(MarketInventoryCarDetail marketInventoryCarDetail)
    {
        return marketInventoryCarDetailMapper.updateMarketInventoryCarDetail(marketInventoryCarDetail);
    }

    /**
     * 批量删除库存车明细
     * 
     * @param micdIds 需要删除的库存车明细主键
     * @return 结果
     */
    @Override
    public int deleteMarketInventoryCarDetailByMicdIds(Long[] micdIds)
    {
        return marketInventoryCarDetailMapper.deleteMarketInventoryCarDetailByMicdIds(micdIds);
    }

    /**
     * 删除库存车明细信息
     * 
     * @param micdId 库存车明细主键
     * @return 结果
     */
    @Override
    public int deleteMarketInventoryCarDetailByMicdId(Long micdId)
    {
        return marketInventoryCarDetailMapper.deleteMarketInventoryCarDetailByMicdId(micdId);
    }
}
