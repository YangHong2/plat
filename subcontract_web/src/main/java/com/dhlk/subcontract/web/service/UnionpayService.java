package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.subcontract.web.service.fbk.SolutionServiceFbk;
import com.dhlk.subcontract.web.service.fbk.UnionpayServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description
 * @Author lpsong
 * @Date 2021/3/15
 */
@FeignClient(value = "subcontract-service/unionpay", fallback = UnionpayServiceFbk.class)
public interface UnionpayService {

    /**
     * 支付
     */
    @GetMapping("payFor")
    String payFor(@RequestParam(value = "txnAmt") String txnAmt) throws IOException;
    /**
     * 返回结果
     */
    @PostMapping("payBack")
    Result payBack(HttpServletRequest req);


    @PostMapping("payFront")
    Result payFront(@RequestBody  Map<String, String> respParam);
}
