package com.dhlk.light.factoryconstruction.handler.command.receive;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dhlk.light.factoryconstruction.command.send.FirmwareUpdateSendHander;
import com.dhlk.light.factoryconstruction.common.exception.BaseException;
import com.dhlk.light.factoryconstruction.common.result.ServerErrorResultCodeEnum;
import com.dhlk.light.factoryconstruction.datamap.FirmwareUpgradeMap;
import com.dhlk.light.factoryconstruction.datamap.DeviceCommandCheckMap;
import com.dhlk.light.factoryconstruction.enums.CommandReceiveHanderEnum;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.enums.UpdateRecordCommandTypeEnum;
import com.dhlk.light.factoryconstruction.handler.RecordHelper;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;
import com.dhlk.light.factoryconstruction.pojo.entity.FirmwareUpgrade;
import com.dhlk.light.factoryconstruction.pojo.vo.DebugMessageVO;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static com.dhlk.light.factoryconstruction.enums.CommandReceiveHanderEnum.FIRMWARE_UPGRADE;

/**
 * @author xmdeng
 * @date 2021/8/18 16:04
 */
@Component
@Slf4j
public class FirmwareUpgradeReceiveHander  implements  CommandReceiveHander{
    @Override
    public Object receiveCommand(String commandStr, SocketHandler socketHandler) {
        log.info("返回消息：{}",commandStr);
        Socket socket = socketHandler.getSocket();
        String key = socket.getInetAddress()+"_"+socket.getPort();
        String localPort = socket.getLocalPort()+"";
        String [] cmdArr = commandStr.split(" ");
        String cmdStr = cmdArr[3];
        if (StringUtils.isBlank(cmdStr)) {
            FirmwareUpgradeMap.removeUpgrade(socket);
            FirmwareUpgradeMap.removeSendNum(socket);
            throw new BaseException(ServerErrorResultCodeEnum.TCP_FIRMWARE_CMD_ERROR);
        }
        OutputStream outputStream = null;
        DebugMessageVO messageVO = new DebugMessageVO(socketHandler.getDeviceReportId(),"");
        try {
            outputStream = socket.getOutputStream();
            //解析返回消息是成功还是失败
            String message = cmdArr[6];
            FirmwareUpdateSendHander firmware = new FirmwareUpdateSendHander(outputStream,key,socketHandler);
            resultReply(firmware,message,cmdStr,key,socketHandler,messageVO);
        } catch (IOException e) {
            log.error(e.getMessage());
            FirmwareUpgradeMap.SEND_NUM_MAP.remove(key);
            FirmwareUpgradeMap.FIRMWARE_UPGRADE_MAP.remove(key);
            FirmwareUpgradeMap.removeUpgradeMap(socketHandler.getDeviceReportId());
            DeviceCommandCheckMap.remove(socketHandler.getDeviceReportId(),CommandTypeEnum.START_IPA.getCommandType());
            WebsocketServerUtil.websocketDebugMessagePush(localPort,messageVO);

            RecordHelper.insertUpdateRecord(socketHandler.getDeviceReportId(),
                    UpdateRecordCommandTypeEnum.FIRMWARE_UPGRADE.getCommandType(),
                    "","固件升级失败");
        }finally {
            try {
                assert outputStream != null;
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public CommandReceiveHanderEnum getCommandReceiveHanderEnum() {
        return FIRMWARE_UPGRADE;
    }


    private void resultReply(FirmwareUpdateSendHander firmware,
                             String resultCmd,
                             String cmdCode,
                             String key,
                             SocketHandler socketHandler,
                             DebugMessageVO messageVO){
        switch (resultCmd){
            case "01":
                  sendMessage(firmware,messageVO,socketHandler,cmdCode,key,resultCmd);
                break;
            case "00":
                  resultError(messageVO,cmdCode,socketHandler);
                break;
            case "03":
                  sendMessage(firmware,messageVO,socketHandler,cmdCode,key,resultCmd);
                  break;
            default:
                break;
        }
    }

    private void resultError(DebugMessageVO messageVO,String cmdCode,SocketHandler socketHandler){
        ServerErrorResultCodeEnum resultCodeEnum = null;
        String localPort = socketHandler.getSocket().getLocalPort()+"";
        switch (cmdCode){
            case "00":
                resultCodeEnum = ServerErrorResultCodeEnum.TCP_FIRMWARE_UPDATE_TOTAL;
                break;
            case "01":
                break;
            case "02":
                resultCodeEnum = ServerErrorResultCodeEnum.TCP_FIRMWARE_UPDATE_PACKGE;
                break;
            case "03":
                resultCodeEnum = ServerErrorResultCodeEnum.TCP_FIRMWARE_UPDATE_SEND_ERROR;
                break;
            case "04":
                break;
            default:
                resultCodeEnum =ServerErrorResultCodeEnum.TCP_FIRMWARE_CMD_ERROR;
                break;
        }
        if(resultCodeEnum != null){
            messageVO.setMessage(resultCodeEnum.getMsg());
            FirmwareUpgrade upgrade = FirmwareUpgradeMap.UPGRADE_MAP.get(socketHandler.getDeviceReportId());
            upgrade.upgradeFail();
            FirmwareUpgradeMap.removeUpgradeMap(upgrade.getSn());
            WebsocketServerUtil.websocketDebugMessagePush(localPort,messageVO);
            throw new BaseException(resultCodeEnum);
        }

    }

    private void sendMessage(FirmwareUpdateSendHander firmware,
                             DebugMessageVO messageVO,
                             SocketHandler socketHandler,
                             String cmdCode,
                             String key,
                             String message){
        String localPort = socketHandler.getSocket().getLocalPort()+"";
        switch (cmdCode){
            case "00":
                DeviceCommandCheckMap.remove(socketHandler.getDeviceReportId(),CommandTypeEnum.START_IPA.getCommandType());
                if(message.equals("03")){
                    firmware.sendFileTotal();
                }else {
                    log.info("开始发送第{}包，共{}包...",1,firmware.getBytes().size());
                    messageVO.setMessage("开始发送第1个包，共" + firmware.getBytes().size()+"包");
                    WebsocketServerUtil.websocketDebugMessagePush(localPort,messageVO);
                    firmware.sendPackage();
                }
                break;
            case "01":
                break;
            case "02":
                log.info("开始发送第{}包，共{}包...",1,firmware.getBytes().size());
                messageVO.setMessage("开始发送第1个包，共" + firmware.getBytes().size()+"包");
                WebsocketServerUtil.websocketDebugMessagePush(localPort,messageVO);
                firmware.sendPackage();
                break;
            case "03":
                if(FirmwareUpgradeMap.getNowNumber(key)==null){
                    break;
                }
                if( FirmwareUpgradeMap.getNowNumber(key)==firmware.getBytes().size()){
                    firmware.sendSuccess();
                }else {
                    log.info("开始发送第{}包，共{}包...",FirmwareUpgradeMap.getNowNumber(key)+1,firmware.getBytes().size());
                    messageVO.setMessage("开始发送第"+(FirmwareUpgradeMap.getNowNumber(key)+1)+"个包，共" + firmware.getBytes().size());
                    WebsocketServerUtil.websocketDebugMessagePush(localPort,messageVO);
                    firmware.sendPackage();
                }
                break;
            case "04":
                messageVO.setMessage("固件升级消息发送结束，请等待升级完毕");
                WebsocketServerUtil.websocketDebugMessagePush(localPort,messageVO);
                log.info("固件升级消息发送结束，请等待升级完毕");
                FirmwareUpgradeMap.FIRMWARE_UPGRADE_MAP.remove(key);
                FirmwareUpgradeMap.SEND_NUM_MAP.remove(key);
                FirmwareUpgradeMap.removeUpgradeMap(socketHandler.getDeviceReportId());
                break;
            default:
                throw new BaseException(ServerErrorResultCodeEnum.TCP_FIRMWARE_CMD_ERROR);
        }
    }

}
