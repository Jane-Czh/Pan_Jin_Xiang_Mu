package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AugustSubBusinesses;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-08-01
 */
public interface IAugustSubBusinessesService 
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
     * 批量删除【请填写功能名称】
     * 
     * @param subbIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAugustSubBusinessesBySubbIds(Long[] subbIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param subbId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAugustSubBusinessesBySubbId(Long subbId);
}
