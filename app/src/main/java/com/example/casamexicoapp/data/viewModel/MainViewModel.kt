package com.example.casamexicoapp.data.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.casamexicoapp.data.repository.MenuRepository
import com.example.casamexicoapp.helper.MenuFactory
import com.example.casamexicoapp.model.Category
import com.example.casamexicoapp.model.Product
import kotlinx.coroutines.launch

class MainViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    var categories: MutableLiveData<List<Category>> = MutableLiveData()
    var products: MutableLiveData<List<Product>> = MutableLiveData()

    init {

        getCategories()

    }

    private fun getCategories() = viewModelScope.launch {
       menuRepository.getCategories()?.let {
           categories.value = it


           // Trae los productos del primer category (category seleccionado por defecto)
           getProductsByCategoryId(it.first().id)
       }

    }

    fun addProducts() {
        menuRepository.addProducts(MenuFactory.getProducts())
    }

    fun addCategories() {
        menuRepository.addCategories(MenuFactory.getCategories())
    }

    fun getProductsByCategoryId(categoryId: Long) = viewModelScope.launch {
        menuRepository.getProductsByCategoryId(categoryId).let {
            products.value = it
        }
    }

    fun loadProductFromCategoryPosition(tabPosition: Int) {

        val categories = categories.value ?: return

        val categoryId = categories.get(tabPosition).id

        getProductsByCategoryId(categoryId)

//        categories.value?.let {
//            getProductsByCategoryId(it.get(categoryPosition).id)
//        }
    }

}




class MainVMFactory(private val menuRepository: MenuRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MenuRepository::class.java).newInstance(menuRepository)
    }
}