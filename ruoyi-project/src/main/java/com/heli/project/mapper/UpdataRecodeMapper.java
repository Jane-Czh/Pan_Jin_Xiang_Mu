package com.heli.project.mapper;

import java.util.List;
import com.heli.project.domain.UpdataRecode;

/**
 * 更新记录Mapper接口
 * 
 * @author teandron
 * @date 2024-07-28
 */
public interface UpdataRecodeMapper 
{
    /**
     * 查询更新记录
     * 
     * @param operId 更新记录主键
     * @return 更新记录
     */
    public UpdataRecode selectUpdataRecodeByOperId(Long operId);

    /**
     * 查询更新记录列表
     * 
     * @param updataRecode 更新记录
     * @return 更新记录集合
     */
    public List<UpdataRecode> selectUpdataRecodeList(UpdataRecode updataRecode);

    /**
     * 新增更新记录
     * 
     * @param updataRecode 更新记录
     * @return 结果
     */
    public int insertUpdataRecode(UpdataRecode updataRecode);

    /**
     * 修改更新记录
     * 
     * @param updataRecode 更新记录
     * @return 结果
     */
    public int updateUpdataRecode(UpdataRecode updataRecode);

    /**
     * 删除更新记录
     * 
     * @param operId 更新记录主键
     * @return 结果
     */
    public int deleteUpdataRecodeByOperId(Long operId);

    /**
     * 批量删除更新记录
     * 
     * @param operIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUpdataRecodeByOperIds(Long[] operIds);
}
