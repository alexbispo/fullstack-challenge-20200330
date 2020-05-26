package com.coodesh.lab.fullstackchallenge.services

import com.coodesh.lab.fullstackchallenge.dtos.RestCreateProductDto
import com.coodesh.lab.fullstackchallenge.models.Product
import com.coodesh.lab.fullstackchallenge.repositories.ProductRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime

@Service
class CreateProductFromJsonService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun execute(json: String) {
        val objMapper = jacksonObjectMapper()

        val productDtoList: List<RestCreateProductDto> = objMapper.readValue(json)

        val productList = productDtoList.map { Product.from(it) }

        productRepository.saveAll(productList)
    }
}