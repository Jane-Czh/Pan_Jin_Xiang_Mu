package com.ruoyi.file;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import org.aspectj.weaver.ast.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
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

    public static void downloadFile(String sourceUrl, String destinationPath) throws Exception {
        URL url = new URL(sourceUrl);
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(destinationPath);
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        fileOutputStream.close();
        readableByteChannel.close();
    }

    public static boolean doc2pdf(String inPath, String outPath) {
//        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
//            return false;
//        }
        FileOutputStream os = null;

//        String inPath = "http://localhost:8080/profile/upload/2024/05/31/文件3（v1.0）_20240531144234A002.docx";
//        String outPath = "D:\\ruoyi\\uploadPath\\upload\\2024\\05\\31\\文件3（v1.0）_20240531144234A002.pdf";

        try {
            System.out.println("inPath==>" + inPath);
            System.out.println("outPath==>" + outPath);
            long old = System.currentTimeMillis();

            File inputFile = null;
            if (inPath.startsWith("http")) {
                // 处理 URL 转为本地路径
                String localPath = "D:\\ruoyi\\uploadPath\\upload\\2024\\05\\31\\文件3（v1.0）_20240531144234A002.docx";
                inputFile = new File(localPath);
            } else {
                inputFile = new File(inPath);
            }

            if (!inputFile.exists()) {
                System.out.println("输入文件不存在: " + inputFile.getAbsolutePath());
//                return;
            }

            // 确保输出目录存在
            File outputFile = new File("D:\\ruoyi\\uploadPath\\upload\\2024\\05\\31");
            File outputDir = outputFile.getParentFile();
            if (!outputDir.exists()) {
                System.out.println("输出目录不存在，创建目录: " + outputDir);
                if (!outputDir.mkdirs()) {
                    System.out.println("无法创建输出目录: " + outputDir);
//                    return;
                }
            }

            System.out.println("outputfile==>" + outputDir);
             os = new FileOutputStream(outputFile);
            System.out.println("os==>" + os);

            Document doc = new Document(inputFile.getAbsolutePath()); // 使用绝对路径
            System.out.println("doc==>" + doc);

            doc.save(os, SaveFormat.PDF); // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("pdf转换成功，共耗时：" + ((now - old) / 1000.0) + "秒");
            os.close(); } catch (Exception e) { e.printStackTrace(); }


//            try {
//            // 下载文件到本地
//            downloadFile(inPath, "D:\\桌面\\test");
//
//
//            System.out.println("inPath==>" + inPath);
//            System.out.println("outPath==>" + outPath);
//
//            long old = System.currentTimeMillis();
//            File inputFile = new File(inPath); // 使用inPath变量创建文件对象
//            if (!inputFile.exists()) {
//                System.out.println("输入文件不存在: " + inPath);
////                return; // 如果输入文件不存在，结束方法
//            }
//            // 确保输出目录存在
//            File outputFile = new File(outPath);
//            File outputDir = outputFile.getParentFile();
//            if (!outputDir.exists()) {
//                System.out.println("输出目录不存在，创建目录: " + outputDir);
//                if (!outputDir.mkdirs()) {
//                    System.out.println("无法创建输出目录: " + outputDir);
////                    return; // 如果无法创建输出目录，结束方法
//                }
//            }
//            System.out.println("outputfile==>" + outputFile);
//            os = new FileOutputStream(outputFile); // 初始化为输出文件路径
//            System.out.println("os==>" + os);
//            Document doc = new Document(new FileInputStream(inputFile)); // 使用文件输入流
//            System.out.println("doc==>" + doc);
//            doc.save(os, SaveFormat.PDF); // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
//            // EPUB, XPS, SWF 相互转换
//            long now = System.currentTimeMillis();
//            System.out.println("pdf转换成功，共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
//            os.close(); // 关闭输出流
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            if (os != null) {
//                try {
//                    os.flush();
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
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