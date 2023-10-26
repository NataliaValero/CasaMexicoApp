package com.example.casamexicoapp.ui.mainMenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
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

        // Cuando se cargan las categorías se da select() al último tab que había quedado seleccionado
        binding.menuTl.getTabAt(viewModel.tabSelectedPosition)?.select()

        // on tab selected
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {

                    // posicion del tab
                    var position = it.position


                    // inicializa con la lista vacía hasta que va a buscar los productos
                    productsAdapter.setList(emptyList())

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

        productsAdapter = ProductsAdapter(products = emptyList(), object : ProductsAdapter.onClickListener{
            override fun onItemClick(product: Product) {

                Log.v("Selected", product.toString())

                //guardar este producto este producto en el viewmodel
                viewModel.onProductSelected(product)


                view?.findNavController()?.navigate(R.id.action_menuFragment_to_cartItemFragment)

            }

        })



        binding.productsRv.adapter = productsAdapter
        binding.productsRv.layoutManager = GridLayoutManager(context, 2)
    }
}

