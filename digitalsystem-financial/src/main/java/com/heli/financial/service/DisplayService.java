package com.heli.financial.service;

import com.heli.financial.domain.DisplayEntity;

import java.util.List;

public interface DisplayService {
    public List<DisplayEntity> selectDisplayData(DisplayEntity displayEntity);
}
