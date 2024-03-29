package com.chenzhicheng.maven;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.rtsp.RtspHeaderValues.KEEP_ALIVE;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.ReferenceCountUtil;

public class HttpHandler extends ChannelInboundHandlerAdapter{

@Override
public void channelReadComplete(ChannelHandlerContext ctx) { ctx.flush();}
    
@Override
public void channelRead(ChannelHandlerContext ctx, Object msg) {
    try {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        String uri = fullRequest.uri();
        if (uri.contains("/test")) {
            handlerTest(fullRequest, ctx);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        ReferenceCountUtil.release(msg);
    }
}

private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
    FullHttpResponse response = null;
    try {
        String value = "hello, chenzhicheng";


        response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes()));
        response.headers().set("Content-Type", "application/json");
        response.headers().setInt("Content-Length", response.content().readableBytes());
    } catch(Exception e) {
        System.out.println("处理出错："+e.getMessage());
        response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
    } finally {
        if (fullRequest != null) {
            if (!HttpUtil.isKeepAlive(fullRequest)) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }
        }
    }
}
}
