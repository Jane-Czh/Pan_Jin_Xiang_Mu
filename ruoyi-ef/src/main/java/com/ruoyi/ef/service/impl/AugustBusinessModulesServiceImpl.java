package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AugustBusinessModulesMapper;
import com.ruoyi.system.domain.AugustBusinessModules;
import com.ruoyi.system.service.IAugustBusinessModulesService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-01
 */
@Service
public class AugustBusinessModulesServiceImpl implements IAugustBusinessModulesService 
{
    @Autowired
    private AugustBusinessModulesMapper augustBusinessModulesMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param bmId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AugustBusinessModules selectAugustBusinessModulesByBmId(Long bmId)
    {
        return augustBusinessModulesMapper.selectAugustBusinessModulesByBmId(bmId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param augustBusinessModules 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AugustBusinessModules> selectAugustBusinessModulesList(AugustBusinessModules augustBusinessModules)
    {
        return augustBusinessModulesMapper.selectAugustBusinessModulesList(augustBusinessModules);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param augustBusinessModules 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAugustBusinessModules(AugustBusinessModules augustBusinessModules)
    {
        return augustBusinessModulesMapper.insertAugustBusinessModules(augustBusinessModules);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param augustBusinessModules 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAugustBusinessModules(AugustBusinessModules augustBusinessModules)
    {
        return augustBusinessModulesMapper.updateAugustBusinessModules(augustBusinessModules);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param bmIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAugustBusinessModulesByBmIds(Long[] bmIds)
    {
        return augustBusinessModulesMapper.deleteAugustBusinessModulesByBmIds(bmIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param bmId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAugustBusinessModulesByBmId(Long bmId)
    {
        return augustBusinessModulesMapper.deleteAugustBusinessModulesByBmId(bmId);
    }
}
