package com.cherry.carenet.data

import com.cherry.carenet.models.HelpRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RequestRepository {

    // 🔥 Firebase reference
    private val db =
        FirebaseDatabase.getInstance()
            .getReference("requests")



    // ✅ CREATE REQUEST
    fun createRequest(request: HelpRequest) {

        val requestId = db.push().key ?: return

        request.requestId = requestId

        db.child(requestId).setValue(request)
    }



    // ✅ GET ALL REQUESTS (REAL-TIME)
    fun getRequests(onResult: (List<HelpRequest>) -> Unit) {

        db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val requestsList = mutableListOf<HelpRequest>()

                for (child in snapshot.children) {

                    val request =
                        child.getValue(HelpRequest::class.java)

                    if (request != null) {
                        requestsList.add(request)
                    }
                }

                onResult(requestsList)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }



    // ✅ ACCEPT REQUEST
    fun acceptRequest(
        requestId: String,
        receiverId: String,
        chatRoomId: String
    ) {

        val updates = mapOf<String, Any>(
            "status" to "accepted",
            "receiverId" to receiverId,
            "chatRoomId" to chatRoomId
        )

        db.child(requestId).updateChildren(updates)
    }



    // ✅ COMPLETE + DELETE REQUEST
    fun completeRequest(requestId: String) {

        db.child(requestId).removeValue()
    }
}