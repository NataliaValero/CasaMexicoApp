package com.example.casamexicoapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.casamexicoapp.R
import com.example.casamexicoapp.data.repository.AuthRepositoryImpl
import com.example.casamexicoapp.data.repository.MenuRepositoryImpl
import com.example.casamexicoapp.data.source.AuthDataSource
import com.example.casamexicoapp.data.source.FirebaseAuthFactory
import com.example.casamexicoapp.data.source.FirestoreFactory
import com.example.casamexicoapp.data.source.MenuDataSource
import com.example.casamexicoapp.data.viewModel.AuthVMFactory
import com.example.casamexicoapp.data.viewModel.AuthViewModel
import com.example.casamexicoapp.data.viewModel.MainVMFactory
import com.example.casamexicoapp.data.viewModel.MainViewModel
import com.example.casamexicoapp.databinding.FragmentMainBinding
import com.example.casamexicoapp.ui.mainMenu.MainActivity2


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    // initialize MainViewModel
    // Initialize viewmodel
    private val viewModel: AuthViewModel by activityViewModels {
        AuthVMFactory(AuthRepositoryImpl(AuthDataSource(FirebaseAuthFactory.auth)))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        // login btn action
        binding.loginBtn1.setOnClickListener{
            view.findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }


        // signup btn action
        binding.signupTv.setOnClickListener{
            view.findNavController().navigate(R.id.action_mainFragment_to_signUpFragment)
        }

    }


    override fun onStart() {
        super.onStart()

        // check if user is signed in (non- null) and go to menu
        val currentUser = viewModel.currentUser
        Log.v("current user", currentUser.toString())
        if(currentUser != null) {

            // va directamente a menu
            val intent = Intent(context, MainActivity2::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}