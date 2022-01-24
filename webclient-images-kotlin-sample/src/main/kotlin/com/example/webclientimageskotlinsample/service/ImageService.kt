package com.example.webclientimageskotlinsample.service

import com.example.webclientimageskotlinsample.model.entities.ImageEntity
import com.example.webclientimageskotlinsample.model.response.ImageResponse
import com.example.webclientimageskotlinsample.repositories.ImageRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class ImageService(
    private val repository: ImageRepository,
    private val modelMapper: ModelMapper
) {

    fun getImages(productId: Long): List<ImageResponse> {
        Thread.sleep(TIME_TO_SLEEP_IN_MILLISECONDS)

        return repository.findByProductId(productId).map { convertToDto(it) }
    }

    private fun convertToDto(productEntity: ImageEntity): ImageResponse {
        return modelMapper.map(productEntity, ImageResponse::class.java)
    }

    companion object {
        private const val TIME_TO_SLEEP_IN_MILLISECONDS = 5_000L
    }
}
