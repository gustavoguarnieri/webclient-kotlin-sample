package com.example.webclientconsumerkotlinsample.client

import com.example.webclientconsumerkotlinsample.model.response.ImageResponse
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Component
class WebClientImagesKotlinSampleClient(
    private val webClient: WebClient
) {

    fun getImages(productId: Long): Flux<ImageResponse> {
        return webClient
            .get()
            .uri(BASE_PATH, productId)
            .retrieve()
            .bodyToFlux(ImageResponse::class.java)
    }

    suspend fun getSuspendImages(productId: Long): List<ImageResponse> {
        return webClient
            .get()
            .uri(BASE_PATH, productId)
            .retrieve()
            .bodyToFlux(ImageResponse::class.java)
            .collectList()
            .awaitSingle()
    }

    companion object {
        const val BASE_PATH = "http://localhost:9081/api/v1/products/{productId}/images"
    }
}
