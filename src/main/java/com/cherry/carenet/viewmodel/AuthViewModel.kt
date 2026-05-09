package com.cherry.carenet.viewmodel

import androidx.lifecycle.ViewModel
import com.cherry.carenet.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
private val db= FirebaseDatabase.getInstance().getReference("users")

    fun registerUser(
        name: String,
        email: String,
        password: String,
        address: String,
        role: String,
        onResult: (Boolean, String?) -> Unit
    ) {

        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            onResult(false, "Fields cannot be empty")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val uid = auth.currentUser?.uid ?: return@addOnCompleteListener

                    val user = User(
                        uid = uid,
                        name = name,
                        email = email,
                        address = address,
                        role = role,
                        trustScore = 0
                    )

                    // SAVE TO FIREBASE DATABASE
                    db.child(uid).setValue(user)
                        .addOnCompleteListener { saveTask ->

                            if (saveTask.isSuccessful) {
                                onResult(true, null)
                            } else {
                                onResult(false, saveTask.exception?.message)
                            }
                        }

                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }


    fun saveUserToDatabase(name: String? = null) {

        val uid = auth.currentUser?.uid ?: return
        val email = auth.currentUser?.email ?: "User"

        val user = User(
            uid = uid,
            name = name ?: email,
            trustScore = 0
        )

        FirebaseDatabase.getInstance()
            .getReference("users")
            .child(uid)
            .setValue(user)
    }


        fun loginUser(
            email: String,
            password: String,
            onResult: (Boolean, String?) -> Unit
        ) {


            if (email.isBlank() || password.isBlank()) {
                onResult(false, "Fields cannot be empty")
                return
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onResult(true, null)
                    } else {
                        onResult(false, task.exception?.message)
                    }
                }


    //        auth.signInWithEmailAndPassword(email, password)
  //              .addOnCompleteListener { task ->
//
                   // if (task.isSuccessful) {
                     //   onResult(true, null)
                   // } else {
                 //       onResult(false, task.exception?.message)
               //     }
             //   }
        }





}