package com.example.casamexicoapp.model


import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date


@Serializable
data class Order (
    var id: String ,
    @Contextual
    var date: Date,
    var cartItemList: String,
    var subtotal: Double,
    var tax:Double,
    var total:Double) {


    // empty constructor
    constructor() : this (
        id = "",
        date = Date(),
        cartItemList = "",
        subtotal = 0.0,
        tax = 0.0,
        total = 0.0
    )

    constructor(cart: Cart):this(
        id = cart.cartId,
        date = Date(),
        cartItemList = "",
        subtotal = cart.subtotal,
        tax = cart.tax,
        total = cart.total
    )



}