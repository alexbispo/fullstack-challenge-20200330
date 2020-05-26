package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.dtos.ItemProductDto
import com.coodesh.lab.fullstackchallenge.models.Product
import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
class ListProductServiceTest {

    @MockBean
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var listProductService: ListProductService

    @Test
    fun `execute should call repository method and return a list of products`() {
        val productOne = Product(
                id = "some-uuid-id",
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

        val productTwo = Product(
                id = "some-uuid-id-2",
                title = "Product two",
                type = "Product two type",
                description = "Product used to test",
                filename = "some-file-name.jpg",
                height = 300,
                width = 400,
                price = BigDecimal("70.00"),
                rating = 3,
                createdAt = LocalDateTime.now().minusHours(4)
        )

        Mockito.`when`(productRepository.findAll()).thenReturn(listOf(productOne, productTwo))


        val productList = listProductService.execute()

        assertThat(productList[0].id).isEqualTo(productOne.id)
        assertThat(productList[1].id).isEqualTo(productTwo.id)
    }
}