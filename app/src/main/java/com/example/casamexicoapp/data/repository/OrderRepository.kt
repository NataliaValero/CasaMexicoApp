package com.example.casamexicoapp.data.repository

import com.example.casamexicoapp.model.Cart
import com.example.casamexicoapp.model.Order

interface OrderRepository {

    suspend fun saveOrder(cart: Cart) : Boolean
    suspend fun getOrders() : List<Cart>?
}