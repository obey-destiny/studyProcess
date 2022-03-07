package com.example.cache;

import com.example.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Slf4j
public class RedisCache implements Cache {
    // namespace
    private String id;

    public RedisCache(String id) {
        log.info("缓存的id：[{}]", id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        log.info("放入缓存的key:[{}] 放入缓存的value:[{}]", key, value);
        getTemplate().opsForHash().put(id, key.toString(), value);
    }

    @Override
    public Object getObject(Object key) {
        log.info("获取缓存的key:[{}]", key);
        return getTemplate().opsForHash().get(id, key.toString());
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        log.info("清除所有缓存....");
        getTemplate().delete(id);
    }

    @Override
    public int getSize() {
        return getTemplate().opsForHash().size(id).intValue();
    }

    public RedisTemplate getTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
