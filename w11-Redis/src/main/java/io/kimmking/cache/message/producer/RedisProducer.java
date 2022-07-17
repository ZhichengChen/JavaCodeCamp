package io.kimmking.cache.message.producer;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisProducer {
  RedisTemplate redisTemplate;
  public void publish(String message) {
    redisTemplate.convertAndSend("message", message);
  }
}
