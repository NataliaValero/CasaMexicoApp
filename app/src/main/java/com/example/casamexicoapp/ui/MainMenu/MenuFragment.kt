package com.example.casamexicoapp.ui.MainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.casamexicoapp.R
import com.example.casamexicoapp.data.repository.MenuRepositoryImpl
import com.example.casamexicoapp.data.source.FirestoreFactory
import com.example.casamexicoapp.data.source.MenuDataSource
import com.example.casamexicoapp.data.viewModel.MainVMFactory
import com.example.casamexicoapp.data.viewModel.MainViewModel
import com.example.casamexicoapp.databinding.FragmentMenuBinding
import com.example.casamexicoapp.model.Category


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding

    // Initialize viewmodel
    private val viewModel:MainViewModel by activityViewModels {
        MainVMFactory(MenuRepositoryImpl(MenuDataSource(FirestoreFactory.firestore)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMenuBinding.bind(view)

        subscribeObservers()

    }

    private fun subscribeObservers() {

        viewModel.categories.observe(viewLifecycleOwner, this::onCategoriesReady)

    }

    private fun onCategoriesReady(categories: List<Category>) {
        val tab = binding.menuTl

        categories.forEach{ category ->
            tab.addTab(tab.newTab().setText(category.categoryName))
        }
    }
}

