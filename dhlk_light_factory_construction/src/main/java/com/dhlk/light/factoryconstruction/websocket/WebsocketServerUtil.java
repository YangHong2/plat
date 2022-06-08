package com.dhlk.light.factoryconstruction.websocket;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.dhlk.light.factoryconstruction.datamap.MessagePortMap;
import com.dhlk.light.factoryconstruction.enums.MessageTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.dto.CommandLogDTO;
import com.dhlk.light.factoryconstruction.pojo.vo.DebugMessageVO;
import com.dhlk.light.factoryconstruction.pojo.vo.DeviceQueryVO;
import com.dhlk.light.factoryconstruction.pojo.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Websocket
 *
 * @author wzx
 * @date 2021/8/10
 */
@Api(tags = "监听websocket")
@Slf4j
@Data
@Component
@EqualsAndHashCode()
@ServerEndpoint("/websocket/{port}")
public class WebsocketServerUtil {
    /**
     * 用于存放当前Websocket对象的map集合 key:端口号，value：监测该端口的websocket客户端的set集合
     */
    public static final Map<String, CopyOnWriteArraySet<WebsocketServerUtil>> CLIENTS = new ConcurrentHashMap<>();
    /**
     * 用于存放用户查询条件的map集合 key:sessionId，value：DeviceQueryVO 查询条件
     */
    public static final Map<String, DeviceQueryVO> QUERY = new ConcurrentHashMap<>();


    /**
     * 设备日志列表 会话id和条件的关系
     */
    public static final Map<String, CommandLogDTO> DEBUG_QUERY_CONDITION_MAP = new ConcurrentHashMap<>();

    /**
     * 与客户端的会话Session
     */
    private Session session;
    /**
     * 端口号
     */
    private String port;

    /**
     * 连接成功调用的方法
     *
     * @param session 客户端会话
     * @param port    端口号
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("port") String port) {
        this.session = session;
        this.port = port;
        //将当前websocket对象存入到map集合中
        CLIENTS.putIfAbsent(port, new CopyOnWriteArraySet<>());
        CopyOnWriteArraySet<WebsocketServerUtil> set = CLIENTS.get(port);
        set.add(this);
        CLIENTS.put(port, set);
        log.info("{} >> 连接成功", session.getId());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //移除当前Websocket对象
        CopyOnWriteArraySet<WebsocketServerUtil> set = CLIENTS.get(port);
        set.remove(this);
        QUERY.remove(this.session.getId());
        DEBUG_QUERY_CONDITION_MAP.remove(this.session.getId());
//        SocketServer socketServer = SocketUtils.SOCKET_SERVER_MAP.get(this.port);
//        if (CollUtil.isEmpty(set) && socketServer != null) {
//            socketServer.close();
//        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 消息
     * @param session 客户端会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("接收到监听端口:{},sessionId:{}的websocket信息:{}", port, session.getId(), message);
    }

    @OnError
    public void onError(Session session, Throwable e) {
        log.error("监听端口:{},sessionId:{} websocket出错:", port, session.getId(), e);
    }

    /**
     * 推送消息
     *
     * @param message 消息字符串
     */
    public void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.info("推送失败:", e);
        }
    }

    /**
     * 推送多条实时数据
     */
    public static void sendRealTimeMessages(String port, int msgType, List<Object> contents) {
        if (CollUtil.isNotEmpty(contents)) {
            MessageVO msg = MessageVO.builder()
                    .type(msgType)
                    .port(port)
                    .content(contents)
                    .build();
            MessagePortMap.put(port, msg);
        }
    }

    /**
     * 推送单条实时数据
     */
    @ApiOperation("单条实时数据")
    public static void sendRealTimeMessage(String port, int msgType, Object content) {
        if (content != null && StrUtil.isNotEmpty(JSONUtil.toJsonStr(content))) {
            MessageVO msg = MessageVO.builder()
                    .type(msgType)
                    .port(port)
                    .content(content)
                    .build();
            MessagePortMap.put(port, msg);
        }
    }

    /**
     * websockert debug 消息推送
     *
     * @param debugMessageVO debug消息对象
     */
    public static void websocketDebugMessagePush(String port, DebugMessageVO debugMessageVO) {
        sendRealTimeMessage(port, MessageTypeEnum.DEBUG_REPORT_MESSAGE.getType(), debugMessageVO);
    }

}
