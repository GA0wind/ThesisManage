//package com.ncu.graduation.util;
//
//
//import com.ncu.graduation.error.CommonException;
//import com.ncu.graduation.error.EmBulletinError;
//import com.ncu.graduation.error.EmCommonError;
//import com.ncu.graduation.error.EmFileError;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//
//import org.jodconverter.core.DocumentConverter;
//import org.jodconverter.core.document.DefaultDocumentFormatRegistry;
//import org.jodconverter.core.document.DocumentFormat;
//import org.jodconverter.core.office.OfficeException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
///**
// * @author ：grh
// * @date ：Created in 2020/3/20 15:56
// * @description：将文件转为PDF
// * @modified By：
// * @version: 0.0.1
// */
//@Component
//public class ConverstPDF {
//
//  @Autowired
//  private DocumentConverter converter;
//
//  public ByteArrayOutputStream officeToPDF(File file) {
//
//    if (file == null) {
//      throw new CommonException(EmFileError.FILE_IS_EMPTY);
//    }
//
//    // Here, we could have a dedicated service that would convert document
//    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//
//      final DocumentFormat targetFormat =
//          DefaultDocumentFormatRegistry.getFormatByExtension("pdf");
//      Assert.notNull(targetFormat, "targetFormat must not be null");
//
//      converter.convert(file).to(baos).as(targetFormat).execute();
//
//      return baos;
//
//    } catch (OfficeException | IOException e) {
//      e.printStackTrace();
//    }
//
//    return null;
//
//  }
//
//}
