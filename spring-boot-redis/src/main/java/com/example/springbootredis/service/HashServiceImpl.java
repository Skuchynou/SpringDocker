package com.example.springbootredis.service;

import com.example.springbootredis.model.Hash;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class HashServiceImpl implements HashService{

    private static final String HASH = "Hash";

    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, Hash> hashOperations;

    @Autowired
    public HashServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }
    @Override
    public Hash save(String string) {
        String sha256hex = DigestUtils.sha256Hex(string);
        Hash h = Hash.builder().string(string).hash(sha256hex).build();
        hashOperations.put(HASH, h.getString(), h);
        return h;
    }
    @Override
    public Hash findByString(String string) {
        return (Hash) hashOperations.get(HASH, string);
    }
}
