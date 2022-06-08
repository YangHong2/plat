package com.dhlk.light.factoryconstruction.pojo.entity;

import com.dhlk.light.factoryconstruction.enums.FirmwareEnum;
import com.dhlk.light.factoryconstruction.util.CrcUtil;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import com.dhlk.light.factoryconstruction.util.SocketUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xmdeng
 * @date 2021/8/17 13:49
 */
@Data
@Slf4j
public class FirmwareMessage {


    public FirmwareMessage(String commandTypeStr,Integer length,byte [] bytes){
        this.setCommandType(commandTypeStr);
        this.bytes = bytes;
        if(this.bytes == null){
            this.length = Integer.toHexString(0).getBytes();
        }else {
            this.length = Integer.toHexString(this.bytes.length+1).getBytes();
        }


    }
    private byte commandType;

    private byte[] length;

    private byte[] bytes;

    public void setCommandType(String commandTypeStr) {
        commandType = Objects.requireNonNull(DataUtil.hexStringToBytes(commandTypeStr))[0];
    }

    public byte[] getDate(){

        List<Byte>list = new ArrayList<>();
        //帧尾
        list.add((byte)0xA5);
        list.add((byte)0xA5);
        list.add((byte)0xA5);
        //命令
        list.add(this.getCommandType());
        //数据长度
        int num = this.bytes== null?0:this.bytes.length;
        if(num == 1025){
            list.add((byte) 0x04);
            list.add((byte) 0x01);
        }else {
            list.add((byte) ((num >> 8) & 0x0ff));
            list.add((byte) (num &  0x0ff));
        }

        //数据

        if(bytes != null){
            for(byte b1:this.getBytes()){
                list.add(b1);
            }
        }

        //CRC16校验
        byte [] tmpBytes = new byte[list.size()];
       for(int a = 0 ; a < list.size() ; a++){
           tmpBytes[a] = list.get(a);

       }
        int crc16Maxim = CrcUtil.CRC16_MAXIM(tmpBytes,0, tmpBytes.length);
        list.add((byte) (crc16Maxim>>8));
        list.add((byte) crc16Maxim);
        list.add((byte) 0x00);
        list.add((byte)0x5A);
        list.add((byte)0x5A);
        StringBuilder sb = new StringBuilder();
       byte [] bytes = new byte[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            bytes[i] = list.get(i);
            byte[] b = new byte[1];
            b[0] = list.get(i);
            sb.append(SocketUtils.bytesToHexString(b)+" ");
        }
        log.info(sb.toString());
       return bytes;
    }

    public static void main(String[] args) {
//        String code = " ";
//        byte[] bytes = code.getBytes();
//        FirmwareMessage firmwareMessage = new FirmwareMessage(FirmwareEnum.DATA.getCmd(),bytes.length,bytes);
//        System.out.println(firmwareMessage.getDate());
        String code = "aaaaaaaadsdsdsds ";
        byte[] bytes = code.getBytes();
        FirmwareMessage firmwareMessage = new FirmwareMessage(FirmwareEnum.DATA.getCmd(), bytes.length, bytes);
        byte[] date = firmwareMessage.getDate();

        for(byte b :date){
            byte[] a = new byte[1];
            a[0] = b;
            System.out.print(SocketUtils.bytesToHexString(a)+" ");

        }
//        for(byte b:date){
//            System.out.print(b);
//            System.out.print(" ");
//        }
//        System.out.println(" ");
//        int tmpV = 110;
//        String hex = Integer.toHexString(tmpV);
////        System.out.print(hex);
//        byte[] bytes = hex.getBytes();
//        for(byte b:bytes){
//            System.out.print(b);
//            System.out.print(" ");
//        }
//        System.out.println(" ");
//        Integer.parseInt(hex,16);
    }

}
