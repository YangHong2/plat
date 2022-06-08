package com.dhlk.light.test.util;


/**
 * @Description
 * @Author lpsong
 * @Date 2020/6/12
 */
public class LedConst {
    //redis 中灯数据存储
    public final static String REDIS_POWER = "REDIS_POWER_"; //灯能耗
    //本地与硬件通讯主题
    public final static String LOCALTOHARD_TOPIC_DHLKLIGHT = "bi_light";//本地发送给硬件主题
    public final static String HARDTOLOCAL_TOPIC_BILIGHT= "dhlk_light/";//硬件发送本地mqtt主题

}
