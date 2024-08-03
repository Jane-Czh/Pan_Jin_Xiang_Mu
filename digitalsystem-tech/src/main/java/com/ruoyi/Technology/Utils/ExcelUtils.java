package com.ruoyi.Technology.Utils;

import com.ruoyi.Technology.domain.TechnologyFunctionChangerorderTable;
import com.ruoyi.market.utils.ExcelDateUtils;
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

public class ExcelUtils {

    public static List<TechnologyFunctionChangerorderTable> parseExcel(MultipartFile excelFile) throws IOException {
        List<TechnologyFunctionChangerorderTable> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(excelFile.getInputStream());
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
            TechnologyFunctionChangerorderTable technologyFunctionChangerorderTable = new TechnologyFunctionChangerorderTable();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

//            marketSalesTable.setMsId(GenerateId.getUUid());

            int count = 0;
            //1.序号
            technologyFunctionChangerorderTable.setNumber(Long.valueOf(getIntegerCellValue(row.getCell(count++))));

            //2.变更单号
            technologyFunctionChangerorderTable.setChangeorderId(Long.valueOf(getIntegerCellValue(row.getCell(count++))));

            //3.信息描述
            technologyFunctionChangerorderTable.setInformation(getStringCellValue(row.getCell(count++)));

            //4.接受人/实施人
            technologyFunctionChangerorderTable.setReceiver(getStringCellValue(row.getCell(count++)));

            //5.完成情况
            technologyFunctionChangerorderTable.setIsfinish(getStringCellValue(row.getCell(count++)));

            //6.分配时间
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)), "yyyy.MM.dd")))){
//                technologyFunctionChangerorderTable.setAssignmentTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)), "yyyy.MM.dd")));//
//            }
//6.分配时间
            double assignmentTimeValue = getNumericCellValue(row.getCell(count));
            String assignmentTimeString = ExcelDateUtils.converttoExcelDateToString(assignmentTimeValue, "yyyy.MM.dd");

                technologyFunctionChangerorderTable.setAssignmentTime(getDateCellValue(assignmentTimeString));

            count++;
            //7.完成时间
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
//
                technologyFunctionChangerorderTable.setFinishTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));//

            //7.完成时间
            //8.凭证归档
            technologyFunctionChangerorderTable.setVoucher(getStringCellValue(row.getCell(count++)));

            //9.类型/生效时间
            technologyFunctionChangerorderTable.setType(getStringCellValue(row.getCell(count++)));

            //10.备注
            technologyFunctionChangerorderTable.setRemark(getStringCellValue(row.getCell(count++)));


//            //5、产品型号
//            prouctionFunctionQualifiedRate.setModel(getStringCellValue(row.getCell(count++)));
//
//            //生产数量
//            prouctionFunctionQualifiedRate.setProductionNumber(getIntegerCellValue(row.getCell(count++)));
//
//            //2、合格数量
//            prouctionFunctionQualifiedRate.setQualifiedNumber(getIntegerCellValue(row.getCell(count++)));
//            //3、不合格数量
//            prouctionFunctionQualifiedRate.setDisqualifiedNumber(getIntegerCellValue(row.getCell(count++)));
            //1、合格率
            // 获取合格率并设置到对象中
//            if (getStringCellValue(row.getCell(count))!=null) {
//                String rawQualificationRate = getStringCellValue(row.getCell(count++));
//
//// 将合格率转换为浮点数
//                double qualificationRate = Double.parseDouble(rawQualificationRate);
//
//// 格式化为百分比形式
//                String formattedRate = String.format("%.0f%%", qualificationRate * 100);
//
//// 将格式化后的合格率设置到对象中
//                prouctionFunctionQualifiedRate.setQualificationRate(formattedRate);
//
////            System.out.println("========"+prouctionFunctionQualifiedRate.getQualificationRate()+"*******");
//            }
//            else {
//                count++;
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



            dataList.add(technologyFunctionChangerorderTable);
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

    private static Long getLOngCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(CellType.NUMERIC);
        return (long) cell.getNumericCellValue();
    }
    // 将单元格内容转换为double类型值
    private static double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0.0;
        }
        cell.setCellType(CellType.NUMERIC);
        return cell.getNumericCellValue();
    }

//    private static Date getDateCellValue(String dateString) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        try {
//            return sdf.parse(dateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

private static Date getDateCellValue(String dateString) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
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
}
