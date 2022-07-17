package io.kimmking.cache.message.consumer;

import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

@Component
public class RedisConsumer {

  public void onMessage(Message message, byte[] pattern) {
    System.out.println(message.toString());
  }
}
