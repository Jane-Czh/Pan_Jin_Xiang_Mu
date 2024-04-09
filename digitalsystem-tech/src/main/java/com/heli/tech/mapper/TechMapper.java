package com.heli.tech.mapper;

import java.util.Date;
import java.util.List;

import com.heli.tech.domain.Tech;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;

/**
 * @description: [技术]指标Mapper接口
 * @author: hong
 * @date: 2024/4/9 15:53
 **/
@DataSource(value = DataSourceType.SLAVE)
public interface TechMapper {

    /**
     * @description: 统计当年已经研发完成的数目
     * @author: hong
     * @date: 2024/4/9 11:10
     **/
    Long countAnnualCompletionNumber(String year);

    /**
     * @description: 按年查询列表
     * @author: hong
     * @date: 2024/4/9 14:14
     **/
    List<Tech> selectTechListByYear(int year);

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
}
