package com.dhlk.light.factoryconstruction.websocket;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.light.factoryconstruction.datamap.FirmwareUpgradeMap;
import com.dhlk.light.factoryconstruction.pojo.entity.FirmwareUpgrade;
import com.dhlk.light.factoryconstruction.pojo.entity.UpgradePace;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * @author xmdeng
 * @date 2021/8/5 11:18
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/pace")
public class WebsocketServicePace {


    /**
     * 用于存放当前Websocket对象的Set集合
     */
    private static Map<String, WebsocketServicePace> websocketServerEndpoints = new HashMap<>();

    /**
     * 与客户端的会话Session
     */
    private Session session;


    private ScheduledExecutorService scheduledThreadPool = newScheduledThreadPool(5);


    /**
     * 链接成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        log.info("onOpen >> 连接成功");
        this.session = session;
        //将当前websocket对象存入到Set集合中
        websocketServerEndpoints.put(this.session.getId(),this);

        try {
            while (session.isOpen()){
                List<UpgradePace> upgradePaceMap = FirmwareUpgradeMap.getUpgradePaceMap();
                if(upgradePaceMap.size() > 0){
                    session.getBasicRemote().sendText(JSONObject.toJSONString(upgradePaceMap));
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            log.error("数据发送异常");
        }


    }

    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info("onClose >> 链接关闭");
        //移除当前Websocket对象
        websocketServerEndpoints.remove(this.session.getId());
        if (scheduledThreadPool!=null){
            scheduledThreadPool.shutdown();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("接收到窗口：" + message + " 的信息：" + message);
    }

    @OnError
    public void onError(Session session, Throwable e) {
       log.error("session:{}", JSONObject.toJSONString(session));
    }


}
