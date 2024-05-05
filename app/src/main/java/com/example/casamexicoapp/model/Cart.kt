package com.example.casamexicoapp.model


import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date
import java.util.UUID

@Serializable
data class Cart(
    var cartId: String,
    @Contextual
    var date: Date,
    var cartItemList: List<CartItem>,
    var subtotal: Double,
    var tax:Double,
    var total: Double
) {


    constructor() : this (
        cartId = UUID.randomUUID().toString(),
        date = Date(),
        cartItemList = emptyList(),
        subtotal = 0.0,
        tax = 0.0,
        total = 0.0
    )

    companion object{
        const val TAX: Double = 0.07
    }


    fun addCartItem(cartItem: CartItem) {
        cartItemList += cartItem
        recalculateCart()
    }

    fun removeCartItem(cartItem: CartItem) {

        if(cartItemList.contains(cartItem)) {
            cartItemList -= cartItem
            recalculateCart()
        }

    }


    fun clearCart() {
        cartItemList = emptyList()
        recalculateCart()
    }

    fun updateCartItemQuantity(cartItem: CartItem,isAddition: Boolean) {

        var quantity = cartItem.productQuantity

        if(!isAddition) {
            quantity --
        } else {
            quantity ++
        }

        if(cartItemList.contains(cartItem)) {
            cartItem.updateQuantity(quantity)
            recalculateCart()
        }
    }

    fun isCartEmpty() : Boolean {
        return cartItemList.isEmpty()
    }


    fun recalculateCart() {

        subtotal = 0.0

        cartItemList.forEach{item->
            subtotal += item.total
        }

        tax = subtotal * TAX
        total = subtotal + tax
    }

    fun getFormattedId() :String {
        return Formatter.formatId(cartId)
    }

    fun getFormattedDate() :String {
        return Formatter.formatDate(date)
    }

    fun getFormattedTotal() : String {
        return Formatter.getFormattedCurrency(total)
    }
    fun getFormattedSubtotal() : String {
        return Formatter.getFormattedCurrency(subtotal)
    }

    fun getFormattedTax() : String {
        return Formatter.getFormattedCurrency(tax)
    }


}