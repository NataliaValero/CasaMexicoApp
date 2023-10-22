package com.example.casamexicoapp.ui.mainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.casamexicoapp.R
import com.example.casamexicoapp.data.repository.MenuRepositoryImpl
import com.example.casamexicoapp.data.source.FirestoreFactory
import com.example.casamexicoapp.data.source.MenuDataSource
import com.example.casamexicoapp.data.viewModel.MainVMFactory
import com.example.casamexicoapp.data.viewModel.MainViewModel
import com.example.casamexicoapp.databinding.FragmentMenuBinding
import com.example.casamexicoapp.model.Category
import com.example.casamexicoapp.model.Product
import com.example.casamexicoapp.ui.adapter.ProductsAdapter
import com.google.android.material.tabs.TabLayout


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding

    // Adapter
    private lateinit var productsAdapter: ProductsAdapter

    // Initialize viewmodel
    private val viewModel:MainViewModel by activityViewModels {
        MainVMFactory(MenuRepositoryImpl(MenuDataSource(FirestoreFactory.firestore)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMenuBinding.bind(view)

        initializeAdapters()
        subscribeObservers()

    }

    private fun subscribeObservers() {
        // Live data notifica a la funcion las categorias
        viewModel.categories.observe(viewLifecycleOwner, this::onCategoriesReady)

        viewModel.products.observe(viewLifecycleOwner) {

            onProductsReady(it)
        }
    }



    private fun onProductsReady(products: List<Product>) {
        productsAdapter.setList(products)
    }

    private fun onCategoriesReady(categories: List<Category>) {
        val tab = binding.menuTl


        // agregar categories names al tab layout
        categories.forEach{ category ->
            tab.addTab(tab.newTab().setText(category.name))

        }


        // on tab selected
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {

                    // posicion del tab
                    var position = it.position


                    // busca los productos
                    viewModel.loadProductFromCategoryPosition(position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    private fun initializeAdapters() {

        productsAdapter = ProductsAdapter(products = emptyList())
        binding.productsRv.adapter = productsAdapter
        binding.productsRv.layoutManager = GridLayoutManager(context, 2)
    }
}

