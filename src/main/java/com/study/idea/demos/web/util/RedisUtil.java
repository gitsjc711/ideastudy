package com.study.idea.demos.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;
    private long default_time=300;
    private long login_time=60*60*24*7;
    public Object get(String key) {
        return key == null ? null : serializableRedisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入，默认5分钟
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Serializable value) {
        try {
            serializableRedisTemplate.opsForValue().set(key, value, default_time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean loginSet(String key, Serializable value) {
        try {
            serializableRedisTemplate.opsForValue().set(key, value, login_time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean delete(String key) {
        try {
            serializableRedisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
