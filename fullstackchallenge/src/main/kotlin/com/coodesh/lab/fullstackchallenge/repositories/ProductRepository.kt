package com.coodesh.lab.fullstackchallenge.repositories

import com.coodesh.lab.fullstackchallenge.models.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String> {
}