package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DevProduce;
import com.dhlk.subcontract.web.service.DevProduceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: dhlk.tenant.plat
 * @description:
 * @author: wqiang
 * @create: 2021-03-15 14:56
 **/

@RestController
@RequestMapping("/devProduce")
@Api(description = "开发生产", value = "DevProduceController")
public class DevProduceController {


    /**
     * 服务对象
     */
    @Resource
    private DevProduceService devProduceService;

    /**
     * 新增或保存
     *
     * @param devProduce
     */

    @ApiOperation(value = "新增或更新")
    @PostMapping("/save")
    public Result save(@RequestBody DevProduce devProduce) {
        return this.devProduceService.save(devProduce);
    }


    @ApiOperation(value = "删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids",required = true) String ids){
        return this.devProduceService.delete(ids);
    }
}
