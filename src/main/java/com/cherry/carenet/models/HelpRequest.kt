package com.cherry.carenet.models

data class HelpRequest(

var id: String="",
//var title: String="",
var distance: String="",
//var description: String="",
var userId: String="",
var requestId: String = "",
var title: String = "",
var description: String = "",
var senderId: String = "",
var senderName: String = "",
var status: String = "pending", // pending | accepted | completed
var timestamp: Long = System.currentTimeMillis(),
var receiverId: String="",
var chatRoomId: String = "",
    var isEmergency: Boolean=false

)