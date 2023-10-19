package com.example.casamexicoapp.model

import java.io.Serializable

//enum class Category(name:String, products:List<Product>) {
//
//    APPETIZER("Appetizers", emptyList()),
//    ENTREES("Mains", emptyList()),
//    DRINKS("Drinks", emptyList()),
//    DESSERT("Desserts", emptyList()),
//    COCKTAILS("Cocktail", emptyList())
//}

data class Category (val id: Int, val categoryName: String):Serializable {
    constructor(): this(0, "")
}