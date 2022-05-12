package com.bl.ep.cache;

import com.bl.ep.utils.SpringUtil;
import org.apache.ibatis.cache.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName RedisCache
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/25 16:32
 * @Version 1.0
 **/
public final class RedisCache implements Cache {

    /**
     * 日志
     */
    private static final Logger logger = LogManager.getLogger(RedisCache.class);

    /**
     * 读写锁
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * ID,获取缓存对象的唯一标识
     */
    private String id;

    /**
     * redisTemplate
     */
    private static RedisTemplate redisTemplate;

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("缓存实例需要一个id!");
        } else {
            logger.info("开启Redis缓存: id = {}", id);
            this.id = id;
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            Long size = redisTemplate.opsForHash().size(this.id.toString());
            logger.info("Redis缓存大小: id = {}, size = {}", id, size);
            return size.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void putObject(final Object key, final Object value) {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            logger.info("设置Redis缓存: id = {}, key = {}, value = {}", id, key, value);
            redisTemplate.opsForHash().put(this.id.toString(), key.toString(), value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getObject(final Object key) {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            Object hashVal = redisTemplate.opsForHash().get(this.id.toString(), key.toString());
            logger.info("获取Redis缓存: id = {}, key = {}, hashVal = {}", id, key, hashVal);
            return hashVal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object removeObject(final Object key) {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            redisTemplate.opsForHash().delete(this.id.toString(), key.toString());
            logger.info("移除Redis缓存: id = {}, key = {}", id, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void clear() {
        try {
            if (redisTemplate == null) {
                redisTemplate = getRedisTemplate();
            }
            redisTemplate.delete(this.id.toString());
            logger.info("清空Redis缓存: id = {}", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    @Override
    public String toString() {
        return "RedisCache {" + this.id + "}";
    }

    /**
     * 由于启动期间注入失败，只能运行期间注入
     *
     * @return
     */
    public RedisTemplate getRedisTemplate() {
        redisTemplate = (RedisTemplate<String, Object>) SpringUtil.getBean("redisTemplate");
        return redisTemplate;
    }

}