package com.example.casamexicoapp.data.source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthDataSource(private val firebaseAuth: FirebaseAuth) {

    suspend fun login(email: String, password: String) : FirebaseUser? {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signup(name: String, email: String, password: String): FirebaseUser? {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            result.user!!





        } catch (e: Exception) {
            null
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }


    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

}