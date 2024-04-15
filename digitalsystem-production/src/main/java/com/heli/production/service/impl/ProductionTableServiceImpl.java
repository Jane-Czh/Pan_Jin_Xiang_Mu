package com.heli.production.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson2.JSON;
import com.heli.production.domain.ProductionCountNumberEntity;
import com.heli.production.domain.ProductionTable;
import com.heli.production.listener.ProductionTableListener;
import com.heli.production.mapper.ProductionTableMapper;
import com.heli.production.service.IProductionTableService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.excel.ReadExcelCellUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@Service
public class ProductionTableServiceImpl implements IProductionTableService {

    @Autowired
    private ProductionTableMapper productionTableMapper;

    private static final Logger log = LoggerFactory.getLogger(ProductionTableServiceImpl.class);

    @Override
    public R<String> readProductionExcelToDB(String fileName, InputStream inputStream) {
        try {
            // 读取文件内容
            EasyExcel.read(inputStream, ProductionTable.class, new ProductionTableListener(productionTableMapper)).sheet().doRead();



            //开始计算
            List<ProductionCountNumberEntity> list1 = productionTableMapper.selectLaunchNumGroupByDate();
            System.out.println(list1);

            List<ProductionCountNumberEntity> list2 = productionTableMapper.selectCompletionNumGroupByDate();
            System.out.println(list2);

            int i = productionTableMapper.batchInsertOrUpdateLaunch(list1);

            System.out.println(i);
            int j = productionTableMapper.batchInsertOrUpdateCompletion(list2);

            System.out.println(j);

            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,您需要上传商品车台账,当前上传的文件为：" + fileName);
        }

    }

    /**
     * 查询商品车台账
     *
     * @param mcvId 商品车台账主键
     * @return 商品车台账
     */
    @Override
    public ProductionTable selectProductionTableByMcvId(Long mcvId) {
        return productionTableMapper.selectProductionTableByMcvId(mcvId);
    }

    /**
     * 查询商品车台账列表
     *
     * @param productionTable 商品车台账
     * @return 商品车台账
     */
    @Override
    public List<ProductionTable> selectProductionTableList(ProductionTable productionTable) {
        return productionTableMapper.selectProductionTableList(productionTable);
    }

    /**
     * 新增商品车台账
     *
     * @param productionTable 商品车台账
     * @return 结果
     */
    @Override
    public int insertProductionTable(ProductionTable productionTable) {
        return productionTableMapper.insertProductionTable(productionTable);
    }

    /**
     * 修改商品车台账
     *
     * @param productionTable 商品车台账
     * @return 结果
     */
    @Override
    public int updateProductionTable(ProductionTable productionTable) {
        return productionTableMapper.updateProductionTable(productionTable);
    }

    /**
     * 批量删除商品车台账
     *
     * @param mcvIds 需要删除的商品车台账主键
     * @return 结果
     */
    @Override
    public int deleteProductionTableByMcvIds(Long[] mcvIds) {
        return productionTableMapper.deleteProductionTableByMcvIds(mcvIds);
    }

    /**
     * 删除商品车台账信息
     *
     * @param mcvId 商品车台账主键
     * @return 结果
     */
    @Override
    public int deleteProductionTableByMcvId(Long mcvId) {
        return productionTableMapper.deleteProductionTableByMcvId(mcvId);
    }
}
