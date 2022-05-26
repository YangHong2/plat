package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ArticlePub;
import com.dhlk.subcontract.web.service.fbk.ArticlePubServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @des:
 * @author: xkliu
 * @date: 2021/03/23
 */
@FeignClient(value = "subcontract-service/articlePub", fallback = ArticlePubServiceFbk.class)
public interface ArticlePubService {

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    Result selectOne(@RequestParam(value = "id") Integer id);

    /**
     * 新增/修改
     *
     * @param rticlePub
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody ArticlePub rticlePub);

    /**
     * 列表查询
     *
     * @param title
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "title", required = false) String title,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

}
