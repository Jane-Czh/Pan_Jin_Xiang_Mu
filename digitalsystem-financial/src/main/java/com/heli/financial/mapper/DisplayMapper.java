package com.heli.financial.mapper;

import com.heli.financial.domain.DisplayEntity;
import com.heli.financial.domain.FinancialBalanceTable;

import java.util.List;

public interface DisplayMapper {

    public List<DisplayEntity> selectDisplayData(DisplayEntity displayEntity);

}
