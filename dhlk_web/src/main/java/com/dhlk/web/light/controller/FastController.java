package com.dhlk.web.light.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.SwitchHumanSenseDTO;
import com.dhlk.entity.light.Switch;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.light.service.FastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xmdeng
 * @date 2022/2/28 9:25
 */
@Api(tags = "法士特相关接口")
@RequestMapping("/fast")
@RestController
public class FastController {

    @Resource
    private FastService fastService;

    @GetMapping("/switch/list")
    @ApiOperation("获取租户下所有分组信息")
    public Result<List<Switch>> getSwitchList(){
        return fastService.getSwitchList();
    }

    @PostMapping("/switch/open")
    @ApiOperation("根据分组集合进行开灯")
    public Result openSwitchLed(@RequestBody List<Integer> switchIds){
        fastService.openSwitchLed(switchIds);
        return ResultUtils.success("命令已发送");
    }

    @PostMapping("/switch/close")
    @ApiOperation(("根据分组进行关灯"))
    public Result closeLed(@RequestBody List<Integer> switchIds){
        fastService.closeLed(switchIds);
        return ResultUtils.success("命令已发送");
    }

    @PostMapping("/switch/humanSense")
    @ApiOperation("根据分组集合进行人感开启或关闭")
    public Result openOrCloseHumanSense(@RequestBody SwitchHumanSenseDTO dto){
        return fastService.openOrCloseHumanSense(dto);
    }

}
