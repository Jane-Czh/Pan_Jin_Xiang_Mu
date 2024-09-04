package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.ef.entity.LineEntity;
import com.ruoyi.system.domain.AugustBusinessModules;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-01
 */
public interface AugustBusinessModulesMapper extends BaseMapper<AugustBusinessModules>
{
    /**
     * 查询【请填写功能名称】
     *
     * @param bmId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AugustBusinessModules selectAugustBusinessModulesByBmId(Long bmId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param augustBusinessModules 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AugustBusinessModules> selectAugustBusinessModulesList(AugustBusinessModules augustBusinessModules);

    /**
     * 新增【请填写功能名称】
     *
     * @param augustBusinessModules 【请填写功能名称】
     * @return 结果
     */
    public int insertAugustBusinessModules(AugustBusinessModules augustBusinessModules);

    /**
     * 修改【请填写功能名称】
     *
     * @param augustBusinessModules 【请填写功能名称】
     * @return 结果
     */
    public int updateAugustBusinessModules(AugustBusinessModules augustBusinessModules);

    /**
     * 删除【请填写功能名称】
     *
     * @param bmId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAugustBusinessModulesByBmId(Long bmId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param bmIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAugustBusinessModulesByBmIds(Long[] bmIds);
}
