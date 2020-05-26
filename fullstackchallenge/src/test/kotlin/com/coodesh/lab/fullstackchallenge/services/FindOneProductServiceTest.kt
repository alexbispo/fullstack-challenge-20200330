package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.dtos.ItemProductDto
import com.coodesh.lab.fullstackchallenge.models.Product
import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
class FindOneProductServiceTest {

    @MockBean
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var findOneProductService: FindOneProductService

    @Test
    fun `execute should call repository find method`() {
        val productOne = Product(
                id = "some-uuid-product-id",
                title = "Product one",
                type = "Product one type",
                description = "Product used to test",
                filename = "some-file-name.jpg",
                height = 600,
                width = 700,
                price = BigDecimal("50.00"),
                rating = 4,
                createdAt = LocalDateTime.now().minusHours(5)
        )

        `when`(productRepository.findById(productOne.id)).thenReturn(Optional.of(productOne))

        val productDto = findOneProductService.execute(productOne.id)

        verify(productRepository, times(1)).findById(productOne.id)
        assertThat(productDto?.id).isEqualTo(productOne.id)
    }
}