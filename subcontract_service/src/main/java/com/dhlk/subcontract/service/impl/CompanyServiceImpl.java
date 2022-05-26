package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Company;
import com.dhlk.subcontract.dao.CompanyDao;
import com.dhlk.subcontract.service.CompanyService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 企业管理(Company)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:20:57
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyDao companyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer id) {
        return ResultUtils.success(this.companyDao.queryById(id));
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Company> queryAllByLimit(int offset, int limit) {
        return this.companyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @Override
    public Company insert(Company company) {
        this.companyDao.insert(company);
        return company;
    }

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(Company company) {
        int update = this.companyDao.update(company);
        return update > 0?ResultUtils.success():ResultUtils.failure();
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.companyDao.deleteById(id) > 0;
    }

    @Override
    public Result getFinancial(String name) {
        return ResultUtils.success(companyDao.getFinancial(name));
    }

    @Override
    public Result getConsult(String name) {
        return ResultUtils.success(companyDao.getConsult(name));
    }
}
