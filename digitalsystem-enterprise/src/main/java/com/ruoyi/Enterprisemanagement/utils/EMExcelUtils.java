package com.ruoyi.Enterprisemanagement.utils;

import com.ruoyi.Enterprisemanagement.domain.*;
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
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

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
                    if(getStringCellValue(row.getCell(count))!=null) {
                        enterpriseManagementSpecialOperationsManagementLedger.setName(getStringCellValue(row.getCell(count++)));
                    }
                    else {
                        continue;
                    }
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

    public static List<EnterpriseManagementPersonnelRoster> PRparseExcel(MultipartFile file) throws IOException {
        List<EnterpriseManagementPersonnelRoster > dataList = new ArrayList<>();
        LocalDate myDate = LocalDate.of(1902, 1, 1);
        LocalDate myDate1 = LocalDate.of(2900, 1, 1);
        // 转换为Date类型
        Date date = java.sql.Date.valueOf(myDate);
        Date date1 = java.sql.Date.valueOf(myDate1);
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header rows
        for (int i = 0; i < 1 && rowIterator.hasNext(); i++) {
            rowIterator.next();
        }


        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster = new EnterpriseManagementPersonnelRoster();


            int count = 0;


            //0.序号
            enterpriseManagementPersonnelRoster.setNumber(Long.valueOf(getIntegerCellValue(row.getCell(count++))));

            //员工ID
            enterpriseManagementPersonnelRoster.setEmployeeId(getStringCellValue(row.getCell(count++)));
//
            //姓名
            enterpriseManagementPersonnelRoster.setName(getStringCellValue(row.getCell(count++)));

            //性别
            enterpriseManagementPersonnelRoster.setGender(getStringCellValue(row.getCell(count++)));
            //
            //生日
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementPersonnelRoster.setBirthday(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

            //年龄
            enterpriseManagementPersonnelRoster.setAge(Long.valueOf(getIntegerCellValue(row.getCell(count++))));

            //档案生日
            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementPersonnelRoster.setRecordBirth(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

            //身份证
            enterpriseManagementPersonnelRoster.setIdCard(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSocialSecurityNumber(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPersonnelScope(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPersonnelScopeText(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPersonnelSubScope(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPersonnelSubScopeText(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSalaryAccountingScope(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSalaryAccountingScopeText(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setCostCenter(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setCostCenterText(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setRootTissueId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setRootTissue(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFirstTissueId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFirstTissue(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSecondTissueId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSecondTissue(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setThirdTissueId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setThirdTissue(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFourthTissueId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFourthTissue(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFifthTissueId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFifthTissue(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSixthTissueId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSixthTissue(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPositionEthnicGroupId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPositionEthnicGroup(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setJobCategoryId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setJobCategory(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setJob(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setJobTitle(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTechnicalTitle(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTheHighestTechnicalTitl(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTechnicalLevel(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTheHighestTechnicalLevel(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setRankked(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPositionId(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPositionno(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setJobbTitle(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSalaryGrade(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSalaryyGrade(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPositionSalary(Long.valueOf(getIntegerCellValue(row.getCell(count++))));

            enterpriseManagementPersonnelRoster.setEmployeeGroup(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setEmployeeSubGroup(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setBackboneLevel(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPersonnelManagementClassification(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTheHighestEducationalLevel(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTheHighestEducationalSchool(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTheHighestEducationalMajor(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTheHighestDegree(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTheHighestDegreeSchool(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setTheHighestDegreeMajor(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFirstEducationalLevel(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFirstHighestDegree(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFirstHighestDegreeSchool(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setFirstHighestDegreeMajor(getStringCellValue(row.getCell(count++)));

            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementPersonnelRoster.setContractStartTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }
            // 假设date为您的java.util.Date对象
            Date date2 = getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count))));
//            //转成localdate
//            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date.before(date2)&&date2.before(date1)){

                enterpriseManagementPersonnelRoster.setContractEndTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

            enterpriseManagementPersonnelRoster.setContractTerm(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setContractSigningUnit(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setSigningFrequency(Long.valueOf(getIntegerCellValue(row.getCell(count++))));

            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementPersonnelRoster.setWorkingHours(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementPersonnelRoster.setJoinedTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementPersonnelRoster.setStartingTimeOfServiceLife(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementPersonnelRoster.setRetirementDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

            enterpriseManagementPersonnelRoster.setSiLing(Long.valueOf(getIntegerCellValue(row.getCell(count++))));

            enterpriseManagementPersonnelRoster.setNation(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setPoliticalLandscape(getStringCellValue(row.getCell(count++)));

            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){

                enterpriseManagementPersonnelRoster.setPartyMembershipTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
            }
            else {
                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
                count++;
            }

            enterpriseManagementPersonnelRoster.setMaritalStatus(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setHometown(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setHouseholdRegistrationNature(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setResidentAddress(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setContactInformation(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setEmergencyContact(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setEmergencyContactPhone(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setWorkContract(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setProvidentFundAccount(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setEnterpriseAnnuityAccount(getStringCellValue(row.getCell(count++)));

            enterpriseManagementPersonnelRoster.setUploadPhoto(getStringCellValue(row.getCell(count++)));






//
//            //2、部门
//            enterpriseManagementLaborContractLedger.setDocument(getStringCellValue(row.getCell(count++)));
//
//            //3.就职状态
//            enterpriseManagementLaborContractLedger.setEmploymentStatus(getStringCellValue(row.getCell(count++)));
//
//            //7.身份证号
//            enterpriseManagementLaborContractLedger.setIdNumber(getStringCellValue(row.getCell(count++)));
//
//            //电话号码
//            enterpriseManagementLaborContractLedger.setPhoneNumber(getStringCellValue(row.getCell(count++)));
//
//            //第一次合同起始
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
//
//                enterpriseManagementLaborContractLedger.setFirstContractPeriodStart(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
//            //第一次合同结束
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
//
//                enterpriseManagementLaborContractLedger.setFirstContractPeriodEnd(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
//            //第一次合同情况
//            if (getStringCellValue(row.getCell(count))!=null) {
//                enterpriseManagementLaborContractLedger.setContractStatusFirstly(getStringCellValue(row.getCell(count++)));
//
//            }
//            else {
//                count++;
//            }
//            //第二次合同起始
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
//
//                enterpriseManagementLaborContractLedger.setSecondContractPeriodStart(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
//            //第二次合同结束
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
//
//                enterpriseManagementLaborContractLedger.setSecondContractPeriodEnd(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
//            //第二次合同情况
//            if (getStringCellValue(row.getCell(count))!=null) {
//                System.out.println(row.getCell(count));
//                System.out.println("================");
//                enterpriseManagementLaborContractLedger.setContractStatusSecondly(getStringCellValue(row.getCell(count++)));
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
//            //第三次合同起始
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
//
//                enterpriseManagementLaborContractLedger.setThirdContractPeriodStart(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
//            //第三次合同结束
//            if (date.before(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count)))))){
//
//                enterpriseManagementLaborContractLedger.setThirdContractPeriodEnd(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));
//            }
//            else {
//                // 如果单元格数据为空，则跳过设置 Java 对象的属性值
//                count++;
//            }
//            //第三次合同情况
//            enterpriseManagementLaborContractLedger.setContractStatusThirdly(getStringCellValue(row.getCell(count++)));
//
            dataList.add(enterpriseManagementPersonnelRoster);

        }
        workbook.close();

        return dataList;

    }
}
