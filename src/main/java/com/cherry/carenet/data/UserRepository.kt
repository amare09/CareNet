package com.cherry.carenet.data

import com.cherry.carenet.models.User
import com.google.firebase.database.*

class UserRepository {

    private val db = FirebaseDatabase.getInstance().reference.child("users")

    fun getUsers(onResult: (List<User>) -> Unit) {

        db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<User>()

                for (child in snapshot.children) {

                    val user = child.getValue(User::class.java)

                    if (user != null) {
                        list.add(user)
                    }
                }

                onResult(list)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}