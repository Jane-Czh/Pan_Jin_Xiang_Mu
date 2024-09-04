package com.heli.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.project.mapper.UpdataRecodeMapper;
import com.heli.project.domain.UpdataRecode;
import com.heli.project.service.IUpdataRecodeService;

/**
 * 更新记录Service业务层处理
 * 
 * @author teandron
 * @date 2024-07-28
 */
@Service
public class UpdataRecodeServiceImpl implements IUpdataRecodeService 
{
    @Autowired
    private UpdataRecodeMapper updataRecodeMapper;

    /**
     * 查询更新记录
     * 
     * @param operId 更新记录主键
     * @return 更新记录
     */
    @Override
    public UpdataRecode selectUpdataRecodeByOperId(Long operId)
    {
        return updataRecodeMapper.selectUpdataRecodeByOperId(operId);
    }

    /**
     * 查询更新记录列表
     * 
     * @param updataRecode 更新记录
     * @return 更新记录
     */
    @Override
    public List<UpdataRecode> selectUpdataRecodeList(UpdataRecode updataRecode)
    {
        return updataRecodeMapper.selectUpdataRecodeList(updataRecode);
    }

    /**
     * 新增更新记录
     * 
     * @param updataRecode 更新记录
     * @return 结果
     */
    @Override
    public int insertUpdataRecode(UpdataRecode updataRecode)
    {
        return updataRecodeMapper.insertUpdataRecode(updataRecode);
    }

    /**
     * 修改更新记录
     * 
     * @param updataRecode 更新记录
     * @return 结果
     */
    @Override
    public int updateUpdataRecode(UpdataRecode updataRecode)
    {
        return updataRecodeMapper.updateUpdataRecode(updataRecode);
    }

    /**
     * 批量删除更新记录
     * 
     * @param operIds 需要删除的更新记录主键
     * @return 结果
     */
    @Override
    public int deleteUpdataRecodeByOperIds(Long[] operIds)
    {
        return updataRecodeMapper.deleteUpdataRecodeByOperIds(operIds);
    }

    /**
     * 删除更新记录信息
     * 
     * @param operId 更新记录主键
     * @return 结果
     */
    @Override
    public int deleteUpdataRecodeByOperId(Long operId)
    {
        return updataRecodeMapper.deleteUpdataRecodeByOperId(operId);
    }
}
