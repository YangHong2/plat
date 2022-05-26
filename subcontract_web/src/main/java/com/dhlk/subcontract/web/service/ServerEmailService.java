package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ServerEmail;
import com.dhlk.subcontract.web.service.fbk.ServerEmailServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/15
 * Time:10:50
 * @Description:
 */
@FeignClient(value = "subcontract-service/serverEmail", fallback = ServerEmailServiceFbk.class)
public interface ServerEmailService {
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    Result selectOne(@RequestParam(value = "id",defaultValue = "1") Integer id);

    /**
     * 修改服务器邮箱
     */
    @PostMapping("update")
    Result update(@RequestBody ServerEmail serverEmail);
}
