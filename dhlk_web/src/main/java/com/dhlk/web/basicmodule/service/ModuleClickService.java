package com.dhlk.web.basicmodule.service;

import com.dhlk.domain.Result;
import com.dhlk.web.basicmodule.service.fbk.ModuleClickServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xkliu
 * @date 2020/8/24
 */
@FeignClient(value = "basicmodule-service/moduleClick", fallback = ModuleClickServiceFbk.class)
public interface ModuleClickService {

    /**
     * 记录点击次数
     *
     * @param moduleName
     * @return
     */
    @GetMapping(value = "/recordNum")
    Result recordNum(@RequestParam(value = "moduleName") String moduleName);

    /**
     * 列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);
}
