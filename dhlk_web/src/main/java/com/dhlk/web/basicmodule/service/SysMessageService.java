package com.dhlk.web.basicmodule.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.InfoBox;
import com.dhlk.entity.basicmodule.SysMessage;
import com.dhlk.web.basicmodule.service.fbk.SysMessageServiceFbk;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "basicmodule-service/sysMessage", fallback = SysMessageServiceFbk.class)
public interface SysMessageService {
    /**
     * 保存
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody SysMessage sysMessage);
    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    Result delete(@RequestBody InfoBox infoBox);
    /**
     * 查询
     */
    @GetMapping("/findPageList")
    Result findPageList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize);
}
