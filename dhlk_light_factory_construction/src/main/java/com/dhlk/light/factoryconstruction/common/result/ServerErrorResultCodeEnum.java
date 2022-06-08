package com.dhlk.light.factoryconstruction.common.result;

/**
 * <p>
 *
 * </p>
 *
 * @author jisen wang
 * @Date 2020/9/11
 */
public enum ServerErrorResultCodeEnum implements ServerErrorResultCode {
    UNKNOWN(500, "服务器异常，请联系管理员"),
    FILE_ERROR(501,"文件上传失败！"),
    FILE_NULL_ERROR(502,"文件不能为空或解析失败"),
    FILE_BIG_ERROR(503,"文件大小不能超过70kb"),
    TCP_FIRMWARE_UPDATE_TOTAL(504,"tcp发送固件大小失败"),
    TCP_FIRMWARE_UPDATE_PACKGE(505,"tcp发送固件包失败"),
    TCP_FIRMWARE_UPDATE_SEND_ERROR(506,"发送固件传输成功失败"),
    TCP_FIRMWARE_CMD_ERROR(507,"返回消息命令无法识别"),
    UPGRADE_ERROR(508,"升级任务启动失败")
    ;


    ServerErrorResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
