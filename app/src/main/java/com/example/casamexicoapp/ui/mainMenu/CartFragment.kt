package com.example.casamexicoapp.ui.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.casamexicoapp.R
import com.example.casamexicoapp.data.repository.MenuRepositoryImpl
import com.example.casamexicoapp.data.repository.OrderRepositoryImpl
import com.example.casamexicoapp.data.source.FirestoreFactory
import com.example.casamexicoapp.data.source.MenuDataSource
import com.example.casamexicoapp.data.source.OrdersDataSource
import com.example.casamexicoapp.data.viewModel.MainVMFactory
import com.example.casamexicoapp.data.viewModel.MainViewModel
import com.example.casamexicoapp.databinding.FragmentCartBinding
import com.example.casamexicoapp.model.CartItem
import com.example.casamexicoapp.model.Formatter
import com.example.casamexicoapp.ui.adapter.CartItemAdapter

class CartFragment : Fragment(R.layout.fragment_cart) {

    // Adapter
    private lateinit var cartItemsAdapter: CartItemAdapter
    // Initialize viewmodel
    private val viewModel: MainViewModel by activityViewModels {
        MainVMFactory(MenuRepositoryImpl(MenuDataSource(FirestoreFactory.firestore)), OrderRepositoryImpl(
            OrdersDataSource(FirestoreFactory.firestore)
        ))
    }
    private lateinit var binding: FragmentCartBinding




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)



        initializeAdapters()
        loadCartItems()
        setupViews(view)
        suscribeObservers()


    }

    private fun setupViews(view: View) = with(binding){

        // back icon
        backIcon.setOnClickListener{
            view.findNavController().navigate(R.id.action_cartFragment_to_menuFragment)
            //viewModel.orderSaved.value = false
        }

        payButton.setOnClickListener {
            viewModel.saveOrder()
        }

        showEmptyCartLayout()
    }

    private fun initializeAdapters() = with(binding) {
        cartItemsAdapter = CartItemAdapter(emptyList())

        itemsRv.adapter = cartItemsAdapter
        itemsRv.layoutManager = LinearLayoutManager(context)



        cartItemsAdapter.listener = object : CartItemAdapter.CartItemAdapterListener {


            override fun onRemoveClick(cartItem: CartItem) {
                viewModel.removeCartItem(cartItem)
                cartItemsAdapter.setCartItemsList(viewModel.getCartItems())
                refreshTotals()
                showEmptyCartLayout()

            }

            override fun onQuantityUpdated(cartItem: CartItem, isAddition: Boolean) {
                viewModel.onQuantityChanged(cartItem, isAddition)
                refreshTotals()
            }


        }
    }

    private fun suscribeObservers(){

        // live data notifica
        viewModel.orderSaved.observe(viewLifecycleOwner) {orderSaved ->
            if(orderSaved) {
                showOrderProcessedLayout()
            }

        }
    }

    // carga items con el carro inicial
    private fun loadCartItems() = with(binding) {

        cartItemsAdapter.setCartItemsList(viewModel.getCartItems())

        refreshTotals()

    }

    private fun refreshTotals() = with(binding) {

        val cart = viewModel.cart

        val subtotal = cart.getFormattedSubtotal()
        val tax = cart.getFormattedTax()
        val total =  cart.getFormattedTotal()

        totalItemsValueTv.setText(subtotal)
        taxValueTv.setText(tax)
        totalValueTv.setText(total)
    }

    private fun showEmptyCartLayout() {


        if(viewModel.isCartEmpty()) {

            // remove the regular cart layout views
            binding.orderDetailsGroup.visibility = View.GONE


            // include empty cart layout
            binding.emptyCartView.root.visibility = View.VISIBLE

            binding.emptyCartView.goShoppingBtn.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_cartFragment_to_menuFragment)
            }

        }


    }

    private fun showOrderProcessedLayout() {
        // remove the regular cart layout views
        binding.orderDetailsGroup.visibility = View.GONE


        // include order processed layout
        binding.orderProcessedView.root.visibility = View.VISIBLE

        binding.orderProcessedView.backMenuBtn.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_cartFragment_to_menuFragment)
        }
    }


}