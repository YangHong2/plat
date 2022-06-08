package com.dhlk.light.test.entity;

import lombok.Data;

/**
 * 人感配置
 */
@Data
public class PeopleFeelInfo {
    private Integer on_off;// 开 1 启,关 0
    private Integer trig_delay_tm =10 ; //触发延迟时间
    private Integer n_ramp_tm = 2; //不触发渐变时间
    private Integer minvalue; //人感目标最低亮度
    private Integer maxvalue; //人感目标最高亮度
    private Integer outTime;//超时时间

}
