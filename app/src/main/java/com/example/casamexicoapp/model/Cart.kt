package com.example.casamexicoapp.model

import java.util.UUID

data class Cart(
    var cartId: String = UUID.randomUUID().toString(),
    var cartItemList: List<CartItem> = emptyList(),
    var subtotal: Double = 0.0,
    var tax:Double = 0.0,
    var total: Double = 0.0
) {

    companion object{
        const val TAX: Double = 0.07
    }


    fun addCartItem(cartItem: CartItem) {
        cartItemList += cartItem
        recalculateCart()
    }

    fun removeCartItem(cartItem: CartItem) {
        cartItemList -= cartItem
        recalculateCart()
    }


    fun clearCart() {
        cartItemList = emptyList()
        recalculateCart()
    }

    fun updateCartItemQuantity(cartItem: CartItem, quantity: Int) {
        cartItem.updateQuantity(quantity)
        recalculateCart()
    }

    fun recalculateCart() {

        cartItemList.forEach{item->
            subtotal += item.total
        }

        tax = subtotal * TAX
        total = subtotal + tax
    }
}