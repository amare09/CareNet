package com.cherry.carenet.utilis



import com.google.firebase.auth.FirebaseAuth

object AdminUtils {

    // ✅ PUT ADMIN EMAIL HERE
    private val adminEmails = listOf(
        "cherryladwar90@gmail.com"
    )

    fun isAdmin(): Boolean {

        val currentUser =
            FirebaseAuth.getInstance().currentUser

        return currentUser?.email in adminEmails
    }
}