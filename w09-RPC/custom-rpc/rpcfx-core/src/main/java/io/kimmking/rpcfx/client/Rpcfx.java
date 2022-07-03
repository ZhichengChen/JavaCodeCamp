package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.ssl.SslContext;
import io.netty.util.CharsetUtil;
import java.net.URI;
import java.net.URISyntaxException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class Rpcfx {

  private static StringBuffer buf = new StringBuffer();

  static {
    ParserConfig.getGlobalInstance().addAccept("io.kimmking");
  }

  public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl,
      Router router, LoadBalancer loadBalance, Filter filter) {

    // 加filte之一

    // curator Provider list from zk
    List<String> invokers = new ArrayList<>();
    // 1. 简单：从zk拿到服务提供的列表
    // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）

    List<String> urls = router.route(invokers);

    String url = loadBalance.select(urls); // router, loadbalance

    return (T) create(serviceClass, url, filter);

  }

  public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {

    // 0. 替换动态代理 -> 字节码生成
    return (T) new CGLibProxy().createProxyObject(
        new RpcfxInvocationHandler(serviceClass, url, filters));

  }

  public static class RpcfxInvocationHandler implements InvocationHandler {

    public final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    private final Class<?> serviceClass;
    private final String url;
    private final Filter[] filters;

    public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url, Filter... filters) {
      this.serviceClass = serviceClass;
      this.url = url;
      this.filters = filters;
    }

    // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
    // int byte char float double long bool
    // [], data class

    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {

      // 加filter地方之二
      // mock == true, new Student("hubao");

      RpcfxRequest request = new RpcfxRequest();
      request.setServiceClass(this.serviceClass.getName());
      request.setMethod(method.getName());
      request.setParams(params);

      if (null != filters) {
        for (Filter filter : filters) {
          if (!filter.filter(request)) {
            return null;
          }
        }
      }

      RpcfxResponse response = post(request, url);

      // 加filter地方之三
      // Student.setTeacher("cuijing");

      // 这里判断response.status，处理异常
      // 考虑封装一个全局的RpcfxException

      return JSON.parse(response.getResult().toString());
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
      String reqJson = JSON.toJSONString(req);
      System.out.println("req json: " + reqJson);

      // 1.可以复用client
      // 2.尝试使用httpclient或者netty client
      // Configure the client.
      EventLoopGroup group = new NioEventLoopGroup();
      try {
        Bootstrap b = new Bootstrap();
        b.group(group)
            .channel(NioSocketChannel.class)
            .handler(new HttpSnoopClientInitializer(null));
        ;

        URI uri = new URI(url);
        // Make the connection attempt.
        Channel ch = b.connect(uri.getHost(), uri.getPort()).sync().channel();

        // Prepare the HTTP request.
        HttpRequest request = new DefaultFullHttpRequest(
            HttpVersion.HTTP_1_1, HttpMethod.GET, uri.getRawPath(), Unpooled.EMPTY_BUFFER);

        // Send the HTTP request.
        ch.writeAndFlush(request);

        // Wait for the server to close the connection.
        ch.closeFuture().sync();

        return JSON.parseObject(buf.toString(), RpcfxResponse.class);

      } catch (URISyntaxException e) {
        throw new RuntimeException(e);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        // Shut down executor threads to exit.
        group.shutdownGracefully();
      }
    }
  }

  static class HttpSnoopClientHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
      if (msg instanceof HttpResponse) {
        HttpResponse response = (HttpResponse) msg;

        System.err.println("STATUS: " + response.status());
        System.err.println("VERSION: " + response.protocolVersion());
        System.err.println();

        if (!response.headers().isEmpty()) {
          for (CharSequence name : response.headers().names()) {
            for (CharSequence value : response.headers().getAll(name)) {
              System.err.println("HEADER: " + name + " = " + value);
            }
          }
          System.err.println();
        }

        if (HttpUtil.isTransferEncodingChunked(response)) {
          System.err.println("CHUNKED CONTENT {");
        } else {
          System.err.println("CONTENT {");
        }
      }
      if (msg instanceof HttpContent) {
        HttpContent content = (HttpContent) msg;

        buf.append((content.content().toString(CharsetUtil.UTF_8)));
        System.err.flush();

        if (content instanceof LastHttpContent) {
          System.err.println("} END OF CONTENT");
          ctx.close();
        }
      }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      cause.printStackTrace();
      ctx.close();
    }
  }

  static class HttpSnoopClientInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public HttpSnoopClientInitializer(SslContext sslCtx) {
      this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) {
      ChannelPipeline p = ch.pipeline();

      // Enable HTTPS if necessary.
      if (sslCtx != null) {
        p.addLast(sslCtx.newHandler(ch.alloc()));
      }

      p.addLast(new HttpClientCodec());

      // Remove the following line if you don't want automatic content decompression.
      p.addLast(new HttpContentDecompressor());

      // Uncomment the following line if you don't want to handle HttpContents.
      //p.addLast(new HttpObjectAggregator(1048576));

      p.addLast(new HttpSnoopClientHandler());
    }
  }
}
