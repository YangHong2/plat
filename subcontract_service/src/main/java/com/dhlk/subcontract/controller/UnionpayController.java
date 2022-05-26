package com.dhlk.subcontract.controller;


import com.dhlk.domain.Result;
import com.dhlk.subcontract.service.UnionpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 银联支付
 * @author lpsong
 * @Date 2021/3/15
 */
@RestController
@RequestMapping("unionpay")
public class UnionpayController {
    /**
     * 服务对象
     */
    @Autowired
    private UnionpayService unionpayService;

    /**
     * 支付
     */
    @GetMapping("payFor")
    public String payFor(@RequestParam(value = "txnAmt") String txnAmt) throws IOException {
        return unionpayService.payFor(txnAmt);
    }
    /**
     * 返回结果
     */
    @PostMapping("payBack")
    public Result payBack(HttpServletRequest req) throws IOException {
       return  unionpayService.payBack(req);
    }
    /**
     * 返回结果
     */
    @PostMapping("payFront")
    public Result payFront(@RequestBody Map<String, String> respParam) throws Exception {
        return unionpayService.payFront(respParam);
    }
}
