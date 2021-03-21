package com.rocketbnk.kafkaconsumer;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoMessage {

  private final String message;

  public MongoMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
