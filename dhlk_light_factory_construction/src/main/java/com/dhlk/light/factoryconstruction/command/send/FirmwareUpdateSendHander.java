package com.dhlk.light.factoryconstruction.command.send;

import com.dhlk.light.factoryconstruction.common.exception.BaseException;
import com.dhlk.light.factoryconstruction.common.result.ServerErrorResultCodeEnum;
import com.dhlk.light.factoryconstruction.datamap.FirmwareUpgradeMap;
import com.dhlk.light.factoryconstruction.enums.FirmwareEnum;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;
import com.dhlk.light.factoryconstruction.pojo.entity.FirmwareMessage;
import com.dhlk.light.factoryconstruction.pojo.entity.UpgradePace;
import com.dhlk.light.factoryconstruction.pojo.vo.DebugMessageVO;
import com.dhlk.light.factoryconstruction.util.SocketUtils;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xmdeng
 * @date 2021/8/18 17:13
 */
@Data
@Slf4j
public class FirmwareUpdateSendHander {

    /**
     * TCP写入流
     */
    private OutputStream dataOutputStream;

    private SocketHandler socketHandler;

    /**
     * 数据字节集合
     */
    private List<byte[]> bytes;

    /**
     * tcp ip+"_" + port
     */
    private String socketKey;

    /**
     * 初始化tcp发送信息
     * @param dataOutputStream
     * @param socketKey
     */
    public FirmwareUpdateSendHander(OutputStream dataOutputStream,String socketKey,SocketHandler socketHandler){
        this.dataOutputStream =dataOutputStream;
        this.bytes = FirmwareUpgradeMap.FIRMWARE_UPGRADE_MAP.get(socketKey);
        this.socketKey = socketKey;
        this.socketHandler = socketHandler;
    }

    public void sendFileTotal(){
        int leg = 0;
        for(byte[] bs:this.bytes){
            leg += bs.length;
        }
        String md5 = FirmwareUpgradeMap.getMd5(socketKey);
        byte[] b = new byte[3];
        b[2] = (byte) (leg >> 8 & 0xff);
        b[1] = (byte) (leg >> 16 & 0xff);
        b[0] = (byte) (leg >> 24 & 0xff);
        byte[] md5Bytes = md5.getBytes();
        byte [] data = new byte[b.length+md5Bytes.length];
        for(int i = 0 ; i < md5Bytes.length; i++){
            data[i] = md5Bytes[i];
        }
        int tmpL= md5Bytes.length;
        data[tmpL] = b[0];
        data[++tmpL] = b[1];
        data[++tmpL] = b[2];
        //输出日志
        String str = "";
        for(byte d:data){
            byte [] b1 = new byte[1];
            b1[0] = d;
            str += SocketUtils.bytesToHexString(b1)+" ";
        }
        log.info("{}:{}",FirmwareEnum.TOTAL.getMessage(),str);
        log.info("设备：{},{}开始，传输大小{}...",socketHandler.getDeviceReportId(),FirmwareEnum.TOTAL.getMessage(),leg);
        WebsocketServerUtil.websocketDebugMessagePush(socketHandler.getSocket().getLocalPort()+"",new DebugMessageVO(socketHandler.getDeviceReportId(),getStartMessage(FirmwareEnum.TOTAL.getMessage(),"传输大小"+leg,"个字节")));
        try {
            this.dataOutputStream.write(new FirmwareMessage(FirmwareEnum.TOTAL.getCmd(),leg,data).getDate());
            WebsocketServerUtil.websocketDebugMessagePush(socketHandler.getSocket().getLocalPort()+"",new DebugMessageVO(socketHandler.getDeviceReportId(),getEndMessage(FirmwareEnum.TOTAL.getMessage())));
            log.info("设备：{},{}完成...",socketHandler.getDeviceReportId(),FirmwareEnum.TOTAL.getMessage());
        } catch (IOException e) {
            WebsocketServerUtil.websocketDebugMessagePush(socketHandler.getSocket().getLocalPort()+"",new DebugMessageVO(socketHandler.getDeviceReportId(),ServerErrorResultCodeEnum.TCP_FIRMWARE_UPDATE_TOTAL.getMsg()));
            throw new BaseException(ServerErrorResultCodeEnum.TCP_FIRMWARE_UPDATE_TOTAL);
        }finally {
            try {
                this.dataOutputStream.flush();
            }catch (IOException ioe){
                log.error("设备：{}，发送数据包传输总量消息失败",socketHandler.getDeviceReportId());
            }
        }
    }

    public void sendPackage(){
        Integer number = FirmwareUpgradeMap.getNumber(this.socketKey);
        byte[] bytes = this.bytes.get(number);
        try {
            log.info("设备：{},{}开始，传输大小{}...",socketHandler.getDeviceReportId(),FirmwareEnum.DATA.getMessage(),bytes.length);
            WebsocketServerUtil.websocketDebugMessagePush(socketHandler.getSocket().getLocalPort()+"",new DebugMessageVO(socketHandler.getDeviceReportId(),getStartMessage(FirmwareEnum.DATA.getMessage(),"传输大小"+bytes.length,"个字节")));
            this.dataOutputStream.write(new FirmwareMessage(FirmwareEnum.DATA.getCmd(),bytes.length,bytes).getDate());
            WebsocketServerUtil.websocketDebugMessagePush(socketHandler.getSocket().getLocalPort()+"",new DebugMessageVO(socketHandler.getDeviceReportId(),getEndMessage(FirmwareEnum.DATA.getMessage())));
            log.info("设备：{},{}完成...",socketHandler.getDeviceReportId(),FirmwareEnum.DATA.getMessage());
            setUpgradePace(socketHandler,number);
        } catch (IOException e) {
            WebsocketServerUtil.websocketDebugMessagePush(socketHandler.getSocket().getLocalPort()+"",new DebugMessageVO(socketHandler.getDeviceReportId(),ServerErrorResultCodeEnum.TCP_FIRMWARE_UPDATE_PACKGE.getMsg()));
            throw new BaseException(ServerErrorResultCodeEnum.TCP_FIRMWARE_UPDATE_PACKGE);
        }finally {
            try {
                this.dataOutputStream.flush();
            }catch (IOException ioe){
                log.error("设备：{}，发送数据包失败",socketHandler.getDeviceReportId());
            }
        }
    }

    private void setUpgradePace(SocketHandler socketHandler,Integer nowNumber){
        UpgradePace upgradePace = new UpgradePace();
        upgradePace.setNowNumber(nowNumber+1);
        upgradePace.setSn(socketHandler.getDeviceReportId());
        upgradePace.setTotalNumber(this.bytes.size());
        upgradePace.setUpgradeTime(LocalDateTime.now());
        FirmwareUpgradeMap.syncUpgradePaceMap(upgradePace);
    }

    public void sendSuccess(){
        try {
            log.info("设备：{},{}开始...",socketHandler.getDeviceReportId(),FirmwareEnum.SUCCESS.getMessage());
            WebsocketServerUtil.websocketDebugMessagePush(socketHandler.getSocket().getLocalPort()+"",new DebugMessageVO(socketHandler.getDeviceReportId(),getStartMessage(FirmwareEnum.SUCCESS.getMessage())));
            this.dataOutputStream.write(new FirmwareMessage(FirmwareEnum.SUCCESS.getCmd(), 0, null).getDate());
            WebsocketServerUtil.websocketDebugMessagePush(socketHandler.getSocket().getLocalPort()+"",new DebugMessageVO(socketHandler.getDeviceReportId(),getEndMessage(FirmwareEnum.SUCCESS.getMessage())));
            log.info("设备：{},{}完成...",socketHandler.getDeviceReportId(),FirmwareEnum.SUCCESS.getMessage());
        } catch (IOException e) {
            throw new BaseException(ServerErrorResultCodeEnum.TCP_FIRMWARE_UPDATE_SEND_ERROR);
        }finally {
            try {
                this.dataOutputStream.flush();
            }catch (IOException ioe){
                log.error("设备：{}，发送数据包传输成功消息失败",socketHandler.getDeviceReportId());
            }
        }
    }

    private String getStartMessage(String ... str){
        StringBuilder sb = new StringBuilder();
        sb.append("发送");
        for(String msg:str){
            sb.append(msg);
        }
        sb.append("....");
        return sb.toString();
    }

    private String getEndMessage(String ... str){
        StringBuilder sb = new StringBuilder();
        sb.append("发送");
        for(String msg:str){
            sb.append(msg);
        }
        sb.append("结束");
        return sb.toString();
    }
}
