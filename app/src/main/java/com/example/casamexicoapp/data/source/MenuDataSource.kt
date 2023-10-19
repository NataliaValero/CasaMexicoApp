package com.example.casamexicoapp.data.source

import com.example.casamexicoapp.model.Category
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MenuDataSource (private val firestore: FirebaseFirestore){



    suspend fun getCategories(): List<Category>?{

        return try {
            val documents = firestore.collection("/menus/casamexico/categories")
                .get()
                .await()
                .documents


            val categories = mutableListOf<Category>()
            documents.forEach{
                val category = it.toObject(Category::class.java)

                if(category != null) {
                    categories.add(category)
                }
            }

            categories
        } catch (e: Exception) {
            null
        }

    }



}