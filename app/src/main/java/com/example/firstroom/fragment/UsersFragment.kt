package com.example.firstroom.fragment

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstroom.model.MainViewModel
import com.example.firstroom.adapter.UserAdapter
import com.example.firstroom.databinding.DialogLayoutBinding
import com.example.firstroom.databinding.FragmentUsersBinding
import com.example.firstroom.extension.init


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

    private fun initRecycler() {
        adapter = UserAdapter { user ->
            val dialog = Dialog(requireContext())
            val dialogBinding = DialogLayoutBinding.inflate(layoutInflater)
            dialog.init(dialogBinding.root)
            dialogBinding.btnYes.setOnClickListener {
                userviewModel.delete(user)
                dialog.cancel()
            }
            dialogBinding.btnNo.setOnClickListener {
                dialog.cancel()
            }
            dialog.show()
        }
        binding!!.rvUsers.adapter = adapter
        binding!!.rvUsers.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observes() {
        userviewModel._userLiveData.observe(viewLifecycleOwner, {
            adapter!!.setUsers(it)
        })
    }

    private fun screenWidth(): Int {
        val displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().display?.getRealMetrics(displayMetrics)
        } else {
            requireActivity().windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        }
        return displayMetrics.widthPixels
    }
}