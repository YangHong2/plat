package com.dhlk.light.factoryconstruction.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * 数据处理帮助类
 * @author yangfan
 * @since 2021-08-12
 */
@Slf4j
public class DataUtil {

    private DataUtil(){

    }

    /**
     * byte转16进制字符串
     * @param src byte数组
     * @return 16进制字符串
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 转化16进制字符串为byte数组
     * @param hexString 16进制字符串
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }


    // byte[] 转化十六进制的字符串
    public static String bytesToHex(byte b) {
        Formatter formatter = new Formatter();
        formatter.format("%02x", b);
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 16进制字符串得到bigdecimal字符串
     * @param numStr 16进制字符串
     * @return bigdecimal字符串
     */
    public static String getDecimalStr(String numStr){
        return BigDecimal.valueOf(new BigInteger(numStr,16).intValue()*1.0D/1000).setScale( 6, RoundingMode.HALF_UP).toString();
    }


    /**
     * 将16进制字符串转化为10进制数字字符串
     * @param numStr  16进制字符串
     * @return 10进制数字字符串
     */
    public static String getLongStr(String numStr){
        try {
            return  String.valueOf(Long.parseLong(numStr,16));
        }catch (Exception e){
            log.error("numStr:{}",numStr );
            throw e;
        }
    }

    /**
     * 将16进制字符串转化为10进制数字
     * @param numStr  16进制字符串
     * @return 10进制数字
     */
    public static Integer geInteger(String numStr){
        try {
            return  Integer.parseInt(numStr,16);
        }catch (Exception e){
            log.error("numStr:{}",numStr );
            throw e;
        }
    }

    /**
     * @Description String字符串码转换为16进制(后面补0)
     * @Date 2021/8/20
     * @Param [str]
     **/
    public static String convertStringToHex(String str,int byteNum){
        try{
            //位数等于字节数*2
            byteNum = byteNum*2;
            char[] chars = str.toCharArray();
            StringBuffer hex = new StringBuffer();
            for(int i = 0; i < chars.length; i++){
                hex.append(Integer.toHexString(chars[i]));
            }
            String reStr = hex.toString();
            if (byteNum >= reStr.length()){
                int j = byteNum - hex.length();
                for (int i = 0; i < j; i++) {
                    reStr += "0";
                }
            }else {
                throw new Exception("String字符串长度不能超过字节长度！");
            }
            return reStr;
        }catch (Exception e){
            log.error("String字符串转化成为16进制字符串出错：",e);
            return null;
        }
    }
    /**
     * int数字转化成为16进制字符串(前面补0)
     * @param s,byteNum(位数)
     * @return
     */
    public static String strTo16(String s,int byteNum) {
        try{
            //位数等于字节数*2
            byteNum = byteNum*2;
            int ss = Integer.valueOf(s);
            String hex= Integer.toHexString(ss);
            if (byteNum >= hex.length()){
                int j = byteNum - hex.length();
                for (int i = 0; i < j; i++) {
                    hex = "0"+hex;
                }
            }else {
                throw new Exception("字符串长度不能超过字节长度！");
            }
            return hex;
        }catch (Exception e){
            log.error("数字字符串转化成为16进制字符串出错：",e);
            return null;
        }
    }


    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param hexString 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replace(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }

    public static List<byte[]> getBytesList(byte[] bytes){
        double f = Math.ceil((double) bytes.length/1024);
        double ceil = Math.ceil(f);
        int fileSize = (int) ceil;
        List<byte[]> list = new ArrayList<>();
        for(int a = 0; a < fileSize-1 ; a++){
            byte [] tmpB = new byte[1025];
            tmpB[0] =  (byte) a;
            for( int i = 1 ; i <= 1024; i++){
                tmpB[i] = bytes[(a * 1024 +i)-1];
            }
            list.add(tmpB);
        }

        int surplus = bytes.length -(list.size() * 1024);
        int start = bytes.length - surplus -1;
        if(surplus > 0){
            byte [] tmpC = new byte[surplus+1];
            tmpC[0] =  (byte) (fileSize-1);
            for(int j = 1 ; j <= surplus ; j++){
                tmpC[j] = bytes[start+j];
            }
            list.add(tmpC);
        }
        System.out.println("");
        int totalLen = 0;
        for(byte[] bytes1:list){
            totalLen += bytes1.length;
        }
        totalLen = totalLen - fileSize;
        log.info("原大小：{},现大小：{}",bytes.length,totalLen);
        return list;
    }

}
