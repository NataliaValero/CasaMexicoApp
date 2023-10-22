package com.example.casamexicoapp.data.repository

import com.example.casamexicoapp.data.source.MenuDataSource
import com.example.casamexicoapp.helper.MenuFactory
import com.example.casamexicoapp.model.Category
import com.example.casamexicoapp.model.Product
import com.google.firebase.firestore.DocumentSnapshot

class MenuRepositoryImpl(private val menuDataSource: MenuDataSource): MenuRepository{


    override suspend fun getCategories(): List<Category>? {
        return menuDataSource.getCategories()
    }

    override fun addProducts(products: List<Product>) {
        menuDataSource.addProducts(products)
    }

    override fun addCategories(categories: List<Category>) {
        menuDataSource.addCategories(categories)
    }

    suspend override fun getProductsByCategoryId(categoryId: Long): List<Product> {
        return menuDataSource.getProductsByCategoryId(categoryId)
    }


}