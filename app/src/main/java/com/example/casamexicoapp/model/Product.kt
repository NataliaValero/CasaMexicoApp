package com.example.casamexicoapp.model

import java.io.Serializable

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val categoryId: Long,
    val imageUrl: String,
    val description: String
) : Serializable {


    constructor() : this("", "", 0.0, 0, "", "")
}
