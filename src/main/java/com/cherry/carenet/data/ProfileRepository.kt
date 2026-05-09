package com.cherry.carenet.data

import com.cherry.carenet.models.User
import com.google.firebase.database.*

class ProfileRepository {

    private val db =
        FirebaseDatabase.getInstance()
            .getReference("users")



    // ✅ LOAD USER
    fun getUser(
        uid: String,
        onResult: (User?) -> Unit
    ) {

        db.child(uid)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        val user =
                            snapshot.getValue(User::class.java)

                        onResult(user)
                    }

                    override fun onCancelled(error: DatabaseError) {

                        onResult(null)
                    }
                }
            )
    }



    // ✅ UPDATE USER
    fun updateUser(user: User) {

        db.child(user.uid)
            .setValue(user)
    }
}