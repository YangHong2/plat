package com.dhlk.light.factoryconstruction.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xmdeng
 * @date 2021/9/2 15:33
 */
@Data
public class UpgradePace {

    private String sn;

    private Integer status;

    private Integer nowNumber = 0;

    private Integer totalNumber;

    private LocalDateTime upgradeTime;
}
