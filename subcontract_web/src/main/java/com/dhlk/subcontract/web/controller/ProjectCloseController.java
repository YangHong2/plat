package com.dhlk.subcontract.web.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectClose;
import com.dhlk.subcontract.web.service.ProjectCloseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 关闭项目(ProjectClose)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:15
 */
@RestController
@RequestMapping("/projectClose")
@Api(description = "关闭项目", value = "ProjectCloseController")
public class ProjectCloseController {


    @Autowired
    private ProjectCloseService projectCloseService;


    /**
     * 新增/修改
     *
     * @param projectClose
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("新增/修改")
    public Result save(@RequestBody ProjectClose projectClose) {
        return projectCloseService.save(projectClose);
    }

    /**
     * 项目关闭列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @ApiOperation("项目关闭列表查询")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return projectCloseService.findList(name,pageNum, pageSize);
    }

}
