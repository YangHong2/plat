package com.dhlk.light.factoryconstruction.common.result;

/**
 * <p>
 *
 * </p>
 *
 * @author jisen wang
 * @Date 2020/9/9
 */
public interface ResultCode {

    /**
     * 状态码
     * @return
     */
    int getCode();

    /**
     * 返回处理消息
     * @return
     */
    String getMsg();

    /**
     * 设置消息内容
     * @param msg
     */
    void setMsg(String msg);

    default String format() {
        return format(this.getCode(), this.getMsg());
    }

    default String format(int code, String msg) {
        return String.format("错误消息: %s，错误状态码：%s", msg, code);
    }
}
