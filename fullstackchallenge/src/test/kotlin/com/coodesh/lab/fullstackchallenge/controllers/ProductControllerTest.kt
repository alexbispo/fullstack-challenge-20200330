package com.coodesh.lab.fullstackchallenge.controllers

import com.coodesh.lab.fullstackchallenge.dtos.ItemProductDto
import com.coodesh.lab.fullstackchallenge.dtos.UpdateProductDto
import com.coodesh.lab.fullstackchallenge.models.Product
import com.coodesh.lab.fullstackchallenge.services.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.internal.verification.VerificationModeFactory.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDateTime

@WebMvcTest
class ProductControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    lateinit var createProductFromJsonService: CreateProductFromJsonService

    @MockBean
    lateinit var listProductService: ListProductService

    @MockBean
    lateinit var findOneProductService: FindOneProductService

    @MockBean
    lateinit var deleteProductService: DeleteProductService

    @MockBean
    lateinit var updateProductService: UpdateProductService

    @Test
    fun `success when post products with file`() {
        val inputStream = this::class.java.getResourceAsStream("/products.json")

        mockMvc.perform(multipart("/products")
                .file("file", inputStream.readBytes()))
                .andExpect(status().isOk)
    }

    @Test
    fun `bad request when post products missing file`() {
        mockMvc.perform(multipart("/products"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun `get products should returns a list of products`() {
        val productOne = ItemProductDto(
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

        val productTwo = ItemProductDto(
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

        `when`(listProductService.execute()).thenReturn(listOf(productOne, productTwo))

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.[0].title").value(productOne.title ?: ""))
                .andExpect(jsonPath("\$.[1].title").value(productTwo.title ?: ""))
    }

    @Test
    fun `get products by product id`() {
        val productOne = ItemProductDto(
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

        `when`(findOneProductService.execute(productOne.id ?: "")).thenReturn(productOne)

        mockMvc.perform(get("/products/${productOne.id}"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("title", equalTo(productOne.title)))

        verify(findOneProductService, times(1)).execute(productOne.id ?: "")
    }

    @Test
    fun `should return 404 when product not found`() {
        val invalidId = "some-uuid-id"

        `when`(findOneProductService.execute(invalidId)).thenReturn(null)

        mockMvc.perform(get("/products/${invalidId}"))
                .andExpect(status().isNotFound)
    }


    @Test
    fun `success when delete products`() {
        val productId = "some-product-id"
        mockMvc.perform(delete("/products/${productId}"))
                .andExpect(status().isOk)

        verify(deleteProductService, times(1)).execute(productId)
    }

    @Test
    fun `success when update products`() {
        val productId = "some-uuid-id"

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

        val objMapper = jacksonObjectMapper()
        objMapper.writeValueAsString(updateProductDto)

        mockMvc.perform(put("/products/${productId}").contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(updateProductDto)))
                .andExpect(status().isOk)

        verify(updateProductService, times(1)).execute(productId, updateProductDto)
    }

}