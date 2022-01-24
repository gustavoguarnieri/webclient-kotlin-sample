package com.example.webclientconsumerkotlinsample.util.mock

import com.example.webclientconsumerkotlinsample.model.response.ProductResponse
import java.math.BigDecimal

class Product {
    companion object {
        val products = listOf(
            ProductResponse(
                1L,
                "Name one",
                "Description one",
                BigDecimal("100")
            ),
            ProductResponse(
                2L,
                "Name two",
                "Description two",
                BigDecimal("100")
            )
        )
        val product = ProductResponse(
            1L,
            "Name one",
            "Description one",
            BigDecimal("100")
        )
    }
}