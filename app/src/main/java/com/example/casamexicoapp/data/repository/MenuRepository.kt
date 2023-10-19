package com.example.casamexicoapp.data.repository

import com.example.casamexicoapp.model.Category
import com.google.firebase.firestore.DocumentSnapshot

interface MenuRepository {

    suspend fun getCategories() : List<Category>?


}