package com.rocketbnk.kafkaproducer.config;

import com.rocketbnk.kafkaproducer.handler.CandidateDataHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HandlerConfig {

    @Bean
    public RouterFunction<ServerResponse> route(CandidateDataHandler candidateDataHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/postData")
                                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                       candidateDataHandler::handleData);
    }
}
