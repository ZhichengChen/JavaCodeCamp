package io.kimmking.cache.services;

import org.springframework.stereotype.Service;

@Service
public interface Stock {
  void initStock(Long count);
  void reduceStock(Long count);
}
