package com.example.webclientimageskotlinsample.repositories

import com.example.webclientimageskotlinsample.model.entities.ImageEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface ImageRepository : MongoRepository<ImageEntity, String> {
    fun findByProductId(id: Long): List<ImageEntity>
}
