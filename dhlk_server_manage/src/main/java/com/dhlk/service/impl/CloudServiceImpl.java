package com.dhlk.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.feign.CloudFeign;
import com.dhlk.service.CloudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xmdeng
 * @date 2021/8/2 13:29
 */
@Service
@Slf4j
public class CloudServiceImpl implements CloudService {

    @Resource
    private CloudFeign cloudFeign;

    @Override
    public void sendServiceInfo(Result result) {
        cloudFeign.sendServiceInfo(result);
    }
}
