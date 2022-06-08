package com.dhlk.web.light.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.ScheduledCronDTO;
import com.dhlk.entity.dto.SyncScheduledCronDTO;
import com.dhlk.web.light.service.fbk.ScheduledCronServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xmdeng
 * @date 2021/8/3 12:28
 */
@FeignClient(value = "light-service/scheduled", fallback = ScheduledCronServiceFbk.class)
public interface ScheduledCronService {

    @PostMapping("/list/page")
    Result getPage(@RequestBody ScheduledCronDTO dto);

    @PostMapping("/sync")
    Result syncData(@RequestBody SyncScheduledCronDTO dto);

    @GetMapping("/{id}")
    Result delete(@PathVariable("id") Integer id);

    @GetMapping("/one")
    Result getOne();

    @PostMapping("/list")
    Result getList(@RequestBody ScheduledCronDTO dto);
}
