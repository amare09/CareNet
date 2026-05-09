package com.cherry.carenet.models

data class ChatMessage(
    val sender:String="",
    val receiverId: String="",
    val message:String="",
    val timestamp: Long=System.currentTimeMillis()



)
