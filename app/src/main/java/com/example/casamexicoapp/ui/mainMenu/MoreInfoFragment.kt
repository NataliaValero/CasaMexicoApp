package com.example.casamexicoapp.ui.mainMenu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.casamexicoapp.R
import com.example.casamexicoapp.data.repository.AuthRepositoryImpl
import com.example.casamexicoapp.data.source.AuthDataSource
import com.example.casamexicoapp.data.source.FirebaseAuthFactory
import com.example.casamexicoapp.data.viewModel.AuthVMFactory
import com.example.casamexicoapp.data.viewModel.AuthViewModel
import com.example.casamexicoapp.databinding.FragmentMoreInfoBinding
import com.example.casamexicoapp.ui.login.MainActivity


class MoreInfoFragment : Fragment(R.layout.fragment_more_info) {

    private lateinit var binding: FragmentMoreInfoBinding

    // Initialize viewmodel
    private val viewModel: AuthViewModel by activityViewModels {
        AuthVMFactory(AuthRepositoryImpl(AuthDataSource(FirebaseAuthFactory.auth)))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoreInfoBinding.bind(view)

        loadUserInfo()

    }

    private fun loadUserInfo() {
        val user = viewModel.currentUser
        user?.let {
            binding.userNameTv.text = it.displayName
            binding.userEmailTv.text = it.email
        }

        binding.logoutIv.setOnClickListener{
            viewModel.logout()

            // Va al menu una vez ingresado usuario
            val intent = Intent(context, MainActivity::class.java)

            //indicates that the existing task should be cleared before the activity is started
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            requireActivity().finish()

        }
    }


}