package com.example.firstroom.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.firstroom.model.MainViewModel
import com.example.firstroom.R
import com.example.firstroom.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentMainBinding.inflate(inflater, container, false)
            init()
        }
        return binding!!.root
    }

    private fun init() {
        binding!!.btnRegister.setOnClickListener {
            register()
        }

        binding!!.btnUsers.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_usersFragment)
        }
    }

    private fun register() {
        if (binding!!.root.children.filter { it is EditText }
                .all { it is EditText && it.text.isNotEmpty() }) {
            mainViewModel.write(
                binding!!.firstName.text.toString(),
                binding!!.lastName.text.toString(),
                binding!!.age.text.toString().toInt(),
                binding!!.address.text.toString(),
                binding!!.height.text.toString().toInt(),
                binding!!.profile.text.toString(),
            )
            binding!!.root.children.forEach { if (it is EditText) it.text.clear() }
        } else{
            Snackbar.make(binding!!.root,"fill all input",Snackbar.LENGTH_LONG).show()
        }
    }
}