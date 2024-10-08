package com.example.casamexicoapp.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class CartItem(
    var cartItemId: String,
    var productId: String,
    var name: String,
    var price: Double,
    var description: String,
    var imageUrl: String,
    var productQuantity: Int,
    var total: Double) {


    constructor(product: Product, quantity: Int) : this(
        cartItemId = UUID.randomUUID().toString(),
        productId = product.id,
        name = product.name,
        price = product.price,
        description = product.description,
        imageUrl = product.imageUrl,
        productQuantity = quantity,
        total= product.price * quantity
    )

    fun updateQuantity(newQuantity: Int) {
        this.productQuantity = newQuantity
        total = productQuantity * price
    }

    fun getFormattatedTotal() :String {
        return Formatter.getFormattedCurrency(total)
    }

}


