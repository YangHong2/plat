package com.dhlk.subcontract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Solution;

/**
 * 解决方案(Solution)表服务接口
 *
 * @author makejava
 * @since 2021-03-12 09:54:22
 */
public interface SolutionService extends IService<Solution> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Result queryById(Integer id);

    /**
     * 新增数据
     *
     * @param solution 实例对象
     * @return 实例对象
     */
    Integer insert(Solution solution);

    /**
     * 修改数据
     *
     * @param solution 实例对象
     * @return 实例对象
     */
    Integer update(Solution solution);

    Result queryAll(String projectTheme, Integer companyId,Integer pageNum, Integer pageSize);

    Result delete(String ids);

}
