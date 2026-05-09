package com.cherry.carenet.data

import com.cherry.carenet.models.HelpRequest
import com.cherry.carenet.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class HomeRepository {
    private val auth= FirebaseAuth.getInstance()
    private val database= FirebaseDatabase.getInstance().reference

    fun getUser(onResult: (User) -> Unit) {

        val userId = auth.currentUser?.uid ?: return

        database.child("users")
            .child(userId)
            .get()
            .addOnSuccessListener { snapshot ->

                val user = snapshot.getValue(User::class.java)

                if (user != null) {
                    onResult(user)
                }
            }
    }

    fun getRequests(onResult: (List<HelpRequest>) -> Unit) {

        database.child("requests")
            .get()
            .addOnSuccessListener { snapshot ->

                val list = mutableListOf<HelpRequest>()

                for (child in snapshot.children) {
                    val item = child.getValue(HelpRequest::class.java)
                    if (item != null) {
                        list.add(item)
                    }
                }

                onResult(list)
            }
    }

    fun addRequest(
        title: String,
        description: String,
        onComplete: (Boolean) -> Unit
    ) {

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val requestId = database.child("requests").push().key ?: return

        val request = HelpRequest(
            id = requestId,
            title = title,
            description = description,
            userId = userId,
            distance = "Unknown"
        )

        database.child("requests")
            .child(requestId)
            .setValue(request)
            .addOnCompleteListener {
                onComplete(it.isSuccessful)
            }
    }


}


