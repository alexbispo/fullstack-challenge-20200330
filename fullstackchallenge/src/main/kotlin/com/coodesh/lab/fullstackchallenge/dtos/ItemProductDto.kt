package com.coodesh.lab.fullstackchallenge.dtos

import java.math.BigDecimal
import java.time.LocalDateTime

data class ItemProductDto(
        var id: String? = null,
        var title: String? = null,
        var type: String? = null,
        var description: String? = null,
        var filename: String? = null,
        var height: Int? = null,
        var width: Int? = null,
        var price: BigDecimal? = null,
        var rating: Int? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
)