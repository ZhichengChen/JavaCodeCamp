package io.github.kimmking.gateway.filter;

import okhttp3.Headers;

public interface HttpRequestFilter {

  Headers filter(Headers headers);

}
