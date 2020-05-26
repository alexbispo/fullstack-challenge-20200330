package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class DeleteProductServiceTest {

    @MockBean
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var deleteProductService: DeleteProductService

    @Test
    fun `execute should call repository delete method`() {
        val productId =  "some-uuid-product-id"

        deleteProductService.execute(productId)

        verify(productRepository, times(1)).deleteById(productId)
    }
}