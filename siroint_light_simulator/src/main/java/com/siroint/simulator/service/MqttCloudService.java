package com.siroint.simulator.service;


/**
 * @author xmdeng
 * @date 2021/6/2 13:25
 */
public interface MqttCloudService {

    void subsribe(String topic, String mess);

    void localSubsribe(String topic,String mess);
}
