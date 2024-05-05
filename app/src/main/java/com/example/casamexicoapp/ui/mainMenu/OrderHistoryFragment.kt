package com.example.casamexicoapp.ui.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
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
import com.example.casamexicoapp.databinding.FragmentOrderHistoryBinding
import com.example.casamexicoapp.model.Cart
import com.example.casamexicoapp.ui.adapter.OrderAdapter


class OrderHistoryFragment : Fragment(R.layout.fragment_order_history) {



    private lateinit var binding: FragmentOrderHistoryBinding
    private lateinit var ordersAdapter : OrderAdapter

    // Initialize viewmodel
    private val viewModel: MainViewModel by activityViewModels {
        MainVMFactory(MenuRepositoryImpl(MenuDataSource(FirestoreFactory.firestore)), OrderRepositoryImpl(
            OrdersDataSource(FirestoreFactory.firestore)
        ))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderHistoryBinding.bind(view)


        initializeAdapters(view)
        suscribeObservers()
        loadOrders()
    }





    private fun initializeAdapters(view: View) = with(binding) {
        ordersAdapter = OrderAdapter(emptyList(),object : OrderAdapter.onClickListener {
            override fun onItemClick(item: Cart) {

                // guardar order selected en view model
                viewModel.onOrderSelected(item)

                // navegar a orderDescription Fragment
                view?.findNavController()?.navigate(R.id.action_orderHistoryFragment_to_orderDescriptionFragment)
            }
        }

        )
        ordersRv.adapter = ordersAdapter
        ordersRv.layoutManager= LinearLayoutManager(context)

    }
    private fun suscribeObservers() {
        viewModel.orders.observe(viewLifecycleOwner) {
            ordersAdapter.setOrderItemsList(it)

            // Desplegar ui de zero orders si la lista de ordenes esta vacía
            binding.zeroOrdersLayout.root.isVisible = it.isEmpty()

        }
    }


    private fun loadOrders() {
        viewModel.getOrders()
    }

}