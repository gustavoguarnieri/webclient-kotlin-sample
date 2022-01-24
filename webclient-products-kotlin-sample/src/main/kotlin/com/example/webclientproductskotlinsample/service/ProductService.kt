package com.example.webclientproductskotlinsample.service

import com.example.webclientproductskotlinsample.constants.ErrorMessages.Companion.PRODUCT_NOT_FOUND
import com.example.webclientproductskotlinsample.exceptions.NotFoundException
import com.example.webclientproductskotlinsample.model.entities.ProductEntity
import com.example.webclientproductskotlinsample.model.response.ProductResponse
import com.example.webclientproductskotlinsample.repositories.ProductRepository
import mu.KotlinLogging
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val repository: ProductRepository,
    private val modelMapper: ModelMapper
) {

    private val log = KotlinLogging.logger {}

    fun getProducts(): List<ProductResponse> {
        Thread.sleep(TIME_TO_SLEEP_IN_MILLISECONDS)

        return repository.findAll().map { convertToDto(it) }
    }

    fun getProduct(productId: Long): ProductResponse {
        Thread.sleep(TIME_TO_SLEEP_IN_MILLISECONDS)

        val product = repository.findById(productId) ?: throw NotFoundException(PRODUCT_NOT_FOUND).also {
            log.warn { "getProduct: product not found, productId:$productId" }
        }
        return convertToDto(product)
    }

    private fun convertToDto(productEntity: ProductEntity): ProductResponse {
        return modelMapper.map(productEntity, ProductResponse::class.java)
    }

    companion object {
        private const val TIME_TO_SLEEP_IN_MILLISECONDS = 5_000L
    }
}
