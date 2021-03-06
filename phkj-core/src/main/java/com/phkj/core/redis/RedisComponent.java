package com.phkj.core.redis;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ：zl
 * @date ：Created in 2019/5/13 15:06
 * @description：redis缓存
 * @modified By：
 * @version: 0.0.1$
 */
@Component("RedisComponent")
public class RedisComponent {
    private Logger      logger = LoggerFactory.getLogger(RedisComponent.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * create by: zl
     * description: 将数据以hash保存到redis并设置过期时间
     * create time:
     *
     * @return
     * @Param: redisKey
     * @Param: str
     * @Param: object
     */
    public void setRedisHashData(String redisKey, String key, String object, long expire) {
        logger.info("setDataAndExpireTime,redisKey:{},uid:{},object:{},expire:{}", redisKey, key,
            object, expire);
        stringRedisTemplate.opsForHash().put(redisKey, key, object);
        stringRedisTemplate.opsForHash().getOperations().expire(redisKey, expire, TimeUnit.DAYS);
    }

    /**
     * create by: zl
     * description: 获取redis中数据
     * create time:
     *
     * @return
     * @Param: redisKey
     * @Param: str
     */
    public String getRedisHashData(String redisKey, String str) {
        return (String) stringRedisTemplate.opsForHash().get(redisKey, str);
    }

    /**
     * 流量的原子性操作
     * @param redisKey
     * @return
     */
    public Long increment(String redisKey , Long increment) {
        return stringRedisTemplate.opsForValue().increment(redisKey, increment);
    }
    
    /**
     * 删除redis存储的信息
     * @param redisKey
     */
    public void deleteBrowse(String redisKey) {
        stringRedisTemplate.delete(redisKey);
    }

    /**
     * create by: zl
     * description: 将数据以string形式保存到redis并设置过期时间
     * create time:
     *
     * @return
     * @Param: redisKey
     * @Param: value
     * @Param: expireTime
     */
    public void setStringExpire(String redisKey, String value, long expireTime) {
        stringRedisTemplate.opsForValue().set(redisKey, value, expireTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 将字典表数据放入缓存
     * @param redisKey
     * @param value
     */
    public void setStringPersistence(String redisKey, String value) {
        stringRedisTemplate.opsForValue().set(redisKey, value);
    }

    /**
     * create by: zl
     * description: 获取redis数据
     * create time:
     *
     * @return
     * @Param: redisKey
     */
    public String getRedisString(String redisKey) {
        return stringRedisTemplate.opsForValue().get(redisKey);
    }
}
