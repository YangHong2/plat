package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Solution;
import com.dhlk.subcontract.web.service.SolutionService;
import com.dhlk.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: dhlk.tenant.plat
 * @description:
 * @author: wqiang
 * @create: 2021-03-12 11:03
 **/
@RestController
@RequestMapping("/solution")
@Api(description = "解决方案", value = "SolutionController")
public class SolutionController {
    /**
     * 服务对象
     */
    @Resource
    private SolutionService solutionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ApiOperation("通过主键查询单条数据")
    public Result selectOne(@RequestParam(value = "id", required = true) Integer id) {
        return solutionService.selectOne(id);
    }

    /**
     * 新增或保存
     *
     * @param solution
     * @return
     */
    @ApiOperation("新增或保存")
    @PostMapping("/saveOrUpdate")
    public Result insert(@RequestBody Solution solution) {
        return solutionService.insert(solution);
    }


    /**
     * 条件查询
     *
     * @param projectTheme 主题
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/findList")
    @ApiOperation("条件查询")
    public Result findList(@RequestParam (value = "projectTheme", required = false) String projectTheme,
                           @RequestParam(value = "companyId", required = false) Integer companyId,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return solutionService.findList(projectTheme, companyId,pageNum, pageSize);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    @ApiOperation("批量删除")
    public Result delete(@RequestParam(value = "ids", required = true) String ids) {
        return solutionService.delete(ids);
    }

}
