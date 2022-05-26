package com.dhlk.web.basicmodule.controller;
import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.InfoBox;
import com.dhlk.entity.basicmodule.SysMessage;
import com.dhlk.web.basicmodule.service.SysMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 系统消息管理
 */
@RestController
@RequestMapping(value = "/sysMessage")
@Api(description = "系统消息管理")
public class SysMessageController {
    @Autowired
    private SysMessageService sysMessageService;

    /**
     * 保存
     * @param
     * @return
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "保存")
    public Result save(@RequestBody SysMessage sysMessage) {
        return sysMessageService.save(sysMessage);
    }
    /**
     * 删除
     * @param infoBox
     * @return result
     */
    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除")
    public Result delete(@RequestBody InfoBox infoBox) {
        return sysMessageService.delete(infoBox);
    }

    /**
     * 列表查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findPageList")
    @ApiOperation(value = "查询")
    public Result findPageList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {
        return  sysMessageService.findPageList(pageNum, pageSize);
    }
}

