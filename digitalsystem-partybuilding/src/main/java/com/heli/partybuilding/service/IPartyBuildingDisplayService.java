package com.heli.partybuilding.service;

import com.ruoyi.common.core.domain.entity.DisplayEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IPartyBuildingDisplayService {

    List<DisplayEntity> selectPartyBuildingRank(Date startTime, Date endTime);
}
