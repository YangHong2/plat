package com.dhlk.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xmdeng
 * @date 2021/9/26 14:39
 */
@Data
public class UserBean {

    private String userName;

    private String userPwd;

    private LocalDateTime loginTime;
}
