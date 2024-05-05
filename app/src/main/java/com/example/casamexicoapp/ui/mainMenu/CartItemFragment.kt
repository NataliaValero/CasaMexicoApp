package com.example.casamexicoapp.ui.mainMenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.casamexicoapp.R
import com.example.casamexicoapp.data.repository.MenuRepositoryImpl
import com.example.casamexicoapp.data.repository.OrderRepositoryImpl
import com.example.casamexicoapp.data.source.FirestoreFactory
import com.example.casamexicoapp.data.source.MenuDataSource
import com.example.casamexicoapp.data.source.OrdersDataSource
import com.example.casamexicoapp.data.viewModel.MainVMFactory
import com.example.casamexicoapp.data.viewModel.MainViewModel
import com.example.casamexicoapp.databinding.FragmentCartItemBinding
import com.example.casamexicoapp.model.Formatter


class CartItemFragment : Fragment(R.layout.fragment_cart_item) {

    // binding
    private lateinit var binding : FragmentCartItemBinding



    // Initialize viewmodel
    private val viewModel: MainViewModel by activityViewModels {
        MainVMFactory(MenuRepositoryImpl(MenuDataSource(FirestoreFactory.firestore)), OrderRepositoryImpl(
            OrdersDataSource(FirestoreFactory.firestore)
        ))
    }
    //private val sharedViewModel:MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartItemBinding.bind(view)




        loadProduct()
        setupViews(view)



    }

    private fun setupViews(view: View) = with(binding){
        // volver a menu
        arrowIv.setOnClickListener{
            view.findNavController().navigate(R.id.action_cartItemFragment_to_menuFragment)
        }

        // ir al cart
        cartIv.setOnClickListener {
            view.findNavController().navigate(R.id.action_cartItemFragment_to_cartFragment)
        }


        // quantity and add to cart
        val productQuantityTv=  binding.addItemView.itemQuantityTv.text
        var quantity = productQuantityTv.toString().toInt()

        binding.addItemView.minusTv.setOnClickListener{

            if (quantity > 1) {
                quantity --
                resfreshTotals(quantity)
            }

        }

        binding.addItemView.plusTv.setOnClickListener {
            quantity++
            resfreshTotals(quantity)
        }

        // agrega producto al carro con la cantidad actual
        binding.addBtn.setOnClickListener{
            viewModel.addProductToCart(quantity)
            Log.v("Selected", viewModel.cart.toString())
        }

    }



    // carga la info del producto al cargar el fragmento
    private fun loadProduct() = with(binding) {

        val product = viewModel.productSelected
        productNameTv.text = product.name
        ingredientsTv.text = product.description
        totalTv.text = product.getFormattedPrice()


        Glide.with(requireContext())
            .load(product.imageUrl)
            .into(productImage)
    }


    // refresca cantidad y total cuando se agregan o se remueven las unidades
    private fun resfreshTotals(quantity: Int) = with(binding) {
        val product = viewModel.productSelected
        val total = product.price * quantity

        totalTv.text = Formatter.getFormattedCurrency(total)
        addItemView.itemQuantityTv.text = quantity.toString()
    }



}