package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.BaseFile;
import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ServerEmail;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.AttachmentService;
import com.dhlk.subcontract.web.service.ServerEmailService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description
 * @Author lpsong
 * @Date 2020/3/31
 */
@Service
public class ServerEmailServiceFbk implements ServerEmailService {
    @Override
    public Result selectOne(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(ServerEmail serverEmail) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}