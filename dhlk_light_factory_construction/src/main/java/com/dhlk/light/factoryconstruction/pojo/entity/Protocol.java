package com.dhlk.light.factoryconstruction.pojo.entity;

import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备协议
 * @author yangfan
 * @since 2021-08-10
 */
@Data
public class Protocol implements Serializable {

    /**
     * 包头 2byte
     */
    private byte[] head;

    /**
     * 协议版本 1byte
     */
    private byte version;

    /**
     * 寻址类型 1byte
     */
    private byte addressingType;

    /**
     * 组id 1byte
     */
    private byte groupId;

    /**
     * id 7byte
     */
    private byte[] idBytes;

    /**
     * 设备类型 1byte
     */
    private byte deviceType;


    /**
     * 命令类型 1byte
     */
    private byte commandType;

    /**
     *时间戳 6byte
     */
    private byte[] timestamp;

    /**
     *预留 4byte
     */
    private byte[] reserved;

    /**
     *数据长度 1byte
     */
    private byte dataLength;

    /**
     *数据 n*byte
     */

    private byte[] data;

    /**
     * 校验位 1byte
     */
    private byte checkDigit;

    /**
     *包尾 3byte
     */
    private byte[] packageEnd;



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DataUtil.bytesToHexString(head))
        .append(DataUtil.bytesToHex(version))
        .append(DataUtil.bytesToHex(addressingType))
        .append(DataUtil.bytesToHex(groupId))
        .append(DataUtil.bytesToHexString(idBytes))
         .append(DataUtil.bytesToHex(deviceType))
         .append(DataUtil.bytesToHex(commandType))
         .append(DataUtil.bytesToHexString(timestamp))
         .append(DataUtil.bytesToHexString(reserved))
         .append(DataUtil.bytesToHex(dataLength));
         if(data!=null){
             stringBuilder.append(DataUtil.bytesToHexString(data));
         }
        stringBuilder.append(DataUtil.bytesToHex(checkDigit))
         .append(DataUtil.bytesToHexString(packageEnd))
        ;
        return stringBuilder.toString();
    }
}
