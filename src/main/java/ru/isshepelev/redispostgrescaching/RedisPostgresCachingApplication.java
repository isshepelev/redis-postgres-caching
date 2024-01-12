package ru.isshepelev.redispostgrescaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisPostgresCachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisPostgresCachingApplication.class, args);
    }

}
