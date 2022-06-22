package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageMessage;
import com.dhlk.subcontract.web.service.SubpackageMessageService;
import com.dhlk.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 消息(SubpackageMessage)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:24:13
 */

@RestController
@RequestMapping("subpackageMessage")
@Api(description = "消息", value = "SubpackageMessageController")
public class SubpackageMessageController {
    /**
     * 服务对象
     */
    @Resource
    private SubpackageMessageService subpackageMessageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    @ApiOperation("通过主键查询单条数据")
    public Result selectOne(@RequestParam(value = "id", required = false) Integer id) {
        return this.subpackageMessageService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    @PostMapping("/save")
    @ApiOperation("新增数据")
    public Result save(@RequestBody SubpackageMessage subpackageMessage) {
        return subpackageMessageService.insert(subpackageMessage);
    }

    /**
     * 修改数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    @PostMapping("/update")
    @ApiOperation("修改数据")
    public Result update(@RequestBody SubpackageMessage subpackageMessage) {
        return subpackageMessageService.update(subpackageMessage);
    }

    /**
     * 查询多条数据
     *
     * @param pageNum  查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @GetMapping("queryAllByLimit")
    @ApiOperation("查询多条数据")
//    public Result queryAllByLimit(@RequestParam(value = "offset", required = false) int offset,
//                                  @RequestParam(value = "limit", required = false) int limit) {
//        return subpackageMessageService.queryAllByLimit(offset,limit);
//    }
//    @GetMapping("queryAllByLimit")
    public Result queryAllByLimit(@RequestParam(value = "messageName", required = false) String messageName,
                                  @RequestParam(value = "pageNum", required = false) int pageNum,
                                  @RequestParam(value = "pageSize", required = false) int pageSize) {
        if (messageName == null || messageName == "") {
            return subpackageMessageService.queryAllByLimit(pageNum, pageSize);
        } else
            return subpackageMessageService.queryBymessageName(messageName, pageNum, pageSize);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过id删除数据")
    @GetMapping("deleteById")
    public Result deleteById(@RequestParam(value = "id", required = false) Integer id) {
        return ResultUtils.success(subpackageMessageService.deleteById(id));
    }

}
