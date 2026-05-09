package com.cherry.carenet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.cherry.carenet.data.RequestRepository
import com.cherry.carenet.models.HelpRequest
import com.cherry.carenet.utilis.getRoomId
import com.google.firebase.auth.FirebaseAuth
import com.cherry.carenet.data.ChatRoomRepository
import com.cherry.carenet.models.ChatRoom
import com.cherry.carenet.data.NotificationRepository
import com.cherry.carenet.models.AppNotification


class RequestViewModel : ViewModel() {

    private val repo = RequestRepository()
    private val chatRoomRepo = ChatRoomRepository()

    private val notificationRepo= NotificationRepository()
    private val auth = FirebaseAuth.getInstance()

    val requests = mutableStateListOf<HelpRequest>()




    init {
        loadRequests()
    }




    // ✅ LOAD ALL REQUESTS
    private fun loadRequests() {

        repo.getRequests { list ->

            requests.clear()

            requests.addAll(list)
        }
    }




    // ✅ CREATE REQUEST
    fun createRequest(
        title: String,
        description: String
    ) {

        val currentUser = auth.currentUser ?: return

        val request = HelpRequest(

            title = title,

            description = description,

            senderId = currentUser.uid,

            senderName = currentUser.email ?: "User"
        )

        repo.createRequest(request)
    }




    // ✅ ACCEPT REQUEST
    fun acceptRequest(

        requestId: String,

        senderId: String,

        senderName: String,

        onRoomReady:(String, String)-> Unit


    )
    {

        val currentUser =
            auth.currentUser ?: return



        val receiverId = currentUser.uid

        val receiverName =
            currentUser.email ?: "User"



        val roomId =
            getRoomId(senderId, receiverId)



        // ✅ CREATE CHAT ROOM
        val room = ChatRoom(

            roomId = roomId,

            user1Id = senderId,

            user2Id = receiverId,

            user1Name = senderName,

            user2Name = receiverName,

            lastMessage = ""
        )



        chatRoomRepo.createRoom(room)



        // ✅ UPDATE REQUEST
        repo.acceptRequest(

            requestId = requestId,

            receiverId = receiverId,

            chatRoomId = roomId
        )
        // ✅ SEND NOTIFICATION TO REQUEST OWNER
        notificationRepo.sendNotification(
            AppNotification(
                userId = senderId,
                type = "request",
                message = "Your help request was accepted"
            )
        )
        //return room info to ui
        onRoomReady(roomId,senderName)

    }
    // ✅ COMPLETE REQUEST
    fun completeRequest(requestId: String) {

        repo.completeRequest(requestId)
    }
}