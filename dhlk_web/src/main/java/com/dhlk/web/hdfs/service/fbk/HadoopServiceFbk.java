package com.dhlk.web.hdfs.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.hdfs.service.HadoopService;
import org.springframework.stereotype.Service;

/**
 * @Author: jzhao
 * @Date: 2020/4/21 09:37
 * @Description:
 */
@Service
public class HadoopServiceFbk implements HadoopService {

    @Override
    public Result listFile(String filePath, String viewType, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
