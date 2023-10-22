package com.example.casamexicoapp.data.repository

import com.example.casamexicoapp.model.Category
import com.example.casamexicoapp.model.Product
import com.google.firebase.firestore.DocumentSnapshot

interface MenuRepository {

    // Agregar desde productos y categories desde MenuFactory a firestore
    fun addProducts(products: List<Product>)

    fun addCategories(categories: List<Category>)

    suspend fun getCategories() : List<Category>?

    suspend fun getProductsByCategoryId(categoryId: Long) : List<Product>


}