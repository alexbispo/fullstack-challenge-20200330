package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DeleteProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun execute(productId: String) {
        productRepository.deleteById(productId)
    }
}