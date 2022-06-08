package com.dhlk.light.factoryconstruction.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dhlk.light.factoryconstruction.constants.SocketConstant;
import com.dhlk.light.factoryconstruction.datamap.DeviceReportMap;
import com.dhlk.light.factoryconstruction.datamap.HisIpMap;
import com.dhlk.light.factoryconstruction.datamap.LedPortMap;
import com.dhlk.light.factoryconstruction.datamap.SocketClinetMap;
import com.dhlk.light.factoryconstruction.enums.CommandReceiveHanderEnum;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.enums.DeviceReadEnum;
import com.dhlk.light.factoryconstruction.enums.MessageTypeEnum;
import com.dhlk.light.factoryconstruction.handler.command.receive.CommandReceiveHander;
import com.dhlk.light.factoryconstruction.mapper.DeviceDataInfoMapper;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceDataInfo;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceReportData;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.Protocol;
import com.dhlk.light.factoryconstruction.service.Connection;
import com.dhlk.light.factoryconstruction.service.DeviceReportService;
import com.dhlk.light.factoryconstruction.service.SocketServer;
import com.dhlk.light.factoryconstruction.thread.DeviceReadThread;
import com.dhlk.light.factoryconstruction.util.SocketUtils;
import com.dhlk.light.factoryconstruction.util.SpringContextHolder;
import com.dhlk.light.factoryconstruction.util.ThreadPoolUtils;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.*;


/**
 * Socket处理器
 *
 * @author wzx
 * @date 2021/8/10
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class SocketHandler extends Thread {
    /**
     * 设备上传信息id
     */
    private String deviceReportId;
    /**
     * 客户端的socket连接实例
     */
    private Socket socket;
    /**
     * 输出流
     */
    private OutputStream os;
    /**
     * 服务socket
     */
    private SocketServer socketServer;
    /**
     * 判断当前连接是否运行
     */
    private boolean isRunning;
    /**
     * 封装的客户端连接socket
     */
    private Connection connection;

    DeviceDataInfoMapper deviceDataInfoMapper = SpringContextHolder.getBean(DeviceDataInfoMapper.class);

    DeviceReportMap deviceReportMap = SpringContextHolder.getBean(DeviceReportMap.class);

    public SocketHandler(Socket socket, SocketServer socketServer) throws IOException {
        this.socket = socket;
        this.socketServer = socketServer;
        connection = new Connection(socket, this);
        LocalDateTime now = LocalDateTime.now();
        connection.setCreateTime(now);
        connection.setLastOnTime(now);
        isRunning = true;
        os = socket.getOutputStream();
    }

    @Override
    public void run() {
        while (isRunning) {
            //线程关闭标识
            if (socketServer.getServerSocket().isClosed() || socket.isClosed()) {
                isRunning = false;
                break;
            }
            //封装输入流（接收客户端的流）
            try (InputStream inputStream = socket.getInputStream();
                 BufferedInputStream bis = new BufferedInputStream(inputStream);
                 DataInputStream dis = new DataInputStream(bis)
            ) {
                // 一次读取一个byte
                byte[] bytes = new byte[1];
                StringBuilder result = new StringBuilder();
                while (dis.read(bytes) != -1) {
                    result.append(SocketUtils.bytesToHexString(bytes)).append(" ");

                    //一个请求
                    if (dis.available() == 0) {
                        MDC.put("sn", deviceReportId);
                        String commandStr = result.toString();
                        InetSocketAddress remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                        log.info("收到设备数据,地址：{},数据：{}", remoteSocketAddress, commandStr);
                        //接收数据处理
                        receiveHander(result.toString());
                        result = new StringBuilder();
                    }
                }
            } catch (Exception e) {
                if (socket == null || socket.isClosed()) {
                    log.error("接收数据失败，已断开连接");
                } else {
                    log.error("接收{}:{} 数据失败:",
                            socket.getInetAddress().getHostAddress(),
                            socket.getPort(),
                            e);
                }
            } finally {
                this.stopRunning();
            }
        }
    }

    /**
     * 接收数据处理
     *
     * @param commandStr 接收到的数据
     */
    private void receiveHander(String commandStr) {
        CommandReceiveHander commandReceiveHander = ProtocolHelper.getProtocolType(commandStr);
        if (commandReceiveHander == null) {
            log.error("请求协议头错误");
            return;
        }
        try {
            CommandReceiveHanderEnum commandReceiveHanderEnum = commandReceiveHander.getCommandReceiveHanderEnum();
            //普通命令处理
            if (CommandReceiveHanderEnum.NOMAL.equals(commandReceiveHanderEnum)) {
                nomalDataHander(commandStr, commandReceiveHander);
            } else if (CommandReceiveHanderEnum.FIRMWARE_UPGRADE.equals(commandReceiveHanderEnum)) {
                commandReceiveHander.receiveCommand(commandStr, this);
            }
        } catch (Exception e) {
            log.error("接收数据处理失败:", e);
        }
    }

    /**
     * 普通命令处理
     *
     * @param commandStr           消息字符串
     * @param commandReceiveHander 消息处理类
     */
    private void nomalDataHander(String commandStr, CommandReceiveHander commandReceiveHander) {
        DeviceReportData deviceReportData = (DeviceReportData) commandReceiveHander.receiveCommand(commandStr, this);


        String jsonStr = JSON.toJSONString(deviceReportData);
        log.info("数据对象：" + jsonStr);
        String sn = deviceReportData.getId();

        SocketClinetMap.put(sn, this);

        String commandType = deviceReportData.getCommandType();
        CommandTypeEnum commandTypeEnum = CommandTypeEnum.getCommandTypeEnum(commandType);
        if (commandTypeEnum != null) {
            //记录命令日志
            RecordHelper.recordCommandLog(commandTypeEnum, commandStr.replace(" ", ""), sn);
        }

        InetSocketAddress remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

        InetAddress address = remoteSocketAddress.getAddress();
        String hostAddress = address.getHostAddress();
        HisIpMap.put(sn, hostAddress);
        handleDeviceReport(deviceReportData);
        //更新交互时间
        connection.setLastOnTime(LocalDateTime.now());
        //已连接的客户端
        this.deviceReportId = sn;

    }


    /**
     * 处理设备上报信息 并推送到websocket
     *
     * @param deviceReportData 设备上报信息
     */
    private void handleDeviceReport(DeviceReportData deviceReportData) {
        String sn = deviceReportData.getId();
        String commandType = deviceReportData.getCommandType();
        //根据上报命令类型进行处理
        DeviceReportService deviceReportService = deviceReportMap.get(CommandTypeEnum.getCommandTypeEnum(commandType));
        if (deviceReportService != null) {
            LedData ledData;
            if (LedPortMap.get(this.socket.getLocalPort() + "").containsKey(sn)) {
                ledData = LedPortMap.get(this.socket.getLocalPort() + "").get(sn);
            } else {
                //初始状态全为准备读取
                ledData = LedData.builder()
                        .sn(sn)
                        .type(deviceReportData.getDeviceType())
                        .readFlag(DeviceReadEnum.READY.getCode())
                        .wifiReadFlag(DeviceReadEnum.READY.getCode())
                        .humanConfigReadFlag(DeviceReadEnum.READY.getCode())
                        .lightConfigReadFlag(DeviceReadEnum.READY.getCode())
                        .versionReadFlag(DeviceReadEnum.READY.getCode())
                        .reportTimeSlotReadFlag(DeviceReadEnum.READY.getCode())
                        .ap(false)
                        .build();

                //第一次连接则去数据库查询绑定ap信息
                LambdaQueryWrapper<DeviceDataInfo> queryDeviceInfoWrapper = Wrappers.lambdaQuery(DeviceDataInfo.class).
                        eq(DeviceDataInfo::getSn, sn);
                DeviceDataInfo deviceDataInfo = deviceDataInfoMapper.selectOne(queryDeviceInfoWrapper);
                Optional.ofNullable(deviceDataInfo).ifPresent(d -> ledData.setAp(d.getAp()));
            }
            //初始化设备数据
            readData(ledData);
            //处理数据 发送websocket消息
            deviceReportService.handle(ledData, deviceReportData, this.socket.getLocalPort() + "");
            //设备数据读取中的进入判断
            if (DeviceReadEnum.LOADING.getCode().equals(ledData.getReadFlag())) {
                //判断现在是否读取完毕
                if (BeanUtil.isNotEmpty(ledData.getLightingStatusData())
                        && StrUtil.isNotEmpty(ledData.getLightingStatusData().getLampBrightness())
                        && DeviceReadEnum.FINISH.getCode().equals(ledData.getWifiReadFlag())
                        && DeviceReadEnum.FINISH.getCode().equals(ledData.getHumanConfigReadFlag())
                        && DeviceReadEnum.FINISH.getCode().equals(ledData.getLightConfigReadFlag())
                        && DeviceReadEnum.FINISH.getCode().equals(ledData.getVersionReadFlag())
                        && DeviceReadEnum.FINISH.getCode().equals(ledData.getReportTimeSlotReadFlag())
                ) {
                    //读取完毕则添加设备信息到数据库
                    DeviceDataInfo deviceDataInfo = DeviceDataInfo.builder()
                            .sn(sn)
                            .brightness(ledData.getLightingStatusData().getLampBrightness())
                            .wifiConfig(JSONUtil.toJsonStr(ledData.getWifiConfigData()))
                            .humanConfig(JSONUtil.toJsonStr(ledData.getHumanFeelConfigData()))
                            .lightConfig(JSONUtil.toJsonStr(ledData.getLightFeelConfigData()))
                            .timeSlot(ledData.getReportTimeSlot())
                            .version(ledData.getVersion())
                            .build();
                    deviceDataInfoMapper.insertOrUpdate(deviceDataInfo);
                    //状态改为读取完毕
                    ledData.setReadFlag(DeviceReadEnum.FINISH.getCode());
                }
            }
            //放到ledPortMap中
            LedPortMap.get(this.socket.getLocalPort() + "").put(sn, ledData);
        } else {
            log.error("上报命令类型错误:{}", commandType);
        }
    }

    /**
     * 发送命令 更新设备数据
     *
     * @param ledData 设备信息
     */
    private void readData(LedData ledData) {
        //如果读取状态为准备读取，发送命令查询设备信息
        if (DeviceReadEnum.READY.getCode().equals(ledData.getReadFlag())) {
            ThreadPoolUtils.execute(() -> {
                DeviceReadThread deviceReadThread = new DeviceReadThread(ledData);
                //读取数据次数
                int count = 1;
                //执行结果成功标识
                boolean flag = false;
                Future<Boolean> submit = null;
                while (this.isRunning
                        && this.socket.isConnected()
                        && !this.socketServer.getServerSocket().isClosed()
                        && !this.socketServer.getDeviceReadExecutor().isShutdown()
                        && !flag
                        && count < SocketConstant.retryCount + 1
                ) {
                    try {
                        submit = this.socketServer.getDeviceReadExecutor().submit(deviceReadThread);
                        flag = submit.get(SocketConstant.maxTime, TimeUnit.SECONDS);
                    } catch (InterruptedException | ExecutionException e) {
                        log.error("设备: {}  第{}次初始化数据失败", ledData.getSn(), count, e);
                    } catch (TimeoutException e) {
                        log.error("设备: {}  第{}次初始化数据超时", ledData.getSn(), count);
                    } catch (RejectedExecutionException e) {
                        log.error("设备: {}  初始化数据被拒绝，当前线程池任务已满", ledData.getSn());
                        //避免出现第一次进入了任务没有读取完毕，第二次被拒绝的情况
                        ledData.setReadFlag(DeviceReadEnum.READY.getCode());
                        //线程池已满，直接退出循环，等待下次主动上报时再来读取
                        break;
                    } finally {
                        if (submit != null) {
                            submit.cancel(true);
                        }
                        count++;
                    }
                }
                if (!flag) {
                    if (count == SocketConstant.retryCount + 1) {
                        log.error("设备: {}  初始化数据超过最大次数", ledData.getSn());
                    } else if (!this.isRunning
                            || this.socketServer.getServerSocket().isClosed()
                            || this.socketServer.getDeviceReadExecutor().isShutdown()
                    ) {
                        log.error("设备: {}  初始化数据失败，已断开连接", ledData.getSn());
                    }
                } else {
                    log.debug("设备: {}  初始化数据成功", ledData.getSn());
                }
            });
        }
    }

    /**
     * 移除当前socket客户端连接
     */
    public void stopRunning() {
        isRunning = false;
        //从客户端map移除
        SocketClinetMap.remove(this.deviceReportId);
        if (null != socket) {
            if (LedPortMap.INSTANCE.getInstance() != null
                    && LedPortMap.get(this.socket.getLocalPort() + "") != null
                    && this.deviceReportId != null
            ) {
                //从设备map移除
                LedPortMap.get(this.socket.getLocalPort() + "").remove(this.deviceReportId);
                //发送设备离线消息通知前端
                WebsocketServerUtil.sendRealTimeMessage(this.socket.getLocalPort() + "",
                        MessageTypeEnum.OFFLINE_MESSAGE.getType(),
                        this.deviceReportId);
            }
            log.info("监听端口号 {} ，设备：{} 断开连接", this.socket.getLocalPort(), this.deviceReportId);
            this.socket = null;
        }
    }

    /**
     * 发送消息
     */
    public void send(Protocol protocol) throws IOException {
        byte[] bytes = ProtocolHelper.getBytes(protocol);
        String commandTypeDesc = ProtocolHelper.getCommandTypeStr(protocol.getCommandType());
        log.info("发送命令=>[{}]:{}", commandTypeDesc, protocol.toString());
        try {
            //向客户端发送消息
            os.write(bytes);
            os.flush();
            log.info("指令=>[{}]发送成功", commandTypeDesc);
        } catch (Exception e) {
            log.error("指令发送失败", e);
            throw e;
        }
    }
}
