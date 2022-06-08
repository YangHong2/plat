package com.dhlk.light.factoryconstruction.service.impl;

import com.dhlk.light.factoryconstruction.DhlkLightFactoryConstructionApplication;
import com.dhlk.light.factoryconstruction.common.exception.BaseException;
import com.dhlk.light.factoryconstruction.common.result.ServerErrorResultCodeEnum;
import com.dhlk.light.factoryconstruction.datamap.FirmwareUpgradeMap;
import com.dhlk.light.factoryconstruction.pojo.entity.UpgradePace;
import com.dhlk.light.factoryconstruction.service.FirmwareUpdateService;
import com.dhlk.light.factoryconstruction.thread.FirmwareUpgradeThread;
import com.dhlk.light.factoryconstruction.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author xmdeng
 * @date 2021/8/16 13:34
 */
@Service
@Slf4j
public class FirmwareUpdateServiceImpl implements FirmwareUpdateService {


    @Override
    public void uploadFirmware(MultipartFile firmwareFile,  String devIds,Integer threadSize) {
        if(firmwareFile.getSize() < 1){
            throw new BaseException(ServerErrorResultCodeEnum.FILE_NULL_ERROR);
        }
        if(firmwareFile.getSize() > 70*1024){
            throw new BaseException(ServerErrorResultCodeEnum.FILE_BIG_ERROR);
        }
        ApplicationHome ah = new ApplicationHome(DhlkLightFactoryConstructionApplication.class);
        String path = ah.getSource().getParentFile().toString() + File.separator + "file" + File.separator + firmwareFile.getOriginalFilename();
        log.info("文件上传路径：{}",path);
        File file = new File(path);
        //文件上传至服务器
        boolean flag = FileUtil.uploadFile(firmwareFile,file);
        if(!flag){
            throw new BaseException(ServerErrorResultCodeEnum.FILE_ERROR);
        }
        //文件转换为流
        byte[] fileByte = getFileByte(file);
        //判断文件
        if(fileByte == null){
            throw new BaseException(ServerErrorResultCodeEnum.FILE_NULL_ERROR);
        }
        String[] devArray = devIds.split(",");
        List<String> devList = Arrays.asList(devArray);
        try {
            new FirmwareUpgradeThread(fileByte,devList,threadSize,path).run();
        }catch (Exception e){
            throw new BaseException(ServerErrorResultCodeEnum.UPGRADE_ERROR);
        }

    }

    @Override
    public List<UpgradePace> getList() {
        return FirmwareUpgradeMap.getUpgradePaceMap();
    }

    @Override
    public void stopUpgrade(List<String> sns) {
        for(String sn:sns){
            FirmwareUpgradeMap.stopUpgrade(sn);
        }
    }

    /**
     * 文件转换为byte数组
     * @param file 文件信息
     * @return
     */
    private byte[] getFileByte(File file){
        InputStream is = null;
        byte[] bytes = null;
        try {
            is = new FileInputStream(file);
            bytes = readBytes(is);
        }catch (Exception e){
            log.error("解析失败，{}",e);
            return null;
        }finally {
            try {
                if(is != null){
                    is.close();
                }
            }catch (Exception e){
                log.error("InputStream关闭失败");
            }
        }
        return bytes;
    }

    public static byte[] readBytes(InputStream in) throws IOException {
        BufferedInputStream bufin = new BufferedInputStream(in);
        int buffSize = 1024;
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);
        byte[] temp = new byte[buffSize];
        int size = 0;
        while ((size = bufin.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        bufin.close();
        byte[] bytes = out.toByteArray();
        return bytes;
    }


}
