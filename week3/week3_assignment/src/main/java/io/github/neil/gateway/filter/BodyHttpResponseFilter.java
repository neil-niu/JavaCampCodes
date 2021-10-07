package io.github.neil.gateway.filter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.FullHttpResponse;

import java.nio.charset.Charset;

public class BodyHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        final String stringSrc = "hello";
        final String stringdst = "hi";

        // System.out.println("responses content: " + response.content().toString(Charset.defaultCharset()));

        String oldContent = response.content().toString(Charset.defaultCharset());
        String newContent = oldContent.replaceAll(stringSrc, stringdst);

        // System.out.println("responses new content : " + newContent);

        byte[]bytes = newContent.getBytes(Charset.defaultCharset());
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);

        //System.out.println("new content readable bytes num : " + buf.readableBytes());
        response.headers().setInt("Content-Length", buf.readableBytes());
        response.content().clear();
        response.content().writeBytes(buf);
    }
}
