package com.ncu.graduation.conf;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ：grh
 * @date ：Created in 2020/4/17 6:16
 * @description： jedis配置
 * @modified By：
 * @version: 0.0.1
 */

@Slf4j
@Configuration
public class JedisConfig extends CachingConfigurerSupport {

  private Logger logger = LoggerFactory.getLogger(JedisConfig.class);

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Value("${spring.redis.password}")
  private String password;

  @Value("${spring.redis.timeout}")
  private int timeout;

  @Value("${spring.redis.jedis.pool.max-active}")
  private int maxActive;

  @Value("${spring.redis.jedis.pool.max-idle}")
  private int maxIdle;

  @Value("${spring.redis.jedis.pool.min-idle}")
  private int minIdle;

  @Value("${spring.redis.jedis.pool.max-wait}")
  private long maxWaitMillis;


  @Bean
  public JedisPool jedisPoolFactory() {
    System.out.println("JedisPool注入开始...");
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxIdle(maxIdle);
    jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
    // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
    jedisPoolConfig.setBlockWhenExhausted(true);
    // 是否启用pool的jmx管理功能, 默认tru
    jedisPoolConfig.setJmxEnabled(true);
    JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout,password);
    System.out.println("JedisPool注入成功...");
    return jedisPool;
  }

}
