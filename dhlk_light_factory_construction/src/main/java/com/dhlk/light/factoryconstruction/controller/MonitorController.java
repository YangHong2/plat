package com.dhlk.light.factoryconstruction.controller;

import com.dhlk.light.factoryconstruction.common.result.ResultVO;
import com.dhlk.light.factoryconstruction.util.SocketUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzx
 * @date 2021/8/9 17:04
 */
@RestController
@Api(tags = "监听控制器")
@Slf4j
@RequestMapping("/monitor")
public class MonitorController {

    @GetMapping(value = "/start")
    @ApiOperation("开始监听")
    public ResultVO start(@ApiParam(required = true, value = "端口号") @RequestParam String port) {
        SocketUtils.createServer(port);
        return ResultVO.ok();
    }

    @GetMapping(value = "/end")
    @ApiOperation("结束监听")
    public ResultVO end(@ApiParam(required = true, value = "端口号") @RequestParam String port) {
        SocketUtils.closeServer(port);
        return ResultVO.ok();
    }

    @GetMapping(value = "/state")
    @ApiOperation("端口监听状态")
    public ResultVO state(@ApiParam(required = true, value = "端口号") @RequestParam String port) {
        return ResultVO.ok(SocketUtils.isRunning(port));
    }

}
