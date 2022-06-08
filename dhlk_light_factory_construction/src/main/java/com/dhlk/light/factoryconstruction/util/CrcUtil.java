package com.dhlk.light.factoryconstruction.util;

import lombok.extern.slf4j.Slf4j;

/**
 * crc校验位工具类
 * @author yangfan
 * @since 2021-08-12
 */
@Slf4j
public class CrcUtil {

    public static void main(String[] args) {
        //          a5 a5 01 00 ff 00 20 20 31 00 00 13 00 93 01 7b 3d 8e 65 c3 ff ff ff ff 00 7a 00 5a 5a
        String s = "a5a50100ff00202031000013008d017b623bdfdcffffffff0101";
        System.out.println(crcHexStr(s));

    }
    /**
     * 获取crc校验位16进制字符串
     * @param str 需要得到校验位的16字符串
     * @return 校验位16进制字符串
     */
    public static String crcHexStr(String str) {
        str = str.replace(" ","");
        byte[] bytes = DataUtil.hexStringToBytes(str);
        //crc校验位
        byte b = crc8Maxim(bytes,0,bytes.length);
        byte[] crcByte = {b};
        return DataUtil.bytesToHexString(crcByte);

    }

    /**
     * crc计算
     * @param data byte数组
     * @param offset 起始位置
     * @param length 长度
     * @return crc字节
     */
    public static byte crc8Maxim(byte[] data,int offset,int length){
        byte i;
        byte crc = 0;        // Initial value
        length += offset;
        for(int j=offset;j<length;j++) {
            crc ^= data[j];
            for ( i = 0; i < 8; i++ ){
                if ( (crc & 1) == 0){
                    crc = (byte) ((crc&0xff) >> 1);
                }else{
                    crc = (byte) (((crc&0xff) >> 1) ^ 0x8C);
                }
            }
        }
        return crc;
    }

    public static int CRC16_MAXIM(byte[] source, int offset, int length) {
        int wCRCin = 0x0000;
        // Integer.reverse(0x8005) >>> 16
        int wCPoly = 0xA001;
        for (int i = offset, cnt = offset + length; i < cnt; i++) {
            wCRCin ^= ((int) source[i] & 0x00FF);
            for (int j = 0; j < 8; j++) {
                if ((wCRCin & 0x0001) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= wCPoly;
                } else {
                    wCRCin >>= 1;
                }
            }
        }
        return wCRCin ^= 0xFFFF;
    }

    public static int crc16Maxim(byte[] buffer, int len)
    {
        //初始值
        int wCRCin = 0x0000;
        //多项式
        int wCPoly = 0xA001;
        byte wChar = 0;
        int i = 0, j = 0;

        for (i = 0; i < len; i++)
        {
            wChar = buffer[i];
            //InvertUint8(out wChar, wChar);//输入值反转

            wCRCin ^= wChar;
            //wCRCin <<= 8;

            for (j = 0; j < 8; j++)
            {
                if ((wCRCin & 0x0001) != 0)
                {
                    wCRCin >>= 1;
                    wCRCin ^= wCPoly;
                }
                else
                {
                    wCRCin >>= 1;
                }
            }
            //InvertUint16(out wCRCin, wCRCin);//输出值反转
        }
        wCRCin ^= 0xffff;
        return wCRCin;
    }
}
