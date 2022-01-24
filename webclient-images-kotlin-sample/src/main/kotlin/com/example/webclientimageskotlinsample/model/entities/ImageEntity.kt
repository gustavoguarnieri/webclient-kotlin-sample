package com.example.webclientimageskotlinsample.model.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("image")
data class ImageEntity(
    @Id
    var id: Long? = null,

    var productId: Long? = null,
    @Indexed
    var name: String? = null,
    val url: String? = null
)
