package com.dhlk.light.factoryconstruction.thread;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dhlk.light.factoryconstruction.datamap.FirmwareUpgradeMap;
import com.dhlk.light.factoryconstruction.datamap.SocketClinetMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.handler.SendCommandHelper;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;
import com.dhlk.light.factoryconstruction.pojo.entity.FirmwareUpgrade;
import com.dhlk.light.factoryconstruction.pojo.vo.DebugMessageVO;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.*;

/**
 * @author xmdeng
 * @date 2021/8/20 10:31
 */
@Slf4j
public class FirmwareUpgradeThread {

    private byte[] fileBytes;

    private List<String> sns;

    private int threadSize;

    private String filePath;

    public FirmwareUpgradeThread(byte[] fileBytes, List<String> sns, int threadSize,String filePath){
        this.fileBytes = fileBytes;
        this.sns = sns;
        this.threadSize = threadSize;
        this.filePath = filePath;
    }

    public void run(){
        for(String sn:sns){
            FirmwareUpgrade upgrade = new FirmwareUpgrade();
            upgrade.setStatus(0);
            upgrade.setSn(sn);
            FirmwareUpgradeMap.setUpgradeMap(upgrade);
        }
        ExecutorService executor = newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(this.threadSize);
        try {
            boolean flag = true;
            while (flag){
                if(FirmwareUpgradeMap.UPGRADE_MAP.size() < 1){
                    flag = false;
                }
                int runSize = FirmwareUpgradeMap.getRunUpgrade().size();
                if(FirmwareUpgradeMap.UPGRADE_MAP.size() > 0 && runSize < this.threadSize){
                    FirmwareUpgrade run = FirmwareUpgradeMap.getRun();
                    if(run == null){
                        flag = false;
                    }else {
                        run.setStartDate(LocalDateTime.now());
                        run.setStatus(1);
                        FirmwareUpgradeMap.syncUpgradeMap(run);
                        UpgradeTask task = new UpgradeTask(countDownLatch, this.fileBytes,run,filePath);
                        executor.execute(task);
                    }
                }
                Thread.sleep(1000);
            }
//            countDownLatch.wait();
        }catch (Exception e){
            log.error("固件升级失败，失败原因：{}",e);
        }finally {
            executor.shutdown();
        }
    }

    static class UpgradeTask implements Runnable {

        private CountDownLatch countDownLatch;
        private byte[] bytes;
        private String sn;
        private String  filePath;
        private FirmwareUpgrade upgrade;
        public UpgradeTask(CountDownLatch countDownLatch,byte[] bytes,FirmwareUpgrade upgrade,String filePath){
            this.countDownLatch = countDownLatch;
            this.bytes = bytes;
            this.sn = upgrade.getSn();
            this.filePath = filePath;
            this.upgrade = upgrade;
        }

        @Override
        public void run() {
            try {
                if(StringUtils.isNotBlank(sn)&& bytes != null){
                    SocketHandler socketHandler = SocketClinetMap.get(sn);
                    Socket socket = socketHandler.getSocket();
                    FirmwareUpgradeMap.putUpgradeMap(socket,bytes);
                    FirmwareUpgradeMap.putMD5Map(socket,filePath);
                    try {
                        log.info("设备ID:{}启动{}",sn,CommandTypeEnum.START_IPA);
                        SendCommandHelper.startIPA(sn);
                        WebsocketServerUtil.websocketDebugMessagePush(socket.getPort()+"",new DebugMessageVO(sn,"设备启动IPA"));
                    }catch (Exception e){
                        log.error("发送固件包至硬件失败，{}",e);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                // 发出线程任务完成的信号
                countDownLatch.countDown();
            }

        }
    }
}
