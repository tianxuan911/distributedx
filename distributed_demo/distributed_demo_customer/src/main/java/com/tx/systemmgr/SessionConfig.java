package com.tx.systemmgr;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import redis.clients.jedis.JedisShardInfo;

/**
 * Created by tianxuan on 16/4/4.
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=7200)
public class SessionConfig {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisShardInfo shardInfo = new JedisShardInfo("192.168.99.100",9379,"docker-redis");
        return new JedisConnectionFactory(shardInfo);
    }
}
