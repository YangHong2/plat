package com.dhlk.light.factoryconstruction.enums;

/**
 * @author xmdeng
 * @date 2021/8/17 14:12
 */
public enum FirmwareEnum {

    IAP_DOWNLOAD("81","IAP下载"),
    TOTAL("82","传输烧写数据总量"),
    DATA("83","数据包"),
    SUCCESS("84","传输新固件成功")
    ;
    private String cmd;

    private String message;

    FirmwareEnum(String cmd,String message){
        this.cmd = cmd;
        this.message = message;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
