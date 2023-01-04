package com.example.wschat.utils;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringGenerator {

    public String randomString() {
        return UUID.randomUUID().toString();
    }

    @Cacheable("cached-string")
    public String randomCachedString() {
        return UUID.randomUUID().toString();
    }
}
