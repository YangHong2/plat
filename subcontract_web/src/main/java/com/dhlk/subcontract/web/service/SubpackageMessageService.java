package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageMessage;
import com.dhlk.subcontract.web.service.fbk.SubpackageMessageServiceFBK;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "subcontract-service/subpackageMessage", fallback = SubpackageMessageServiceFBK.class)
public interface SubpackageMessageService {

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    Result queryById(@RequestParam(value = "id", required = false) Integer id);

    /**
     * 新增数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    @PostMapping("/save")
    Result insert(@RequestBody SubpackageMessage subpackageMessage);

    /**
     * 修改数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    @PostMapping("/update")
    public Result update(@RequestBody SubpackageMessage subpackageMessage);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(@RequestParam(value = "pageNum", required = false) int pageNum,
                                  @RequestParam(value = "pageSize", required = false) int pageSize);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @GetMapping("deleteById")
    public Result deleteById(@RequestParam(value = "id", required = false) Integer id);
}
