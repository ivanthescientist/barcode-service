package com.isc.barcode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import redis.embedded.RedisServer;

import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestConfiguration {

    @Bean(destroyMethod = "stop")
    public RedisServer redisServer(@Value("${redis.port:6379}") Integer port) throws IOException {
        RedisServer redisServer = new RedisServer(port);
        redisServer.start();
        return redisServer;
    }
}
