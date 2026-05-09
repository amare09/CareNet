package com.cherry.carenet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.cherry.carenet.data.BlockedUsersRepository


class BlockedUsersViewModel : ViewModel() {

    private val repo = BlockedUsersRepository()

    val blockedUsers =
        mutableStateListOf<String>()



    fun loadBlockedUsers(currentUserId: String) {

        repo.getBlockedUsers(currentUserId) {

            blockedUsers.clear()
            blockedUsers.addAll(it)
        }
    }



    fun blockUser(
        currentUserId: String,
        blockedUserId: String
    ) {

        repo.blockUser(currentUserId, blockedUserId)
    }



    fun unblockUser(
        currentUserId: String,
        blockedUserId: String
    ) {

        repo.unblockUser(currentUserId, blockedUserId)
    }
}