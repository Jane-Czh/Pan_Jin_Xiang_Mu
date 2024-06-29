package com.ruoyi.common.utils.excel;

import com.ruoyi.common.annotation.RecordTemplate;
import com.ruoyi.common.enums.FillCommentEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.Threads;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @Author: hong
 * @CreateTime: 2024-03-31  11:04
 * @Description: excel读取指定单元格工具类
 */

public class ReadExcelCellUtils {

    private static final Logger logger = LoggerFactory.getLogger(ReadExcelCellUtils.class);

    /**
     * 获取sheet对象
     */

    public static Sheet getSheetByStream(InputStream inputStream, String sheetName) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            logger.info("excel文件有误");
            throw new ServiceException("excel文件有误");
        }
        Sheet sheet = null;
        if (StringUtils.isBlank(sheetName)) {
            //取第一个
            sheet = workbook.getSheetAt(0);
        } else {
            sheet = workbook.getSheet(sheetName.trim());
        }
        System.out.println("当前sheet为" + sheet);
        return sheet;
    }

    /**
     * 读取单个单元格的值
     *
     * @param sheet
     * @param name
     * @param rowNo
     * @param columnNo
     */
    public static String readCell(Sheet sheet, int rowNo, int columnNo, FillCommentEnum comment, String name) {
        //5、获取行
        Row row = sheet.getRow(rowNo);
        //6、获取单元格
        Cell cell = row.getCell(columnNo);
        String stringCellValue;
        if (comment.getCode() == 0 && cell == null) {
            throw new ServiceException(name + "不能为空");
        } else if (cell == null) {
            stringCellValue = "0";
        } else {
            //7、读取单元格内容
            stringCellValue = getCellValueByType(cell, name);
        }

//        logger.info(stringCellValue);
        return stringCellValue;
    }

    public static String getCellValueByType(Cell cell, String name) {
        String cellValue = "";
        if (cell.getCellType() == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                cellValue = DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd");
            } else {
                NumberFormat nf = NumberFormat.getInstance();
                cellValue = String.valueOf(nf.format(cell.getNumericCellValue())).replace(",", "");
            }
//            logger.info(cellValue);
        } else if (cell.getCellType() == CellType.STRING) {
            cellValue = String.valueOf(cell.getStringCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            cell.setCellType(CellType.STRING);
            cellValue = String.valueOf(cell.getStringCellValue());
        } else if (cell.getCellType() == CellType.ERROR) {
            cellValue = "错误类型";
            throw new ServiceException("单元格" + name + ": " + cellValue);
        }
        return cellValue;
    }


    public static String getCellValueByType12345(Cell cell, String name) {
        String cellValue = "";
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                cellValue = DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd");
            } else {
                NumberFormat nf = NumberFormat.getInstance();
                cellValue = String.valueOf(nf.format(cell.getNumericCellValue())).replace(",", "");
            }
//            logger.info(cellValue);
        } else if (cell.getCellTypeEnum() == CellType.STRING) {
            cellValue = String.valueOf(cell.getStringCellValue());
        } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellTypeEnum() == CellType.ERROR) {
            cellValue = "错误类型";
            throw new ServiceException("单元格" + name + ": " + cellValue);
        }
        return cellValue;
    }


    /**
     * 解析excel备案数据到对象
     *
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object parseExcelToModel(String className, Sheet sheet) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        Field[] declaredFields = clazz.getDeclaredFields();
        Object o = clazz.newInstance();
        //获取excel的流，前端传入
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(RecordTemplate.class)) {
                RecordTemplate annotation = field.getAnnotation(RecordTemplate.class);
                Class<?> type = field.getType();
//                logger.info(type.getName());
                //单元格的值
                String value = ReadExcelCellUtils.readCell(sheet, annotation.rowNo(), annotation.columnNo(), annotation.comment(), annotation.name());
                //通过反射把 单元格的值 给对象属性
                setFieldValue(o, field, type, value);
            }
        }
        return o;
    }


    /**
     * 通过反射把 单元格的值 给对象属性
     *
     * @param o
     * @param field
     * @param type
     * @param value
     * @throws IllegalAccessException
     */
    private static void setFieldValue(Object o, Field field, Class<?> type, String value) throws IllegalAccessException {
        Object targetValue = null;
        if (StringUtils.isEmpty(value)) {
            return;
        }
        if (type.equals(Integer.class)) {
            targetValue = Integer.parseInt(value);
        } else if (type.equals(Double.class)) {
            targetValue = Double.parseDouble(value);
        } else if (type.equals(Float.class)) {
            targetValue = Float.parseFloat(value);
        } else if (type.equals(Boolean.class)) {
            targetValue = Boolean.getBoolean(value);
        } else if (type.equals(BigDecimal.class)) {
            //反射使用，将string转换为bigdecimal
            targetValue = new BigDecimal(value);
        } else if (type.equals(Long.class)) {
            //反射使用，将string转换为Long
            targetValue = Long.parseLong(value);
        } else {
            targetValue = value;
        }
        field.set(o, targetValue);
    }

    /**
     * 解析数据到model
     *
     * @param className   类名
     * @param inputStream 输入流
     * @param sheetName   sheetname 可以为null，为null时取第一页
     * @return 映射对象
     */
    public static Object parseExcelToModel(String className, InputStream inputStream, String sheetName) {
        Sheet sheetByStream = getSheetByStream(inputStream, sheetName);
        try {
            return parseExcelToModel(className, sheetByStream);
        } catch (Exception e) {
            logger.info("解析数据到model失败");
            e.printStackTrace();
            throw new ServiceException("excel格式错误");
        }
    }

}
