package com.ruoyi.Enterprisemanagement.utils;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionLaborReminder;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;
import com.ruoyi.market.utils.ExcelDateUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EMExcelUtils {
    public static List<EnterpriseManagementSpecialOperationsManagementLedger> SOparseExcel(MultipartFile file) throws IOException {
        List<EnterpriseManagementSpecialOperationsManagementLedger> dataList = new ArrayList<>();
        LocalDate myDate = LocalDate.of(2000, 1, 1);
        // 转换为Date类型
        Date date = java.sql.Date.valueOf(myDate);
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        int care =2;
        // Skip header rows
        for (int i = 0; i < 2 && rowIterator.hasNext(); i++) {
            rowIterator.next();
        }


        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger = new EnterpriseManagementSpecialOperationsManagementLedger();



            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

//            marketSalesTable.setMsId(GenerateId.getUUid());


                int count = 0;

                    //0.序号
                    enterpriseManagementSpecialOperationsManagementLedger.setNumber(Long.valueOf(getIntegerCellValue(row.getCell(count++))));

                    //1、部门
                    enterpriseManagementSpecialOperationsManagementLedger.setDepartment(getStringCellValue(row.getCell(count++)));

                    //2、姓名

                    enterpriseManagementSpecialOperationsManagementLedger.setName(getStringCellValue(row.getCell(count++)));

                    //3、性别

                    enterpriseManagementSpecialOperationsManagementLedger.setGender(getStringCellValue(row.getCell(count++)));

                    //4、发证机关

                    enterpriseManagementSpecialOperationsManagementLedger.setIssuingAuthority(getStringCellValue(row.getCell(count++)));

                    //5、证件类型

                    enterpriseManagementSpecialOperationsManagementLedger.setDocumentType(getStringCellValue(row.getCell(count++)));

                    //6.作业类别

                    enterpriseManagementSpecialOperationsManagementLedger.setAssignmentCategory(getStringCellValue(row.getCell(count++)));

                    //7.身份证号

                    enterpriseManagementSpecialOperationsManagementLedger.setIdNumber(getStringCellValue(row.getCell(count++)));

                    //8.证件号

                    enterpriseManagementSpecialOperationsManagementLedger.setIdCard(getStringCellValue(row.getCell(count++)));

                    //9.取证时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
                    enterpriseManagementSpecialOperationsManagementLedger.setEvidenceCollectionTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
                    //10.第一次到期时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
                    enterpriseManagementSpecialOperationsManagementLedger.setFirstExpiration(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

                    //11.第一次复审日期
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
                    enterpriseManagementSpecialOperationsManagementLedger.setReexaminationFirstly(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
                    //12.第二次到期时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
                    enterpriseManagementSpecialOperationsManagementLedger.setSecondExpiration(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
                //13.第二次复审时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
                    enterpriseManagementSpecialOperationsManagementLedger.setReexaminationSecondly(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
                    //14.第三次到期时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
                    System.out.println("----------");
                enterpriseManagementSpecialOperationsManagementLedger.setThirdExpiration(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//                        enterpriseManagementSpecialOperationsManagementLedger.setThirdExpiration(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));

            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

//                    enterpriseManagementSpecialOperationsManagementLedger.setThirdExpiration(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//                    System.out.println("第三次到期时间"+row.getCell(count)+"==========="+getStringCellValue(row.getCell(count)));
                    //15.第三次复审时间
//            if (!getStringCellValue(row.getCell(count)).isEmpty() && getStringCellValue(row.getCell(count)) != null) {
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
                enterpriseManagementSpecialOperationsManagementLedger.setReexaminationThirdly(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
                /*     System.out.println("第三次复审时间"+row.getCell(count)+"==========="+getStringCellValue(row.getCell(count)));*/
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//            }

            dataList.add(enterpriseManagementSpecialOperationsManagementLedger);
        }



        workbook.close();

        return dataList;
    }

    private static String getStringCellValue(Cell cell) {
        if (cell == null||cell.getCellType() == CellType.BLANK) {
            return null;
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }
    private static Integer getIntegerCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(CellType.NUMERIC);
        return (int) cell.getNumericCellValue();
    }
    // 将单元格内容转换为double类型值
    private static double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0.0;
        }
        cell.setCellType(CellType.NUMERIC);
        return cell.getNumericCellValue();
    }

    private static Date getDateCellValue(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<EnterpriseManagementLaborContractLedger> LRparseExcel(MultipartFile file) throws IOException {
        List<EnterpriseManagementLaborContractLedger > dataList = new ArrayList<>();
        LocalDate myDate = LocalDate.of(2000, 1, 1);
        // 转换为Date类型
        Date date = java.sql.Date.valueOf(myDate);
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header rows
        for (int i = 0; i < 4 && rowIterator.hasNext(); i++) {
            rowIterator.next();
        }


        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger = new EnterpriseManagementLaborContractLedger();


            int count = 0;
            //0.序号
            enterpriseManagementLaborContractLedger.setNumber(Long.valueOf(getIntegerCellValue(row.getCell(count++))));

            //1、姓名
            enterpriseManagementLaborContractLedger.setName(getStringCellValue(row.getCell(count++)));

            //2、部门
            enterpriseManagementLaborContractLedger.setDocument(getStringCellValue(row.getCell(count++)));

            //3.就职状态
            enterpriseManagementLaborContractLedger.setEmploymentStatus(getStringCellValue(row.getCell(count++)));

            //7.身份证号
            enterpriseManagementLaborContractLedger.setIdNumber(getStringCellValue(row.getCell(count++)));

            //电话号码
            enterpriseManagementLaborContractLedger.setPhoneNumber(getStringCellValue(row.getCell(count++)));

            //第一次合同起始
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementLaborContractLedger.setFirstContractPeriodStart(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //第一次合同结束
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementLaborContractLedger.setFirstContractPeriodEnd(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //第一次合同情况
            if (getStringCellValue(row.getCell(count))!=null) {
                enterpriseManagementLaborContractLedger.setContractStatusFirstly(getStringCellValue(row.getCell(count++)));

            }
            else {
                count++;
            }
            //第二次合同起始
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementLaborContractLedger.setSecondContractPeriodStart(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //第二次合同结束
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementLaborContractLedger.setSecondContractPeriodEnd(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //第二次合同情况
            if (getStringCellValue(row.getCell(count))!=null) {
                System.out.println(row.getCell(count));
                System.out.println("================");
                enterpriseManagementLaborContractLedger.setContractStatusSecondly(getStringCellValue(row.getCell(count++)));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //第三次合同起始
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementLaborContractLedger.setThirdContractPeriodStart(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //第三次合同结束
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementLaborContractLedger.setThirdContractPeriodEnd(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //第三次合同情况
            enterpriseManagementLaborContractLedger.setContractStatusThirdly(getStringCellValue(row.getCell(count++)));

            dataList.add(enterpriseManagementLaborContractLedger);

        }
        workbook.close();

        return dataList;

    }
}
