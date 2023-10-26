package com.example.casamexicoapp.ui.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.casamexicoapp.R
import com.example.casamexicoapp.databinding.FragmentCartBinding
import com.example.casamexicoapp.model.Cart

class CartFragment : Fragment(R.layout.fragment_cart) {


    private lateinit var binding: FragmentCartBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
    }

    private fun refreshCart(cart: Cart) {
        binding.totalItemsValueTv.text = cart.subtotal.toString()
        binding.taxValueTv.text = cart.tax.toString()
        binding.totalValueTv.text = cart.total.toString()
    }
}