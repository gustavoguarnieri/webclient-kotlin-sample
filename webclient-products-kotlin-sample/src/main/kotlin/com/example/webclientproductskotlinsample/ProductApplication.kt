package com.example.webclientproductskotlinsample

import com.example.webclientproductskotlinsample.constants.Products
import com.example.webclientproductskotlinsample.repositories.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductApplication(
    private val repository: ProductRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        repository.deleteAll()
        Products.productCatalog.forEach(repository::save)
    }
}

fun main(args: Array<String>) {
    runApplication<ProductApplication>(*args)
}
