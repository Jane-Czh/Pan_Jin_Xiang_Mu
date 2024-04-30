package com.heli.financial.service;


import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import org.aspectj.lang.JoinPoint;

import java.util.Date;

public interface IFinancialDataService {

    boolean checkDataUploadedForCurrentMonth(Date yearAndMonth);

    int calculateCurrentMonthFinancialData(Date yearAndMonth);
//    int calculateCurrentMonthFinancialData(JoinPoint joinPoint);

}
