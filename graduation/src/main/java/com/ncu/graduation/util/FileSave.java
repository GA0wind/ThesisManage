package com.ncu.graduation.util;

import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmBulletinError;
import com.ncu.graduation.error.EmFileError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileSave {
    public static String fileSave(MultipartFile file, FileTypeEnum fileTypeEnum) {

        if (file.isEmpty()) {
            throw new CommonException(EmFileError.FILE_IS_EMPTY);
        }
        String path = fileTypeEnum.getPreUrl();
        String fileTempName = file.getOriginalFilename();
        //加个时间戳，尽量避免文件名称重复
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "#" + fileTempName;
        File dest = new File(path, fileName);

        //判断文件是否已经存在
        if (dest.exists()) {
            throw new CommonException(EmFileError.FILE_IS_EXIST);
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
            throw new CommonException(EmFileError.FILE_UPLOAD_FAIL);
        }
        return fileName;
    }


}
