package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.dtos.ItemProductDto
import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ListProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun execute(): List<ItemProductDto> {
        val findAll = productRepository.findAll()

        return findAll.map { it.toItemProductDto() }
    }
}