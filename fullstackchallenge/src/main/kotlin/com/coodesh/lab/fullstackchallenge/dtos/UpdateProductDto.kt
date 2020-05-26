package com.coodesh.lab.fullstackchallenge.dtos

import java.math.BigDecimal

data class UpdateProductDto(
        var title: String? = null,
        var type: String? = null,
        var description: String? = null,
        var filename: String? = null,
        var height: Int? = null,
        var width: Int? = null,
        var price: BigDecimal? = null,
        var rating: Int? = null
)