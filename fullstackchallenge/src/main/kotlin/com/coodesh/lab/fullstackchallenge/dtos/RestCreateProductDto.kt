package com.coodesh.lab.fullstackchallenge.dtos

import java.math.BigDecimal

data class RestCreateProductDto(
        val title: String = "",
        val type: String = "",
        val description: String = "",
        val filename: String = "",
        val height: Int = 0,
        val width: Int = 0,
        val price: BigDecimal = BigDecimal.ZERO,
        val rating: Int = 0
)