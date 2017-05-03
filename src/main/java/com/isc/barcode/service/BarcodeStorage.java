package com.isc.barcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class BarcodeStorage {
    private static final String REDIS_STORAGE_KEY = "barcodes";
    private final JedisPool jedisPool;

    @Autowired
    public BarcodeStorage(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public boolean add(String barcode) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.sadd(REDIS_STORAGE_KEY, barcode) == 1L;
        } catch (Exception exception) {
            throw new RuntimeException(exception); // handle someplace else
        }
    }

    public boolean isPresent(String barcode) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.sismember(REDIS_STORAGE_KEY, barcode);
        } catch (Exception exception) {
            throw new RuntimeException(exception); // handle someplace else
        }
    }
}
