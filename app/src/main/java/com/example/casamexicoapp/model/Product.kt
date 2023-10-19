package com.example.casamexicoapp.model

import java.io.Serializable

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val categoryId: Int,
    val imageUrl: String,
    val description: String,
) : Serializable {


    constructor(): this(0,"", 0.0,0, "","")
}
