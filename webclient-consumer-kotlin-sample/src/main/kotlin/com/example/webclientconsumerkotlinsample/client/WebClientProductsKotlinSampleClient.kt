package com.example.webclientconsumerkotlinsample.client

import com.example.webclientconsumerkotlinsample.model.response.ProductResponse
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class WebClientProductsKotlinSampleClient(
    private val webClient: WebClient
) {

    fun getProducts(): Flux<ProductResponse> {
        return webClient
            .get()
            .uri(BASE_PATH)
            .retrieve()
            .bodyToFlux(ProductResponse::class.java)
    }

    fun getProduct(productId: Long): Mono<ProductResponse> {
        return webClient
            .get()
            .uri("$BASE_PATH/{productId}", productId)
            .retrieve()
            .bodyToMono(ProductResponse::class.java)
    }

    suspend fun getSuspendProduct(productId: Long): ProductResponse? {
        return webClient
            .get()
            .uri("$BASE_PATH/{productId}", productId)
            .retrieve()
            .bodyToMono(ProductResponse::class.java)
            .awaitSingle()
    }

    companion object {
        const val BASE_PATH = "http://localhost:9082/api/v1/products"
    }
}
