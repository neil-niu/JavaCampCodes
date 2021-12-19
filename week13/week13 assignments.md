**1.（必做）**搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作，将代码提交到 github。

# 搭建三节点集群：

还是在ubuntu环境下，运行zookeeper：

bin/zookeeper-server-start.sh config/zookeeper.properties

运行三个 kafka节点：

 bin/kafka-server-start.sh config/kafka9001.properties

 bin/kafka-server-start.sh config/kafka9002.properties

 bin/kafka-server-start.sh config/kafka9003.properties

如果kafka集群的节点如果不正常 down机，则再启动之前需要清空相关日志目录：

创建带有副本的 topic： bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic test32 --partitions 3 --replication-factor 2

# 
# 

# 测试相关功能：

 bin/kafka-console-producer.sh --bootstrap-server 192.168.29.137:9003 --topic test32

bin/kafka-console-consumer.sh --bootstrap-server 192.168.29.137:9001 --topic test32 --from-beginning

# 性能测试：

```plain
bin/kafka-producer-perf-test.sh --topic test32 --num-records 100000 --record-size 1000 --throughput 200000 --producer-props bootstrap.servers=192.168.29.137:9002
```
性能结果：
48689 records sent, 9737.8 records/sec (9.29 MB/sec), 1893.9 ms avg latency, 2894.0 ms max latency.

100000 records sent, 12594.458438 records/sec (12.01 MB/sec), 1994.12 ms avg latency, 2894.00 ms max latency, 1968 ms 50th, 2810 ms 95th, 2864 ms 99th, 2892 ms 99.9th.


```plain
bin/kafka-consumer-perf-test.sh --bootstrap-server 192.168.29.137:9002 --topic test32 --fetch-size 1048576 --messages 100000 --threads 1

性能测试结果：
start.time, end.time, data.consumed.in.MB, MB.sec, data.consumed.in.nMsg, nMsg.sec, rebalance.time.ms, fetch.time.ms, fetch.MB.sec, fetch.nMsg.sec
2021-12-20 00:33:47:163, 2021-12-20 00:33:52:396, 95.7632, 18.2999, 100416, 19188.9929, 2001, 3232, 29.6297, 31069.3069
```
