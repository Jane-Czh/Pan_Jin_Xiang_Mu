package com.heli.financial.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class FinancialImportEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date yearAndMonth;

    MultipartFile InterestsFile;
}
