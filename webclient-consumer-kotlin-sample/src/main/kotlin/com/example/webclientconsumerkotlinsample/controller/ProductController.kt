package com.example.webclientconsumerkotlinsample.controller

import com.example.webclientconsumerkotlinsample.model.response.ProductResponse
import com.example.webclientconsumerkotlinsample.service.ProductService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService
) {

    private val log = KotlinLogging.logger {}

    @GetMapping
    private fun getProducts(): Flux<ProductResponse> {
        log.info("getProducts: getting list of products")
        return productService.getProducts().also {
            log.info("getProducts: finished getting list of products")
        }
    }

    @GetMapping("/{productId}")
    private fun getProduct(@PathVariable productId: Long): Mono<ProductResponse> {
        log.info("getProduct: getting product, productId=$productId")
        return productService.getProduct(productId).also {
            log.info("getProduct: finished getting product, productId=$productId")
        }
    }

}