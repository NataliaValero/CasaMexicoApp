package com.example.casamexicoapp.ui.Login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.casamexicoapp.R
import com.example.casamexicoapp.data.repository.AuthRepositoryImpl
import com.example.casamexicoapp.data.source.AuthDataSource
import com.example.casamexicoapp.data.source.FirebaseAuthFactory
import com.example.casamexicoapp.data.viewModel.AuthVMFactory
import com.example.casamexicoapp.data.viewModel.AuthViewModel
import com.example.casamexicoapp.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    // Initialize viewmodel
    private val viewModel: AuthViewModel by activityViewModels {
        AuthVMFactory(AuthRepositoryImpl(AuthDataSource(FirebaseAuthFactory.auth)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)



        binding.signupBtn.setOnClickListener{
            val name = binding.nameInputEt.text.toString()
            val email = binding.emailInputEt.text.toString()
            val password = binding.passwordInputEt.text.toString()
            viewModel.signup(name, email, password)
        }

        viewModel.signup.observe(viewLifecycleOwner) {
            Log.v("SIGNUP", "user=  $it")
        }

        binding.signinTv.setOnClickListener{
            view.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
}