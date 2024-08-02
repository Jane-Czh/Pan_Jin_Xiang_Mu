package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.AugustBusinessModules;
import com.ruoyi.system.domain.AugustSubBusinesses;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2024-08-01
 */
public interface AugustSubBusinessesMapper extends BaseMapper<AugustSubBusinesses>
{
    /**
     * 查询【请填写功能名称】
     *
     * @param subbId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AugustSubBusinesses selectAugustSubBusinessesBySubbId(Long subbId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param augustSubBusinesses 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AugustSubBusinesses> selectAugustSubBusinessesList(AugustSubBusinesses augustSubBusinesses);

    /**
     * 新增【请填写功能名称】
     *
     * @param augustSubBusinesses 【请填写功能名称】
     * @return 结果
     */
    public int insertAugustSubBusinesses(AugustSubBusinesses augustSubBusinesses);

    /**
     * 修改【请填写功能名称】
     *
     * @param augustSubBusinesses 【请填写功能名称】
     * @return 结果
     */
    public int updateAugustSubBusinesses(AugustSubBusinesses augustSubBusinesses);

    /**
     * 删除【请填写功能名称】
     *
     * @param subbId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAugustSubBusinessesBySubbId(Long subbId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param subbIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAugustSubBusinessesBySubbIds(Long[] subbIds);
}
