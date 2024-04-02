package com.heli.financial.service.impl;

import com.heli.financial.domain.DisplayEntity;
import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.mapper.DisplayMapper;
import com.heli.financial.service.DisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.financial.service.impl
 * @Author: hong
 * @CreateTime: 2024-04-01  12:00
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class DisplayServiceImpl implements DisplayService {

    @Autowired
    private DisplayMapper displayMapper;

    @Override
    public List<DisplayEntity> selectDisplayData(DisplayEntity displayEntity) {
        return displayMapper.selectDisplayData(displayEntity);
    }


}
