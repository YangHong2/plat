package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectIssue;
import com.dhlk.subcontract.service.ProjectIssueService;
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
    public Result findProjectManage(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return projectIssueService.findProjectManage(name, pageNum, pageSize);
    }


    /**
     * 我的项目数量
     *
     * @return
     */
    @GetMapping("/getProjectCount")
    public Result getProjectCount(@RequestParam(value = "companyId") Integer companyId) {
        return projectIssueService.getProjectCount(companyId);
    }

    /**
     * 获取关联项目
     *
     * @return
     */
    @GetMapping("/getRelevance")
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
    public Result getProjectIssue(@RequestParam(value = "id") Integer id) {
        return projectIssueService.getProjectIssue(id);
    }
}