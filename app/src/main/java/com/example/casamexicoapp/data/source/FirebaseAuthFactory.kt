package com.example.casamexicoapp.data.source

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthFactory {

    companion object{
        val auth by lazy {
            Firebase.auth
        }
    }
}