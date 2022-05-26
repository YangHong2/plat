package com.dhlk.bi.controller;

import com.dhlk.domain.Result;
import com.dhlk.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public Result test(){
        return ResultUtils.success("项目搭建成功-----------------");
    }
}
