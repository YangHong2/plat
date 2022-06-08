package com.dhlk.light.factoryconstruction.test;

import com.dhlk.light.factoryconstruction.util.DataUtil;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TimesTest {

    public static void main(String[] args) {
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime now = LocalDateTime.now();
        Instant instant = now.atZone(zone).toInstant();
        long timestamp = instant.toEpochMilli();
        timestamp = 1628740271479L;
        System.out.println(timestamp);

        String hex= Long.toHexString(timestamp);




        String substring = Long.toHexString((timestamp & 0x000000FF) | 0xFFFFFF00).substring(6);


        System.out.println("sub:"+substring);
        System.out.println(hex);

        //017b387c3d77
        List<Byte> byteList = new ArrayList<>();

        byteList.add((byte)(timestamp >> 40));
        byteList.add((byte)(timestamp >> 32));
        byteList.add((byte)(timestamp >> 24));
        byteList.add((byte)(timestamp >> 16));
        byteList.add((byte)(timestamp >> 8));
        byteList.add((byte)(timestamp >> 0));



        System.out.println("=================");
        BigInteger bigint=new BigInteger("017b387c3d77", 16);
        long numb=bigint.longValue();
        System.out.println(numb);

        instant = Instant.ofEpochMilli(numb);
        zone = ZoneId.systemDefault();
        LocalDateTime dataCollectTime = LocalDateTime.ofInstant(instant, zone);

        System.out.println(dataCollectTime);

        byte[] bytes = DataUtil.hexStringToBytes("017b387c3d77");

        for(byte b : bytes){
            System.out.print(b+" ");
        }
        System.out.println();

        bytes = DataUtil.hexStringToBytes("17b387c3d77");

        for(byte b : byteList){
            System.out.print(b+" ");
        }


        System.out.println("===============");

        test();
    }

    public static String test(){
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime now = LocalDateTime.now();
        Instant instant = now.atZone(zone).toInstant();
        long timestamp = instant.toEpochMilli();

        byte[] byteArr = new byte[6];
        byteArr[0]=(byte)(timestamp >> 40);
        byteArr[1]=(byte)(timestamp >> 32);
        byteArr[2]=(byte)(timestamp >> 24);
        byteArr[3]=(byte)(timestamp >> 16);
        byteArr[4]=(byte)(timestamp >> 8);
        byteArr[5]=(byte)(timestamp >> 0);

        return DataUtil.bytesToHexString(byteArr);
    }
}
