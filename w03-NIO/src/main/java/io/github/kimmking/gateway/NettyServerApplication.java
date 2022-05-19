package io.github.kimmking.gateway;

import io.github.kimmking.gateway.filter.HeaderHttpRequestFilter;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.inbound.OKHttpInboundServer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import okhttp3.Headers;
import okhttp3.Response;

public class NettyServerApplication {

  public static void main(String[] args) throws Exception {
    String proxyServers =
        System.getProperty("proxyServers", "http://localhost:8801,http://localhost:8802");
    ServerSocket serverSocket = new ServerSocket(8888);
    OKHttpInboundServer server = new OKHttpInboundServer(Arrays.asList(proxyServers.split(",")));
    while (true) {
      Socket socket = serverSocket.accept();
      service(socket, server);
    }
  }

  private static void service(Socket socket, OKHttpInboundServer server) throws Exception {
    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
    Headers.Builder headerBuilder = new Headers.Builder();
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    String inputLine;
    while (!"".equals(inputLine = in.readLine())) {
      if (inputLine == null) {
        break;
      }
      if (inputLine.split(":").length > 1) {
        headerBuilder.set(inputLine.split(":")[0], inputLine.split(":")[1]);
      }
    }
    Headers requestHeaders = headerBuilder.build();
    HttpRequestFilter requestFilter = new HeaderHttpRequestFilter();
    Response response = server.run(requestFilter.filter(requestHeaders));
    Headers headers = response.headers();
    printWriter.println("HTTP/1.1 200 OK" + response.code() + " " + response.message());
    printWriter.print(headers);
    String body = response.body().string();
    printWriter.println();
    printWriter.write(body);
    printWriter.close();
    socket.close();
    in.close();
  }
}
