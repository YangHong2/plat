package com.dhlk.subcontract.web.controller;


import com.dhlk.domain.Result;
import com.dhlk.subcontract.web.service.UnionpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
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
    @Resource
    private UnionpayService unionpayService;

    /**
     * 支付
     */
    @GetMapping("payFor")
    public void payFor(HttpServletResponse resp, @RequestParam(value = "txnAmt") String txnAmt) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(unionpayService.payFor(txnAmt));
    }
    /**
     * 后台返回结果
     */
    @PostMapping("payBack")
    public Result payBack(HttpServletRequest req){
        return unionpayService.payBack(req);
    }
    /**
     * 页面返回结果
     */
    @PostMapping("payFront")
    public Result payFront(HttpServletRequest req){
        Map<String, String> respParam = getAllRequestParam(req);
        String encoding = req.getParameter("encoding");
        System.out.println("返回报文中encoding=[" + encoding + "]");
        return unionpayService.payFront(respParam);
    }

    /**
     * 获取请求参数中所有的信息
     * 当商户上送frontUrl或backUrl地址中带有参数信息的时候，
     * 这种方式会将url地址中的参数读到map中，会导多出来这些信息从而致验签失败，这个时候可以自行修改过滤掉url中的参数或者使用getAllRequestParamStream方法。
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                // 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                if (res.get(en) == null || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
}
