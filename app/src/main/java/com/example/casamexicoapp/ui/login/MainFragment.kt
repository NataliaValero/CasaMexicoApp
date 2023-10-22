package com.example.casamexicoapp.ui.login

import android.content.Intent
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
import com.example.casamexicoapp.databinding.FragmentMainBinding
import com.example.casamexicoapp.ui.mainMenu.MainActivity2


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    // initialize MainViewModel
    private val viewModel: MainViewModel by activityViewModels {
        MainVMFactory(MenuRepositoryImpl(MenuDataSource(FirestoreFactory.firestore)))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)



//        binding.loginBtn1.setOnClickListener{
//            view.findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
//        }


//        binding.signupTv.setOnClickListener{
//            view.findNavController().navigate(R.id.action_mainFragment_to_signUpFragment)
//        }



        // Ir a menu con sign up temporal
        binding.signupTv.setOnClickListener {
            val intent = Intent(context, MainActivity2::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

// agregar productos a menu de firestore con boton
//        binding.loginBtn1.setOnClickListener{
//            viewModel.addProducts()
//        }

    }
}