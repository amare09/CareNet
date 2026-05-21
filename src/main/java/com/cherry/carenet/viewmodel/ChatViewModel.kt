package com.cherry.carenet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.cherry.carenet.data.BlockedUsersRepository
import com.cherry.carenet.data.ChatRepository
import com.cherry.carenet.data.NotificationRepository
import com.cherry.carenet.models.AppNotification
import com.cherry.carenet.models.ChatMessage
import com.google.firebase.auth.FirebaseAuth

class ChatViewModel : ViewModel() {

    private val blockedRepo = BlockedUsersRepository()
    private val notificationRepo = NotificationRepository()
    private val auth = FirebaseAuth.getInstance()
    private val repo = ChatRepository()

    val messages = mutableStateListOf<ChatMessage>()

    private val currentUserId = auth.currentUser?.uid ?: ""
    private val currentUserName = auth.currentUser?.email ?: "User"

    // 🔥 LOAD MESSAGES (BLOCK SAFE)
    fun loadMessages(roomId: String) {

        repo.listenMessages(roomId) { list ->

            // 🔥 filter blocked users BEFORE showing messages
            val filtered = list


            messages.clear()
            messages.addAll(filtered)
        }
    }

    // 🔥 SEND MESSAGE (FULLY FIXED)
    fun sendMessage(
        roomId: String,
        senderId: String,
        receiverId: String,
        text: String
    ) {

        val msg = ChatMessage(
            senderId = currentUserId,
            senderName=currentUserName,
            receiverId=receiverId,
            message = text
        )

        // 🔥 CHECK IF ANY BLOCK EXISTS (BOTH SIDES SAFE)
        blockedRepo.isBlocked(receiverId, currentUserId) { blocked1 ->

            blockedRepo.isBlocked(currentUserId, receiverId) { blocked2 ->

                if (!blocked1 && !blocked2) {

                    // ✅ SEND MESSAGE
                    repo.sendMessage(roomId, msg)

                    // 🔔 NOTIFICATION
                    notificationRepo.sendNotification(
                        AppNotification(
                        userId = receiverId,
                            type = "message",

                                    // title = "New Message",
                        message = "You received a new message",
                        )
                    )
                }
            }
        }
    }
}