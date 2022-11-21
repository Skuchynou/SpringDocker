package com.example.springbootredis.service;

import com.example.springbootredis.model.Hash;

public interface HashService {
    public Hash save(String string);

    public Hash findByString(String string);
}
