package com.ruoyi.market.utils;

import com.ruoyi.market.domain.*;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ExcelUtils {

    public static List<MarketSalesTable> parseExcel(MultipartFile file) throws IOException {
        List<MarketSalesTable> dataList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        ZipSecureFile.setMinInflateRatio(0);
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            MarketSalesTable marketSalesTable = new MarketSalesTable();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

//            marketSalesTable.setMsId(GenerateId.getUUid());

            int count = 0;
            //1、网点
            marketSalesTable.setBranch(getStringCellValue(row.getCell(count++)));
            //2、合同号
            marketSalesTable.setContractNumber(getStringCellValue(row.getCell(count++)));
            //3、订单号
            marketSalesTable.setOrderNumber(getStringCellValue(row.getCell(count++)));
            //4、接单日期
            if(getNumericCellValue(row.getCell(count)) != 0.0){
                marketSalesTable.setOrderAcceptanceTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }else {
                count++;
            }
            //5、车型
            marketSalesTable.setVehicleModel(getStringCellValue(row.getCell(count++)));
            //6、数量
            marketSalesTable.setNumber(getIntegerCellValue(row.getCell(count++)).longValue());
            //7、阀片
            marketSalesTable.setValveBlock(getStringCellValue(row.getCell(count++)));
            //8、货叉
            marketSalesTable.setFork(getStringCellValue(row.getCell(count++)));
            //9、门架
            marketSalesTable.setDoorFrame(getStringCellValue(row.getCell(count++)));
            //10、空滤
            marketSalesTable.setAirFilter(getStringCellValue(row.getCell(count++)));
            //11、属具
            marketSalesTable.setAccessory(getStringCellValue(row.getCell(count++)));
            //12、轮胎
            marketSalesTable.setTyre(getStringCellValue(row.getCell(count++)));
            //13、配置
            marketSalesTable.setConfiguration(getStringCellValue(row.getCell(count++)));
            //14、车号
            marketSalesTable.setCarNumber(getStringCellValue(row.getCell(count++)));
            //15、备注
            marketSalesTable.setRemarks(getStringCellValue(row.getCell(count++)));
            //16、交货形式
            marketSalesTable.setDeliveryForm(getStringCellValue(row.getCell(count++)));
            //17、交货地点
            marketSalesTable.setDeliveryLocation(getStringCellValue(row.getCell(count++)));
            //18、联系人
            marketSalesTable.setContacts(getStringCellValue(row.getCell(count++)));
            //19、电话
            marketSalesTable.setTelephone(getStringCellValue(row.getCell(count++)));
            //20、订单系统交货期
            if(getNumericCellValue(row.getCell(count)) != 0.0){
                marketSalesTable.setOrderSystemDeliveryTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }else {
                count++;
            }
            //21、订单超期预警
            marketSalesTable.setOrderOverdueWarning(getStringCellValue(row.getCell(count++)));
            //22、计划发车日期
            if(getNumericCellValue(row.getCell(count)) != 0.0){
                marketSalesTable.setPlannedDepartureDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }else {
                count++;
            }
            //23、实际发车日期
            if (DateUtil.isCellDateFormatted(row.getCell(count))){
//                System.out.println("实际发车日期"+row.getCell(count).getDateCellValue());
                Date dateCellValue = row.getCell(count).getDateCellValue();
//                Data dateValue =  row.getCell(count).getDateCellValue();
                marketSalesTable.setActualDepartureDate( dateCellValue);
                count++;
//                System.out.println("实际发车======"+getNumericCellValue(row.getCell(count)));
//                System.out.println("实际发车======"+formatter.formatCellValue(row.getCell(count)));
//                System.out.println("This cell is formatted as a date."+DateUtil.isCellDateFormatted(row.getCell(count)));
//                marketSalesTable.setActualDepartureDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }else {
                count++;
            }
            //24、系统交货期
//            System.out.println("系统交货期"+(row.getCell(count++)));

//            row.getCell(count++).setDate(new Date())
            Cell cell = row.getCell(count++);
//            System.out.println("测试");
//            System.out.println(cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue() :
//                    cell.getCellType() == CellType.NUMERIC ? cell.getNumericCellValue() :
//                            cell.getCellType() == CellType.STRING ? cell.getStringCellValue() :
//                                    "Unknown type: " + cell.getCellType());



            if (DateUtil.isCellDateFormatted(row.getCell(count))){
//                System.out.println("系统交货期"+row.getCell(count).getDateCellValue());
                Date dateCellValue = row.getCell(count).getDateCellValue();
                marketSalesTable.setSystemDeliveryTime( dateCellValue);
                count++;
//                System.out.println("系统交货======"+getNumericCellValue(row.getCell(count)));
//                System.out.println("系统交货======"+formatter.formatCellValue(row.getCell(count)));
//                System.out.println("This cell is formatted as a date."+DateUtil.isCellDateFormatted(row.getCell(count)));
//                marketSalesTable.setSystemDeliveryTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));

            }else {
                count++;
            }
            //25、交货单号
            marketSalesTable.setDeliveryNoteNumber(getStringCellValue(row.getCell(count++)));
//            26、技术准备完成时间
            if (getNumericCellValue(row.getCell(count)) != 0.0){

                marketSalesTable.setTechnicalPreparationCompletionTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//                System.out.println("当前日期"+getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            if (DateUtil.isCellDateFormatted(row.getCell(count))) {
                Date dateValue =  row.getCell(count).getDateCellValue();
                marketSalesTable.setTechnicalPreparationCompletionTime(dateValue);
            }


//            System.out.println(marketSalesTable);

            dataList.add(marketSalesTable);
        }



        workbook.close();

        return dataList;
    }

    public static List<MarketInventoryCarDetail> parseExcel2MarketInventoryCarDetail(MultipartFile file)throws IOException {
        List<MarketInventoryCarDetail> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            MarketInventoryCarDetail marketInventoryCarDetail = new MarketInventoryCarDetail();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

//            int count = 0;


//            count++;
            //2、车号
            marketInventoryCarDetail.setWagonNumber(getStringCellValue(row.getCell(3)));
            //3、车型
            marketInventoryCarDetail.setVehicleModel(getStringCellValue(row.getCell(4)));
            //4、门架
            marketInventoryCarDetail.setDoorFrame(getStringCellValue(row.getCell(5)));
            //5、属具
            marketInventoryCarDetail.setAccessory(getStringCellValue(row.getCell(6)));
            //6、阀片数
            marketInventoryCarDetail.setValveBlockNumber(getIntegerCellValue(row.getCell(7)));
            //7、配置
            marketInventoryCarDetail.setConfiguration(getStringCellValue(row.getCell(8)));
            //8、计划完工期
            marketInventoryCarDetail.setPlanndeCompletionTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(9)))));

            dataList.add(marketInventoryCarDetail);
        }



        workbook.close();

        return dataList;
    }

    public static List<MarketAfterSaleLedger> parseExcel2MarketAfterSaleLedger(MultipartFile file)throws IOException{
        List<MarketAfterSaleLedger> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            MarketAfterSaleLedger marketAfterSaleLedger = new MarketAfterSaleLedger();

            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

            int count = 0;

            //5、反馈日期
            if (row.getCell(4).getCellType() == CellType.NUMERIC)
            {
                marketAfterSaleLedger.setFeedbackDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(4)))));
            }
            else if (row.getCell(4).getCellType() == CellType.STRING)
            {
                marketAfterSaleLedger.setFeedbackDate(getDateCellValue(getStringCellValue(row.getCell(4))));
            }
            //7、车
            marketAfterSaleLedger.setVehicle(getStringCellValue(row.getCell(6)));
            //10、车型
            marketAfterSaleLedger.setVehicleModel(getStringCellValue(row.getCell(9)));
            //16、一级分类
            marketAfterSaleLedger.setPrimaryClassification(getStringCellValue(row.getCell(15)));
            //17、二级分类
            marketAfterSaleLedger.setSecondaryClassification(getStringCellValue(row.getCell(16)));
            //18、最终处理办法
            marketAfterSaleLedger.setFinalTreatmentMethod(getStringCellValue(row.getCell(17)));
            //27、一级网点
            marketAfterSaleLedger.setPrimaryBranch(getStringCellValue(row.getCell(26)));

            dataList.add(marketAfterSaleLedger);
        }

        workbook.close();

        return dataList;
    }

    public static List<MarketCarType> parseExcel2MarketCarType(MultipartFile file)throws IOException{
        List<MarketCarType> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            MarketCarType marketCarType = new MarketCarType();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

            int count = 0;

            //1、类别
            marketCarType.setCategory(getStringCellValue(row.getCell(1)));
            //2、车型
            marketCarType.setVehicleModel(getStringCellValue(row.getCell(0)));
            //3、关系
            marketCarType.setRelation(getStringCellValue(row.getCell(1)) + "=" + getStringCellValue(row.getCell(0)) );


            dataList.add(marketCarType);
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

    public static List<MarketCommercialVehicleTable> CVparseExcel(MultipartFile file) throws IOException {
        List<MarketCommercialVehicleTable> CVdataList = new ArrayList<>();

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
            MarketCommercialVehicleTable MarketCommercialVehicleTable = new MarketCommercialVehicleTable();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

            /*            MarketCommercialVehicleTable.setMcvId(String.valueOf(getUUid()));*/

            int count = 0;
            //1、接单日期
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                MarketCommercialVehicleTable.setAcceptanceDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //2.上线日期
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                MarketCommercialVehicleTable.setLaunchDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
           //3、订单号
            if(getStringCellValue(row.getCell(count))!=null){
                MarketCommercialVehicleTable.setOrderNumber(getStringCellValue(row.getCell(count++)));
            }
            else {
                continue;
            }
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
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                MarketCommercialVehicleTable.setPlannedCompletionPeriod(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //13.客户
            MarketCommercialVehicleTable.setCustomer(getStringCellValue(row.getCell(count++)));
            //14、车体上线日期
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                MarketCommercialVehicleTable.setVehicleLaunchDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //15、门架合装完工期
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                MarketCommercialVehicleTable.setCompletionPeriodOfDoorFrameAssembly(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //16、试车完工期
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                MarketCommercialVehicleTable.setTrialCompletionPeriod(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //17、特种作业
            MarketCommercialVehicleTable.setSpecialOperations(getStringCellValue(row.getCell(count++)));
            //18、精整完工期
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                MarketCommercialVehicleTable.setPrecisionCompletionPeriod(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            //19、生产现场问题
            MarketCommercialVehicleTable.setProductionSiteissues(getStringCellValue(row.getCell(count++)));
            //20、现场问题采购
            MarketCommercialVehicleTable.setProcurementOfOnSiteAbnormalIssues(getStringCellValue(row.getCell(count++)));
            //21、现场异常问题质量
            MarketCommercialVehicleTable.setQualityOfOnSiteAbnormalIssues(getStringCellValue(row.getCell(count++)));
            //23、入库
            MarketCommercialVehicleTable.setWarehousing(getStringCellValue(row.getCell(count++)));
            //24、生产周期
            MarketCommercialVehicleTable.setProductionCycle(getStringCellValue(row.getCell(count++)));
            //25、未报工原因
//            if(getStringCellValue(row.getCell(count)).length() > 255){
//                System.out.println("==>"+getStringCellValue(row.getCell(count)));
//            }
            MarketCommercialVehicleTable.setGoLive(getStringCellValue(row.getCell(count++)));


            CVdataList.add(MarketCommercialVehicleTable);
        }



        workbook.close();

        return CVdataList;
    }

}
