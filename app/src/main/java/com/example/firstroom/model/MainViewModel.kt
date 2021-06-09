package com.example.firstroom.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstroom.database.User
import com.example.firstroom.database.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    val _userLiveData: LiveData<List<User>> = UserDatabase.db.userDao().getAll()

    fun write(
        firstName: String?,
        lastName: String?,
        age: Int?,
        address: String?,
        height: Int?,
        profile: String?
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                UserDatabase.db.userDao()
                    .insert(User(firstName, lastName, age, address, height, profile))
            }
        }
    }

    fun delete(user: User){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                UserDatabase.db.userDao().delete(user)
            }
        }
    }
}