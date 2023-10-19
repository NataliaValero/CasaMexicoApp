package com.example.casamexicoapp.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.casamexicoapp.data.repository.AuthRepository
import com.example.casamexicoapp.data.repository.MenuRepository
import com.example.casamexicoapp.model.Category
import kotlinx.coroutines.launch

class MainViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    var categories: MutableLiveData<List<Category>> = MutableLiveData()

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
       menuRepository.getCategories()?.let {
           categories.value = it
       }

    }
}


class MainVMFactory(private val menuRepository: MenuRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MenuRepository::class.java).newInstance(menuRepository)
    }
}