package com.ruoyi.file;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import org.aspectj.weaver.ast.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class Word2PdfAsposeUtil {
//    public static boolean getLicense() {
//        boolean result = false;
//        try {
//            InputStream is = Test.class.getClassLoader().getResourceAsStream("\license.xml"); // license.xml应放在..WebRootWEB-INFclasses路径下
//            License aposeLic = new License();
//            aposeLic.setLicense(is);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public static boolean doc2pdf(String inPath, String outPath) {
//        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
//            return false;
//        }
        FileOutputStream os = null;
        try {
            long old = System.currentTimeMillis();
            File file = new File(outPath); // 新建一个空白pdf文档
            os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("pdf转换成功，共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static void exportPdf(HttpServletResponse response, String pdfPath, String fileName) {
        InputStream inputStream = null;
        ServletOutputStream outputStream = null;
        File pdfFile = new File(pdfPath);
        try {
            byte[] bs = new byte[1024];
            inputStream = new FileInputStream(pdfFile);
            outputStream = response.getOutputStream();

            response.setContentType("application/pdf");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(fileName, "UTF-8"));

            while (inputStream.read(bs) > 0) {
                outputStream.write(bs);
            }

            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}