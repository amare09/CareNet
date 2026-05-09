package com.cherry.carenet.viewmodel

import com.cherry.carenet.data.HomeRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cherry.carenet.models.HelpRequest
import com.cherry.carenet.models.User


class HomeViewModel: ViewModel (){
    private val repository= HomeRepository()
    var user by mutableStateOf(User())
        private set
    var requests by mutableStateOf(listOf<HelpRequest>())
        private set

    init {
        loadHome()
    }
    private fun loadHome() {

        // 👤 load user
        repository.getUser {
            user = it
        }

        // 📋 load help requests
        repository.getRequests {
            requests = it
        }
    }
    fun createRequest(
        title: String,
        description: String,
        onDone: (Boolean) -> Unit
    ) {
        repository.addRequest(title, description) {
            onDone(it)
        }
    }
}



