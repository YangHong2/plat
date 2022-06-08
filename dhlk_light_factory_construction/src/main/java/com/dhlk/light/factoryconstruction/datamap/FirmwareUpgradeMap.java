package com.dhlk.light.factoryconstruction.datamap;

import com.dhlk.light.factoryconstruction.pojo.entity.FirmwareUpgrade;
import com.dhlk.light.factoryconstruction.pojo.entity.UpgradePace;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.io.FileInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author xmdeng
 * @date 2021/8/18 16:10
 */
@Slf4j
public class FirmwareUpgradeMap {

    /**
     * 固件升级消息返回
     */
    public static ConcurrentMap<String, List<byte[]>> FIRMWARE_UPGRADE_MAP = new ConcurrentHashMap<>();

    public static ConcurrentMap<String,Integer> SEND_NUM_MAP = new ConcurrentHashMap<>();

    private static ConcurrentMap<String,String> FIRMWARE_UPGRADE_MD5_MAP = new ConcurrentHashMap<>();

    public static ConcurrentMap<String,FirmwareUpgrade> UPGRADE_MAP = new ConcurrentHashMap<>();

    private static ConcurrentMap<String, UpgradePace> upgradePaceMap =  new ConcurrentHashMap<>();

    public static void syncUpgradePaceMap(UpgradePace upgradePace){
        if(upgradePace.getNowNumber().equals(upgradePace.getTotalNumber())){
//            upgradePaceMap.remove(upgradePace.getSn());
        }else {
            upgradePaceMap.put(upgradePace.getSn(),upgradePace);
        }

    }

    public static List<UpgradePace> getUpgradePaceMap(){
        List<UpgradePace> list = new ArrayList<>();
        upgradePaceMap.forEach((k,v)->{
            list.add(v);
        });
        return list;
    }

    public static String getMd5(String key){
        return FIRMWARE_UPGRADE_MD5_MAP.get(key);
    }

    public static void setUpgradeMap(FirmwareUpgrade upgrade){
        UPGRADE_MAP.put(upgrade.getSn(),upgrade);
    }

    public static void removeUpgradeMap(String sn){
        UPGRADE_MAP.remove(sn);
    }

    public static void syncUpgradeMap(FirmwareUpgrade upgrade){
        if(upgrade.checkOutTime()){
            setUpgradeMap(upgrade);
        }
    }

    public static Integer getNumber(String key){
        if(SEND_NUM_MAP.get(key) == null){
            SEND_NUM_MAP.put(key,1);
            return 0;
        }else {
            Integer num = SEND_NUM_MAP.get(key);
            SEND_NUM_MAP.put(key,num+1);
            return num;
        }
    }


    public static List<FirmwareUpgrade> getRunUpgrade(){
        List<FirmwareUpgrade> list = new ArrayList<>();
        UPGRADE_MAP.forEach((k,v)->{
            if(v.checkOutTime()){
                list.add(v);
            }
        });
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
       return list.stream().filter(l->l.getStatus().equals(1)).collect(Collectors.toList());
    }

    public static FirmwareUpgrade getRun(){
        List<FirmwareUpgrade> list = new ArrayList<>();
        UPGRADE_MAP.forEach((k,v)-> {
            if(v.checkOutTime()){
                list.add(v);
            }
        });
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        List<FirmwareUpgrade> collect = list.stream().filter(l -> l.getStatus().equals(0)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            return collect.get(0);
        }
        return null;
    }

    public static void removeSendNum(Socket socket){
        String key = socket.getInetAddress()+"_"+socket.getPort();
        SEND_NUM_MAP.remove(key);
    }

    public static void putUpgradeMap(Socket socket, byte[] bytes){
        String key = socket.getInetAddress()+"_"+socket.getPort();
        List<byte[]> bytesList = DataUtil.getBytesList(bytes);
        FIRMWARE_UPGRADE_MAP.put(key,bytesList);
    }


    public static void putMD5Map(Socket socket, String filePath) throws Exception{
        String key = socket.getInetAddress()+"_"+socket.getPort();
        FileInputStream inputStream = new FileInputStream(filePath);
        try {
            String hex = DigestUtils.md5DigestAsHex(inputStream);
            log.info("md5:{}:{}",filePath,hex);
            FIRMWARE_UPGRADE_MD5_MAP.put(key,hex.substring(8,24));
        }catch (Exception e){
           log.info("获取md5码错误!");
        }finally {
            inputStream.close();
        }
    }

    public static void removeUpgrade(Socket socket){
        String key = socket.getInetAddress()+"_"+socket.getPort();
        FIRMWARE_UPGRADE_MAP.remove(key);
        FIRMWARE_UPGRADE_MD5_MAP.remove(key);
    }

    public static Integer getNowNumber(String key){
        return SEND_NUM_MAP.get(key);
    }

    public static void stopUpgrade(String sn){
        if(UPGRADE_MAP.get(sn) != null){
            FirmwareUpgrade upgrade = UPGRADE_MAP.get(sn);
            if(upgrade.getStatus() == 0){
                removeUpgradeMap(sn);
            }
        }
    }
}
