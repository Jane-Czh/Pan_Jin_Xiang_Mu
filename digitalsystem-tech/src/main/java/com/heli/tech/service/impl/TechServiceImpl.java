package com.heli.tech.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.heli.tech.controller.TechController;
import com.heli.tech.domain.TechNonStandardDisplayDTO;
import com.heli.tech.mapper.TechAnnualPlanCountMapper;
import com.heli.tech.service.ITechAnnualPlanCountService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.tech.mapper.TechMapper;
import com.heli.tech.domain.Tech;
import com.heli.tech.service.ITechService;

/**
 * [技术]指标填报Service业务层处理
 *
 * @author hong
 * @date 2024-04-27
 */
@Service
public class TechServiceImpl implements ITechService {

    @Autowired
    private TechMapper techMapper;
    @Autowired
    private TechAnnualPlanCountMapper techAnnualPlanCountMapper;
    @Autowired
    private ITechAnnualPlanCountService techAnnualPlanCountService;

    private static final Logger log = LoggerFactory.getLogger(TechServiceImpl.class);

    /**
     * @description: 当月度数据、年度数据修改时，更新数据
     * @author: hong
     * @date: 2024/5/29 19:46
     */
    public int batchUpdateTech(Date date) {
        Date maxMonth = techMapper.selectMaxMonthByYear(DateUtils.getYear(date));
        Date minMonth = techMapper.selectMinMonthByYear(DateUtils.getYear(date));
        log.info(maxMonth.toString());
        ArrayList<Tech> teches = new ArrayList<>();
        Long annualNumber = techAnnualPlanCountMapper.selectTechAnnualNumberByYear(DateUtils.getYear(date));

        for (int i = DateUtils.getMonth(minMonth); i <= DateUtils.getMonth(maxMonth); i++, minMonth = DateUtils.getNextMonth(minMonth)) {

            Tech tech = new Tech();
            tech.setYearAndMonth(minMonth);
            Long annualCompletionNumber = techMapper.countAnnualCompletionNumber(minMonth);
            tech.setPrdscheduleCompletionrate(BigDecimal.valueOf((annualCompletionNumber.doubleValue() / annualNumber.doubleValue()) * 100));

            teches.add(tech);

            log.info("当前更新的数据为" + tech);
        }

        return techMapper.batchUpdateTech(teches);

    }

    /**
     * @description: 新增月度数据
     * @author: hong
     * @date: 2024/4/24 14:10
     **/
    @Override
    public int insertTech(Tech tech) {
        tech.setCreateTime(DateUtils.getNowDate());
        return techMapper.insertTech(tech);
    }


//    /**
//     * @description: 计算研发项目计划进度完成率
//     * @author: hong
//     * @date: 2024/4/27 11:25
//     **/
//    public Tech calculateCompletionRate(Tech tech) {
//        Long annualCompletionNumber;
//        if(checkDataExist()){
//
//            annualCompletionNumber = techMapper.countAnnualCompletionNumber(tech.getYearAndMonth()) + tech.getCompletedmonthlyPlancounts();
//            log.info("当前计算的数据为"+tech);
//        }else {
//            annualCompletionNumber = tech.getCompletedmonthlyPlancounts();
//        }
//        Long annualNumber = techAnnualPlanCountMapper.selectTechAnnualNumberByYear(DateUtils.getYear(tech.getYearAndMonth()));
//        tech.setPrdscheduleCompletionrate(BigDecimal.valueOf((annualCompletionNumber.doubleValue() / annualNumber.doubleValue()) * 100));
//        return tech;
//    }

    /**
     * @description: 更新完成率
     * @author: hong
     * @date: 2024/8/16 13:37
     */
    @Override
    public int updateCompletionRate() {
        Date minMonth = techMapper.selectMinMonth();
        Date maxMonth = techMapper.selectMaxMonth();

        int i = 0;
        //循环从start Time，到endTime
        for (Date date = minMonth; date.before(DateUtils.getNextMonth(maxMonth)); date = DateUtils.getNextMonth(date),i++) {
            log.info("当前计算的月份为：" + date);

            calculateCompletionRate(date);


        }
        return i;
    }

    /**
     * @description: 计算完成率
     * @author: hong
     * @date: 2024/8/16 10:33
     */
    @Override
    public int calculateCompletionRate(Date yearAndMonth) {
        log.info("时间" + yearAndMonth);
        //检查本月数据是否存在
        if (!techMapper.checkCompletionIsExisted(yearAndMonth)) {
            log.info("计算的当月数据不存在");
            return 0;
        }
        Tech tech = techMapper.selectTechByDate(yearAndMonth);

        //检查从1月到当月数据是否齐全
        if (techMapper.selectCompletionDataNumByYear(yearAndMonth) != DateUtils.getMonth(yearAndMonth)) {
            log.info("当年数据数据不全");
            tech.setCompletedmonthlyPlancounts(null);
            tech.setPrdscheduleCompletionrate(null);
        } else {

            //检查年度计划数是否上传
            if (!techAnnualPlanCountMapper.checkTechAnnualDataIsExisted(DateUtils.getYear(yearAndMonth))) {
                log.info("年度计划数未上传");
                tech.setPrdscheduleCompletionrate(null);
            } else {

                if (DateUtils.getMonth(yearAndMonth) == 1) {
                    log.info("当前月份为1月，当月完成数 = 当月完成总数");
                    tech.setCompletedmonthlyPlancounts(tech.getCompletedPlanCount());
                } else {

                    Tech techLastMonth = techMapper.selectTechByDate(DateUtils.getLastMonth(yearAndMonth));

                    log.info("统计结果：截至本月完成总数：" + tech.getCompletedPlanCount());
                    log.info("统计结果：截至上月完成总数：" + techLastMonth.getCompletedPlanCount());
                    long temp = tech.getCompletedPlanCount() - techLastMonth.getCompletedPlanCount();
                    tech.setCompletedmonthlyPlancounts(temp);
                }

                //开始计算
                Long annualNumber = techAnnualPlanCountService.selectTechAnnualNumberByYear(DateUtils.getYear(yearAndMonth));
                tech.setPrdscheduleCompletionrate(BigDecimal.valueOf(tech.getCompletedPlanCount() * 100.0 / annualNumber));

            }

        }

        return techMapper.insertOrUpdateTech(tech);
    }

    /**
     * @description: 展示非标准单平均技术准备天数
     * @author: hong
     * @date: 2024/5/28 22:53
     */
    @Override
    public List<DisplayEntity> selectNonStandardAVGPreparationDays(Date startTime, Date endTime) {
        return techMapper.selectNonStandardAVGPreparationDays(startTime, endTime);
    }

    /**
     * @description: 展示研发项目计划进度完成率
     * @author: hong
     * @date: 2024/5/28 22:52
     */
    @Override
    public List<DisplayEntity> selectPRDScheduleCompletionRate(Date startTime, Date endTime) {
        return techMapper.selectPRDScheduleCompletionRate(startTime, endTime);
    }

    @Override
    public int insertOrUpdateTech(Tech tech) {
        return techMapper.insertOrUpdateTech(tech);
    }

    /**
     * @description: 检查当月月度数据是否存在
     * @param: date
     * @return: java.lang.Boolean
     * @author: hong
     * @date: 2024/5/28 22:51
     */
    @Override
    public Boolean checkTechMonthlyDataIsExisted(Date date) {
        return techMapper.checkTechMonthlyDataIsExisted(date);
    }

    @Override
    public Boolean checkDataExist() {
        return techMapper.checkDataExist();
    }

    /**
     * 查询[技术]指标填报
     *
     * @param techId [技术]指标填报主键
     * @return [技术]指标填报
     */
    @Override
    public Tech selectTechByTechId(Long techId) {
        return techMapper.selectTechByTechId(techId);
    }

    /**
     * 查询[技术]指标填报列表
     *
     * @param tech [技术]指标填报
     * @return [技术]指标填报
     */
    @Override
    public List<Tech> selectTechList(Tech tech) {
        return techMapper.selectTechList(tech);
    }

    /**
     * 修改[技术]指标填报
     *
     * @param tech [技术]指标填报
     * @return 结果
     */
    @Override
    public int updateTech(Tech tech) {
        tech.setUpdateTime(DateUtils.getNowDate());
        return techMapper.updateTech(tech);
    }

    /**
     * 批量删除[技术]指标填报
     *
     * @param techIds 需要删除的[技术]指标填报主键
     * @return 结果
     */
    @Override
    public int deleteTechByTechIds(Long[] techIds) {
        return techMapper.deleteTechByTechIds(techIds);
    }

    /**
     * 删除[技术]指标填报信息
     *
     * @param techId [技术]指标填报主键
     * @return 结果
     */
    @Override
    public int deleteTechByTechId(Long techId) {
        return techMapper.deleteTechByTechId(techId);
    }

    @Override
    public boolean checkTechMonthlyDataIsExistedByYear(Integer naturalYear) {
        return techMapper.checkTechMonthlyDataIsExistedByYear(naturalYear);
    }

    @Override
    public List<TechNonStandardDisplayDTO> selectNonStandardAVGDaysAndNum(Date startTime, Date endTime) {
        return techMapper.selectNonStandardAVGDaysAndNum(startTime, endTime);

    }
}
