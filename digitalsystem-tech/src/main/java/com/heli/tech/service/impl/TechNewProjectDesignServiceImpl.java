package com.heli.tech.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.tech.domain.Tech;
import com.heli.tech.domain.TechNonStandardOrder;
import com.heli.tech.listener.TechNewProjectListener;
import com.heli.tech.listener.TechNonStandardOrderListener;
import com.heli.tech.service.ITechAnnualPlanCountService;
import com.heli.tech.service.ITechService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.tech.mapper.TechNewProjectDesignMapper;
import com.heli.tech.domain.TechNewProjectDesign;
import com.heli.tech.service.ITechNewProjectDesignService;

/**
 * 技术-项目计划完成统计Service业务层处理
 *
 * @author hong
 * @date 2024-08-03
 */
@Slf4j
@Service
public class TechNewProjectDesignServiceImpl implements ITechNewProjectDesignService {
    @Autowired
    private TechNewProjectDesignMapper techNewProjectDesignMapper;
    @Autowired
    private ITechService techService;
    @Autowired
    private ITechAnnualPlanCountService techAnnualPlanCountService;

    @Override
    public boolean checkTechNewProjectDesignIsExisted(Date yearAndMonth) {
        return techNewProjectDesignMapper.checkTechNewProjectDesignIsExisted(yearAndMonth);
    }

    @Override
    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream, Date yearAndMonth) {
        try {
            //若数据存在，删除当月数据
            if (checkTechNewProjectDesignIsExisted(yearAndMonth)) {
                techNewProjectDesignMapper.deleteTechNewProjectDesignByYearAndMonth(yearAndMonth);
            }


            // 读取文件内容
            log.info("开始读取文件: {}", fileName);
            EasyExcel.read(inputStream, TechNewProjectDesign.class, new TechNewProjectListener(techNewProjectDesignMapper, yearAndMonth)).sheet().doRead();
//            return R.ok("读取" + fileName + "文件成功");

            log.info("读取文件: {} 成功", fileName);

            log.info("开始统计，时间：" + yearAndMonth);

            Integer completeNum = techNewProjectDesignMapper.selectTechNewProjectDesignCount(yearAndMonth);
            Integer completeNumLastMonth = techNewProjectDesignMapper.selectTechNewProjectDesignCount(DateUtils.getLastMonth(yearAndMonth));
            log.info("统计结果：本月完成总数：" + completeNum);
            log.info("统计结果：上月完成总数：" + completeNumLastMonth);
            int temp = completeNum - completeNumLastMonth;

            Tech tech = new Tech();
            tech.setYearAndMonth(yearAndMonth);
            tech.setCompletedmonthlyPlancounts((long) temp);
            tech.setCompletedPlanCount(Long.valueOf(completeNum));

//            int i = techService.insertOrUpdateTech(techService.calculateCompletionRate(tech));
            Long annualNumber = techAnnualPlanCountService.selectTechAnnualNumberByYear(DateUtils.getYear(yearAndMonth));
            tech.setPrdscheduleCompletionrate(BigDecimal.valueOf(completeNum* 100.0 / annualNumber ));
            techService.insertOrUpdateTech(tech);

            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,当前上传的文件为：" + fileName);
        }
    }


    /**
     * 查询技术-项目计划完成统计
     *
     * @param tdId 技术-项目计划完成统计主键
     * @return 技术-项目计划完成统计
     */
    @Override
    public TechNewProjectDesign selectTechNewProjectDesignByTdId(Long tdId) {
        return techNewProjectDesignMapper.selectTechNewProjectDesignByTdId(tdId);
    }

    /**
     * 查询技术-项目计划完成统计列表
     *
     * @param techNewProjectDesign 技术-项目计划完成统计
     * @return 技术-项目计划完成统计
     */
    @Override
    public List<TechNewProjectDesign> selectTechNewProjectDesignList(TechNewProjectDesign techNewProjectDesign) {
        return techNewProjectDesignMapper.selectTechNewProjectDesignList(techNewProjectDesign);
    }

    /**
     * 新增技术-项目计划完成统计
     *
     * @param techNewProjectDesign 技术-项目计划完成统计
     * @return 结果
     */
    @Override
    public int insertTechNewProjectDesign(TechNewProjectDesign techNewProjectDesign) {
        techNewProjectDesign.setCreateTime(DateUtils.getNowDate());
        return techNewProjectDesignMapper.insertTechNewProjectDesign(techNewProjectDesign);
    }

    /**
     * 修改技术-项目计划完成统计
     *
     * @param techNewProjectDesign 技术-项目计划完成统计
     * @return 结果
     */
    @Override
    public int updateTechNewProjectDesign(TechNewProjectDesign techNewProjectDesign) {
        techNewProjectDesign.setUpdateTime(DateUtils.getNowDate());
        return techNewProjectDesignMapper.updateTechNewProjectDesign(techNewProjectDesign);
    }

    /**
     * 批量删除技术-项目计划完成统计
     *
     * @param tdIds 需要删除的技术-项目计划完成统计主键
     * @return 结果
     */
    @Override
    public int deleteTechNewProjectDesignByTdIds(Long[] tdIds) {
        return techNewProjectDesignMapper.deleteTechNewProjectDesignByTdIds(tdIds);
    }

    /**
     * 删除技术-项目计划完成统计信息
     *
     * @param tdId 技术-项目计划完成统计主键
     * @return 结果
     */
    @Override
    public int deleteTechNewProjectDesignByTdId(Long tdId) {
        return techNewProjectDesignMapper.deleteTechNewProjectDesignByTdId(tdId);
    }
}
