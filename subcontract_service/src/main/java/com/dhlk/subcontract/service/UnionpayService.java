package com.dhlk.subcontract.service;

import com.dhlk.domain.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description
 * @Author lpsong
 * @Date 2021/3/15
 */
public interface UnionpayService {
    String payFor(String txnAmt) throws IOException;


    Result payBack(HttpServletRequest req) throws IOException;

    Result payFront(Map<String, String> respParam) throws Exception;

}
