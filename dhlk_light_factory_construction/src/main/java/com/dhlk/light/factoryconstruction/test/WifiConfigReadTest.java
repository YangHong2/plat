package com.dhlk.light.factoryconstruction.test;

import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.WifiConfigData;
import com.dhlk.light.factoryconstruction.util.DataUtil;

public class WifiConfigReadTest {

    public static void main(String[] args) {
        String data = "02035472656d5f41500000000000000000000000000000000000000000000000000064686c6b746563680000000000000000c0a80a02";
        System.out.println(data.length());
        WifiConfigData wifiConfigData = new WifiConfigData();
        //模块
        String module = data.substring(0,2);
        wifiConfigData.setModule(module);
        //双频
        String dualFrequency = data.substring(2,4);
        wifiConfigData.setDualFrequency(dualFrequency);
        //ssid
        String ssid = data.substring(4,68);

        byte[] bytes = DataUtil.hexStringToBytes(ssid);
        ssid = new String(bytes);

        wifiConfigData.setSsId(ssid);
        //密码
        String password = data.substring(68,100);

        byte[] bytes1 = DataUtil.hexStringToBytes(password);
        password = new String(bytes1);

        wifiConfigData.setPassword(password);
        //ip
        String ip = DataUtil.getLongStr(data.substring(100,102))+"."
                + DataUtil.getLongStr(data.substring(102,104))+"."
        + DataUtil.getLongStr(data.substring(104,106))+"."
        + DataUtil.getLongStr(data.substring(106,108));


        wifiConfigData.setIp(ip);


        System.out.println(wifiConfigData);
    }


}
