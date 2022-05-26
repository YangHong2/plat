package com.dhlk.web.basicmodule.controller;

import com.dhlk.domain.Result;
import com.dhlk.web.basicmodule.service.ModuleClickService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xkliu
 * @date 2020/8/24
 */
@RestController
@RequestMapping(value = "/moduleClick")
@Api(description = "模块点击", value = "ModuleClickController")
public class ModuleClickController {

    @Autowired
    private ModuleClickService moduleClickService;

    /**
     * 记录用户点击模块次数
     *
     * @param
     * @return
     */
    @ApiOperation("记录用户点击模块次数")
    @GetMapping(value = "/recordNum")
    public Result recordNum(@RequestParam(value = "moduleName") String moduleName) {
        return moduleClickService.recordNum(moduleName);
    }


    /**
     * 列表查询
     *
     * @param
     * @return
     */
    @ApiOperation("列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return moduleClickService.findList(pageNum, pageSize);
    }
}
