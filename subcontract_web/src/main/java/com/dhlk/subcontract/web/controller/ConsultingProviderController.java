package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ConsultingProvider;
import com.dhlk.subcontract.web.service.ConsultingProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: dhlk.tenant.plat
 * @description:
 * @author: wqiang
 * @create: 2021-03-15 14:13
 **/
@RestController
@RequestMapping("/consultingProvider")
@Api(description = "咨询服务商", value = "ConsultingProviderController")
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
    @ApiOperation(value = "删除")
    @GetMapping("/delete")
    public Result delete(@RequestParam(value = "ids", required = true) String ids) {
        return consultingProviderService.delete(ids);
    }


    @ApiOperation(value = "保存或更新")
    @PostMapping("/save")
    public Result save(@RequestBody ConsultingProvider consultingProvider) {
        return consultingProviderService.save(consultingProvider);
    }
}
