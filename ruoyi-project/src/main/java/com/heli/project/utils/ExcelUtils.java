package com.heli.project.utils;

import com.heli.project.domain.ProjectHistoryInfoTable;
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

    public static List<ProjectHistoryInfoTable> parseExcel(MultipartFile file) throws IOException {
        List<ProjectHistoryInfoTable> dataList = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            ProjectHistoryInfoTable projectHistoryInfoTable = new ProjectHistoryInfoTable();
            /**
             * 将excel设置的字段，写入到数据库对应字段
             */

//            marketSalesTable.setMsId(GenerateId.getUUid());

            int count = 0;

            projectHistoryInfoTable.setProjectName(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setCategory(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setLevel(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setDepartment(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setAttribute(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setDescription(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setStartDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));

            projectHistoryInfoTable.setProgressAlloverProgress(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setImportDate(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));

            projectHistoryInfoTable.setRemake(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setManager(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setTeamMembers(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setStatus(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setProgress(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setCurrentStatus(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setGoal(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setScope(getStringCellValue(row.getCell(count++)));

            projectHistoryInfoTable.setPlannedCompletionTime(getDateCellValue(ExcelDateUtils.convertExcelDateToString(getNumericCellValue(row.getCell(count++)))));

            projectHistoryInfoTable.setCompletionSummary(getStringCellValue(row.getCell(count)));


            dataList.add(projectHistoryInfoTable);
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
