package com.ruoyi.ef.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.ef.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liuchengbiao
 * @date 2020-05-22 21:26
 */
@Mapper
public interface ProjectDao extends BaseMapper<ProjectEntity> {

    //自定义mapper方法, 根据当前newest == 1 的数据
    List<ProjectEntity> queryDatas();


}
