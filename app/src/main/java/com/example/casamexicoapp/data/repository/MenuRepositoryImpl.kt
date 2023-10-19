package com.example.casamexicoapp.data.repository

import com.example.casamexicoapp.data.source.MenuDataSource
import com.example.casamexicoapp.model.Category
import com.google.firebase.firestore.DocumentSnapshot

class MenuRepositoryImpl(private val menuDataSource: MenuDataSource): MenuRepository{


    override suspend fun getCategories(): List<Category>? {
        return menuDataSource.getCategories()
    }
}