package com.example.webclientconsumerkotlinsample.service

import com.example.webclientconsumerkotlinsample.client.WebClientImagesKotlinSampleClient
import com.example.webclientconsumerkotlinsample.constants.ErrorMessages.Companion.IMAGES_NOT_FOUND
import com.example.webclientconsumerkotlinsample.exceptions.NotFoundException
import com.example.webclientconsumerkotlinsample.model.response.ImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ImageService(
    private val webClientImageKotlinSampleClient: WebClientImagesKotlinSampleClient,
    private val productService: ProductService
) {

    fun getSyncImages(productId: Long): List<ImageResponse> {
        val product = productService.getProduct(productId).block()

        val images =
            webClientImageKotlinSampleClient.getImages(productId).collectList().block() ?: throw NotFoundException(
                IMAGES_NOT_FOUND
            )
        images.forEach { image -> image.product = product }
        return images
    }

    fun getAsyncImages(productId: Long): List<ImageResponse> {
        val product = productService.getProduct(productId)
        val images = webClientImageKotlinSampleClient.getImages(productId).collectList()

        return Mono.zip(images, product)
            .map { tuple ->
                tuple.t1.forEach { image -> image.product = tuple.t2 }
                tuple.t1
            }.block() ?: listOf()
    }

    suspend fun getAsyncCoroutinesImages(productId: Long): List<ImageResponse> {
        return coroutineScope {
            val product = async(Dispatchers.IO) { productService.getSuspendProduct(productId) }
            val images = async(Dispatchers.IO) { webClientImageKotlinSampleClient.getSuspendImages(productId) }

            images.await().forEach { image -> image.product = product.await() }

            images.await()
        }
    }
}
