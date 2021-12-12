1、（必做）配置 redis 的主从复制，sentinel 高可用，Cluster 集群。

提交如下内容到 GitHub：

1）config 配置文件，

2）启动和操作、验证集群下数据读写的命令步骤

#### 

#### redis 主从复制：

1. 从本机 /etc/redis/redis.conf中复制 配置文件为 ：
      redis6379neil.conf 

      redis6380neil.conf

2. 启动 redis-server redis6379neil.conf
3. 启动 redis-server redis6380neil.conf
4. 2,3步骤为两个独立的redis server 启动，都为master 模式， 添加数据互相不影响。
5. 将6380 redis server变为从模式： 
      redis-cli -p 6380 

      在client端命令行中，运行 slaveof 127.0.0.1 6379

      变为从模式后 从数据库不能再写入

6. 6379主数据库有写入，就会同步到 6380从数据里头。
#### sentinel 高可用：

1. 运行两个sentinel分别监视 主从两个redis server
      redis-server sentinel0.conf --sentinel

      redis-server sentinel1.conf --sentinel

2. 杀掉 主 redis server ， 则sentinel会将从 6380 redis server 设置为主模式
3. 再次启动 之前的 6379 redis server，则会切换为从模式。

#### cluster 集群：

建立两个虚拟机：

centos 6u3： ip：192.168.29.136

ubuntu 16     ip:   192.168.29.137

```plain
使用两台虚拟机的7000,7001,7002端口搭建三主三从的Redis Cluster
    其中192.168.29.137主机三个端口启动的Redis Server为主节点
    192.168.29.136主机三个端口启动的Redis Server为从节点
```
 
分别启动 三主 和三从 ：

redis-server redis_7000.conf

redis-server redis_7001.conf

redis-server redis_7002.conf

127.0.0.1:7000> CLUSTER nodes   目前只有当前节点信息

127.0.0.1:7000> CLUSTER info       查询 集群状态为fail

执行meet 操作：

redis-cli -p 7000 cluster meet 127.0.0.1 7001

redis-cli -p 7000 cluster meet 127.0.0.1 7002

redis-cli -p 7000 cluster nodes

e859e218b3a90341a8fb1c9f9dc83a87f2f52537 127.0.0.1:7002@17002 master - 0 1639319862241 0 connected

8338c3dd5037ee0e37303de63f8e461af98a5cc0 127.0.0.1:7001@17001 master - 0 1639319863248 1 connected

d98325be9924429135d0ecaf0e2ebd8ed2fdc95c 127.0.0.1:7000@17000 myself,master - 0 1639319860000 2 connected

显示有三个节点信息：

对于远程节点执行meet操作：

redis-cli -p 7000 cluster meet 192.168.29.136 7000

redis-cli -p 7000 cluster meet 192.168.29.136 7001

redis-cli -p 7000 cluster meet 192.168.29.136 7002

在添加远程节点的时候有问题，执行meet不成功：

69c629f9d827bcd26092b2d3d69838b643509d75 192.168.29.136:7000@17000 handshake - 1639320821415 0 0 connected

d98325be9924429135d0ecaf0e2ebd8ed2fdc95c 127.0.0.1:7000@17000 myself,master - 0 1639320823000 2 connected

8338c3dd5037ee0e37303de63f8e461af98a5cc0 127.0.0.1:7001@17001 master - 0 1639320827363 1 connected

e859e218b3a90341a8fb1c9f9dc83a87f2f52537 127.0.0.1:7002@17002 master - 0 1639320826356 0 connected

这个还需要再看一下。





