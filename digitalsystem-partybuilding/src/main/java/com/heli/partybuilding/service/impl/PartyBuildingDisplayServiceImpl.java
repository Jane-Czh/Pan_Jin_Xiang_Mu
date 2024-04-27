package com.heli.partybuilding.service.impl;

import com.heli.partybuilding.mapper.PartyBuildingDisplayMapper;
import com.heli.partybuilding.service.IPartyBuildingDisplayService;
import com.ruoyi.common.core.domain.entity.DisplayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.partybuilding.service.impl
 * @Author: hong
 * @CreateTime: 2024-04-26  15:25
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class PartyBuildingDisplayServiceImpl implements IPartyBuildingDisplayService {
    @Autowired
    private PartyBuildingDisplayMapper partyBuildingDisplayMapper;

    @Override
    public List<DisplayEntity> selectPartyBuildingRank(Date startTime, Date endTime) {
        return partyBuildingDisplayMapper.selectPartyBuildingRank(startTime,endTime);
    }
}
