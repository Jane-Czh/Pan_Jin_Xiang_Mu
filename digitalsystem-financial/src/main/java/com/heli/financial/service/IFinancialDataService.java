package com.heli.financial.service;


import java.util.Date;

public interface IFinancialDataService {

    int batchUpdateFinancialData(Date yearAndMonth);


    boolean checkDataUploadedForCurrentMonth(Date yearAndMonth);

    int calculateCurrentMonthFinancialData(Date yearAndMonth);

    int batchCalculateBalanceIndicator();
//    int calculateCurrentMonthFinancialData(JoinPoint joinPoint);

    void countTurnoverRateReceivable(Date yearAndMonth) ;

}
