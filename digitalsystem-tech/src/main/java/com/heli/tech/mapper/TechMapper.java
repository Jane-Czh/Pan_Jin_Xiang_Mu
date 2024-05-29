package com.heli.tech.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.heli.tech.domain.Tech;
import com.ruoyi.common.core.domain.DisplayEntity;
import org.apache.ibatis.annotations.Param;

/**
 * [技术]指标填报Mapper接口
 *
 * @author hong
 * @date 2024-04-27
 */
public interface TechMapper {

    /**
     * @description: 统计当年已经研发完成的数目
     * @author: hong
     * @date: 2024/4/9 11:10
     **/
    Long countAnnualCompletionNumber(@Param("yearAndMonth") Date yearAndMonth);

    /**
     * @description: 查询当年已上传数据的最大月份，用于数据更新
     * @author: hong
     * @date: 2024/5/29 19:36
     */
    Date selectMaxMonthByYear(@Param("year") int year);

    /**
     * @description: 检查当月是否已经上传数据
     * @author: hong
     * @date: 2024/5/29 19:37
     */
    Boolean checkTechMonthlyDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);

    /**
     * @description: 查询非标准单平均技术准备天数
     * @author: hong
     * @date: 2024/5/29 19:37
     */
    List<DisplayEntity> selectNonStandardAVGPreparationDays(@Param("startTime") Date startTime,
                                                            @Param("endTime") Date endTime);

    /**
     * @description: 查询研发项目计划进度完成率
     * @author: hong
     * @date: 2024/5/29 19:38
     */
    List<DisplayEntity> selectPRDScheduleCompletionRate(@Param("startTime") Date startTime,
                                                        @Param("endTime") Date endTime);

    /**
     * @description: 批量更新月度数据
     * @author: hong
     * @date: 2024/5/29 19:38
     */
    int batchUpdateTech(@Param("teches") ArrayList<Tech> teches);

    /**
     * 查询[技术]指标填报
     *
     * @param techId [技术]指标填报主键
     * @return [技术]指标填报
     */
    public Tech selectTechByTechId(Long techId);

    /**
     * 查询[技术]指标填报列表
     *
     * @param tech [技术]指标填报
     * @return [技术]指标填报集合
     */
    public List<Tech> selectTechList(Tech tech);

    /**
     * 新增[技术]指标填报
     *
     * @param tech [技术]指标填报
     * @return 结果
     */
    public int insertTech(Tech tech);

    /**
     * 修改[技术]指标填报
     *
     * @param tech [技术]指标填报
     * @return 结果
     */
    public int updateTech(Tech tech);

    /**
     * 删除[技术]指标填报
     *
     * @param techId [技术]指标填报主键
     * @return 结果
     */
    public int deleteTechByTechId(Long techId);

    /**
     * 批量删除[技术]指标填报
     *
     * @param techIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTechByTechIds(Long[] techIds);

    int batchUpdateTech(@Param("teches") ArrayList<Tech> teches);

    boolean checkTechMonthlyDataIsExistedByYear(Integer naturalYear);
}
