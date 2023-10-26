package com.example.casamexicoapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.casamexicoapp.R
import com.example.casamexicoapp.data.repository.AuthRepositoryImpl
import com.example.casamexicoapp.data.source.AuthDataSource
import com.example.casamexicoapp.data.source.FirebaseAuthFactory
import com.example.casamexicoapp.data.viewModel.AuthVMFactory
import com.example.casamexicoapp.data.viewModel.AuthViewModel
import com.example.casamexicoapp.databinding.FragmentLoginBinding
import com.example.casamexicoapp.ui.mainMenu.MainActivity2


class LoginFragment : Fragment(R.layout.fragment_login) {


    private lateinit var binding: FragmentLoginBinding

    // Initialize viewmodel
    private val viewModel:AuthViewModel by activityViewModels {
        AuthVMFactory(AuthRepositoryImpl(AuthDataSource(FirebaseAuthFactory.auth)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)


        binding.loginBtn2.setOnClickListener{

            val email = binding.emailInputEt.text.toString()
            val password  = binding.passwordInputEt.text.toString()


            //Logea usuario
            viewModel.login(email, password)

            // Va al menu una vez ingresado usuario
            val intent = Intent(context, MainActivity2::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    //viewModel.getOrder()

    }

}