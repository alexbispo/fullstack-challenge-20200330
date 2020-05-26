package com.coodesh.lab.fullstackchallenge.models

import com.coodesh.lab.fullstackchallenge.dtos.ItemProductDto
import com.coodesh.lab.fullstackchallenge.dtos.RestCreateProductDto
import com.coodesh.lab.fullstackchallenge.dtos.UpdateProductDto
import org.apache.tomcat.jni.Local
import org.springframework.data.annotation.Id
import java.math.BigDecimal
import java.time.LocalDateTime

data class Product(
        @Id
        val id: String = "",
        val title: String = "",
        val type: String = "",
        val description: String = "",
        val filename: String = "",
        val height: Int = 0,
        val width: Int = 0,
        val price: BigDecimal = BigDecimal.ZERO,
        val rating: Int = 0,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun from(dto: RestCreateProductDto) = Product(
                title = dto.title,
                type = dto.type,
                description = dto.description,
                filename = dto.filename,
                height = dto.height,
                width = dto.width,
                price = dto.price,
                rating = dto.rating
        )
    }

    fun toItemProductDto() = ItemProductDto(
            id = this.id,
            title = this.title,
            type = this.type,
            description = this.description,
            filename = this.filename,
            width = this.width,
            height = this.height,
            price = this.price,
            rating = this.rating,
            createdAt = this.createdAt,
            updatedAt = LocalDateTime.now()
    )

    fun prepareToUpdate(dto: UpdateProductDto) : Product {
        return Product(
                id = this.id,
                title = dto.title ?: this.title,
                type = dto.type ?: this.type,
                filename = dto.filename ?: this.filename,
                description = dto.description ?: this.description,
                width = dto.width ?: this.width,
                height = dto.height ?: this.height,
                price = dto.price ?: this.price,
                rating = dto.rating ?: this.rating,
                createdAt = this.createdAt
        )
    }
}