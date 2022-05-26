package com.dhlk.web.bi.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiDatabase;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.bi.service.BiDatabaseService;
import org.springframework.stereotype.Service;

/**
 * @des: 数据库连接Feign接口调用失败
 * @author: xkliu
 * @date: 2021/02/22
 */
@Service
public class BiDatabaseServiceFbk implements BiDatabaseService {

    @Override
    public Result save(BiDatabase biDatabase) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result testConnection(BiDatabase biDatabase) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getTableNames(BiDatabase biDatabase) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getTableColumns(BiDatabase biDatabase) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
