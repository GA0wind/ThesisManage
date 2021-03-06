package com.ncu.graduation.controller;


import com.ncu.graduation.dto.ImageDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.EmBulletinError;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmFileError;
//import com.ncu.graduation.util.ConverstPDF;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {

//  @Autowired
//  private ConverstPDF converstPDF;

  @RequestMapping(value = "/img/upload", method = RequestMethod.GET)
  @ResponseBody
  public ImageDTO imageUpload(@RequestParam("editormd-image-file") MultipartFile file) {
    // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
    // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
    String path =
        Objects.requireNonNull(
            Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath()
            + "static/images/markdown/";

    ImageDTO imageDTO = new ImageDTO();
    if (file.isEmpty()) {
      imageDTO.setErrno(1);
//      imageDTO.setMessage(EmBulletinError.BULLETIN_FILE_IS_EMPTY.getErrMsg());
      return imageDTO;
    }
    String fileTempName = file.getOriginalFilename();
    //加个时间戳，尽量避免文件名称重复
    String fileName =
        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileTempName;
    File dest = new File(path, fileName);
    //判断文件是否已经存在
    if (dest.exists()) {
      imageDTO.setErrno(2);
//      imageDTO.setMessage(EmBulletinError.BULLETIN_FILE_IS_EXIST.getErrMsg());
      return imageDTO;
    }
    //判断文件父目录是否存在
    if (!dest.getParentFile().exists()) {
      dest.getParentFile().mkdir();
    }
    try {
      //保存文件
      file.transferTo(dest);
    } catch (IOException e) {
      e.printStackTrace();
      imageDTO.setErrno(3);
//      imageDTO.setMessage(EmBulletinError.UNKNOWN_ERROR.getErrMsg());
      return imageDTO;
    }
    imageDTO.setErrno(0);
    String[] url = new String[1];
    url[0] = "/images/markdown/" + fileName;
    imageDTO.setData(url);
    return imageDTO;
  }

  @GetMapping("/download")
  @ResponseBody
  public Object downloadFile(@RequestParam("filePath") String fileName,
      String fileType,
      HttpServletResponse response) {
    //判断文件类型
    FileTypeEnum emfileType = FileTypeEnum.judgeType(fileType);
    if (emfileType == null) {
      return ResultDTO.errorOf(EmFileError.FILE_TYPE_IS_EMPTY);
    }
    //设置完整文件路径
    String trueFileName = emfileType.getPreUrl() + fileName;

    File file = new File(trueFileName);
    if (file.exists()) {
      response.reset();
      response.setContentType("application/octet-stream");
      try {
        response.setHeader("Content-Disposition",
            "attachment;fileName=" + URLEncoder.encode(fileName.split("#", 2)[1], "utf8"));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      byte[] buffer = new byte[1024];
      //输出流
      OutputStream os = null;
      try (FileInputStream fis = new FileInputStream(file);
          BufferedInputStream bis = new BufferedInputStream(fis);) {
        os = response.getOutputStream();
        int i = bis.read(buffer);
        while (i != -1) {
          os.write(buffer);
          i = bis.read(buffer);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          if (os != null) {
            os.close();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
      return ResultDTO.okOf("下载成功");

    }
    return ResultDTO.errorOf(EmCommonError.UNKNOWN_ERROR.getErrCode(), "下载失败");
  }

//  @ResponseBody
//  @GetMapping("/readOnline")
//  public Object readonline(@RequestParam("filePath") String fileName, String fileType,
//      HttpServletResponse response) {
//    //获取文件真实路径, 获取文件
//    FileTypeEnum emfileType = FileTypeEnum.judgeType(fileType);
//    if (emfileType == null) {
//      return ResultDTO.errorOf(EmFileError.FILE_TYPE_IS_EMPTY);
//    }
//    String trueFileName = emfileType.getPreUrl() + fileName;
//    File file = new File(trueFileName);
//    if (!file.exists()) {
//      return ResultDTO.errorOf(EmFileError.FILE_IS_NOT_EXIST);
//    }
//
//    ByteArrayOutputStream baos = null;
//    //如果文件不以pdf结尾, 则转化, 否则直接返回
//    if (!trueFileName.endsWith(".pdf")) {
//      baos = converstPDF.officeToPDF(file);
//    } else {
//      //将文件转入baos流
//      try (FileInputStream fis = new FileInputStream(file);) {
//        baos = new ByteArrayOutputStream();
//        byte[] buff = new byte[1024];
//        int len = -1;
//        while ((len = fis.read(buff)) != -1) {
//          baos.write(buff, 0, len);
//        }
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//    response.reset();
//    response.setContentType("application/pdf");
//    //输出流
//    OutputStream os = null;
//    try {
//      os = response.getOutputStream();
//      if (baos != null) {
//        os.write(baos.toByteArray());
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    } finally {
//      try {
//        if (os != null) {
//          os.close();
//        }
//        if (baos != null) {
//          baos.close();
//        }
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//    return new ModelAndView("/pdf");
//  }


}
