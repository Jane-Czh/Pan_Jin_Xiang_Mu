package com.ruoyi.Technology.domain;//import com.alibaba.excel.EasyExcel;
//import com.aspose.cells.PdfSaveOptions;
//import com.aspose.cells.Workbook;
//import com.aspose.words.Document;
//import com.aspose.words.License;
//import com.aspose.words.SaveFormat;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.Map;
//
//
//
//@Slf4j
//@Component
//public class FileUtil {
//
//
//    /**
//     * 文件下载方法
//     *
//     * @param filename 文件保存名
//     * @param filePath 文件下载路径
//     * @param res
//     * @throws IOException
//     */
//    public static void downloadFile(String filename, String filePath, HttpServletResponse res) throws IOException {
//        // 发送给客户端的数据
//        OutputStream outputStream = res.getOutputStream();
//        byte[] buff = new byte[1024];
//        BufferedInputStream bis = null;
//        // 读取filename
//        bis = new BufferedInputStream(new FileInputStream(new File(filePath + filename)));
//        int i = bis.read(buff);
//        while (i != -1) {
//            outputStream.write(buff, 0, buff.length);
//            outputStream.flush();
//            i = bis.read(buff);
//        }
//        outputStream.close();
//    }
//
//    public static void deleteFile(String filename, String filePath) {
//        File file = new File(filePath + filename);
//        if (file.exists()) {//文件是否存在
//            file.delete();//删除文件
//        }
//    }
//
//
//
//
//    /**
//     * @Author WXK
//     * @Description 返回预览pdf流
//     * @Date 2021/3/11
//     **/
//    public static void previewFile1(String filePath, HttpServletResponse res) throws IOException {
//        // 发送给客户端的数据
//        OutputStream outputStream = res.getOutputStream();
//        try {
//            byte[] buff = new byte[1024];
//            BufferedInputStream bis = null;
//            // 读取filename
//            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
//            int i = bis.read(buff);
//            while (i != -1) {
//                outputStream.write(buff, 0, buff.length);
//                outputStream.flush();
//                i = bis.read(buff);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            outputStream.close();
//        }
//
//
//    }
//
//    /**
//     * @Author WXK
//     * @Description 删除转换生成的pdf文件
//     * @Date 2021/3/11
//     **/
//    public static void deleteFile1(String filePath) {
//        File file = new File(filePath);
//        if (file.exists()) {//文件是否存在
//            file.delete();//删除文件
//        }
//    }
//
//
//
//
//
//
//
//    public static boolean getLicense() {
//        boolean result = false;
//        try {
//            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml"); //
//            License aposeLic = new License();
//            aposeLic.setLicense(is);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static boolean getExcelLicense() {
//        boolean result = false;
//        try {
//            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml"); //
//            com.aspose.cells.License license = new com.aspose.cells.License();
//            license.setLicense(is);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * @Author WXK
//     * @Description word转 pdf
//     * @Date 2021/3/12
//     **/
//    public static String doc2pdf(String fileName, String filePath) {
//
//        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
//            return null;
//        }
//        try {
//            String oldFile = filePath + fileName;
//            String newFile = oldFile.substring(0, oldFile.lastIndexOf("."))+".pdf";
//
//            File file = new File(newFile);  //新建一个空白pdf文档
//            FileOutputStream os = new FileOutputStream(file);
//            Document doc = new Document(oldFile);                    //Address是将要被转化的word文档
//            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
//            log.info("转换成功");  //转化用时
//            return newFile;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * @Author WXK
//     * @Description 上传文件时,word转pdf 保存在本地
//     * @Date 2021/3/29
//     **/
//    public static String doc2pdf(InputStream inputStream,String fileName, String filePath) {
//
//        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
//            return null;
//        }
//        try {
//            String newFile = filePath + fileName;
//
//            File file = new File(newFile);  //新建一个空白pdf文档
//            FileOutputStream os = new FileOutputStream(file);
//            Document doc = new Document(inputStream);                    //Address是将要被转化的word文档
//            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
//            log.info("转换成功");  //转化用时
//            return newFile;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * @Author WXK
//     * @Description excel转pdf
//     * @Date 2021/3/22
//     **/
//    public static String excelToPdf(String fileName, String filePath) throws IOException{
//
//        if (!getExcelLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
//            return null;
//        }
//        FileOutputStream fileOS=null;
//        try {
//            String oldFile = filePath + fileName;
//            String newFile = oldFile.substring(0, oldFile.lastIndexOf("."))+".pdf";
//            File pdfFile = new File(newFile);// 输出路径
//           /* Workbook wb = new Workbook(oldFile);// 原始excel路径
//             fileOS = new FileOutputStream(pdfFile);
//            wb.save(fileOS, SaveFormat.PDF);
//            fileOS.flush();
//
//            log.info("转换成功");*/
//
//            Workbook wb = new Workbook(oldFile);// 原始excel路径
//
//
//            fileOS = new FileOutputStream(newFile);
//            PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
//            pdfSaveOptions.setOnePagePerSheet(true);
//
//
//            int[] autoDrawSheets={3};
//            //当excel中对应的sheet页宽度太大时，在PDF中会拆断并分页。此处等比缩放。
//            autoDraw(wb,autoDrawSheets);
//
//            int[] showSheets={0};
//            //隐藏workbook中不需要的sheet页。
//            printSheetPage(wb,showSheets);
//            wb.save(fileOS, pdfSaveOptions);
//            fileOS.flush();
//            fileOS.close();
//            log.info("完毕");
//            return newFile;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            fileOS.close();
//        }
//        return null;
//    }
//
//
//    /**
//     * @Author WXK
//     * @Description 上传文件时 excel转pdf保存在本地
//     * @Date 2021/3/29
//     **/
//    public static String excelToPdf(InputStream inputStream, String fileName,String filePath) throws IOException{
//
//        if (!getExcelLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
//            return null;
//        }
//        FileOutputStream fileOS=null;
//        try {
//            String newFile = filePath + fileName;
//
//            Workbook wb = new Workbook(inputStream);// 原始excel路径
//
//
//            fileOS = new FileOutputStream(newFile);
//            PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
//            pdfSaveOptions.setOnePagePerSheet(true);
//
//
//            int[] autoDrawSheets={3};
//            //当excel中对应的sheet页宽度太大时，在PDF中会拆断并分页。此处等比缩放。
//            autoDraw(wb,autoDrawSheets);
//
//            int[] showSheets={0};
//            //隐藏workbook中不需要的sheet页。
//            printSheetPage(wb,showSheets);
//            wb.save(fileOS, pdfSaveOptions);
//            fileOS.flush();
//            fileOS.close();
//            log.info("完毕");
//            return newFile;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            fileOS.close();
//        }
//        return null;
//    }
//
//    /**
//     * 设置打印的sheet 自动拉伸比例
//     * @param wb
//     * @param page 自动拉伸的页的sheet数组
//     */
//    public static void autoDraw(Workbook wb,int[] page){
//        if(null!=page&&page.length>0){
//            for (int i = 0; i < page.length; i++) {
//                wb.getWorksheets().get(i).getHorizontalPageBreaks().clear();
//                wb.getWorksheets().get(i).getVerticalPageBreaks().clear();
//            }
//        }
//    }
//
//
//    /**
//     * 隐藏workbook中不需要的sheet页。
//     * @param wb
//     * @param page 显示页的sheet数组
//     */
//    public static void printSheetPage(Workbook wb,int[] page){
//        for (int i= 1; i < wb.getWorksheets().getCount(); i++)  {
//            wb.getWorksheets().get(i).setVisible(false);
//        }
//        if(null==page||page.length==0){
//            wb.getWorksheets().get(0).setVisible(true);
//        }else{
//            for (int i = 0; i < page.length; i++) {
//                wb.getWorksheets().get(i).setVisible(true);
//            }
//        }
//    }
//
//
//
//
//
//
//
//    /**
//     * @Author WXK
//     * @Description 查看对应的.pdf文件是否存在
//     * @Date 2021/3/12
//     **/
//    public static boolean checkFileExist(String filePath) {
//        File file = new File(filePath);
//        if (file.exists()) {//文件是否存在
//            return true;
//        }
//        return false;
//    }
//}
