package com.example.webclientproductskotlinsample.controller

import com.example.webclientproductskotlinsample.model.response.ProductResponse
import com.example.webclientproductskotlinsample.service.ProductService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    val productService: ProductService
) {

    private val log = KotlinLogging.logger {}

    @GetMapping
    private fun getProducts(): List<ProductResponse> {
        log.info("getProducts: getting list of products")
        return productService.getProducts().also {
            log.info("getProducts: finished getting list of products")
        }
    }

    @GetMapping("/{productId}")
    private fun getProduct(@PathVariable productId: Long): ProductResponse {
        log.info("getProduct: getting productId: $productId")
        return productService.getProduct(productId).also {
            log.info("getProduct: finished getting productId: $productId")
        }
    }
}
