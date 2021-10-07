base on the netty-gateway demo project  from [https://github.com/JavaCourse00/JavaCourseCodes/tree/main/02nio/nio02](https://github.com/JavaCourse00/JavaCourseCodes/tree/main/02nio/nio02)

整体代码结构：

```plain
io.github.neil.gateway.backend   对应的具体的后端业务服务器：HttpServer02，和HttpServer03
io.github.neil.gateway.client    对应的具体的客户端程序，分别用不同的框架实现
io.github.neil.gateway.filter    对应gateway不同实现的过滤器。
io.github.neil.gateway.router    对应gateway不同实现的路由器。
io.github.neil.gateway.inbound   网关入站流量处理
io.github.neil.gateway.outbound  网关出站流量处理
NettyServerApplication           网关入口程序
```



1.（必做）整合你上次作业的 httpclient/okhttp；

```plain
      io.github.neil.gateway.client.httpclient:
      该包下边对应的分别是httpclient，okhttp实现的 网络client端程序
          HttpClientUtils
          OkHttpUtils
      
```

 2.（选做）使用 netty 实现后端 http 访问（代替上一步骤）

```plain
      io.github.neil.gateway.client.httpclient:
      该包新增netty实现的网络client端程序
          NettyHttpClient
      
```

 3.（必做）实现过滤器。

```plain
 io.github.neil.gateway.filter   
      实现 BodyHttpResponseFilter 进行 包身部分内容的过滤处理
      将内容中设计到hello部分替换为hi，再转发给业务服务器处理
      
同时过滤器也可以考虑一下安全相关的场景，
加密流量发给网关，网关可以实现一个解密的过滤器，转换为明文后再交给业务服务器处理。
```

 4.（选做）实现路由。

```plain
io.github.neil.gateway.router
       WeightHttpEndpointRouter  实现一个加权的R-R路由器
       
大致逻辑：   给每个后端业务服务器分配相应的权重，接到路由器过来的网址列表，按照分配权重进行访问，如果不在列表内，则还按照之前的随机路由来选择访问。
```

