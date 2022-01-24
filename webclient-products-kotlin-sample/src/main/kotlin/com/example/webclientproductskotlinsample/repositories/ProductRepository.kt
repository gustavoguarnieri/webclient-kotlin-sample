package com.example.webclientproductskotlinsample.repositories

import com.example.webclientproductskotlinsample.model.entities.ProductEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<ProductEntity, String> {
    fun findById(id: Long): ProductEntity?
}
