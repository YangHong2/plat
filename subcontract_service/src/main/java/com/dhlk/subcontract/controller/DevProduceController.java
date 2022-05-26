package com.dhlk.subcontract.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DevProduce;
import com.dhlk.subcontract.service.DevProduceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 开发生产(DevProduce)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:04
 */
@RestController
@RequestMapping("/devProduce")
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
    @PostMapping("/save")
    public Result save(@RequestBody DevProduce devProduce) {
        return this.devProduceService.save(devProduce);
    }

    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids",required = true) String ids){
        return this.devProduceService.delete(ids);
    }

}
