package com.dhlk.light.factoryconstruction.pojo.vo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * debug消息VO
 * @author yangfan
 * @since 2021-08-19
 */
@Data
public class DebugMessageVO {

    public DebugMessageVO(){

    }

    public DebugMessageVO(String sn,String message){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.sn = sn;
        this.message = message;
        this.time = LocalDateTime.now().format(dtf);
    }
    /**
     * 时间
     */
    private String time;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 命令类型
     */
    private String commandType;
    /**
     * 设备sn
     */
    private String sn;

    /**
     * 消息
     */
    private String message;

    @Override
    public String toString() {
        String toString;
        if(StringUtils.isBlank(commandType)){
            toString =  "["+messageType+"] ["+time+" ] ["+sn+" ] ["+message+"]";
        }else{
            toString =  "["+messageType+"] [0x"+commandType+ "] ["+time+" ] ["+sn+" ] ["+message+"]";
        }
        return toString;
    }
}
