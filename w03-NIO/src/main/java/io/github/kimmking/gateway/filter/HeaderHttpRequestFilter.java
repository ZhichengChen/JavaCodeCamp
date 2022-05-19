package io.github.kimmking.gateway.filter;

import okhttp3.Headers;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public Headers filter(Headers headers) {
        return headers.newBuilder().set("xjava", "kimmking").build();
    }
}
