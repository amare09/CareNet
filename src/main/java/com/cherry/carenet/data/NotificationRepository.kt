package com.cherry.carenet.data

import com.cherry.carenet.models.AppNotification
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NotificationRepository {

    private val db =
        FirebaseDatabase.getInstance()
            .getReference("notifications")



    // ✅ ADD NOTIFICATION
    fun sendNotification(notification: AppNotification) {

        val id =
            db.child(notification.userId)
                .push()
                .key ?: return

        notification.id = id

        db.child(notification.userId)
            .child(id)
            .setValue(notification)
    }



    // ✅ LISTEN REAL-TIME
    fun getNotifications(
        userId: String,
        onResult: (List<AppNotification>) -> Unit
    ) {

        db.child(userId)
            .addValueEventListener(
                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        val list = mutableListOf<AppNotification>()

                        for (child in snapshot.children) {

                            val item =
                                child.getValue(AppNotification::class.java)

                            if (item != null) {
                                list.add(item)
                            }
                        }

                        list.sortByDescending { it.timestamp }

                        onResult(list)
                    }

                    override fun onCancelled(error: DatabaseError) {}
                }
            )
    }



    // ✅ MARK AS READ
    fun markAsRead(userId: String, notifId: String) {

        db.child(userId)
            .child(notifId)
            .child("read")
            .setValue(true)
    }
}