package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ServerEmail;
import com.dhlk.subcontract.service.ServerEmailService;
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
    public Result selectOne(@RequestParam(value = "id",defaultValue = "1") Integer id) {
        return this.serverEmailService.queryById(id);
    }

    /**
     * 修改服务器邮箱
     */
    @PostMapping("update")
    public Result update(@RequestBody ServerEmail serverEmail) {
        return this.serverEmailService.update(serverEmail);
    }

}
