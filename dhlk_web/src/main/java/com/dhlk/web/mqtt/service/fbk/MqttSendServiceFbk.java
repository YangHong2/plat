package com.dhlk.web.mqtt.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.mqtt.service.MqttSendService;
import org.springframework.stereotype.Service;

@Service
public class MqttSendServiceFbk implements MqttSendService {

    @Override
    public Result sendMessage(String topic, String biInfo) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
