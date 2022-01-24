package com.example.webclientconsumerkotlinsample.service

import com.example.webclientconsumerkotlinsample.client.WebClientProductsKotlinSampleClient
import com.example.webclientconsumerkotlinsample.model.response.ProductResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProductService(
    private val webClientProductsKotlinSampleClient: WebClientProductsKotlinSampleClient
) {

    fun getProducts(): Flux<ProductResponse> {
        return webClientProductsKotlinSampleClient.getProducts()
    }

    fun getProduct(productId: Long): Mono<ProductResponse> {
        return webClientProductsKotlinSampleClient.getProduct(productId)
    }

    suspend fun getSuspendProduct(productId: Long): ProductResponse? {
        return webClientProductsKotlinSampleClient.getSuspendProduct(productId)
    }
}
