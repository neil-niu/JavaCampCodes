* **.（必做）**改造自定义 RPC 的程序，提交到 GitHub：尝试将服务端写死查找接口实现类变成泛型和反射；
```plain
package io.kimmking.rpcfx.server;

package io.kimmking.rpcfx.demo.provider;
该package下 DemoResolver 查找实现类使用get bean的方式获取
@Override
public Object resolve(String serviceClass) {
    return this.applicationContext.getBean(serviceClass);
}



```



* 尝试将客户端动态代理改成 AOP，添加异常处理；
```plain
package io.kimmking.rpcfx.client;

类Rpcfx下 静态方法 createWithByteCode 为实现的 字节码生成的方式创建代理。
```



* 尝试使用 Netty+HTTP 作为 client 端传输方式。
```plain
package io.kimmking.rpcfx.client;
下新增 NettyHttpClient 和NettyHttpClientOutboundHandler
用来使用netty来作为client端传输方式：
目前还有一点问题，正在调试。
```


