package com.example.firstroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstroom.databinding.FragmentUsersBinding


class UsersFragment : Fragment() {

    private var binding: FragmentUsersBinding? = null
    private var adapter: UserAdapter? = null
    private val userviewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentUsersBinding.inflate(inflater, container, false)
            init()
        }
        return binding!!.root
    }

    private fun init() {
        initRecycler()
        observes()
    }

    private fun initRecycler(){
        adapter = UserAdapter()
        binding!!.rvUsers.adapter = adapter
        binding!!.rvUsers.layoutManager = GridLayoutManager(requireContext(),2)
    }

    private fun observes(){
        userviewModel._userLiveData.observe(viewLifecycleOwner,{
            adapter!!.setUsers(it)
        })
    }
}