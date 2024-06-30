package com.ruoyi.market.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.market.domain.MarketFunctionQuickReport;
import com.ruoyi.market.domain.MarketInventoryCarDetail;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.*;
import com.ruoyi.market.domain.MarketUnsoldCar;
import com.ruoyi.market.service.IMarketUnsoldCarService;

/**
 * 统计库存车数量Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
@Service
public class MarketUnsoldCarServiceImpl implements IMarketUnsoldCarService 
{
    @Autowired
    private MarketUnsoldCarMapper marketUnsoldCarMapper;
    @Autowired
    private MarketInventoryCarDetailMapper marketInventoryCarDetailMapper;
    /**
     * 查询统计库存车数量
     * 
     * @param ucId 统计库存车数量主键
     * @return 统计库存车数量
     */
    @Override
    public MarketUnsoldCar selectMarketUnsoldCarByUcId(Long ucId)
    {
        return marketUnsoldCarMapper.selectMarketUnsoldCarByUcId(ucId);
    }

    /**
     * 查询统计库存车数量列表
     * 
     * @param marketUnsoldCar 统计库存车数量
     * @return 统计库存车数量
     */
    @Override
    public List<MarketUnsoldCar> selectMarketUnsoldCarList(MarketUnsoldCar marketUnsoldCar)
    {
        return marketUnsoldCarMapper.selectMarketUnsoldCarList(marketUnsoldCar);
    }

    @Override
    public void Synchronization(){
        //删除数据库中已有数据
        MarketInventoryCarDetail marketInventoryCarDetail = new MarketInventoryCarDetail();

        marketUnsoldCarMapper.deleteAllMarketUnsoldCar();
        System.out.println("删除成功");
        //生成插入数据
        List<MarketInventoryCarDetail> marketInventoryCarDetails = marketInventoryCarDetailMapper.selectAllData();
        System.out.println(marketInventoryCarDetails.size());

        if(marketInventoryCarDetails != null){
            int insert_order = 0;
            HashMap<String, MarketUnsoldCar> insert = new HashMap<>();
            while (insert_order < marketInventoryCarDetails.size()){
                String Vehicle_Model = StringUtils.getPrefix(marketInventoryCarDetails.get(insert_order).getVehicleModel());
                //hash中不存在对应车型
                if (insert.get(Vehicle_Model) == null){
                    MarketUnsoldCar marketUnsoldCar1 = new MarketUnsoldCar();
                    marketUnsoldCar1.setVehicleModel(Vehicle_Model);
                    marketUnsoldCar1.setNumber(1L);
                    insert.put(Vehicle_Model,marketUnsoldCar1);
                }else {
                    MarketUnsoldCar marketUnsoldCar1 = insert.get(Vehicle_Model);
                    marketUnsoldCar1.setNumber(marketUnsoldCar1.getNumber() + 1);
                    insert.replace(Vehicle_Model,marketUnsoldCar1);
                }
                insert_order++;
            }
//            System.out.println(insert);
            //放入UC_id后插入
            Long UC_ID = 0L;
            for (Map.Entry<String, MarketUnsoldCar> entry : insert.entrySet()){
                MarketUnsoldCar value = entry.getValue();
                value.setUcId(GenerateId.getNextId(UC_ID));
                insertMarketUnsoldCar(value);
                UC_ID++;
            }
        }
    }

    /**
     * 新增统计库存车数量
     * 
     * @param marketUnsoldCar 统计库存车数量
     * @return 结果
     */
    @Override
    public int insertMarketUnsoldCar(MarketUnsoldCar marketUnsoldCar)
    {
        return marketUnsoldCarMapper.insertMarketUnsoldCar(marketUnsoldCar);
    }

    /**
     * 修改统计库存车数量
     * 
     * @param marketUnsoldCar 统计库存车数量
     * @return 结果
     */
    @Override
    public int updateMarketUnsoldCar(MarketUnsoldCar marketUnsoldCar)
    {
        return marketUnsoldCarMapper.updateMarketUnsoldCar(marketUnsoldCar);
    }

    /**
     * 批量删除统计库存车数量
     * 
     * @param ucIds 需要删除的统计库存车数量主键
     * @return 结果
     */
    @Override
    public int deleteMarketUnsoldCarByUcIds(Long[] ucIds)
    {
        return marketUnsoldCarMapper.deleteMarketUnsoldCarByUcIds(ucIds);
    }

    /**
     * 删除统计库存车数量信息
     * 
     * @param ucId 统计库存车数量主键
     * @return 结果
     */
    @Override
    public int deleteMarketUnsoldCarByUcId(Long ucId)
    {
        return marketUnsoldCarMapper.deleteMarketUnsoldCarByUcId(ucId);
    }
}
