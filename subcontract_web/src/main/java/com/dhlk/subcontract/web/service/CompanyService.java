package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Company;
import com.dhlk.subcontract.web.service.fbk.CompanyServiceFbk;
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
@FeignClient(value = "subcontract-service/company", fallback = CompanyServiceFbk.class)
public interface CompanyService {

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    Result selectOne(@RequestParam(value = "id") Integer id);

    /**
     * 修改
     */
    @PostMapping("update")
    Result update(@RequestBody Company company);

    /**
     * 获取金融服务商的名字
     *
     * @param name
     * @return
     */
    @GetMapping("/getFinancial")
    Result getFinancial(@RequestParam(value = "name", required = false) String name);


    /**
     * 获取咨询服务商的名字
     *
     * @param name
     * @return
     */
    @GetMapping("/getConsult")
    Result getConsult(@RequestParam(value = "name", required = false) String name);
}
