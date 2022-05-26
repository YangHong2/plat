package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Gold;
import com.dhlk.subcontract.web.service.fbk.GoldFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 金币表(Gold)表服务接口
 *
 * @author xkliu
 * @since 2021-03-16 09:34:17
 */
@FeignClient(value = "subcontract-service/gold", fallback = GoldFbk.class)
public interface GoldService {

    @PostMapping(value = "/save")
    public Result insert(@RequestBody Gold gold);

    @PostMapping(value = "/updateGold")
    public Result updateGoldByUserId(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "num", required = false) Double num);

    @PostMapping(value = "/updateBalance")
    public Result updateBalabceByUserId(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "num", required = false) Double num);

    @GetMapping(value = "/queryGold")
    public Result queryByUserId(@RequestParam(value = "id", required = false) Integer id);
}
