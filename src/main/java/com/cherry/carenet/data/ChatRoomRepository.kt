package com.cherry.carenet.data

import com.cherry.carenet.models.ChatRoom
import com.google.firebase.database.*

class ChatRoomRepository {

    private val db =
        FirebaseDatabase.getInstance()
            .getReference("chatrooms")



    // ✅ CREATE ROOM
    fun createRoom(room: ChatRoom) {

        db.child(room.roomId)
            .setValue(room)
    }



    // ✅ GET USER ROOMS
    fun getUserRooms(
        currentUserId: String,
        onResult: (List<ChatRoom>) -> Unit
    ) {

        db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val rooms = mutableListOf<ChatRoom>()

                for (child in snapshot.children) {

                    val room =
                        child.getValue(ChatRoom::class.java)

                    if (
                        room != null &&
                        (
                                room.user1Id == currentUserId ||
                                        room.user2Id == currentUserId
                                )
                    ) {

                        rooms.add(room)
                    }
                }

                onResult(rooms)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}