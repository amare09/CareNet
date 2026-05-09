package com.cherry.carenet.models

data class ChatRoom(

    var roomId: String = "",

    var user1Id: String = "",

    var user2Id: String = "",

    var user1Name: String = "",

    var user2Name: String = "",

    var lastMessage: String = ""
)