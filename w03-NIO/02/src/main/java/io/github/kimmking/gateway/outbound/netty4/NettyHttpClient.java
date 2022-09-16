package io.github.kimmking.gateway.outbound.netty4;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.github.kimmking.gateway.filter.HeaderHttpRequestFilter;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.util.CharsetUtil;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Reference https://www.jianshu.com/p/be644dca17cf, https://dev-tang.com/post/2020/06/httpclient-netty.html

public class NettyHttpClient {

  private List<String> backendUrls;

  HttpRequestFilter requestFilter = new HeaderHttpRequestFilter();
  HttpEndpointRouter router = new RandomHttpEndpointRouter();

  private ChannelHandlerContext parentCtx;

  private Bootstrap b;

  private ChannelFuture f;


  private String formatUrl(String backend) {
    return backend.endsWith("/") ? backend.substring(0, backend.length() - 1) : backend;
  }

  public NettyHttpClient(List<String> backends) {
    this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());
  }

  public void connect(String host, int port) throws Exception {
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      b = new Bootstrap();
      b.group(workerGroup);
      b.channel(NioSocketChannel.class);
      b.handler(new ChannelInitializer<SocketChannel>() {
        @Override
        public void initChannel(SocketChannel ch) throws Exception {
          //客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
          ch.pipeline().addLast(new HttpRequestEncoder());
          // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
          ch.pipeline().addLast(new HttpResponseDecoder());
          ch.pipeline().addLast(new OutputResultHandler());
        }
      });

      // Start the client.
      f = b.connect(host, port).sync();
      Channel channel = f.channel();
      channel.closeFuture().sync();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      workerGroup.shutdownGracefully();
    }

  }


  public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx,
      HttpRequestFilter filter) throws Exception {
    String backendUrl = router.route(this.backendUrls);
    final String url = backendUrl + fullRequest.uri();
    filter.filter(fullRequest, ctx);
    URI uri = new URI(url);

    requestFilter = filter;
    parentCtx = ctx;

    connect(uri.getHost(), uri.getPort());
  }

  private class OutputResultHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      FullHttpResponse fullResponse = null;
      if (msg instanceof HttpResponse) {
        HttpResponse response = (HttpResponse) msg;
      }
      if (msg instanceof HttpContent) {
        HttpContent content = (HttpContent) msg;
        ByteBuf buf = content.content();
        String body = buf.toString(CharsetUtil.UTF_8);
        buf.release();
        fullResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body.getBytes()));

        fullResponse.headers().set("Content-Type", "application/json");
        fullResponse.headers().setInt("Content-Length",body.length());

//        requestFilter.filter(fullResponse);
        parentCtx.write(fullResponse);
        parentCtx.flush();
        ctx.close();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    String proxyServers = System.getProperty("proxyServers","http://localhost:8801,http://localhost:8802");
    new NettyHttpClient(Arrays.asList(proxyServers.split(","))
    ).connect("localhost", 8801);
  }
}