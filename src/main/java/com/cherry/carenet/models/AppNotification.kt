package com.cherry.carenet.models



data class AppNotification(
    var id: String = "",
    var userId: String = "",
    var type: String = "",   // "request", "message", "emergency"
    var message: String = "",
    var timestamp: Long = System.currentTimeMillis(),
    var read: Boolean = false
)