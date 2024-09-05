package com.heli.financial.service;


import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.ruoyi.common.core.domain.AjaxResult;
import org.aspectj.lang.JoinPoint;

import java.util.Date;

public interface IFinancialDataService {

    int batchUpdateFinancialData(Date yearAndMonth);


    boolean checkDataUploadedForCurrentMonth(Date yearAndMonth);

    int calculateCurrentMonthFinancialData(Date yearAndMonth);

    int batchCalculateBalanceIndicator();
//    int calculateCurrentMonthFinancialData(JoinPoint joinPoint);

}
