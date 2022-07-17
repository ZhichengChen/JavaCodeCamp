package io.kimmking.cache.services.impl;

import io.kimmking.cache.services.DistributedLock;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class DistributedLockImpl implements DistributedLock {

  @Autowired
  Jedis jedis;

  private final static  String unLockScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

  public String lock(String key) {
    String uuid = UUID.randomUUID().toString();
    String result = jedis.set( key, uuid, "NX", "PX", System.currentTimeMillis() + 30 * 1000);
    if ("OK".equals(result)) {
      return uuid;
    }
    return null;
  }
  public boolean unlock(String key, String value) {
    Object result = jedis.eval(unLockScript, Collections.singletonList(key), Collections.singletonList(value));
    if (((Long)1L).equals(result)) {
      return true;
    }
    return false;
  }
}
