package com.example.casamexicoapp.data.repository

import com.example.casamexicoapp.model.Order
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class OrderRepositoryImpl(private val firestore: FirebaseFirestore) {

    suspend fun getOrder() {

        try{
            var data = firestore.collection("/users/e4reVtDMocRL6jgYFAAisqloqI12/orders")
                .document("order1")
                .get()
                .await()

            var orderItem = data.toObject(Order::class.java)

            if(orderItem == null) {

            }

            var test= ""

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}