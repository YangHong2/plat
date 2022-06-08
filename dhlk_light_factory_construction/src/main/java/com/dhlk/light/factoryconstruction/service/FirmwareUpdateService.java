package com.dhlk.light.factoryconstruction.service;

import com.dhlk.light.factoryconstruction.pojo.entity.UpgradePace;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author xmdeng
 * @date 2021/8/16 13:34
 */
public interface FirmwareUpdateService {

    /**
     * 固件升级步骤：
     * 1、发送IAP升级指令，收到后，开始发送包
     * 2、将bin程序包按照n+1 进行字节拆分 规则为1024 + 1 即：包索引+包数据
     * 3、按照协议完成发送数据组装 帧头 3个字节（0xA5 0xA5 0xA5） 命令一个字节（0xxx） 数据长度(高位在前，低位在后) 数据 校验位2位，帧尾3位
     * 4、发送成功消息
     *
     * 上传固件包
     * @param firmwareFile 固件包
     * @param devIds 设备ID
     */
    void uploadFirmware(MultipartFile firmwareFile, String devIds,Integer threadSize);


    List<UpgradePace> getList();

    void stopUpgrade(List<String> sns);
}
