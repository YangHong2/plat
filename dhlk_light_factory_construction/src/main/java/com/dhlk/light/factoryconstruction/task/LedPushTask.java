package com.dhlk.light.factoryconstruction.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.dhlk.light.factoryconstruction.datamap.LedPortMap;
import com.dhlk.light.factoryconstruction.datamap.MessagePortMap;
import com.dhlk.light.factoryconstruction.enums.MessageTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.dto.CommandLogDTO;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.vo.DebugMessageVO;
import com.dhlk.light.factoryconstruction.pojo.vo.MessageVO;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author wzx
 * @date 2021/8/12
 */
@Component
@Slf4j
@EnableScheduling
@Api(tags = "设备数据推送")
public class LedPushTask {
    /**
     * 所有websocket消息都是由这个方法推送 1s推送一次
     */
    @ApiOperation("定时推送websocket消息")
    @Scheduled(cron = "*/1 * * * * ?")
    public void pushList() {
        MessagePortMap.INSTANCE.getInstance().forEach((port, messageSet) -> {
                    LinkedHashSet<MessageVO> otherSet = new LinkedHashSet<>();
                    LinkedHashSet<LedData> ledSet = new LinkedHashSet<>();
                    LinkedHashSet<DebugMessageVO> debugMessageSet =new LinkedHashSet<>();
                    messageSet.forEach(m -> {
                        if (MessageTypeEnum.TYPE_REAL_TIME_DEVICES.getType() == m.getType()) {
                            //实时多条
                            ledSet.addAll((List<LedData>) m.getContent());
                        } else if (MessageTypeEnum.TYPE_REAL_TIME_DEVICE.getType() == m.getType()) {
                            //实时单条
                            ledSet.add((LedData) m.getContent());
                        } else if (MessageTypeEnum.DEBUG_REPORT_MESSAGE.getType() == m.getType()) {
                            //debug日志
                            debugMessageSet.add((DebugMessageVO) m.getContent());
                        } else {
                            //其他类型
                            otherSet.add(m);
                        }
                    });
                    //得到该端口的客户端集合
                    CopyOnWriteArraySet<WebsocketServerUtil> clientSet = WebsocketServerUtil.CLIENTS.get(port);
                    if (CollUtil.isNotEmpty(clientSet)) {
                        clientSet.forEach(client -> doSendMessage(port,client,otherSet,ledSet,debugMessageSet));
                    }
                    //处理完成以后删除该消息
                    MessagePortMap.remove(port);
                }
        );
    }

    /**
     * 发送消息
     * @param port 端口
     * @param client WebsocketServerUtil对象
     * @param otherSet 其他推送数据集合
     * @param ledSet 灯数据推送集合
     * @param debugMessageSet debug数据推送集合
     */
    private void doSendMessage(String port,WebsocketServerUtil client,HashSet<MessageVO> otherSet,HashSet<LedData> ledSet, LinkedHashSet<DebugMessageVO> debugMessageSet) {
        String sessionId = client.getSession().getId();
        if (CollUtil.isNotEmpty(otherSet)) {
            //不需要筛选的消息
            otherSet.forEach(d -> {
                d.setSessionId(sessionId);
                client.sendMessage(JSONUtil.toJsonStr(d));
            });
        }
        //debug数据处理
        if(CollUtil.isNotEmpty(debugMessageSet)){
            //debug消息发送
            doSendDebugMessage(sessionId,debugMessageSet,port,client);
        }
        if (CollUtil.isNotEmpty(ledSet)) {
            //需要筛选的消息
            List<LedData> ledList = new ArrayList<>(ledSet);
            if (WebsocketServerUtil.QUERY.containsKey(sessionId)) {
                //如果有存在的筛选条件 进行筛选
                ledList = LedPortMap.queryLedData(WebsocketServerUtil.QUERY.get(sessionId), ledList);
            }
            //构建发送数据
            MessageVO msg = MessageVO.builder()
                    .type(MessageTypeEnum.TYPE_REAL_TIME_DEVICES.getType())
                    .port(port)
                    .content(ledList)
                    .sessionId(sessionId)
                    .build();
            //发送
            client.sendMessage(JSONUtil.toJsonStr(msg));
        }
    }

    /**
     * debug消息发送
     * @param sessionId  sessionId
     * @param debugMessageSet 消息集合
     * @param port 端口
     * @param client WebsocketServerUtil对象
     */
    private void doSendDebugMessage(String sessionId,Set<DebugMessageVO> debugMessageSet,String port,WebsocketServerUtil client) {
        List<DebugMessageVO> pushDebugMessageList =  new ArrayList<>(debugMessageSet);
        if (WebsocketServerUtil.DEBUG_QUERY_CONDITION_MAP.containsKey(sessionId)) {
            CommandLogDTO dto = WebsocketServerUtil.DEBUG_QUERY_CONDITION_MAP.get(sessionId);
            //过滤数据
            pushDebugMessageList = filterDebugMessageVoList(debugMessageSet,dto);
        }

        if(CollUtil.isNotEmpty(pushDebugMessageList)){
            List<String> pushMessage = new ArrayList<>();
            for(DebugMessageVO debugMessageVO : pushDebugMessageList){
                pushMessage.add(debugMessageVO.toString());
            }
            //构建发送数据
            MessageVO msg = MessageVO.builder()
                    .type(MessageTypeEnum.DEBUG_REPORT_MESSAGE.getType())
                    .port(port)
                    .content(pushMessage)
                    .sessionId(sessionId)
                    .build();
            client.sendMessage(JSONUtil.toJsonStr(msg));
        }

    }

    /**
     * 过滤消息
     * @param debugMessageSet debug消息集合
     * @param dto 过滤条件
     * @return 过滤后的结果
     */
    private List<DebugMessageVO> filterDebugMessageVoList(Set<DebugMessageVO> debugMessageSet,CommandLogDTO dto) {
        return debugMessageSet.stream().filter(debugMessageVO ->
                (StrUtil.isEmpty(dto.getCommandType()) || (StrUtil.isNotEmpty(debugMessageVO.getCommandType()) && dto.getCommandType().equals(debugMessageVO.getCommandType())))
                        && (StrUtil.isEmpty(dto.getSn()) || (StrUtil.isNotEmpty(debugMessageVO.getSn()) && debugMessageVO.getSn().contains(dto.getSn()))
                        && (StrUtil.isEmpty(dto.getMessageType()) || (StrUtil.isNotEmpty(debugMessageVO.getMessageType()) && dto.getMessageType().equals(debugMessageVO.getMessageType())))
                        && (StrUtil.isEmpty(dto.getQueryStartTime()) || (StrUtil.isNotEmpty(debugMessageVO.getTime()) && dto.getQueryStartTime().compareTo(debugMessageVO.getTime())>0)))
        ).collect(Collectors.toList());
    }

}
