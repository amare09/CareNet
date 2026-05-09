package com.cherry.carenet.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RoleNavigationViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("users")

    fun routeUser(
        onUser: () -> Unit,
        onOrg: () -> Unit,
        onAdmin: () -> Unit
    ) {

        val uid = auth.currentUser?.uid ?: return

        db.child(uid).get().addOnSuccessListener { snapshot ->

            val role = snapshot.child("role").value.toString()

            when (role) {

                "user" -> onUser()

                "organization" -> onOrg()

                "admin" -> onAdmin()

                else -> onUser()
            }
        }
    }
}