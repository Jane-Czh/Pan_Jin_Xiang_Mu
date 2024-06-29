package com.ruoyi.product.utils;

import com.ruoyi.market.utils.ExcelDateUtils;
import com.ruoyi.product.domain.ProductionClockInForm;
import com.ruoyi.product.domain.ProductionOvertimeApplicationForm;
import com.ruoyi.product.domain.ProuctionFunctionQualifiedRate;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelUtilsPro {

    public static List<ProuctionFunctionQualifiedRate> parseExcel(MultipartFile file) throws IOException {
        List<ProuctionFunctionQualifiedRate> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        LocalDate myDate = LocalDate.of(2000, 1, 1);
        // 转换为Date类型
        Date date = java.sql.Date.valueOf(myDate);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate = new ProuctionFunctionQualifiedRate();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

//            marketSalesTable.setMsId(GenerateId.getUUid());

            int count = 0;
            //1、合格率
            prouctionFunctionQualifiedRate.setDisqualifiedNumber(getIntegerCellValue(row.getCell(count++)));
            //2、合格数量
            prouctionFunctionQualifiedRate.setQualifiedNumber(getIntegerCellValue(row.getCell(count++)));
            //3、不合格数量
            prouctionFunctionQualifiedRate.setDisqualifiedNumber(getIntegerCellValue(row.getCell(count++)));
            System.out.println(date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count))))));
            System.out.println(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))));
            System.out.println(row.getCell(count).getCellType());
            System.out.println(row.getCell(count).getDateCellValue());
            String dateString;
            if (row.getCell(count).getCellType() == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(row.getCell(count))) {
                    Date date1 = row.getCell(count).getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    dateString = sdf.format(date1);
                    System.out.println(dateString);
                } else {
                    // 处理其他数值类型
                }
            } else {
                // 处理其他类型
            }




            //4、日期
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                prouctionFunctionQualifiedRate.setDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));

            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //5、产品型号
            prouctionFunctionQualifiedRate.setModel(getStringCellValue(row.getCell(count++)));
            //生产数量
            prouctionFunctionQualifiedRate.setProductionNumber(getIntegerCellValue(row.getCell(count++)));


            dataList.add(prouctionFunctionQualifiedRate);
        }



        workbook.close();

        return dataList;
    }
    //打卡表导入
    public static List<ProductionClockInForm> parseExcelform(MultipartFile file) throws IOException {
        List<ProductionClockInForm> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        LocalDate myDate = LocalDate.of(2000, 1, 1);
        // 转换为Date类型
        Date date = java.sql.Date.valueOf(myDate);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            ProductionClockInForm productionClockInForm = new ProductionClockInForm();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

//            marketSalesTable.setMsId(GenerateId.getUUid());

            int count = 0;
            //1、序号
//            prouctionFunctionQualifiedRate.setDisqualifiedNumber(getIntegerCellValue(row.getCell(count++)));
            productionClockInForm.setNumber(Long.valueOf(getIntegerCellValue(row.getCell(count++))));
            //2、人员编号
//            prouctionFunctionQualifiedRate.setQualifiedNumber(getIntegerCellValue(row.getCell(count++)));
            productionClockInForm.setIdNumber(getStringCellValue(row.getCell(count++)));
            //3.姓名
            productionClockInForm.setName(getStringCellValue(row.getCell(count++)));
            //4.性别
            productionClockInForm.setGender(getStringCellValue(row.getCell(count++)));
            //5.第一次上班打卡时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count)))))){

                productionClockInForm.setFirstTimeClockingInAtWork(getDateTimeCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count++)))));

            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
            //6.第一次下班打卡时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count)))))){

                productionClockInForm.setFirstTimeClockingInAfterWork(getDateTimeCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count++)))));

            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //7.第二次上班打卡时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count)))))){

                productionClockInForm.setSecondTimeClockingInAtWork(getDateTimeCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count++)))));

            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //7.第二次下班打卡时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count)))))){

                productionClockInForm.setSecondTimeClockingInAfterWork(getDateTimeCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count++)))));

            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //8.正常上班时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count)))))){

                productionClockInForm.setNormalWorkingHours(getDateTimeCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count++)))));

            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //9.正常下班时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count)))))){

                productionClockInForm.setNormalClosingTime(getDateTimeCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count++)))));

            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

//            prouctionFunctionQualifiedRate.setDisqualifiedNumber(getIntegerCellValue(row.getCell(count++)));
//            System.out.println(date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count))))));
//            System.out.println(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))));
//            System.out.println(row.getCell(count).getCellType());
//            System.out.println(row.getCell(count).getDateCellValue());
//            String dateString;
//            if (row.getCell(count).getCellType() == CellType.NUMERIC) {
//                if (DateUtil.isCellDateFormatted(row.getCell(count))) {
//                    Date date1 = row.getCell(count).getDateCellValue();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//                    dateString = sdf.format(date1);
//                    System.out.println(dateString);
//                } else {
//                    // 处理其他数值类型
//                }
//            } else {
//                // 处理其他类型
//            }




            //4、日期
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
//
//                prouctionFunctionQualifiedRate.setDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
//            //5、产品型号
//            prouctionFunctionQualifiedRate.setModel(getStringCellValue(row.getCell(count++)));
//            //生产数量
//            prouctionFunctionQualifiedRate.setProductionNumber(getIntegerCellValue(row.getCell(count++)));


            dataList.add(productionClockInForm);
        }



        workbook.close();

        return dataList;
    }
    public static List<ProductionOvertimeApplicationForm> OaparseExcel(MultipartFile file) throws IOException {
        List<ProductionOvertimeApplicationForm> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        LocalDate myDate = LocalDate.of(2000, 1, 1);
        // 转换为Date类型
        Date date = java.sql.Date.valueOf(myDate);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            ProductionOvertimeApplicationForm productionOvertimeApplicationForm = new ProductionOvertimeApplicationForm();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

//            marketSalesTable.setMsId(GenerateId.getUUid());

            int count = 0;
            //1、序号
//            prouctionFunctionQualifiedRate.setDisqualifiedNumber(getIntegerCellValue(row.getCell(count++)));
            productionOvertimeApplicationForm.setNumber(Long.valueOf(getIntegerCellValue(row.getCell(count++))));
            //2、人员编号
//            prouctionFunctionQualifiedRate.setQualifiedNumber(getIntegerCellValue(row.getCell(count++)));
            productionOvertimeApplicationForm.setIdNumber(getStringCellValue(row.getCell(count++)));
            //3.姓名
            productionOvertimeApplicationForm.setName(getStringCellValue(row.getCell(count++)));
            //4.性别
            productionOvertimeApplicationForm.setGender(getStringCellValue(row.getCell(count++)));
            //5.申请加班开始时间
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                productionOvertimeApplicationForm.setApplicationForOvertimeStartTime(getDateTimeCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count++)))));

            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //6.申请加班结束时间
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
                System.out.println("---------0"+getDateTimeCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count)))));;

                productionOvertimeApplicationForm.setApplicationForOvertimeEndTime(getDateTimeCellValue(ExcelDateUtils.convertExcelDateTimeToString(getNumericCellValue(row.getCell(count++)))));

//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }

//            //1、合格率
//            prouctionFunctionQualifiedRate.setDisqualifiedNumber(getIntegerCellValue(row.getCell(count++)));
//            //2、合格数量
//            prouctionFunctionQualifiedRate.setQualifiedNumber(getIntegerCellValue(row.getCell(count++)));
//            //3、不合格数量
//            prouctionFunctionQualifiedRate.setDisqualifiedNumber(getIntegerCellValue(row.getCell(count++)));
//            System.out.println(date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count))))));
//            System.out.println(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))));
//            System.out.println(row.getCell(count).getCellType());
//            System.out.println(row.getCell(count).getDateCellValue());
//            String dateString;
//            if (row.getCell(count).getCellType() == CellType.NUMERIC) {
//                if (DateUtil.isCellDateFormatted(row.getCell(count))) {
//                    Date date1 = row.getCell(count).getDateCellValue();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//                    dateString = sdf.format(date1);
//                    System.out.println(dateString);
//                } else {
//                    // 处理其他数值类型
//                }
//            } else {
//                // 处理其他类型
//            }
//
//
//
//
//            //4、日期
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
//
//                prouctionFunctionQualifiedRate.setDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
//            //5、产品型号
//            prouctionFunctionQualifiedRate.setModel(getStringCellValue(row.getCell(count++)));
//            //生产数量
//            prouctionFunctionQualifiedRate.setProductionNumber(getIntegerCellValue(row.getCell(count++)));


            dataList.add(productionOvertimeApplicationForm);
        }



        workbook.close();

        return dataList;
    }

    private static String getStringCellValue(Cell cell) {
        if (cell == null|| cell.getCellType() == CellType.BLANK) {
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

    private static Date getDateTimeCellValue(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }




   /*{
        List<MarketCommercialVehicleTable> CVdataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            MarketCommercialVehicleTable MarketCommercialVehicleTable = new MarketCommercialVehicleTable();
            *//**
             * 将excel设置的字段，写入到数据库对应字段
             *//*

*//*            MarketCommercialVehicleTable.setMcvId(String.valueOf(getUUid()));*//*

            int count = 0;
            //1、接单日期
            MarketCommercialVehicleTable.setAcceptanceDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            //2.上线日期
            MarketCommercialVehicleTable.setLaunchDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            //3、订单号
            MarketCommercialVehicleTable.setOrderNumber(getStringCellValue(row.getCell(count++)));
            //4、合同号
            MarketCommercialVehicleTable.setContractNumber(getStringCellValue(row.getCell(count++)));
            //5、车号
            MarketCommercialVehicleTable.setCarNumber(getStringCellValue(row.getCell(count++)));
            //6、车型
            MarketCommercialVehicleTable.setVehicleModel(getStringCellValue(row.getCell(count++)));
            //7、门架高度
            MarketCommercialVehicleTable.setDoorFrameHeight(getStringCellValue(row.getCell(count++)));
            //8、数量
            MarketCommercialVehicleTable.setNumber(getIntegerCellValue(row.getCell(count++)));
            //9、属具
            MarketCommercialVehicleTable.setAccessory(getStringCellValue(row.getCell(count++)));
            //10、阀片
            MarketCommercialVehicleTable.setValveBlock(getStringCellValue(row.getCell(count++)));
            //11、配置
            MarketCommercialVehicleTable.setConfiguration(getStringCellValue(row.getCell(count++)));
            //12.计划完工期
            MarketCommercialVehicleTable.setPlannedCompletionPeriod(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            //13.客户
            MarketCommercialVehicleTable.setCustomer(getStringCellValue(row.getCell(count++)));
            //14、车体上线日期
            MarketCommercialVehicleTable.setVehicleLaunchDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            //15、门架合装完工期
            MarketCommercialVehicleTable.setCompletionPeriodOfDoorFrameAssembly(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            //16、试车完工期
            MarketCommercialVehicleTable.setTrialCompletionPeriod(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            //17、特种作业
            MarketCommercialVehicleTable.setSpecialOperations(getStringCellValue(row.getCell(count++)));
            //18、精整完工期
            MarketCommercialVehicleTable.setPrecisionCompletionPeriod(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            //19、生产现场问题
            MarketCommercialVehicleTable.setProductionSiteissues(getStringCellValue(row.getCell(count++)));
            //20、现场问题采购
            MarketCommercialVehicleTable.setProcurementOfOnSiteAbnormalIssues(getStringCellValue(row.getCell(count++)));
            //21、现场异常问题质量
            MarketCommercialVehicleTable.setQualityOfOnSiteAbnormalIssues(getStringCellValue(row.getCell(count++)));
            //22、上线
            MarketCommercialVehicleTable.setGoLive(getStringCellValue(row.getCell(count++)));
            //23、入库
            MarketCommercialVehicleTable.setWarehousing(getStringCellValue(row.getCell(count++)));
            //24、生产周期
            MarketCommercialVehicleTable.setProductionCycle(getStringCellValue(row.getCell(count++)));


            CVdataList.add(MarketCommercialVehicleTable);
        }



        workbook.close();

        return CVdataList;
    }*/


}
