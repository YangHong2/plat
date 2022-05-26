package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectIssue;
import com.dhlk.subcontract.web.service.ProjectIssueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 项目发布(ProjectIssue)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:17
 */
@RestController
@RequestMapping("/projectIssue")
@Api(description = "项目发布", value = "ProjectIssueController")
public class ProjectIssueController {

    @Autowired
    private ProjectIssueService projectIssueService;


    /**
     * 新增/修改
     *
     * @param projectIssue
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("新增/修改")
    public Result save(@RequestBody ProjectIssue projectIssue) {
        return projectIssueService.save(projectIssue);
    }


    /**
     * 列表查询
     *
     * @param name
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "type") Integer type,
                           @RequestParam(value = "companyId") Integer companyId,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return projectIssueService.findList(name, type, companyId, pageNum, pageSize);
    }

    /**
     * 查询项目关联数据
     *
     * @param id
     * @return
     */
    @GetMapping("/findRelevance")
    @ApiOperation("查询项目关联数据")
    public Result findRelevance(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "companyId") Integer companyId) {
        return projectIssueService.findRelevance(id, companyId);
    }

    /**
     * 项目管理列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findProjectManage")
    @ApiOperation("项目管理列表查询")
    public Result findProjectManage(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return projectIssueService.findProjectManage(name,pageNum, pageSize);
    }

    /**
     * 我的项目数量
     *
     * @return
     */
    @GetMapping("/getProjectCount")
    @ApiOperation("我的项目数量")
    public Result getProjectCount(@RequestParam(value = "companyId") Integer companyId) {
        return projectIssueService.getProjectCount(companyId);
    }

    /**
     * 获取关联项目
     *
     * @return
     */
    @GetMapping("/getRelevance")
    @ApiOperation("获取关联项目")
    public Result getRelevance(@RequestParam(value = "companyId") Integer companyId) {
        return projectIssueService.getRelevance(companyId);
    }

    /**
     * 首页搜索
     *
     * @param
     * @return
     */
    @GetMapping("/search")
    @ApiOperation("首页搜索")
    public Result search(@RequestParam(value = "projectName", required = false) String projectName,
                         @RequestParam(value = "projectType", required = false) Integer projectType,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return projectIssueService.search(projectName, projectType, pageNum, pageSize);
    }

    /**
     * 根据id获取项目详情
     *
     * @return
     */
    @GetMapping("/getProjectIssue")
    @ApiOperation("根据id获取项目详情")
    public Result getProjectIssue(@RequestParam(value = "id") Integer id) {
        return projectIssueService.getProjectIssue(id);
    }
}
