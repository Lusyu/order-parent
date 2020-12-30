package com.eaphone.jiankang.demo.core.redis;

import com.eaphone.smarthealth.redis.keys.KeyPrefix;

/**
 *
 *存储到redis的用户订单的key
 *
 */
public enum UserDemoKeys implements KeyPrefix {
    USER_DEMO_KEYS("user_demo");

    private String prefix;

    private UserDemoKeys(String prefix) {
        this.prefix=prefix;
    }

    public String prefix() {
        return prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
