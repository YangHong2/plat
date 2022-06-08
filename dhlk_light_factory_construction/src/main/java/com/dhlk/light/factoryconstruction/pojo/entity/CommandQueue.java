package com.dhlk.light.factoryconstruction.pojo.entity;

import cn.hutool.core.util.StrUtil;
import com.dhlk.light.factoryconstruction.common.exception.BaseException;
import com.dhlk.light.factoryconstruction.common.result.ParamErrorResultCodeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 命令队列
 * @author yangfan
 * @since 2021-08-10
 */
@Slf4j
public class CommandQueue {

    LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

    /**
     * 获取队列中的元素并组成字符串
     * @param next 需要取出的元素个数
     * @return 取出元素组成的字符串
     */
    public String next(Integer next){
        StringBuilder resultStr = new StringBuilder();
        for(int i = 0;i<next;i++){
            String poll = blockingQueue.poll();
            if(StrUtil.isEmpty(poll)){
                throw new BaseException(ParamErrorResultCodeEnum.PARAM_INVALID,"原始数据异常:"+poll);
            }
            resultStr.append(poll);
        }

        return resultStr.toString();
    }

    public CommandQueue(String command){
        setBlockingQueue(command);
    }

    private void setBlockingQueue(String command) {
        String[] strArr = command.split(" ");
        blockingQueue.addAll(Arrays.asList(strArr));
    }

}
