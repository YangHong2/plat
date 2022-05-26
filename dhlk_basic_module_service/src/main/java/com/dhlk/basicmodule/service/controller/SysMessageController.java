package com.dhlk.basicmodule.service.controller;

import com.dhlk.basicmodule.service.service.SysMessageService;
import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.InfoBox;
import com.dhlk.entity.basicmodule.SysMessage;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* 系统消息管理
*/
@RestController
@RequestMapping(value = "/sysMessage")
public class SysMessageController {
    @Autowired
    private SysMessageService sysMessageService;

    /**
     * 保存
     * @param
     * @return
     */
    @PostMapping(value = "/save")
    @RequiresAuthentication
    public Result save(@RequestBody SysMessage sysMessage) {
        return sysMessageService.save(sysMessage);
    }
    /**
     * 删除
     * @param infoBox
     * @return result
     */
    @PostMapping(value = "/delete")
    @RequiresAuthentication
    public Result delete(@RequestBody InfoBox infoBox) {
        return sysMessageService.delete(infoBox.getIds());
    }

    /**
    * 列表查询
     * @param pageNum
     * @param pageSize
    * @return
    */
    @GetMapping("/findPageList")
    @RequiresAuthentication
    public Result findPageList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {
        return  sysMessageService.findPageList(pageNum, pageSize);
    }
}
