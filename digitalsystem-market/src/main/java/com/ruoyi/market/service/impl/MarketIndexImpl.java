//package com.ruoyi.market.service.impl;
//
//import com.heli.production.domain.ProductionTable;
//import com.heli.production.mapper.ProductionTableMapper;
//import com.ruoyi.market.domain.MarketIndexResult;
//import com.ruoyi.market.domain.MarketSalesTable;
//import com.ruoyi.market.service.IMarketIndex;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service
//public class MarketIndexImpl implements IMarketIndex {
//    @Resource
//    ProductionTableMapper productionTableMapper;
//
//    @Override
//    public MarketIndexResult ModelMonthlyProduction(MarketSalesTable marketSalesTable) {
//         List<ProductionTable> productionTables = productionTableMapper.selectProductionTableList1();
//        System.out.println("走到market的service层");
//         return null;
//    }
//}
