package com.ruoyi.file;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.ruoyi.common.config.RuoYiConfig;
import org.aspectj.weaver.ast.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
public class Word2PdfAsposeUtil {
//    public static boolean getLicense() {
//        boolean result = false;
//        try {
//            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml"); // license.xml应放在..WebRootWEB-INFclasses路径下
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
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            System.out.println("去水印失败");
        }

        // 从配置中获取上传根路径"D:\\ruoyi\\uploadPath\\upload"
        String uploadPath = RuoYiConfig.getUploadPath();
        // 找到 "upload" 之后的部分作为相对路径\\2024\\06\\03\\文件3（v1.0）_20240603140158A001.docx
        String relativePath = inPath.substring(inPath.indexOf("/upload/") + "/upload/".length());
        // 拼接成本地文件系统路径D:\\ruoyi\\uploadPath\\upload\\2024\\06\\03\\文件3（v1.0）_20240603140158A001.docx
        String localFilePath = uploadPath + File.separator + relativePath;
        // 将路径中的 "/" 替换为系统的文件分隔符
        localFilePath = localFilePath.replace("/", File.separator);

        // doc文件后缀转为.pdf --> 即文件路径 localPdfPath:xxx.pdf  --> 输出目录 outputFile --> os
        String localPdfPath = localFilePath.substring(0, localFilePath.lastIndexOf('.')) + ".pdf";
        System.out.println("localFilePath======>" + localFilePath);
        System.out.println("localPdfPath======>" + localPdfPath);
        FileOutputStream os = null;
        try {
            System.out.println("inPath==>" + inPath);
            System.out.println("outPath==>" + outPath);
            long old = System.currentTimeMillis();
            File inputFile = new File(localFilePath);
            if (!inputFile.exists()) {
                System.out.println("输入文件不存在: " + inputFile.getAbsolutePath());
            }

            // 通过指定文件路径名创建File对象
            File outputFile = new File(localPdfPath);
            // 确保输出目录存在
            File outputDir = outputFile.getParentFile();
            if (!outputDir.exists()) {
                System.out.println("输出目录不存在，创建目录: " + outputDir);
                if (!outputDir.mkdirs()) {
                    System.out.println("无法创建输出目录: " + outputDir);
                }
            }

            System.out.println("outputfile==>" + outputDir);
            //输出流用于将转换后的PDF文件写入指定的输出路径
            os = new FileOutputStream(outputFile);
            System.out.println("os==>" + os);

//            OsInfo osInfo = SystemUtil.getOsInfo();
//            if(osInfo.isLinux()){
//                FontSettings.setFontsFolder("/usr/share/fonts/chinese", true);
//            }

            //使用 Aspose.Words库 来实现从Word到PDF的文件转换
            Document doc = new Document(inputFile.getAbsolutePath()); // 使用绝对路径
            System.out.println("doc==>" + doc);

            doc.save(os, SaveFormat.PDF); // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("pdf转换成功，共耗时：" + ((now - old) / 1000.0) + "秒");
            os.close();
        } catch (Exception e) { e.printStackTrace(); }

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

//    public static boolean getLicense() {
//        boolean result = false;
//        InputStream is = null;
//        try {
//            Resource resource = new ClassPathResource("license.xml");
//            is = resource.getInputStream();
//            License aposeLic = new License();
//            aposeLic.setLicense(is);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return result;
//    }


public static boolean getLicense() {
    boolean result = false;
    InputStream is = null;
    try {
        // 尝试加载许可证文件
        is = Word2PdfAsposeUtil.class.getClassLoader().getResourceAsStream("license.xml");
        if (is == null) {
            throw new FileNotFoundException("License file not found in classpath");
        }else {
            System.out.println("加载成功-------------");
        }

        // 打印许可证文件内容（用于调试）
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder licenseContent = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            licenseContent.append(line).append("\n");
        }
        System.out.println("License file content: " + licenseContent.toString());
        is = new ByteArrayInputStream(licenseContent.toString().getBytes());

        // 设置许可证
        License asposeLic = new License();
        asposeLic.setLicense(is);
        result = true;
    } catch (Exception e) {
        // 打印异常堆栈跟踪
        System.out.println("Failed to load Aspose license:");
        e.printStackTrace();
    } finally {
        // 确保流被关闭
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return result;
}

//    public static boolean getLicense() {
//        boolean result = false;
//        try {
////			InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("license.xml");
//            String license =
//                    "<License>\n" +
//                            "  <Data>\n" +
//                            "    <Products>\n" +
//                            "      <Product>Aspose.Total for Java</Product>\n" +
//                            "      <Product>Aspose.Words for Java</Product>\n" +
//                            "    </Products>\n" +
//                            "    <EditionType>Enterprise</EditionType>\n" +
//                            "    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n" +
//                            "    <LicenseExpiry>20991231</LicenseExpiry>\n" +
//                            "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
//                            "  </Data>\n" +
//                            "  <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n" +
//                            "</License>";
//            InputStream is = new ByteArrayInputStream(license.getBytes("UTF-8"));
//            License aposeLic = new License();
//            aposeLic.setLicense(is);
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("==========失败");
//        }
//        return result;
//    }
//

}