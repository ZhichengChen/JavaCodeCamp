package io.kimmking.cache.services.impl;

import io.kimmking.cache.services.Stock;
import org.springframework.data.redis.core.RedisTemplate;

public class StockImpl implements Stock {

  RedisTemplate redisTemplate;

  private final static String STOCK_KEY = "stock";

  @Override
  public void initStock(Long count) {
    redisTemplate.opsForValue().set(STOCK_KEY, count);
  }

  @Override
  public void reduceStock(Long count) {
    redisTemplate.opsForValue().increment(STOCK_KEY, -1);
  }
}
