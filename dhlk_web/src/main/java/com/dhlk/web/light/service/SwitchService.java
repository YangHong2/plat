package com.dhlk.web.light.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.BrightnessOperDTO;
import com.dhlk.entity.light.Switch;
import com.dhlk.web.light.service.fbk.SwitchServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "light-service/switch", fallback = SwitchServiceFbk.class)
public interface SwitchService {
    /**
     * 保存/修改
     * @author      gchen
     * @param swich
     */
    @PostMapping("/save")
    Result save(@RequestBody Switch swich);

    /**
     * 批量删除
     * @author gchen
     * @param ids
     * @return
     * @date        2020/6/4 15:50
     */
    @GetMapping("/delete")
    Result delete(@RequestParam("ids") String ids);

    /**
     * 保存/修改
     * @author      gchen
     * @param name pageNum pageSize
     * @return
     * @date        2020/6/4 15:50
     */
    @GetMapping("/findList")
    Result findList(@RequestParam("name") String name,
                    @RequestParam("pageNum") Integer pageNum,
                    @RequestParam("pageSize") Integer pageSize);

    @GetMapping("/findGroupList")
    Result findGroupList(@RequestParam(value = "switchId",required = false) Integer switchId);

    @PutMapping("/updateBrightnessBySwitchId")
    Result updateBrightnessBySwitchId(@RequestBody @Validated BrightnessOperDTO dto);
}
