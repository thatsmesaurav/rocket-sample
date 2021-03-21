package com.rocketbnk.kafkaconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaConsumerApplication {

  private final Logger logger = LoggerFactory.getLogger(KafkaConsumerApplication.class);
  private final ReactiveMongoOperations mongoOperations;

  public KafkaConsumerApplication(ReactiveMongoOperations mongoOperations) {
    this.mongoOperations = mongoOperations;
  }

  public static void main(String[] args) {
    SpringApplication.run(KafkaConsumerApplication.class, args);
  }

  @KafkaListener(id = "consumer-rocketbnk", topics = "rocketbnk")
  public void listen(String message) {

    mongoOperations
        .save(new MongoMessage(message))
        .doOnError(throwable -> logger.error("Error", throwable))
        .subscribe();

    logger.info(message);
  }
}
