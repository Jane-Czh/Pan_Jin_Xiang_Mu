package com.ruoyi.file.util;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author: youchao
 * @description:
 * @data: 2024/1/19 16:39
 **/
public class WordToPdf {

    /**
     * word 转 pdf
     *
     * @param wordFile word 文件路径
     * @param pdfFile  生成的 pdf 文件路径
     */
    public static void word2Pdf(String wordFile, String pdfFile) {
        File file = new File(pdfFile);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(pdfFile);
            Document doc = new Document(wordFile);
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            System.out.println("word转pdf失败");
            e.printStackTrace();
        } finally {
            try {
                assert os != null;
                os.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("word转pdf结束");
        }
    }

    /**
     * word 转 pdf 生成文件到服务器
     *
     * @param inputStream word
     * @param pdfFile     生成的 pdf 文件路径
     */
    public static void word2Pdf(InputStream inputStream, String pdfFile) {
        System.out.println("word转pdf[开始]");
        File file = new File(pdfFile);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(pdfFile);
            Document doc = new Document(inputStream);
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            System.out.println("word转pdf失败");
            e.printStackTrace();
        } finally {
            assert os != null;
            try {
                inputStream.close();
                os.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("word转pdf结束");
        }
    }

    /**
     * word 转 pdf 返回文件流
     *
     * @param inputStream word文件流
     * @return 生成pdf文件流
     */
    public static InputStream word2Pdf(InputStream inputStream) {
        System.out.println("word转pdf[开始]");
        //去水印

        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            Document doc = new Document(inputStream);
            doc.save(os, SaveFormat.PDF);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            System.out.println("word转pdf失败");
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("word转pdf[结束]");
        }
        return null;
    }
}

