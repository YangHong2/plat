package com.dhlk.interfaces.service.util;

import com.dhlk.systemconst.Const;
import com.dhlk.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author gchen
 * @Date 2020/10/19
 */
@Component
public class HeaderUtil {

    @Autowired
    private HttpServletRequest request;

    public Integer tenantId() {
//        return Convert.stringToInteger(JWTUtil.getTenantId(cloudToken()));
        return JWTUtil.getTenantId(cloudToken());
    }

    public String cloudToken() {
        return request.getHeader(Const.TOKEN_HEADER);
    }
}