package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AugustSubBusinessesMapper;
import com.ruoyi.system.domain.AugustSubBusinesses;
import com.ruoyi.system.service.IAugustSubBusinessesService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-01
 */
@Service
public class AugustSubBusinessesServiceImpl implements IAugustSubBusinessesService 
{
    @Autowired
    private AugustSubBusinessesMapper augustSubBusinessesMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param subbId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AugustSubBusinesses selectAugustSubBusinessesBySubbId(Long subbId)
    {
        return augustSubBusinessesMapper.selectAugustSubBusinessesBySubbId(subbId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param augustSubBusinesses 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AugustSubBusinesses> selectAugustSubBusinessesList(AugustSubBusinesses augustSubBusinesses)
    {
        return augustSubBusinessesMapper.selectAugustSubBusinessesList(augustSubBusinesses);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param augustSubBusinesses 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAugustSubBusinesses(AugustSubBusinesses augustSubBusinesses)
    {
        return augustSubBusinessesMapper.insertAugustSubBusinesses(augustSubBusinesses);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param augustSubBusinesses 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAugustSubBusinesses(AugustSubBusinesses augustSubBusinesses)
    {
        return augustSubBusinessesMapper.updateAugustSubBusinesses(augustSubBusinesses);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param subbIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAugustSubBusinessesBySubbIds(Long[] subbIds)
    {
        return augustSubBusinessesMapper.deleteAugustSubBusinessesBySubbIds(subbIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param subbId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAugustSubBusinessesBySubbId(Long subbId)
    {
        return augustSubBusinessesMapper.deleteAugustSubBusinessesBySubbId(subbId);
    }
}
