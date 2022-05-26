package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.subcontract.web.service.TestService;
import com.dhlk.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    TestService testService;
    @GetMapping("/helloWorld")
    public Result helloWorld(){
        return testService.helloWorld();
    }
}
