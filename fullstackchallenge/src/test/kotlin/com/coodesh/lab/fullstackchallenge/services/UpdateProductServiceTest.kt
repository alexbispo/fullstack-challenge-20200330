package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.dtos.UpdateProductDto
import com.coodesh.lab.fullstackchallenge.models.Product
import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
class UpdateProductServiceTest {

    @MockBean
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var updateProductService: UpdateProductService

    @Test
    fun `execute should call appropriate repository method`() {
        val productId = "some-uuid-id";

        val updateProductDto = UpdateProductDto(
                title = "Product one",
                type = "New product type",
                description = "New product used to test",
                filename = "some-file-name.jpg",
                height = 600,
                width = 700,
                price = BigDecimal("60.00"),
                rating = 4
        )

        `when`(productRepository.findById(productId)).thenReturn(Optional.of(Product()))

        updateProductService.execute(productId, updateProductDto)

        verify(productRepository, times(1)).findById(productId)

        verify(productRepository, times(1)).save(ArgumentMatchers.any(Product::class.java))
    }
}