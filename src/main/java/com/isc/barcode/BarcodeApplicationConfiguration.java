package com.isc.barcode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
@ComponentScan("com.isc.barcode")
public class BarcodeApplicationConfiguration {

    @Bean
    public JedisPool jedisPool(@Value("${redis.host:localhost}") String host, @Value("${redis.port:6379}") Integer port) {
        return new JedisPool(host, port);
    }
}
