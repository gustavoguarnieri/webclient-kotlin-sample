package com.example.webclientconsumerkotlinsample.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient? {
        val client = HttpClient.create()
            .responseTimeout(Duration.ofSeconds(RESPONSE_TIME_OUT_IN_SECONDS))
        return builder
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .clientConnector(ReactorClientHttpConnector(client))
            .build()
    }

    companion object {
        const val RESPONSE_TIME_OUT_IN_SECONDS = 30L
    }
}
