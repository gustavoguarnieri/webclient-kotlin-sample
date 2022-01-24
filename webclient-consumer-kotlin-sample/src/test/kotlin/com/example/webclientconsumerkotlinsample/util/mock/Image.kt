package com.example.webclientconsumerkotlinsample.util.mock

import com.example.webclientconsumerkotlinsample.model.response.ImageResponse
import com.example.webclientconsumerkotlinsample.model.response.ProductResponse
import java.math.BigDecimal

class Image {
    companion object {
        val images = listOf(
            ImageResponse(
                1L,
                ProductResponse(
                    1L,
                    "Name one",
                    "Description one",
                    BigDecimal("100")
                ),
                "Image one",
                "http://image-one.com.br"
            ),
            ImageResponse(
                1L,
                ProductResponse(
                    2L,
                    "Name two",
                    "Description two",
                    BigDecimal("100")
                ),
                "Image two",
                "http://image-two.com.br"
            )
        )
    }
}