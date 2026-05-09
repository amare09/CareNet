package com.cherry.carenet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.cherry.carenet.data.ChatRoomRepository
import com.cherry.carenet.models.ChatRoom
import com.google.firebase.auth.FirebaseAuth

class ChatRoomViewModel : ViewModel() {

    private val repo = ChatRoomRepository()

    private val auth = FirebaseAuth.getInstance()

    val rooms = mutableStateListOf<ChatRoom>()




    init {

        loadRooms()
    }




    private fun loadRooms() {

        val currentUserId =
            auth.currentUser?.uid ?: return

        repo.getUserRooms(currentUserId) {

            rooms.clear()

            rooms.addAll(it)
        }
    }
}