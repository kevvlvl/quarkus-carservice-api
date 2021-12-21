package com.kevvlvl.quarkus.redis;

import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class RedisService {

    private final RedisClient redisClient;

    private static final int ONE_MINUTE = 60;

    @Inject
    public RedisService(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public String get(String k) {

        Response resp = redisClient.get(k);
        return (resp != null) ? resp.toString() : null;
    }

    public void set(String k, String v) {
        redisClient.set(List.of(k, v));
        redisClient.expire(k, (ONE_MINUTE * 5) + "");
    }

    public void delete(String k) {
        redisClient.del(List.of(k));
    }
}
