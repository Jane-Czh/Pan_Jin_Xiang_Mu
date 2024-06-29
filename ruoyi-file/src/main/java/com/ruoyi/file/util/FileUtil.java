package com.ruoyi.file.util;


import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import org.aspectj.weaver.ast.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;



@Component
public class FileUtil {


    /**
     * 文件下载方法
     *
     * @param filename 文件保存名
     * @param filePath 文件下载路径
     * @param res
     * @throws IOException
     */
    public static void downloadFile(String filename, String filePath, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File(filePath + filename)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
        outputStream.close();
    }

    public static void deleteFile(String filename, String filePath) {
        File file = new File(filePath + filename);
        if (file.exists()) {//文件是否存在
            file.delete();//删除文件
        }
    }


    /**
     * @Author WXK
     * @Description 返回预览pdf流
     * @Date 2021/3/11
     **/
    public static void previewFile1(String filePath, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        try {
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            // 读取filename
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
        }


    }

    /**
     * @Author WXK
     * @Description 删除转换生成的pdf文件
     * @Date 2021/3/11
     **/
    public static void deleteFile1(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {//文件是否存在
            file.delete();//删除文件
        }
    }


    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml"); //
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    /**
     * @Author WXK
     * @Description word转 pdf
     * @Date 2021/3/12
     **/
    public static String doc2pdf(String fileName, String filePath) {

        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
            return null;
        }
        try {
            String oldFile = filePath + fileName;
            String newFile = oldFile.substring(0, oldFile.lastIndexOf(".")) + ".pdf";

            File file = new File(newFile);  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(oldFile);                    //Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            System.out.println("转换成功");
            return newFile;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("转换失败");
        }
        return null;
    }


    /**
     * @Author WXK
     * @Description 上传文件时, word转pdf 保存在本地
     * @Date 2021/3/29
     **/
    public static String doc2pdf(InputStream inputStream, String fileName, String filePath) {

        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
            return null;
        }
        try {
            String newFile = filePath + fileName;

            File file = new File(newFile);  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inputStream);                    //Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            System.out.println("转换成功");
            return newFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Author WXK
     * @Description 查看对应的.pdf文件是否存在
     * @Date 2021/3/12
     **/
    public static boolean checkFileExist(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {//文件是否存在
            return true;
        }
        return false;
    }



}


