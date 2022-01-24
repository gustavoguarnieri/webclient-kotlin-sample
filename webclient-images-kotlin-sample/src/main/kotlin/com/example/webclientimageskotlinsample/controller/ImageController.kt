package com.example.webclientimageskotlinsample.controller

import com.example.webclientimageskotlinsample.model.response.ImageResponse
import com.example.webclientimageskotlinsample.service.ImageService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products/{productId}/images")
class ImageController(
    val imageService: ImageService
) {

    private val log = KotlinLogging.logger {}

    @GetMapping
    private fun getImages(@PathVariable productId: Long): List<ImageResponse> {
        log.info("getImages: getting list of images")
        return imageService.getImages(productId).also {
            log.info("getImages: finished getting list of images")
        }
    }
}
