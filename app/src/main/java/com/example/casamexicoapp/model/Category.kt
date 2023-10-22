package com.example.casamexicoapp.model

import java.io.Serializable


data class Category(val id: Long, val name: String, val ordinal: Int) : Serializable {
    constructor() : this(0, "", 0)
}