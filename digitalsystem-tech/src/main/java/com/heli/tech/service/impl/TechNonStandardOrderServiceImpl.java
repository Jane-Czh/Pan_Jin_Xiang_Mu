package com.heli.tech.service.impl;



import com.alibaba.excel.EasyExcel;
import com.heli.tech.domain.Tech;
import com.heli.tech.domain.TechNonStandardOrder;
import com.heli.tech.listener.TechNonStandardOrderListener;
import com.heli.tech.mapper.TechNonStandardOrderMapper;
import com.heli.tech.service.ITechNonStandardOrderService;
import com.heli.tech.service.ITechService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Tech_Non_Standard_OrderService业务层处理
 *
 * @author hong
 * @date 2024-08-02
 */
@Slf4j
@Service
public class TechNonStandardOrderServiceImpl implements ITechNonStandardOrderService {
    @Autowired
    private TechNonStandardOrderMapper techNonStandardOrderMapper;
    @Autowired
    private ITechService techService;

    @Override
    public int deleteTechNonStandardOrderByMonth(Date yearAndMonth) {
        return techNonStandardOrderMapper.deleteTechNonStandardOrderByMonth(yearAndMonth);
    }

    @Override
    public Boolean checkNonStandardOrderUploadStatus(Date yearAndMonth) {
        return techNonStandardOrderMapper.checkNonStandardOrderUploadStatus(yearAndMonth);
    }

    @Override
    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream , Date yearAndMonth) {
        try {
            //若数据存在，删除当月数据
            if (techNonStandardOrderMapper.checkNonStandardOrderUploadStatus(yearAndMonth)) {
                techNonStandardOrderMapper.deleteTechNonStandardOrderByMonth(yearAndMonth);
            }

            // 读取文件内容
            log.info("开始读取文件: {}", fileName);
            EasyExcel.read(inputStream, TechNonStandardOrder.class, new TechNonStandardOrderListener(techNonStandardOrderMapper,yearAndMonth)).sheet().doRead();
//            return R.ok("读取" + fileName + "文件成功");

            log.info("读取文件: {} 成功", fileName);

            log.info("开始统计，时间："+yearAndMonth);

            int nonStandardOrderOvertimeNum = techNonStandardOrderMapper.countNonStandardOrderOvertimeNum(yearAndMonth);

            BigDecimal nonStandardOrderAvgDays = techNonStandardOrderMapper.countNonStandardOrderAvgDays(yearAndMonth);

            Tech tech = new Tech();
            tech.setYearAndMonth(yearAndMonth);
            tech.setNonStandardAvgPreparationDays(nonStandardOrderAvgDays);
            tech.setNonStandardOvertimeNum(nonStandardOrderOvertimeNum);
            techService.insertOrUpdateTech(tech);

            log.info("统计结果：超时单数："+nonStandardOrderOvertimeNum+"，超时单平均时长："+nonStandardOrderAvgDays);

            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,当前上传的文件为：" + fileName);
        }
    }


    /**
     * 查询Tech_Non_Standard_Order
     *
     * @param tnId Tech_Non_Standard_Order主键
     * @return Tech_Non_Standard_Order
     */
    @Override
    public TechNonStandardOrder selectTechNonStandardOrderByTnId(Long tnId) {
        return techNonStandardOrderMapper.selectTechNonStandardOrderByTnId(tnId);
    }

    /**
     * 查询Tech_Non_Standard_Order列表
     *
     * @param techNonStandardOrder Tech_Non_Standard_Order
     * @return Tech_Non_Standard_Order
     */
    @Override
    public List<TechNonStandardOrder> selectTechNonStandardOrderList(TechNonStandardOrder techNonStandardOrder) {
        return techNonStandardOrderMapper.selectTechNonStandardOrderList(techNonStandardOrder);
    }

    /**
     * 新增Tech_Non_Standard_Order
     *
     * @param techNonStandardOrder Tech_Non_Standard_Order
     * @return 结果
     */
    @Override
    public int insertTechNonStandardOrder(TechNonStandardOrder techNonStandardOrder) {
        techNonStandardOrder.setCreateTime(DateUtils.getNowDate());
        return techNonStandardOrderMapper.insertTechNonStandardOrder(techNonStandardOrder);
    }

    /**
     * 修改Tech_Non_Standard_Order
     *
     * @param techNonStandardOrder Tech_Non_Standard_Order
     * @return 结果
     */
    @Override
    public int updateTechNonStandardOrder(TechNonStandardOrder techNonStandardOrder) {
        techNonStandardOrder.setUpdateTime(DateUtils.getNowDate());
        return techNonStandardOrderMapper.updateTechNonStandardOrder(techNonStandardOrder);
    }

    /**
     * 批量删除Tech_Non_Standard_Order
     *
     * @param tnIds 需要删除的Tech_Non_Standard_Order主键
     * @return 结果
     */
    @Override
    public int deleteTechNonStandardOrderByTnIds(Long[] tnIds) {
        return techNonStandardOrderMapper.deleteTechNonStandardOrderByTnIds(tnIds);
    }

    /**
     * 删除Tech_Non_Standard_Order信息
     *
     * @param tnId Tech_Non_Standard_Order主键
     * @return 结果
     */
    @Override
    public int deleteTechNonStandardOrderByTnId(Long tnId) {
        return techNonStandardOrderMapper.deleteTechNonStandardOrderByTnId(tnId);
    }
}
