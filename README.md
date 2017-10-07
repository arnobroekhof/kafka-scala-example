# kafka-scala-example

## requirements

setup Kafka server and zookeeper with docker and create topic kafka-test

```bash
docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 --name kafka spotify/kafka
docker exec -it kafka /opt/kafka_2.11-0.10.1.0/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic kafka-test
```


## Running Kafka Consumer
```bash
mvn clean package
java -jar kafka-consumer/target/kafka-consumer-LOCAL.jar localhost:9092 MyConsumerGroup kafka-test 10 0
```

## Kafka Producer
```bash
mvn clean package

```