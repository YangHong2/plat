package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Gold;
import com.dhlk.subcontract.service.GoldService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 金币表(Gold)表控制层
 *
 * @author xkliu
 * @since 2021-03-16 09:34:17
 */
@RestController
@RequestMapping("gold")
public class GoldController {
    /**
     * 服务对象
     */
    @Resource
    private GoldService goldService;


    @PostMapping(value = "/save")
    public Result save(@RequestBody Gold gold) {
        return goldService.insert(gold);
    }

    @PostMapping(value = "/updateGold")
    public Result upG(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "num", required = false) Double num) {
        return goldService.updateGoldByUserId(id, num);
    }

    @PostMapping(value = "/updateBalance")
    public Result upB(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "num", required = false) Double num) {
        return goldService.updateBalabceByUserId(id, num);
    }

    @GetMapping(value = "/queryGold")
    public Result selectByUserId(@RequestParam(value = "id", required = false) Integer id){
        return goldService.queryByUserId(id);
    }
}
