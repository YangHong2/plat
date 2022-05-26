package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/helloWorld")
    public Result helloworld(){
        return ResultUtils.success("连接成功");
    }
}
