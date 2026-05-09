package com.cherry.carenet.viewmodel

import androidx.lifecycle.ViewModel
import com.cherry.carenet.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserSessionViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("users")

    var currentUser: User? = null
        private set

    fun loadUser(onLoaded: (User?) -> Unit) {

        val uid = auth.currentUser?.uid

        if (uid == null) {
            onLoaded(null)
            return
        }

        db.child(uid).addListenerForSingleValueEvent(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val user = snapshot.getValue(User::class.java)

                    currentUser = user

                    onLoaded(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    onLoaded(null)
                }
            }
        )
    }

    fun logout() {
        auth.signOut()
        currentUser = null
    }
}