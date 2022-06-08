package com.dhlk.light.test.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.light.test.service.LightService;
import com.dhlk.light.test.util.LedOperate;
import com.dhlk.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author lpsong
 * @Date 2021/3/24
 */
@Service
public class LightServiceImpl implements LightService {
    @Autowired
    private LedOperate ledOperate;
    @Override
    public Result openOrClose(String sns, Integer status) {
        ledOperate.ledOnOrOff(sns, 1);
        try {
            ledOperate.brightness(sns, 1);
            Thread.sleep(5000);
            ledOperate.brightness(sns, 50);
            Thread.sleep(5000);
            ledOperate.brightness(sns, 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ledOperate.ledOnOrOff(sns, 0);
        return ResultUtils.success("命令已发送");
    }
}