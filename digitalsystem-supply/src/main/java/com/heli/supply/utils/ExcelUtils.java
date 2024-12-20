package com.heli.supply.utils;

import com.heli.supply.domain.SupplyMaterialCategoryDictionaryTable;
import com.heli.supply.domain.SupplyPurchaseorderTable;
import com.heli.supply.domain.SupplyRatioFormulaTable;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

    public static List<SupplyPurchaseorderTable> parseExcel2SupplyPurchaseorderTable(MultipartFile file)throws IOException{
        List<SupplyPurchaseorderTable> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            SupplyPurchaseorderTable supplyPurchaseorderTable = new SupplyPurchaseorderTable();

            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

            int count = 0;


            //3、物料号
            if (getStringCellValue(row.getCell(2)) == null || getStringCellValue(row.getCell(2)).equals("")){
                continue;
            }
            supplyPurchaseorderTable.setMaterialNumber(getStringCellValue(row.getCell(2)));
            //5、采购数量
            supplyPurchaseorderTable.setPurchaseQuantity((long)getIntegerCellValue(row.getCell(4)));
            //9、净价
            supplyPurchaseorderTable.setNetPrice(BigDecimal.valueOf(getNumericCellValue(row.getCell(8))));
            //10、订单净值
            supplyPurchaseorderTable.setNetValueOrder(BigDecimal.valueOf(getNumericCellValue(row.getCell(9))));
            //12、凭证日期
            if (row.getCell(11).getCellType() == CellType.NUMERIC)
            {
                supplyPurchaseorderTable.setDocumentDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(11)))));
            }
            else if (row.getCell(11).getCellType() == CellType.STRING)
            {
                supplyPurchaseorderTable.setDocumentDate(getDateCellValue(getStringCellValue(row.getCell(11))));
            }
            //13、供应商
            if (row.getCell(12) == null){
                continue;
            }
            supplyPurchaseorderTable.setSupplier(getStringCellValue(row.getCell(12)));
            //14、供应商名称
            if (row.getCell(13) == null){
                continue;
            }
            supplyPurchaseorderTable.setSupplierName(getStringCellValue(row.getCell(13)));

//            System.out.println(supplyPurchaseorderTable);
            dataList.add(supplyPurchaseorderTable);
        }

        workbook.close();

        return dataList;
    }

    public static List<SupplyRatioFormulaTable> parseExcel2SupplyRatioFormulaTable(MultipartFile file)throws IOException{
        List<SupplyRatioFormulaTable> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            SupplyRatioFormulaTable supplyRatioFormulaTable = new SupplyRatioFormulaTable();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

            int count = 0;

            //1、凭证日期
//            if (row.getCell(0).getCellType() == CellType.NUMERIC)
//            {
//                supplyPurchaseorderTable.setDocumentDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(0)))));
//            }
//            else if (row.getCell(0).getCellType() == CellType.STRING)
//            {
//                supplyPurchaseorderTable.setDocumentDate(getDateCellValue(getStringCellValue(row.getCell(0))));
//            }
            //2、物料类别
            supplyRatioFormulaTable.setMaterialClass(getStringCellValue(row.getCell(1)));
            //3、供应商代码
            supplyRatioFormulaTable.setSupplierCode(getStringCellValue(row.getCell(2))); //取物料前缀
            //4、供应商名称
            supplyRatioFormulaTable.setSupplierName(getStringCellValue(row.getCell(3)));
            //5、供货比例

            String oldValue = getStringCellValue(row.getCell(4));
            if (oldValue.equals("/")){
                oldValue = "0";
            }
            String inputValue = Double.toString(Double.parseDouble(oldValue)*100) + "%";
            supplyRatioFormulaTable.setSupplyProportion(inputValue);
            //6、付款方式
            supplyRatioFormulaTable.setPaymentMethod(getStringCellValue(row.getCell(5)));
            //7、比例统计方式
            supplyRatioFormulaTable.setProportionStatisticalMethod(getStringCellValue(row.getCell(6)));
            //8、是否入驻第三方
            supplyRatioFormulaTable.setThirdParty(getStringCellValue(row.getCell(8)));
            //9、备注
            supplyRatioFormulaTable.setRemarks(getStringCellValue(row.getCell(9)));

            dataList.add(supplyRatioFormulaTable);
        }

        workbook.close();

        return dataList;
    }

    public static List<SupplyMaterialCategoryDictionaryTable> parseExcel2SupplyMaterialCategoryDictionaryTable(MultipartFile file)throws IOException{
        List<SupplyMaterialCategoryDictionaryTable> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable = new SupplyMaterialCategoryDictionaryTable();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

            //1、物料号
            if (row.getCell(0) == null){
                continue;
            }
            supplyMaterialCategoryDictionaryTable.setMaterialNumber(getStringCellValue(row.getCell(0)));
            //2、物料类别
            if (row.getCell(1) == null){
                continue;
            }
            supplyMaterialCategoryDictionaryTable.setMaterialClass(getStringCellValue(row.getCell(1)));

            System.out.println("========>"+supplyMaterialCategoryDictionaryTable);
            dataList.add(supplyMaterialCategoryDictionaryTable);
        }

        workbook.close();

        return dataList;
    }

    private static String getStringCellValue(Cell cell) {
        if (cell == null) {
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



}
