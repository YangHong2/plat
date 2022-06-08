package com.dhlk.task;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.domain.NetworkCardInfo;
import com.dhlk.domain.ProcessInfoEntity;
import com.dhlk.domain.Result;
import com.dhlk.feign.CloudFeign;
import com.dhlk.service.ProcessManageService;
import com.dhlk.service.RedisService;
import com.dhlk.service.SourceMonitoringService;
import com.dhlk.systemconst.Const;
import com.dhlk.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xmdeng
 * @date 2021/8/2 13:36
 */
@Component
@EnableScheduling
@Slf4j
public class CloudTask {

    @Resource
    private ProcessManageService processManageService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private CloudFeign cloudFeign;
    @Autowired
    private SourceMonitoringService sourceMonitoringService;

    @Scheduled(cron = "0/30 * * * * ?")
    public void sendServiceInfoToCloud(){
        try {
            // 判断是否推送
            if (redisService.hasKey(Const.REDIS_MANAGE_KEY)){
                JSONObject jsonObject = new JSONObject();
                List<ProcessInfoEntity> serverList= new ArrayList<>();
                Result result = sourceMonitoringService.getNetworkInfo();
                Result info = processManageService.getAllProcessInfo("", "", "");
                if (result.getCode()==0 && info.getCode()==0){
                    List<NetworkCardInfo> networkCardInfoList= (List<NetworkCardInfo>) result.getData();
                    //解析一体机当前服务信息
                    List<ProcessInfoEntity> allList = (List<ProcessInfoEntity>) info.getData();
                    a:for (String processName : Const.PROCESS_ARRAY){
                        b:for (ProcessInfoEntity processInfoEntity : allList){
                            if (processInfoEntity.getProcessName().contains(processName)){
                                serverList.add(processInfoEntity);
                                continue a;
                            }
                        }
                        // 若没有返回相匹配的数据，将返回默认值。
                        ProcessInfoEntity process = new ProcessInfoEntity();
                        process.setProcessName(processName);
                        process.setStatus("0");
                        serverList.add(process);
                    }
                    log.info("上报服务信息：{}",JSONObject.toJSONString(serverList));
                    //组装返回的数据
                    jsonObject.put("networkCardInfoList",networkCardInfoList);
                    jsonObject.put("data",serverList);
                }
                cloudFeign.sendServiceInfo(ResultUtils.success(jsonObject));
            }
        }catch (Exception e){
            log.error("定时任务推送服务器服务信息出错：",e);
        }
    }
}
