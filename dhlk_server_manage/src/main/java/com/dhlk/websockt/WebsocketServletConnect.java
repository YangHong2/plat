package com.dhlk.websockt;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * @author xmdeng
 * @date 2022/2/9 14:41
 */
@Slf4j
@Component
@ServerEndpoint("/connect/{pingAddr}/{number}")
public class WebsocketServletConnect {

    /**
     * 用于存放当前Websocket对象的Set集合
     */
    private static Map<String, WebsocketServletConnect> websocketServerEndpoints = new HashMap<>();

    /**
     * 与客户端的会话Session
     */
    private Session session;


    private ScheduledExecutorService scheduledThreadPool = newScheduledThreadPool(5);


    private Integer number = 10;

    private Process pro;

    private BufferedReader buf = null;
    /**
     * 链接成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("pingAddr") String pingAddr,@PathParam("number") int number) {
        log.info("onOpen >> 连接成功");
        this.session = session;
        if(number <= 100){
            this.number = number;
        }
        //将当前websocket对象存入到Set集合中
        String line = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info(osName);
            String cmdStr = "";
            if(osName.contains("windows")) {
                cmdStr = "ping " + pingAddr + " -n "+this.number;
                pro = Runtime.getRuntime().exec(cmdStr);
            }else {
                cmdStr = "ping " + pingAddr + " -c"+this.number;
                String [] cmd = {"/bin/sh", "-c", cmdStr};
                pro = Runtime.getRuntime().exec(cmd);
            }
                BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream(),"GB2312"));
                while((line = buf.readLine()) != null){
                    session.getBasicRemote().sendText(line);
                }
                session.getBasicRemote().sendText("network ping end");
        } catch (IOException e) {
            log.error("首次发生失败，{}",e);
        }
        this.onClose();

    }

    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info("onClose >> 链接关闭");
        if(pro != null){
            pro.destroy();
        }

       try {
           if(buf != null){
               buf.close();
           }
       }catch (Exception e){
           log.error("关闭流失败！");
       }
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
