package com.cherry.carenet.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cherry.carenet.data.ProfileRepository
import com.cherry.carenet.models.User
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel() {

    private val repo = ProfileRepository()

    val userState =
        mutableStateOf(User())



    init {

        loadUser()
    }



    // ✅ LOAD CURRENT USER
    fun loadUser() {

        val uid =
            FirebaseAuth.getInstance()
                .currentUser?.uid ?: return

        repo.getUser(uid) { user ->

            if (user != null) {
                userState.value = user
            }
        }
    }



    // ✅ SAVE CHANGES
    fun saveProfile(
        name: String,
        bio: String
    ) {

        val current =
            userState.value

        val updatedUser =
            current.copy(
                name = name,
                bio = bio
            )

        repo.updateUser(updatedUser)

        userState.value = updatedUser
    }
}