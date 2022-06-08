package com.dhlk.light.test.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

/**
 * @Description 照明设备返回结果
 * @Author lpsong
 * @Date 2020/6/8
 */
@ToString
@Data
public class LedResult<T> {
    private Integer code; //code
    private Integer cmd_type; //命令
    private Integer grp_id=0;
    private Integer dev_type=0;
    private Integer addr_type=0;
    private String ts=String.valueOf(System.currentTimeMillis());//时间戳
    private T data; //Data
    @JSONField(name = "SN")
    private String SN;
    private Integer ver=1;

    public LedResult(){

    }
    public LedResult(LedEnum cmdType, T data) {
        this.cmd_type = cmdType.getState();
        this.data = data;
        this.grp_id = 0;
        this.dev_type = 0;
        this.addr_type=0;
        this.code=0;
    }
    public LedResult(String SN,LedEnum cmdType,long ts){
        this.SN=SN;
        this.cmd_type=cmdType.getState();
        this.grp_id=0;
        this.dev_type=0;
        this.addr_type=0;
        this.code=0;
        this.ts=String.valueOf(ts);
    }
    public LedResult(String SN,LedEnum cmdType, T data,long ts){
        this.SN=SN;
        this.cmd_type=cmdType.getState();
        this.data=data;
        this.grp_id=0;
        this.dev_type=0;
        this.addr_type=0;
        this.code=0;
        this.ts=String.valueOf(ts);

    }

    public LedResult(String SN, LedEnum cmdType,LedEnum devType, T data,long ts){
        this.SN=SN;
        this.cmd_type=cmdType.getState();
        this.data=data;
        this.grp_id=0;
        this.dev_type=devType.getState();
        this.code=0;
        this.ts=String.valueOf(ts);
        this.addr_type=0;
    }
}
