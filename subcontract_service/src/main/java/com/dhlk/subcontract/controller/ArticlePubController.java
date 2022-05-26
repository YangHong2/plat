package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ArticlePub;
import com.dhlk.subcontract.service.ArticlePubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 文章发布(ArticlePub)表控制层
 *
 * @author xkliu
 * @since 2021-03-23 11:14:05
 */
@RestController
@RequestMapping("/articlePub")
public class ArticlePubController {

    /**
     * 服务对象
     */
    @Autowired
    private ArticlePubService articlePubService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(@RequestParam(value = "id") Integer id) {
        return this.articlePubService.queryById(id);
    }


    /**
     * 新增/修改
     *
     * @param rticlePub
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody ArticlePub rticlePub) {
        return articlePubService.insert(rticlePub);
    }

    /**
     * 列表查询
     *
     * @param title
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return articlePubService.findList(title, pageNum, pageSize);
    }
}
