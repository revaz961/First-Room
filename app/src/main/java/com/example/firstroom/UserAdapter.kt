package com.example.firstroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstroom.databinding.FragmentUsersBinding
import com.example.firstroom.databinding.UserLayoutBinding

class UserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var users = mutableListOf<User>()
    fun setUsers(list: List<User>) {
        users.clear()
        users.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            UserLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> holder.bind()
        }
    }

    override fun getItemCount() = users.size

    inner class UserViewHolder(val binding: UserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.firstName.text = users[adapterPosition].firstName
            binding.lastName.text = users[adapterPosition].lastName
            binding.age.text = users[adapterPosition].age.toString()
            binding.address.text = users[adapterPosition].address
            binding.height.text = users[adapterPosition].height.toString()
            binding.profile.text = users[adapterPosition].profile
        }
    }
}