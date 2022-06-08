package com.dhlk.light.factoryconstruction.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author xmdeng
 * @date 2021/8/16 13:42
 */
@Slf4j
public class FileUtil {

    public static boolean uploadFile(MultipartFile multipartFile,File file){
        //创建父目录
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            file.mkdir();
        }
        try {
            multipartFile.transferTo(file);
        }catch (Exception e){
            log.error("文件写入失败，失败原因：{}",e);
            return false;
        }
        return true;
    }
}
