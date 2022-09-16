package io.github.kimmking.gateway.inbound;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.internal.http2.Header;

/**
 * Http Test
 * 写一段代码，使用 HttpClient 或 OkHttp 访问  http://localhost:8801 ，代码提交到 GitHub。
 */
public class OKHttpInboundServer {

  private List<String> proxyServers;

  public OKHttpInboundServer(List<String> proxyServers) {
    this.proxyServers = proxyServers;
  }

  public Response run(Headers headers) throws Exception {
    OkHttpClient client = new OkHttpClient().newBuilder()
        .build();

    System.out.println(headers);
    Collections.shuffle(proxyServers);
    Builder builder = new Builder();


    for (String header:headers.names()) {
      builder.addHeader(header, headers.get(header));
    }

    Request request =
        builder.url(proxyServers.get(0))
            .method("GET", null)
            .build();

    try {
      return client.newCall(request).execute();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
