package com.example.casamexicoapp.data.source

import android.util.Log
import com.example.casamexicoapp.data.viewModel.AuthVMFactory
import com.example.casamexicoapp.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.json.Json

class OrdersDataSource(private val firestore: FirebaseFirestore) {

    private val firebaseAuth: FirebaseAuth = FirebaseAuthFactory.auth
    private val currentUser = firebaseAuth.currentUser!!

    suspend fun saveOrder(order: Order) : Boolean {

        //val user = firebaseAuth.currentUser?: return false


        var isSuccess = true
        firestore.collection("users")
            .document(currentUser.uid)
            .collection("orders")
            .document(order.id).set(order).addOnFailureListener {
                isSuccess = false
            }.await()

        Log.v("is success", isSuccess.toString())

        return isSuccess

    }

    suspend fun getOrders() : List<Order>? {

        return try {
            val documents = firestore.collection("users")
                .document(currentUser.uid)
                .collection("orders")
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .await()
                .documents


            val orders = mutableListOf<Order>()
            documents.forEach {
                val order = it.toObject(Order::class.java)

                if(order != null) {
                    orders.add(order)
                }
            }
            orders

        } catch (e:Exception) {
            emptyList()
        }

    }
}