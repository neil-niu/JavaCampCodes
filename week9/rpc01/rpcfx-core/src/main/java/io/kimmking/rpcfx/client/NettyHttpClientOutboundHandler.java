package io.kimmking.rpcfx.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class NettyHttpClientOutboundHandler  extends ChannelInboundHandlerAdapter {
    
    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("neil test channel Active....");
        
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("channel read called...msg : " + msg.toString());
        ByteBuf buf = (ByteBuf) msg;

        System.out.println("netty client received msg: " + buf.toString(Charset.defaultCharset()));
    }
}