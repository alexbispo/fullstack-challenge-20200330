package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.dtos.RestCreateProductDto
import com.coodesh.lab.fullstackchallenge.models.Product
import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDateTime

@SpringBootTest
class CreateProductFromJsonServiceTest {

    @MockBean
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var createProductFromJsonService: CreateProductFromJsonService

    @Test
    fun `execute should call repository save method`() {
        val inputStream = this::class.java.getResourceAsStream("/products.json")
        val content = inputStream.reader(Charsets.UTF_8).readText()

        createProductFromJsonService.execute(content)

        verify(productRepository, times(1)).saveAll(anyList())
    }
}