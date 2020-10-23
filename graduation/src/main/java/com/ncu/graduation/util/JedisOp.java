package com.ncu.graduation.util;

import com.ncu.graduation.conf.JedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ：grh
 * @date ：Created in 2020/4/17 6:18
 * @description：Jedis操作redis整合
 * @modified By：
 * @version: 0.0.1
 */

@Slf4j
@Component
public class JedisOp {

  private static Logger logger = LoggerFactory.getLogger(JedisOp.class);

  @Autowired
  private JedisPool jedisPool;

  /**
   * 向Redis中存值，永久有效
   */
  public String set(String key, String value) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      return jedis.set(key, value);
    } catch (Exception e) {
      e.printStackTrace();
      return "0";
    } finally {
      returnResource(jedis);
    }
  }

  /**
   * 向Redis中存值，设置一天后失效
   */
  public String setex(String key, String value) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();

      return jedis.setex(key, 86400, value);
    } catch (Exception e) {
      e.printStackTrace();

      return "0";
    } finally {
      returnResource( jedis);
    }
  }

  /**
   * 更新redis中的存储时间
   */
  public Long expire(String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();

      return jedis.expire(key, 86400);
    } catch (Exception e) {
      e.printStackTrace();

      return 0L;
    } finally {
      returnResource( jedis);
    }
  }

  /**
   * 根据传入Key获取指定Value
   */
  public String get(String key) {
    Jedis jedis = null;
    String value;
    try {
      jedis = jedisPool.getResource();
      value = jedis.get(key);
    } catch (Exception e) {
      e.printStackTrace();

      return "0";
    } finally {
      returnResource( jedis);
    }
    return value;
  }

  /**
   * 校验Key值是否存在
   */
  public Boolean exists(String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      return jedis.exists(key);
    } catch (Exception e) {
      e.printStackTrace();

      return false;
    } finally {
      returnResource( jedis);
    }
  }

  /**
   * 删除指定Key-Value
   */
  public Long delete(String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      return jedis.del(key);
    } catch (Exception e) {
      e.printStackTrace();

      return 0L;
    } finally {
      returnResource(jedis);
    }
  }

  // 以上为常用方法，更多方法自行百度

  /**
   * 释放连接
   */
  private void returnResource(Jedis jedis) {
    if (jedis != null) {
      jedis.close();
    }
  }
}

