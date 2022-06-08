package com.dhlk.light.factoryconstruction.pojo.entity;

import com.dhlk.light.factoryconstruction.datamap.FirmwareUpgradeMap;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author xmdeng
 * @date 2021/9/2 9:13
 */
@Data
@Slf4j
public class FirmwareUpgrade {


    @ApiModelProperty("设备ID")
    private String sn;

    @ApiModelProperty("开始时间")
    private LocalDateTime startDate;

    @ApiModelProperty("0=等待;1=执行;2=完成;3=失败超时")
    private Integer status = 0;

    public boolean checkOutTime(){
        if(this.status == 1){
            Duration duration = Duration.between(startDate,LocalDateTime.now());
            long millis = duration.toMillis();
            if(millis/1000 > 180){
                FirmwareUpgradeMap.removeUpgradeMap(this.getSn());
                log.info("设备升级失败，原因：升级超时");
                return  false;
            }
        }
        return true;
    }

    public void upgradeSuccess(){
        this.status = 2;
    }

    public void upgradeFail(){
        this.status = 3;
    }

}
