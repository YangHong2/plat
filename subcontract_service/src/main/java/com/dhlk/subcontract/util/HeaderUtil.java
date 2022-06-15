package com.dhlk.subcontract.util;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.service.RedisService;
import com.dhlk.systemconst.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static java.lang.System.out;

/**
 * @Description
 * @Author gchen
 * @Date 2020/10/19
 */
@Component
public class HeaderUtil {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisService redisService;

    public String cloudToken() {
        return request.getHeader(Const.TOKEN_HEADER);
    }

    public SubpackageUser getUserinfo() {
        try {
            return JSONObject.parseObject((String) redisService.get(Const.TOKEN_CACHE_ITEM_PREFIX + cloudToken()),
                    SubpackageUser.class);
        } catch (Exception e) {
            out.println(e);/*换行输出*/
            return null;
        }
    }
}