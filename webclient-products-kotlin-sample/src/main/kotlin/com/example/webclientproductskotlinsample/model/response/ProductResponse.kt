package com.example.webclientproductskotlinsample.model.response

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProductResponse(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var price: BigDecimal? = null
)
