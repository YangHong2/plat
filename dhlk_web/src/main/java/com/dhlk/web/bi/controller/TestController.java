package com.dhlk.web.bi.controller;

import com.dhlk.domain.Result;
import com.dhlk.web.bi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("/hello")
    public Result hello(){
        return testService.hello();
    }
}
