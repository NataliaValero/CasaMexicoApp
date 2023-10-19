package com.example.casamexicoapp.data.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.casamexicoapp.data.repository.AuthRepository
import com.example.casamexicoapp.data.repository.OrderRepositoryImpl
import com.example.casamexicoapp.data.source.FirestoreFactory
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel (private val repository: AuthRepository) : ViewModel(){

    val login = MutableLiveData<FirebaseUser?>()
    val signup = MutableLiveData<FirebaseUser?>()

    fun login(email:String, password:String) = viewModelScope.launch {
        val result = repository.login(email, password)
        login.value = result
    }

    fun signup(name:String, email: String, password: String) = viewModelScope.launch {
        val result = repository.signup(name, email, password)
        signup.value = result
    }

    fun logout() {
        repository.logout()
        login.value = null
        signup.value = null
    }

    val currentUser : FirebaseUser?
        get() = repository.currentUser

    fun getOrder() {
        val repo = OrderRepositoryImpl(FirestoreFactory.firestore)

        viewModelScope.launch {
            repo.getOrder()
        }
    }
}

class AuthVMFactory(private val repository: AuthRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AuthRepository::class.java).newInstance(repository)
    }
}
