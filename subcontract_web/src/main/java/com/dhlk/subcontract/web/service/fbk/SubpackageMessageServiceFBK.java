package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageMessage;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.SubpackageMessageService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

@Service
public class SubpackageMessageServiceFBK implements SubpackageMessageService {
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Override
    public Result queryById(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    /**
     * 新增数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(SubpackageMessage subpackageMessage) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    /**
     * 修改数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */

    @Override
    public Result update(SubpackageMessage subpackageMessage) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result queryAllByLimit(int offset, int limit) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result deleteById(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    /**
     * 根据文字模糊查询
     * @param messageName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result queryBymessageName(String messageName, int pageNum, int pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
