package com.coodesh.lab.fullstackchallenge.controllers

import com.coodesh.lab.fullstackchallenge.dtos.ItemProductDto
import com.coodesh.lab.fullstackchallenge.dtos.UpdateProductDto
import com.coodesh.lab.fullstackchallenge.services.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("products")
class ProductController {

    @Autowired
    private lateinit var createProductFromJsonService: CreateProductFromJsonService

    @Autowired
    private lateinit var listProductService: ListProductService

    @Autowired
    private lateinit var findOneProductService: FindOneProductService

    @Autowired
    private lateinit var deleteProductService: DeleteProductService

    @Autowired
    private lateinit var updateProductService: UpdateProductService

    @PostMapping
    fun create(@RequestParam("file") file: MultipartFile) {
        val fileContent = file.inputStream.bufferedReader(charset = Charsets.UTF_8).readText()

        createProductFromJsonService.execute(fileContent)
    }

    @GetMapping
    fun index() = listProductService.execute()

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id: String): ItemProductDto {
        val productFound = findOneProductService.execute(id)

        return productFound ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String) {
        deleteProductService.execute(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody resource: UpdateProductDto) {
        updateProductService.execute(id, resource)
    }

}