package com.example.casamexicoapp.data.repository

import com.example.casamexicoapp.data.source.OrdersDataSource
import com.example.casamexicoapp.model.Cart
import com.example.casamexicoapp.model.CartItem
import com.example.casamexicoapp.model.Order
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class OrderRepositoryImpl(private val ordersDataSource: OrdersDataSource) :OrderRepository {


    override suspend fun saveOrder(cart: Cart) : Boolean {


        // convertir cart item list a json
        val jsonString = Json.encodeToString(cart.cartItemList)

        // crear orden con el carro que entra por parámetro
        val order = Order(cart)

        // asignar el json a la lista tipo json string
        order.cartItemList += jsonString

        // enviar orden a data source
       return ordersDataSource.saveOrder(order)
    }

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getOrders(): List<Cart>? {

        val orders = ordersDataSource.getOrders()
        val cartList = mutableListOf<Cart>()

        orders?.forEach {order->

            // decodificar cada orden del data source tipo string a lista de ordenes tipo cart item
            val decodeCartItemList = json.decodeFromString<List<CartItem>>(order.cartItemList)

            // crear carro de compras con los atributos y con la lista decodificada
            val cart = Cart(order.id, order.date, decodeCartItemList, order.subtotal, order.tax, order.total)

            // agregar cada carro al cart list (carros = ordenes)
            cartList.add(cart)
        }


        return cartList
    }

}
