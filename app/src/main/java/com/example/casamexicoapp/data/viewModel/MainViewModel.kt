package com.example.casamexicoapp.data.viewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.casamexicoapp.data.repository.MenuRepository
import com.example.casamexicoapp.data.repository.OrderRepository
import com.example.casamexicoapp.data.repository.OrderRepositoryImpl
import com.example.casamexicoapp.helper.MenuFactory
import com.example.casamexicoapp.model.Cart
import com.example.casamexicoapp.model.CartItem
import com.example.casamexicoapp.model.Category
import com.example.casamexicoapp.model.Order
import com.example.casamexicoapp.model.Product
import kotlinx.coroutines.launch

class MainViewModel(private val menuRepository: MenuRepository, private val orderRepository: OrderRepository) : ViewModel() {



    var categories: MutableLiveData<List<Category>> = MutableLiveData()
    var products: MutableLiveData<List<Product>> = MutableLiveData()
    var productSelected : Product = Product()
    var cart: Cart = Cart()
    var orderSaved: MutableLiveData<Boolean> = MutableLiveData()
    var orders : MutableLiveData<List<Cart>> = MutableLiveData()
    var orderSelected: Cart = Cart()



    var tabSelectedPosition = 0

    // Menu and product items --------------------------

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


    private fun getProductsByCategoryId(categoryId: Long) = viewModelScope.launch {
        menuRepository.getProductsByCategoryId(categoryId).let {
            products.value = it
        }
    }

    fun loadProductFromCategoryPosition(tabPosition: Int) {

        val categories = categories.value ?: return

        val categoryId = categories[tabPosition].id

        getProductsByCategoryId(categoryId)

        // Otra forma de hacerlo
//        categories.value?.let {
//            getProductsByCategoryId(it.get(categoryPosition).id)
//        }

        tabSelectedPosition = tabPosition

    }

    // product selected

    fun onProductSelected(product: Product) {
        //productSelected.value = product
        this.productSelected = product

    }


    // Cart item and cart --------------------------


    fun addProductToCart(quantity: Int) {

        val cartItem = CartItem(productSelected, quantity)
        cart.addCartItem(cartItem)

    }

    fun getCartItems(): List<CartItem> {
        return cart.cartItemList
    }


    fun onQuantityChanged(cartItem: CartItem, isAddition: Boolean) {

        cart.updateCartItemQuantity(cartItem, isAddition)
    }

    fun removeCartItem(cartItem: CartItem) {
        cart.removeCartItem(cartItem)
    }

    fun isCartEmpty() : Boolean {
        return cart.isCartEmpty()
    }

    // Menu Factory -------------------------------
    fun addProducts() {
        menuRepository.addProducts(MenuFactory.getProducts())
    }

    fun addCategories() {
        menuRepository.addCategories(MenuFactory.getCategories())
    }


    // Orders save and get
    fun saveOrder()  = viewModelScope.launch {
        orderRepository.saveOrder(cart).let {isSuccess ->
            if(isSuccess) {
                orderSaved.value = true
                initializeCart()
            } else {
                orderSaved.value = false
            }
        }
    }


    fun initializeCart() {

        // reiniciar carro y orden nueva
        cart = Cart()
        orderSaved.value = false
    }

    fun getOrders() = viewModelScope.launch {

        orderRepository.getOrders().let {
            orders.value = it
        }
    }

    fun onOrderSelected(item: Cart) {
        this.orderSelected = item

    }


}




class MainVMFactory(private val menuRepository: MenuRepository, private val orderRepository: OrderRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MenuRepository::class.java, OrderRepository::class.java).newInstance(menuRepository, orderRepository)
    }
}