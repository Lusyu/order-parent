package com.eaphone.jiankang.demo.controller;

import com.eaphone.smarthealth.api.controller.BaseRestController;
import com.eaphone.xxs.passport.client.PassportClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 所有api的基类  目的校验token
 */
public class BaseDemoController extends BaseRestController {
    @Autowired
    protected PassportClient passportClient;

    public String checkUser(String token){
        return passportClient.getUserIdFromToken(token);
    }
}
