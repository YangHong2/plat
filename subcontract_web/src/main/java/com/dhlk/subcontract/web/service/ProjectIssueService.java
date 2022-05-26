package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectIssue;
import com.dhlk.subcontract.web.service.fbk.ProjectIssueServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @des:
 * @author: xkliu
 * @date: 2021/03/15
 */
@FeignClient(value = "subcontract-service/projectIssue", fallback = ProjectIssueServiceFbk.class)
public interface ProjectIssueService {

    /**
     * 新增/修改
     *
     * @param projectIssue
     * @return
     */
    @PostMapping("/save")
    Result save(@RequestBody ProjectIssue projectIssue);

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
    Result findList(@RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "type") Integer type,
                    @RequestParam(value = "companyId") Integer companyId,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 查询项目关联数据
     *
     * @param id
     * @return
     */
    @GetMapping("/findRelevance")
    Result findRelevance(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "companyId") Integer companyId);

    /**
     * 项目管理列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findProjectManage")
    Result findProjectManage(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 我的项目数量
     *
     * @return
     */
    @GetMapping("/getProjectCount")
    Result getProjectCount(@RequestParam(value = "companyId") Integer companyId);

    /**
     * 获取关联项目
     *
     * @return
     */
    @GetMapping("/getRelevance")
    Result getRelevance(@RequestParam(value = "companyId") Integer companyId);

    /**
     * 首页搜索
     *
     * @param
     * @return
     */
    @GetMapping("/search")
    Result search(@RequestParam(value = "projectName", required = false) String projectName,
                  @RequestParam(value = "projectType", required = false) Integer projectType,
                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 根据id获取项目详情
     *
     * @return
     */
    @GetMapping("/getProjectIssue")
    Result getProjectIssue(@RequestParam(value = "id") Integer id);

}
