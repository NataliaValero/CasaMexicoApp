package com.example.casamexicoapp.model

import java.io.Serializable
import java.util.Date

data class Order (
    val id:Int,
    val date: Date,
    val caritemList:String,
    val itemTotal: Double,
    val tax:Double,
    val total:Double): Serializable {

        constructor():this(0,Date(), "", 0.0, 0.0, 0.0)
    }