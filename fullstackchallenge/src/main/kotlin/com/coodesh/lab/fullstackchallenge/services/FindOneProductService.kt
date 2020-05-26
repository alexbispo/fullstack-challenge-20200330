package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.dtos.ItemProductDto
import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FindOneProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun execute(productId: String): ItemProductDto? {
        val productFound = productRepository.findById(productId)

        if (!productFound.isPresent) {
            return null
        }
        return productFound.get().toItemProductDto()
    }
}