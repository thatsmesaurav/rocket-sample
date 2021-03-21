package com.rocketbnk.kafkaproducer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CandidateDataHandler {

    private final Logger logger = LoggerFactory.getLogger(CandidateDataHandler.class);
    private final KafkaTemplate<Integer, String> kafkaTemplate;

    public CandidateDataHandler(
            KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<ServerResponse> handleData(ServerRequest request) {

        return request.bodyToMono(String.class)
                      .log(logger.getName())
                      .doOnNext(this::publishToKafka)
                      .then(ServerResponse.ok()
                                          .contentType(MediaType.TEXT_PLAIN)
                                          .bodyValue("Success")
                      );
    }

    private void publishToKafka(String message) {

        ListenableFuture<SendResult<Integer, String>> sendResult = kafkaTemplate.send("rocketbnk", message);
        sendResult.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onFailure(Throwable ex) {
                logger.info("Could not send the message to kafka");
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                logger.info("yay");
            }
        });

    }
}
