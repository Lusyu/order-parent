package com.eaphone.jiankang.demo.core.redis;

import com.eaphone.smarthealth.redis.keys.KeyPrefix;

/**
 *
 *存储到redis的用户订单的key
 *
 */
public enum  UserOrderKeys implements KeyPrefix {
    USER_ORDER_KEYS("user_order");

    private String prefix;

    private UserOrderKeys(String prefix) {
        this.prefix=prefix;
    }

    public String prefix() {
        return prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
