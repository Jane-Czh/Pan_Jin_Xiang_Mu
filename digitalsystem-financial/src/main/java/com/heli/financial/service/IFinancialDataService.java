package com.heli.financial.service;


import com.heli.financial.domain.FinancialIndicatorsHandfillTable;

import java.util.Date;

public interface IFinancialDataService {

    int importInterestsTable();

    int importBalanceTable();

    int handFillData(FinancialIndicatorsHandfillTable FITable);

    int computeIndicator(Date date);

}
