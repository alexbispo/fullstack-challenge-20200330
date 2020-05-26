package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.dtos.UpdateProductDto
import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UpdateProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun execute(productId: String, productDto: UpdateProductDto) {
        val findById = productRepository.findById(productId)

        if (!findById.isPresent) {
            throw RuntimeException("Product not found")
        }

        val toUpdate = findById.get().prepareToUpdate(productDto)

        productRepository.save(toUpdate)
    }
}