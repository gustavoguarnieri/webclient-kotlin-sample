package com.example.webclientconsumerkotlinsample.controller

import com.example.webclientconsumerkotlinsample.model.response.ImageResponse
import com.example.webclientconsumerkotlinsample.service.ImageService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products/{productId}/images")
class ImageController(
    private val imageService: ImageService
) {

    private val log = KotlinLogging.logger {}

    @GetMapping("/sync")
    private fun getSyncImages(@PathVariable productId: Long): List<ImageResponse> {
        log.info("getSyncImages: getting sync list of images, productId=$productId")
        return imageService.getSyncImages(productId).also {
            log.info("getSyncImages: finished sync getting list of images, productId=$productId")
        }
    }

    @GetMapping("/async")
    private fun getAsyncImages(@PathVariable productId: Long): List<ImageResponse> {
        log.info("getAsyncImages: getting async list of images")
        return imageService.getAsyncImages(productId).also {
            log.info("getAsyncImages: finished async getting list of images")
        }
    }

    @GetMapping("/async/coroutines")
    suspend fun getAsyncCoroutinesImages(@PathVariable productId: Long): List<ImageResponse> {
        log.info("getAsyncCoroutinesImages: getting async coroutines list of images")
        return imageService.getAsyncCoroutinesImages(productId).also {
            log.info("getAsyncCoroutinesImages: finished async coroutines getting list of images")
        }
    }
}