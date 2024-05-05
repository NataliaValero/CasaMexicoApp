package com.example.casamexicoapp.ui.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.casamexicoapp.databinding.FragmentMoreInfoBinding
import com.example.casamexicoapp.databinding.FragmentOrderDescriptionBinding
import com.example.casamexicoapp.ui.adapter.CartItemAdapter


class OrderDescriptionFragment : Fragment(R.layout.fragment_order_description) {

    private lateinit var binding: FragmentOrderDescriptionBinding
    private lateinit var cartItemsAdapter: CartItemAdapter

    // Initialize viewmodel
    private val viewModel: MainViewModel by activityViewModels {
        MainVMFactory(
            MenuRepositoryImpl(MenuDataSource(FirestoreFactory.firestore)), OrderRepositoryImpl(
            OrdersDataSource(FirestoreFactory.firestore)
            )
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderDescriptionBinding.bind(view)


        initializeAdapters()
        setUpViews(view)

    }


    private fun setUpViews(view: View) = with(binding){

        val order = viewModel.orderSelected

        orderIdTv.text = ("Order #${order.getFormattedId()}")
        dateTv.text= order.getFormattedDate()
        totalItemsValueTv.text = order.getFormattedSubtotal()
        taxValueTv.text = order.getFormattedTax()
        totalValueTv.text = order.getFormattedTotal()
        cartItemsAdapter.setCartItemsList(order.cartItemList)

        backIcon.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_orderDescriptionFragment_to_orderHistoryFragment)
        }

    }

    private fun initializeAdapters() = with(binding) {

        cartItemsAdapter = CartItemAdapter(emptyList(), isCartReyclerView = false)


        itemsOrderRv.adapter = cartItemsAdapter
        itemsOrderRv.layoutManager = LinearLayoutManager(context)

    }
}