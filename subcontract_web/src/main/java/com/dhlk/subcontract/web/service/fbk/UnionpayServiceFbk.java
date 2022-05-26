package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.UnionpayService;
import com.dhlk.utils.ResultUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author lpsong
 * @Date 2021/3/15
 */
@Service
public class UnionpayServiceFbk implements UnionpayService {
    @Override
    public String payFor(String txnAmt){
        return null;
    }

    @Override
    public Result payBack(HttpServletRequest req){
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result payFront(Map<String, String> respParam) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}