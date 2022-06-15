package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.api.ApiClassify;
import com.dhlk.entity.sub.Solution;
import com.dhlk.subcontract.dao.SolutionDao;
import com.dhlk.subcontract.service.SolutionService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 解决方案(Solution)表服务实现类
 *
 * @author makejava
 * @since 2021-03-12 09:54:23
 */
@Service("solutionService")
public class SolutionServiceImpl implements SolutionService {
    @Resource
    private SolutionDao solutionDao;


    @Override
    public Result queryById(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error("查询id不能为空!!");
        }
        return ResultUtils.success(solutionDao.queryById(id));
    }


    @Override
    public Integer insert(Solution solution) {
        return solutionDao.insert(solution);
    }

    @Override
    public Integer update(Solution solution) {
        return solutionDao.update(solution);
    }

    @Override
    public Result delete(String ids) {
        if (!CheckUtils.isNull(ids)) {
            return solutionDao.delete(Arrays.asList(ids.split(","))) > 0 ? ResultUtils.success() : ResultUtils.failure();
        }
        return ResultUtils.failure();
    }

    @Override
    public Result queryAll(String projectTheme, Integer companyId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Solution> pageInfo = new PageInfo<Solution>(solutionDao.queryAll(projectTheme,companyId));
        return ResultUtils.success(pageInfo);
    }
}
