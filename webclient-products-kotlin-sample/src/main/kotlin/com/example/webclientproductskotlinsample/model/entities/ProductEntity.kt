package com.example.webclientproductskotlinsample.model.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document("product")
data class ProductEntity(
    @Id var id: Long? = null,
    @Indexed var name: String? = null,
    var description: String? = null,
    var price: BigDecimal? = null
)
