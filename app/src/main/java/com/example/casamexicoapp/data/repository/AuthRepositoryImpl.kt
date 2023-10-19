package com.example.casamexicoapp.data.repository

import com.example.casamexicoapp.data.source.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class AuthRepositoryImpl (private val dataSource: AuthDataSource) : AuthRepository{
    override suspend fun login(email: String, password: String): FirebaseUser? {
        return dataSource.login(email, password)
    }

    override suspend fun signup(name: String, email: String, password: String): FirebaseUser? {
        return dataSource.signup(name, email, password)
    }

    override fun logout() {
        dataSource.logout()
    }

    override val currentUser: FirebaseUser?
        get() = dataSource.currentUser
}