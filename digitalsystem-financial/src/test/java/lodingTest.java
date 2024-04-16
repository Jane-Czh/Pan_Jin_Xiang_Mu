//import com.spire.xls.FileFormat;
//import com.spire.xls.Workbook;
import com.heli.financial.service.IFinancialIndicatorsHandfillTableService;
import jdk.internal.instrumentation.TypeMapping;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.servlet.headers.HttpPublicKeyPinningDsl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: PACKAGE_NAME
 * @Author: hong
 * @CreateTime: 2024-03-31  09:49
 * @Description: TODO
 * @Version: 1.0
 */


public class lodingTest {
    @Autowired
    IFinancialIndicatorsHandfillTableService financialIndicatorsHandfillTableService;

    @Test
    public void checkDataIsExisted(){
//        financialIndicatorsHandfillTableService.checkHandFillDataIsExisted( "2024-02");
    }













    @Test
    public void load() throws Exception {


        //1、指定要读取EXCEL文档名称
        String filename = "d:\\盘锦项目\\dev\\2、利润表.xlsx";
        //2、创建输入流
        FileInputStream input = new FileInputStream(filename);
        //3、通过工作簿工厂类来创建工作簿对象
        org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(input);
        //4、获取工作表 (可以按照sheet名字，也可以按照sheet的索引)
//        String sheetName = "利润表";
//        Sheet sheet = workbook.getSheet(sheetName);
//        Sheet sheet=workbook.getSheet("工作表1");
        Sheet sheet = workbook.getSheetAt(0);
        //5、获取行
        Row row = sheet.getRow(2);
        //6、获取单元格
        Cell cell = row.getCell(1);


//设置单元格类型
//        cell.setCellType(CellType.STRING);
//获取单元格数据
//        String cellValue = cell.getStringCellValue();


        //7、读取单元格内容
        System.out.println(cell.getNumericCellValue());
        System.out.println(cell.getStringCellValue());


    }
}
