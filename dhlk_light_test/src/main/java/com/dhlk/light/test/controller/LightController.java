package com.dhlk.light.test.controller;

import com.dhlk.domain.Result;
import com.dhlk.light.test.entity.InfoBox;
import com.dhlk.light.test.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author lpsong
 * @Date 2021/3/24
 */
@RestController
@RequestMapping("/light")
public class LightController {

    @Autowired
    private LightService lightService;
    /**
     * 开关灯
     *
     * @return
     */
    @PostMapping(value = "/openOrClose")
    public Result openOrClose(@RequestBody InfoBox<Integer> infoBox) {
        return lightService.openOrClose(infoBox.getSns(), infoBox.getT());
    }
}