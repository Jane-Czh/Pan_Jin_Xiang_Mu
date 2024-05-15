package com.heli.partybuilding.mapper;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@DataSource(value = DataSourceType.SLAVE)
public interface PartyBuildingDisplayMapper {
    /**
     * 党建月度考核排名 指标50
     */
    List<DisplayEntity> selectPartyBuildingRank(@Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);
}
