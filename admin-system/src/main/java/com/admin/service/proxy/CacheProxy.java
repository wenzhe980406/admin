package com.admin.service.proxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 缓存代理类，便于缓存变更
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Slf4j
@Component
public class CacheProxy<K,V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    /**
     * 获取缓存值
     * @param key 缓存键
     *
     * @return V
     */
    public V get(K key) throws CacheException {
        log.trace("CacheProxy: get, key = [" + key + "]");
        if (key == null) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存值
     * @param key 缓存键
     * @param value 缓存值
     *
     * @return V
     */
    public V put(K key, V value) throws CacheException {
        log.trace("CacheProxy: put, key = [" + key + "]");
        if (key == null) {
            log.warn("CacheProxy: put, key can not be null");
        }
        redisTemplate.opsForValue().set(key, value);
        return value;
    }

    /**
     * 指定过期时长
     * @param key 缓存键
     * @param value 缓存值
     * @param expire 过期时间(s)
     *
     * @return V
     */
    public V put(K key, V value, int expire) throws CacheException {
        this.put(key, value, expire * 1000L);
        return value;
    }

    /**
     * 指定过期时长
     * @param key 缓存键
     * @param value 缓存值
     * @param expire 过期时间(ms)
     *
     * @return V
     */
    public V put(K key, V value, long expire) throws CacheException {
        log.trace("CacheProxy: put, key = [" + key + "]");
        if (key == null) {
            log.warn("CacheProxy: put, key can not be null");
        }
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
        return value;
    }

    /**
     * 清理缓存
     */
    public void clear() throws CacheException {
        log.trace("CacheProxy: clear");
        Set<K> keys = this.keys();
        redisTemplate.delete(keys);
    }

    /**
     * 获取缓存数
     *
     * @return int
     */
    public int size() {
        log.trace("CacheProxy: size");
        return this.keys().size();
    }

    /**
     * 获取缓存key
     * 
     * @return Set<K>
     */
    public Set<K> keys() {
        log.trace("CacheProxy: keys");
        Set<K> keys = redisTemplate.keys(null);
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptySet();
        }
        return keys;
    }

    /**
     * 获取缓存key
     * @param keyPattern 缓存键正则 
     *
     * @return Set<K>
     */
    public Set<K> keys(String keyPattern) {
        log.trace("CacheProxy: keys, keyPattern = [" + keyPattern + "]");
        if (keyPattern == null || "".equals(keyPattern) || "*".equals(keyPattern)) {
            return keys();
        }
        return redisTemplate.keys((K)keyPattern);
    }

    /**
     * 获取所有缓存值
     *
     * @return Collection<V>
     */
    public Collection<V> values() {
        log.trace("CacheProxy: values");
        Collection<V> values = new ArrayList<>();
        Set<K> keys = this.keys();
        if (CollectionUtils.isEmpty(keys)) {
            return values;
        }
        for (K k : keys) {
            values.add(redisTemplate.opsForValue().get(k));
        }
        return values;
    }

    /**
     * 获取所有缓存值
     * @param key 缓存键
     *
     * @return Collection<V>
     */
    public V remove(K key) throws CacheException {
        log.trace("CacheProxy: remove, key = [" + key + "]");
        if (key == null) {
            return null;
        }
        V value = this.get(key);
        redisTemplate.delete(key);
        return value;
    }
}
