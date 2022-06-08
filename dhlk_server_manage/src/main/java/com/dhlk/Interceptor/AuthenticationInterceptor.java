package com.dhlk.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.domain.Result;
import com.dhlk.enums.ResultEnum;
import com.dhlk.enums.SystemEnums;
import com.dhlk.exception.MyException;
import com.dhlk.service.RedisService;
import com.dhlk.systemconst.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xmdeng
 * @date 2021/9/26 14:30
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private RedisService redisService;

    private static final String Authorization = "Authorization";

    public static final String TOKEN_CACHE_ITEM_PREFIX = "dhlk.cache.item.token.";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object object){
        log.info("开始拦截...");
        String token = httpServletRequest.getHeader(Authorization);
//        if(StringUtils.isBlank(token)){
//            throw new MyException(SystemEnums.LOGOIN_USER_NOT_EXIST);
//        }else {
//           if(redisService.get(TOKEN_CACHE_ITEM_PREFIX + token) != null){
//               return true;
//           }else {
//               throw new MyException(SystemEnums.TOKEN_LOSE_ERR);
//           }
//        }
        return true;
    }
}
