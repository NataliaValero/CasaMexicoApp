package com.example.casamexicoapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
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
import com.example.casamexicoapp.databinding.FragmentSignUpBinding
import com.example.casamexicoapp.ui.mainMenu.MainActivity2
import com.google.firebase.firestore.FirebaseFirestore

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding

    // Initialize Auth viewmodel
    private val authViewModel: AuthViewModel by activityViewModels {
        AuthVMFactory(AuthRepositoryImpl(AuthDataSource(FirebaseAuthFactory.auth)))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)


        setUpViews()
        suscribeObservers()

    }

    private fun setUpViews() {
        binding.signupBtn.setOnClickListener{
            val name = binding.nameInputEt.text.toString()
            val email = binding.emailInputEt.text.toString()
            val password = binding.passwordInputEt.text.toString()
            authViewModel.signup(name, email, password)

        }

        binding.signinTv.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun suscribeObservers() {

        authViewModel.signup.observe(viewLifecycleOwner) {
            // verifica que el usuario no sea nulo (usuario existente)
            if(it != null) {


                // Va al menu cuando se registro y logeo el usuario exitosamente
                val intent = Intent(context, MainActivity2::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(context, "An error has ocurred", Toast.LENGTH_SHORT).show()
            }
        }
    }
}