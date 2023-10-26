package com.example.casamexicoapp.model

import java.io.Serializable
import java.math.BigDecimal
import java.math.RoundingMode


data class Product(
    var id: String,
    var name: String,
    var price: Double,
    var categoryId: Long,
    var imageUrl: String,
    var description: String
) : Serializable {


    constructor() : this("", "", 0.0, 0, "", "")

    fun getFormattedPrice():String {
        return PriceFormatter.getCurrencyTotal(price)
    }
}

