package com.example.webclientconsumerkotlinsample.service

import com.example.webclientconsumerkotlinsample.client.WebClientProductsKotlinSampleClient
import com.example.webclientconsumerkotlinsample.util.mock.Product.Companion.product
import com.example.webclientconsumerkotlinsample.util.mock.Product.Companion.products
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
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class ProductServiceTest {

    private lateinit var productService: ProductService

    @MockK
    private lateinit var webClientProductsKotlinSampleClient: WebClientProductsKotlinSampleClient

    @BeforeEach
    fun setUp() {
        productService = ProductService(webClientProductsKotlinSampleClient)
    }

    @Test
    fun getProductsExistingProductsShouldReturnListOfProducts() {
        every { webClientProductsKotlinSampleClient.getProducts() } returns Flux.fromIterable(products)

        val result = productService.getProducts()

        assertEquals(2, result.collectList().block()?.size)
    }

    @Test
    fun getProductExistingProductShouldReturnProduct() {
        every { webClientProductsKotlinSampleClient.getProduct(any()) } returns Mono.just(product)

        val result = productService.getProduct(PRODUCT_ID).block()

        assertEquals(result, product)

        verify(exactly = 1) {
            webClientProductsKotlinSampleClient.getProduct(any())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getSuspendProductExistingProductShouldReturnProduct() = runTest {
        coEvery { webClientProductsKotlinSampleClient.getSuspendProduct(any()) } returns product

        val result = productService.getSuspendProduct(PRODUCT_ID)

        assertEquals(result, product)

        coVerify(exactly = 1) {
            webClientProductsKotlinSampleClient.getSuspendProduct(any())
        }
    }

    companion object {
        const val PRODUCT_ID = 1L
    }
}
