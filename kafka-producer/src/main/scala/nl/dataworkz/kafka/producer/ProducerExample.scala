package nl.dataworkz.kafka.producer

import java.util.{Date, Properties}

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.util.Random

object ProducerExample extends App {
  val events = args(0).toInt
  val topic = args(1)
  val brokers = args(2)
  val rnd = new Random()
  val properties = new Properties()


  properties.put("bootstrap.servers", brokers)
  properties.put("client.id", "ProducerExample")
  properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](properties)
  val t = System.currentTimeMillis()
  for (nEvents <- Range(0, events)) {
    val runtime = new Date().getTime()
    val rndNumber = "MyRandomTestNumber." + rnd.nextInt(255)
    val msg = runtime + "," + nEvents + ",test-message," + rndNumber
    val data = new ProducerRecord[String, String](topic, rndNumber, msg)

    //async
    //producer.send(data, (m,e) => {})
    //sync
    producer.send(data)
  }

  System.out.println("sent per second: " + events * 1000 / (System.currentTimeMillis() - t))
  producer.close()

}
