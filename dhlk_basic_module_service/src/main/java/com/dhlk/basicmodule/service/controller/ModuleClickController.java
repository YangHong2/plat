package com.dhlk.basicmodule.service.controller;

import com.dhlk.basicmodule.service.service.ModuleClickService;
import com.dhlk.domain.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xkliu
 * @date 2020/8/24
 */
@RestController
@RequestMapping(value = "/moduleClick")
public class ModuleClickController {

    @Autowired
    private ModuleClickService moduleClickService;

    /**
     * 记录用户点击模块次数
     *
     * @param
     * @return
     */
    @GetMapping(value = "/recordNum")
    @RequiresAuthentication
    public Result recordNum(@RequestParam(value = "moduleName") String moduleName) {
        return moduleClickService.recordNum(moduleName);
    }

    /**
     * 列表查询
     *
     * @param
     * @return
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return moduleClickService.findList( pageNum, pageSize);
    }

}
