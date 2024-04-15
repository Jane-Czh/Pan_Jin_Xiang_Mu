package com.heli.financial.service.impl;

import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.heli.financial.service.IFinancialDataService;
import com.heli.financial.service.IFinancialBalanceTableService;
import com.heli.financial.service.IFinancialIndicatorsHandfillTableService;
import com.heli.financial.service.IFinancialInterestsTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.financial.service.impl
 * @Author: hong
 * @CreateTime: 2024-04-15  13:16
 * @Description: TODO
 * @Version: 1.0
 */

@Service
public class FinancialDataServiceImpl implements IFinancialDataService {


    @Autowired
    private IFinancialIndicatorsHandfillTableService financialIndicatorsHandfillTableService;
    @Autowired
    private IFinancialInterestsTableService financialInterestsTableService;
    @Autowired
    private IFinancialBalanceTableService financialBalanceTableService;


    @Override
    public int importInterestsTable() {
//        financialInterestsTableService.checkInterestsDataIsExisted(date);
        return 0;
    }

    @Override
    public int importBalanceTable() {
        return 0;
    }

    @Override
    public int handFillData(FinancialIndicatorsHandfillTable FITable) {

        if(financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(FITable.getYearAndMonth())){
            return 0;
        }

        return 0;
    }

    @Override
    public int computeIndicator(Date date) {


        return 0;
    }
}
