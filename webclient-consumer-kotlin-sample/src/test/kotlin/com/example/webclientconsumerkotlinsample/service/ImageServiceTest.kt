package com.example.webclientconsumerkotlinsample.service

import com.example.webclientconsumerkotlinsample.client.WebClientImagesKotlinSampleClient
import com.example.webclientconsumerkotlinsample.constants.ErrorMessages.Companion.IMAGES_NOT_FOUND
import com.example.webclientconsumerkotlinsample.exceptions.NotFoundException
import com.example.webclientconsumerkotlinsample.util.mock.Image.Companion.images
import com.example.webclientconsumerkotlinsample.util.mock.Product.Companion.product
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class ImageServiceTest {

    private lateinit var imageService: ImageService

    @MockK
    private lateinit var webClientImagesKotlinSampleClient: WebClientImagesKotlinSampleClient

    @MockK
    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        imageService = ImageService(webClientImagesKotlinSampleClient, productService)
    }

    @Test
    fun getSyncImagesExistingImagesShouldReturnListOfImages() {
        every { productService.getProduct(any()) } returns Mono.just(product)
        every { webClientImagesKotlinSampleClient.getImages(any()) } returns Flux.fromIterable(images)

        val result = imageService.getSyncImages(PRODUCT_ID)

        assertEquals(2, result.size)

        verify(exactly = 1) {
            productService.getProduct(any())
            webClientImagesKotlinSampleClient.getImages(any())
        }
    }

    @Test
    fun getSyncImagesNotExistingProductShouldThrowNotFoundException() {
        every { productService.getProduct(any()) } throws NotFoundException("")

        assertThrows<NotFoundException> {
            imageService.getSyncImages(PRODUCT_ID)
        }

        verify(exactly = 1) {
            productService.getProduct(any())
        }
        verify(exactly = 0) {
            webClientImagesKotlinSampleClient.getImages(any())
        }
    }

    @Test
    fun getSyncImagesNotExistingImagesShouldThrowNotFoundException() {
        every { productService.getProduct(any()) } returns Mono.just(product)
        every { webClientImagesKotlinSampleClient.getImages(any()) } throws NotFoundException(IMAGES_NOT_FOUND)

        val result = assertThrows<NotFoundException> {
            imageService.getSyncImages(PRODUCT_ID)
        }

        assertEquals(IMAGES_NOT_FOUND, result.message)
        verify(exactly = 1) {
            productService.getProduct(any())
            webClientImagesKotlinSampleClient.getImages(any())
        }
    }

    @Test
    fun getAsyncImagesExistingImagesShouldReturnListOfImages() {
        every { productService.getProduct(any()) } returns Mono.just(product)
        every { webClientImagesKotlinSampleClient.getImages(any()) } returns Flux.fromIterable(images)

        val result = imageService.getAsyncImages(PRODUCT_ID)

        assertEquals(2, result.size)
        verify(exactly = 1) {
            productService.getProduct(any())
            webClientImagesKotlinSampleClient.getImages(any())
        }
    }

    @Test
    fun getAsyncImagesNotExistingProductShouldThrowNotFoundException() {
        every { productService.getProduct(any()) } throws NotFoundException("")

        assertThrows<NotFoundException> {
            imageService.getAsyncImages(PRODUCT_ID)
        }

        verify(exactly = 1) {
            productService.getProduct(any())
        }
        verify(exactly = 0) {
            webClientImagesKotlinSampleClient.getImages(any())
        }
    }

    @Test
    fun getAsyncImagesNotExistingImagesShouldThrowNotFoundException() {
        every { productService.getProduct(any()) } returns Mono.just(product)
        every { webClientImagesKotlinSampleClient.getImages(any()) } throws NotFoundException(IMAGES_NOT_FOUND)

        val result = assertThrows<NotFoundException> {
            imageService.getAsyncImages(PRODUCT_ID)
        }

        assertEquals(IMAGES_NOT_FOUND, result.message)
        verify(exactly = 1) {
            productService.getProduct(any())
            webClientImagesKotlinSampleClient.getImages(any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAsyncCoroutinesImagesExistingImagesShouldReturnListOfImages() = runTest {

        coEvery { productService.getSuspendProduct(any()) } returns product
        coEvery { webClientImagesKotlinSampleClient.getSuspendImages(any()) } returns images

        val result = imageService.getAsyncCoroutinesImages(PRODUCT_ID)

        assertEquals(2, result.size)
        coVerify(exactly = 1) {
            productService.getSuspendProduct(any())
            webClientImagesKotlinSampleClient.getSuspendImages(any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAsyncCoroutinesImagesNotExistingProductShouldThrowNotFoundException() = runTest {
        coEvery { productService.getSuspendProduct(any()) } throws NotFoundException("")

        assertThrows<NotFoundException> {
            imageService.getAsyncCoroutinesImages(PRODUCT_ID)
        }

        coVerify(exactly = 1) {
            productService.getSuspendProduct(any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAsyncCoroutinesImagesNotExistingImagesShouldThrowNotFoundException() = runTest {
        coEvery { productService.getSuspendProduct(any()) } returns product
        coEvery { webClientImagesKotlinSampleClient.getSuspendImages(any()) } throws NotFoundException(IMAGES_NOT_FOUND)

        val result = assertThrows<NotFoundException> {
            imageService.getAsyncCoroutinesImages(PRODUCT_ID)
        }

        assertEquals(IMAGES_NOT_FOUND, result.message)
        coVerify(exactly = 1) {
            productService.getSuspendProduct(any())
            webClientImagesKotlinSampleClient.getSuspendImages(any())
        }
    }

    companion object {
        const val PRODUCT_ID = 1L
    }
}
