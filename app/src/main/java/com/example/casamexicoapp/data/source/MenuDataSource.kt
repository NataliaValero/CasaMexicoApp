package com.example.casamexicoapp.data.source

import com.example.casamexicoapp.helper.MenuFactory
import com.example.casamexicoapp.model.Category
import com.example.casamexicoapp.model.Product
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class MenuDataSource (private val firestore: FirebaseFirestore){



    suspend fun getCategories(): List<Category>?{

        return try {
            val documents = firestore.collection("/menus/casamexico/categories")
                .orderBy("ordinal")
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



    // agregar products al menu
    fun addProducts(products: List<Product>) {


        products.forEach {product->
            firestore.collection("/menus/casamexico/products").document(product.id).set(product)
        }
    }

    // agregar categories al menu
    fun addCategories(categories: List<Category>) {
        categories.forEach {category->
            firestore.collection("/menus/casamexico/categories").document(category.id.toString()).set(category)
        }
    }

    suspend fun getProductsByCategoryId(categoryId: Long) : List<Product> {


        return try{

            val productsCollection = firestore.collection("/menus/casamexico/products")

            // filtra por category id field
            val query =
                productsCollection.whereEqualTo("categoryId", categoryId).get().await().documents

            val products = mutableListOf<Product>()

            query.forEach {
                val product = it.toObject(Product::class.java)

              

                if (product != null) {
                    products.add(product)
                }
            }

            products

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}