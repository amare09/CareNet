package com.cherry.carenet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.cherry.carenet.data.NotificationRepository
import com.cherry.carenet.models.AppNotification

class NotificationViewModel : ViewModel() {

    private val repo = NotificationRepository()

    val notifications =
        mutableStateListOf<AppNotification>()



    fun loadNotifications(userId: String) {

        repo.getNotifications(userId) { list ->

            notifications.clear()
            notifications.addAll(list)
        }
    }



    fun addNotification(notification: AppNotification) {
        repo.sendNotification(notification)
    }



    fun markRead(userId: String, notifId: String) {
        repo.markAsRead(userId, notifId)
    }
}