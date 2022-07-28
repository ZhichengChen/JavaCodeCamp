1、下载 kafka

https://archive.apache.org/dist/kafka/2.7.2/kafka_2.12-2.7.2.tgz

2、后台运行 zookeeper

```sh
nohup bin/zookeeper-server-start.sh config/zookeeper.properties &
```

3、运行 kafka

```sh
bin/kafka-server-start.sh config/server.properties 
```

4、列出所有 topic

```sh
bin/kafka-topics.sh --zookeeper localhost:2181 --list
```

5、创建一个 topic 名字为 testk，partitions 分片是 3 个，副本因子是 1 个，每个分片有一个副本

```sh
bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic testk --partitions 3 --replication-factor 1
```

6、展示 topic testk 的详细信息

```sh
bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic testk
```

7、创建一个消费者，从当前 broker 9092 端口上从头订阅 topic testk 上面的消息

```sh
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic testk
```

8、创建一个生产者

```sh
bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic testk
```

9、简单测试 throughput 限流

生产者

```sh
bin/kafka-producer-perf-test.sh --topic testk --num-records 100000 --record-size 1000 --throughput 200000 --producer-props bootstrap.servers=localhost:9092
```

消费者

```sh
bin/kafka-consumer-perf-test.sh --bootstrap-server localhost:9092 --topic testk --fetch-size 1048576 --messages 100000 --threads 1
```

10、启动三个 kafka

```sh
./bin/kafka-server-start.sh kafka9001.properties
./bin/kafka-server-start.sh kafka9002.properties
./bin/kafka-server-start.sh kafka9003.properties
```

11、执行操作测试

创建带有副本的 topic 3 个 partitions 2 个副本

```sh
bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic test32 --partitions 3 --replication-factor 2
bin/kafka-console-consumer.sh --bootstrap-server localhost:9001 --topic test32 --from-beginning
bin/kafka-console-producer.sh --bootstrap-server localhost:9003 --topic test32
```

12、简单测试 throughput 限流

生产者

```sh
bin/kafka-producer-perf-test.sh --topic test32 --num-records 100000 --record-size 1000 --throughput 200000 --producer-props bootstrap.servers=localhost:9002
```

消费者

```sh
bin/kafka-consumer-perf-test.sh --bootstrap-server localhost:9002 --topic test32 --fetch-size 1048576 --messages 100000 --threads 1
```









