package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Solution;
import com.dhlk.subcontract.service.SolutionService;
import com.dhlk.utils.ResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.GET;

/**
 * 解决方案(Solution)表控制层
 *
 * @author wangq
 * @since 2021-03-12 09:54:24
 */
@RestController
@RequestMapping("/solution")
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
    public Result selectOne(@RequestParam(value = "id", required = true) Integer id) {
        return solutionService.queryById(id);
    }

    /**
     * 新增或保存
     *
     * @param solution
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result insert(@RequestBody Solution solution) {
        int flag = 0;
        if (solution.getId() == null) {
            flag = solutionService.insert(solution);
        } else {
            flag = solutionService.update(solution);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    /**
     * 条件查询
     *
     * @param projectTheme
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/findList")
    public Result findList(@RequestParam(value = "projectTheme", required = false) String projectTheme,
                           @RequestParam(value = "companyId", required = false) Integer companyId,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return solutionService.queryAll(projectTheme,companyId,pageNum, pageSize);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam(value = "ids", required = true) String ids) {
        return solutionService.delete(ids);
    }

}
