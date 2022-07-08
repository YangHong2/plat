package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectIssue;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.ProjectIssueService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * @des:
 * @author: xkliu
 * @date: 2021/03/15
 */
@Service
public class ProjectIssueServiceFbk implements ProjectIssueService {

    @Override
    public Result save(ProjectIssue projectIssue) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, Integer type, Integer companyId, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findRelevance(Integer id, Integer companyId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findProjectManage(String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getProjectCount(Integer companyId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getRelevance(Integer companyId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result search(String projectName, Integer projectType, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getProjectIssue(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findName(String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result projectRecords(String projectName, Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findProjectName(String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

}
