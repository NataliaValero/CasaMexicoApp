package com.example.casamexicoapp.data.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun login(email:String, password:String) : FirebaseUser?
    suspend fun signup(name:String, email:String, password:String) : FirebaseUser?
    fun logout()
    val currentUser: FirebaseUser?

}