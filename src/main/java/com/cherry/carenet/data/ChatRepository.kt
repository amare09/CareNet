package com.cherry.carenet.data

import com.cherry.carenet.models.ChatMessage
import com.google.firebase.database.*

class ChatRepository {

    private val db =
        FirebaseDatabase.getInstance()
            .getReference("chats")



    // ✅ SEND MESSAGE
    fun sendMessage(roomId: String, chat: ChatMessage) {

        val messageId =
            db.child(roomId)
                .child("messages")
                .push()
                .key ?: return

        db.child(roomId)
            .child("messages")
            .child(messageId)
            .setValue(chat)
    }



    // ✅ LISTEN MESSAGES REAL-TIME
    fun listenMessages(
        roomId: String,
        onResult: (List<ChatMessage>) -> Unit
    ) {

        db.child(roomId)
            .child("messages")
            .addValueEventListener(
                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        val list = mutableListOf<ChatMessage>()

                        for (child in snapshot.children) {

                            val msg =
                                child.getValue(ChatMessage::class.java)

                            if (msg != null) {
                                list.add(msg)
                            }
                        }

                        // optional sorting (important)
                        list.sortBy { it.timestamp }

                        onResult(list)
                    }

                    override fun onCancelled(error: DatabaseError) {}
                }
            )
    }
}