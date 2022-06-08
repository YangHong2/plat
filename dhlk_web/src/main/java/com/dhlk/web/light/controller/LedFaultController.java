package com.dhlk.web.light.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.LedFaultDTO;
import com.dhlk.web.light.service.LedFaultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xmdeng
 * @date 2021/8/4 14:58
 */
@RequestMapping("/ledFault")
@Api(tags = "故障信息")
@RestController
public class LedFaultController {

    @Resource
    private LedFaultService ledFaultService;

    @GetMapping("/page")
    @ApiOperation("故障分页查询")
    public Result getPage(LedFaultDTO dto){
        return ledFaultService.getPage(dto);
    }
}
