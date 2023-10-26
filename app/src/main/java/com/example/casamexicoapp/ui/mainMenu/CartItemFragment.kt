package com.example.casamexicoapp.ui.mainMenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.casamexicoapp.R
import com.example.casamexicoapp.data.repository.MenuRepositoryImpl
import com.example.casamexicoapp.data.source.FirestoreFactory
import com.example.casamexicoapp.data.source.MenuDataSource
import com.example.casamexicoapp.data.viewModel.MainVMFactory
import com.example.casamexicoapp.data.viewModel.MainViewModel
import com.example.casamexicoapp.databinding.FragmentCartItemBinding
import com.example.casamexicoapp.model.CartItem
import com.example.casamexicoapp.model.PriceFormatter
import com.example.casamexicoapp.model.Product


class CartItemFragment : Fragment(R.layout.fragment_cart_item) {

    // binding
    private lateinit var binding : FragmentCartItemBinding



    // Initialize viewmodel
    private val viewModel:MainViewModel by activityViewModels {
        MainVMFactory(MenuRepositoryImpl(MenuDataSource(FirestoreFactory.firestore)))
    }
    private val sharedViewModel:MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartItemBinding.bind(view)

        var productQuantityTv=  binding.addItemView.itemQuantityTv.text
        var quantity = productQuantityTv.toString().toInt()


        loadProduct()


        // volver a menu
        binding.arrowIv.setOnClickListener{
            view.findNavController().navigate(R.id.action_cartItemFragment_to_menuFragment)
        }

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

        binding.addBtn.setOnClickListener{
            viewModel.addProduct(quantity)
            Log.v("Selected", viewModel.cart.toString())
        }


    }


    private fun loadProduct() = with(binding) {

        val product = viewModel.productSelected
        productNameTv.text = product.name
        ingredientsTv.text = product.description
        totalTv.text = product.getFormattedPrice()


        Glide.with(requireContext())
            .load(product.imageUrl)
            .into(imageView3)
    }

    private fun resfreshTotals(quantity: Int) = with(binding) {
        val product = viewModel.productSelected
        val total = product.price * quantity

        totalTv.text = PriceFormatter.getCurrencyTotal(total)
        addItemView.itemQuantityTv.text = quantity.toString()
    }



}