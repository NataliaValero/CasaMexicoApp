package com.example.casamexicoapp.ui.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.findNavController
import com.example.casamexicoapp.R
import com.example.casamexicoapp.databinding.FragmentMainBinding
import com.example.casamexicoapp.ui.MainMenu.MainActivity2


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)



        binding.loginBtn1.setOnClickListener{
            view.findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }


//        binding.signupTv.setOnClickListener{
//            view.findNavController().navigate(R.id.action_mainFragment_to_signUpFragment)
//        }


        binding.signupTv.setOnClickListener {
            val intent = Intent(context, MainActivity2::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    }
}