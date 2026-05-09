package com.cherry.carenet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.cherry.carenet.data.UserRepository
import com.cherry.carenet.models.User

class UserViewModel : ViewModel() {

    private val repo = UserRepository()

    val users = mutableStateListOf<User>()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        repo.getUsers { list ->
            users.clear()
            users.addAll(list)
        }
    }
}