package com.example.webclientimageskotlinsample.model.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ImageResponse(
    var id: Long? = null,
    var productId: Long? = null,
    var name: String? = null,
    var url: String? = null
)
