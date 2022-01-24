package com.example.webclientproductskotlinsample.constants

import com.example.webclientproductskotlinsample.model.entities.ProductEntity
import java.math.BigDecimal

class Products {
    companion object {
        val productCatalog: List<ProductEntity> = listOf(
            ProductEntity(
                1L,
                "Notebook Dell",
                "Notebook Ultrafino Dell Inspiron i5402-M10S 14 Full HD 11ª Geração Intel Core i5",
                BigDecimal(5000)
            ),
            ProductEntity(
                2L,
                "Smartphone Samsung Galaxy A12",
                "Smartphone Samsung Galaxy A12 64GB 4G Wi-Fi Tela 6.5'' Dual Chip 4GB RAM Câmera Quádrupla + Selfie 8MP",
                BigDecimal(1100)
            ),
            ProductEntity(
                3L,
                "Chandon Réserve Brut",
                "Chandon Réserve Brut 750 ml",
                BigDecimal(70)
            )
        )
    }
}
