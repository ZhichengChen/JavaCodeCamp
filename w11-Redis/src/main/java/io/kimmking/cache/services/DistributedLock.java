package io.kimmking.cache.services;

import org.springframework.stereotype.Service;

@Service
public interface DistributedLock {
  String lock(String key);
  boolean unlock(String key, String value);
}
