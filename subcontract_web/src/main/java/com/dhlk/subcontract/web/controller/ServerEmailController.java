package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ServerEmail;
import com.dhlk.subcontract.web.service.ServerEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 服务端邮箱(ServerEmail)表控制层
 *
 * @author makejava
 * @since 2021-03-15 14:13:21
 */
@RestController
@RequestMapping("serverEmail")
@Api(value = "serverEmailController",description = "服务端邮箱")
public class ServerEmailController {
    /**
     * 服务对象
     */
    @Resource
    private ServerEmailService serverEmailService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    @ApiOperation("通过主键查询单条数据")
    public Result selectOne(@RequestParam(value = "id",defaultValue = "1") Integer id) {
        return this.serverEmailService.selectOne(id);
    }

    /**
     * 修改服务器邮箱
     */
    @PostMapping("update")
    @ApiOperation("修改服务器邮箱")
    public Result update(@RequestBody ServerEmail serverEmail) {
        return this.serverEmailService.update(serverEmail);
    }

}
