package com.dhlk.subcontract.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ConsultingProvider;
import com.dhlk.subcontract.service.ConsultingProviderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 咨询服务商(ConsultingProvider)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:00
 */
@RestController
@RequestMapping("/consultingProvider")
public class ConsultingProviderController {
    /**
     * 服务对象
     */
    @Resource
    private ConsultingProviderService consultingProviderService;

    /**
     * 通过主键查询单条数据
     *
     * @param ids 主键
     * @return 单条数据
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam(value = "ids", required = true) String ids) {
        return consultingProviderService.delete(ids);
    }


    @PostMapping("/save")
    public Result save(@RequestBody ConsultingProvider consultingProvider) {
        return consultingProviderService.save(consultingProvider);
    }

}
