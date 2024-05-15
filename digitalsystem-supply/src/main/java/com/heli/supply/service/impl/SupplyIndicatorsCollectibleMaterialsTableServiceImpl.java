package com.heli.supply.service.impl;

import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.supply.domain.SupplyIndicatorsCollectibleMaterialsTable;
import com.heli.supply.listener.CollectibleMaterialsTableListener;
import com.heli.supply.mapper.SupplyIndicatorsCollectibleMaterialsTableMapper;
import com.heli.supply.service.ISupplyIndicatorsCollectibleMaterialsTableService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @description: 供应科-指标-集采物料字典Service业务层处理
 * @author: hong
 * @date: 2024-04-28
 */
@Service
public class SupplyIndicatorsCollectibleMaterialsTableServiceImpl implements ISupplyIndicatorsCollectibleMaterialsTableService {
    @Autowired
    private SupplyIndicatorsCollectibleMaterialsTableMapper supplyIndicatorsCollectibleMaterialsTableMapper;

    private static final Logger log = LoggerFactory.getLogger(SupplyIndicatorsCollectibleMaterialsTableServiceImpl.class);

    /**
     * @description: 读取集采物料台账,并写入数据库
     * @author: hong
     * @date: 2024/4/29 16:43
     **/
    @Override
    public R<String> readCollectibleMaterialsTableToDB(String fileName, InputStream inputStream) {
        try {

            // 清空表格
            supplyIndicatorsCollectibleMaterialsTableMapper.truncateCollectibleMaterialsTable();
            // 读取文件内容
            EasyExcel.read(inputStream, SupplyIndicatorsCollectibleMaterialsTable.class, new CollectibleMaterialsTableListener(supplyIndicatorsCollectibleMaterialsTableMapper)).sheet("集采物料台账").doRead();

            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            log.error(e.toString());
            return R.fail("读取文件失败,您需要上传采购订单汇总表或集采物料台账,当前上传的文件为：" + fileName);
        }

    }

    /**
     * 查询供应科-指标-集采物料字典
     *
     * @param scmId 供应科-指标-集采物料字典主键
     * @return 供应科-指标-集采物料字典
     */
    @Override
    public SupplyIndicatorsCollectibleMaterialsTable selectSupplyIndicatorsCollectibleMaterialsTableByScmId(Long scmId) {
        return supplyIndicatorsCollectibleMaterialsTableMapper.selectSupplyIndicatorsCollectibleMaterialsTableByScmId(scmId);
    }

    /**
     * 查询供应科-指标-集采物料字典列表
     *
     * @param supplyIndicatorsCollectibleMaterialsTable 供应科-指标-集采物料字典
     * @return 供应科-指标-集采物料字典
     */
    @Override
    public List<SupplyIndicatorsCollectibleMaterialsTable> selectSupplyIndicatorsCollectibleMaterialsTableList(SupplyIndicatorsCollectibleMaterialsTable supplyIndicatorsCollectibleMaterialsTable) {
        return supplyIndicatorsCollectibleMaterialsTableMapper.selectSupplyIndicatorsCollectibleMaterialsTableList(supplyIndicatorsCollectibleMaterialsTable);
    }

    /**
     * 新增供应科-指标-集采物料字典
     *
     * @param supplyIndicatorsCollectibleMaterialsTable 供应科-指标-集采物料字典
     * @return 结果
     */
    @Override
    public int insertSupplyIndicatorsCollectibleMaterialsTable(SupplyIndicatorsCollectibleMaterialsTable supplyIndicatorsCollectibleMaterialsTable) {
        supplyIndicatorsCollectibleMaterialsTable.setCreateTime(DateUtils.getNowDate());
        return supplyIndicatorsCollectibleMaterialsTableMapper.insertSupplyIndicatorsCollectibleMaterialsTable(supplyIndicatorsCollectibleMaterialsTable);
    }

    /**
     * 修改供应科-指标-集采物料字典
     *
     * @param supplyIndicatorsCollectibleMaterialsTable 供应科-指标-集采物料字典
     * @return 结果
     */
    @Override
    public int updateSupplyIndicatorsCollectibleMaterialsTable(SupplyIndicatorsCollectibleMaterialsTable supplyIndicatorsCollectibleMaterialsTable) {
        return supplyIndicatorsCollectibleMaterialsTableMapper.updateSupplyIndicatorsCollectibleMaterialsTable(supplyIndicatorsCollectibleMaterialsTable);
    }

    /**
     * 批量删除供应科-指标-集采物料字典
     *
     * @param scmIds 需要删除的供应科-指标-集采物料字典主键
     * @return 结果
     */
    @Override
    public int deleteSupplyIndicatorsCollectibleMaterialsTableByScmIds(Long[] scmIds) {
        return supplyIndicatorsCollectibleMaterialsTableMapper.deleteSupplyIndicatorsCollectibleMaterialsTableByScmIds(scmIds);
    }

    /**
     * 删除供应科-指标-集采物料字典信息
     *
     * @param scmId 供应科-指标-集采物料字典主键
     * @return 结果
     */
    @Override
    public int deleteSupplyIndicatorsCollectibleMaterialsTableByScmId(Long scmId) {
        return supplyIndicatorsCollectibleMaterialsTableMapper.deleteSupplyIndicatorsCollectibleMaterialsTableByScmId(scmId);
    }
}
