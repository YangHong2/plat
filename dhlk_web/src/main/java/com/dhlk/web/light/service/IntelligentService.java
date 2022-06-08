package com.dhlk.web.light.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.IntelligentButton;
import com.dhlk.entity.light.IntelligentSwitch;
import com.dhlk.web.light.service.fbk.IntelligentServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 智能开关接口类
 */
@FeignClient(value = "light-service/intelligent", fallback = IntelligentServiceFbk.class)
public interface IntelligentService {

    @PostMapping(value = "/insert")
    Result save(@RequestBody IntelligentSwitch intelligentSwitch);

    @PostMapping(value = "/insertBatch")
    Result saveBatch(@RequestBody List<IntelligentSwitch> list);

    @GetMapping(value = "/findListByAreaId")
    Result findListByAreaId (@RequestParam String areaId);

    @GetMapping(value = "/findListByTenantId")
    Result findListByTenantId (@RequestParam String tenantId);

    @GetMapping(value = "/findIntelligentBySn")
    Result findIntelligentBySn (@RequestParam String sn);

    @GetMapping(value = "/deleteIntelligent")
    Result deleteIntelligent(@RequestParam String sn);

    @GetMapping(value = "/findButtonBySn")
    Result findButtonBySn(@RequestParam String sn);

}
