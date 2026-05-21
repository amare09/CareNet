package com.cherry.carenet.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class BlockedUsersRepository {

    private val db =
        FirebaseDatabase.getInstance()
            .getReference("blockedUsers")



    //SAFETY KEYS!!
    private fun safeKey(value: String): String {
        return value
            .replace(".", "_")
            .replace("#", "_")
            .replace("$", "_")
            .replace("[", "_")
            .replace("]", "_")
    }

    // ✅ BLOCK USER
    fun blockUser(
        currentUserId: String,
        blockedUserId: String
    ) {

        db.child(safeKey(currentUserId))
            .child(safeKey(blockedUserId))
            .setValue(true)
    }



    // ✅ UNBLOCK USER
    fun unblockUser(
        currentUserId: String,
        blockedUserId: String
    ) {

        db.child(safeKey(currentUserId))
            .child(safeKey(blockedUserId))
            .removeValue()
    }



    // ✅ CHECK BLOCK STATUS
    fun isBlocked(
        currentUserId: String,
        otherUserId: String,
        onResult: (Boolean) -> Unit
    ) {

        db.child(safeKey(currentUserId))
            .child(safeKey(otherUserId))
            .get()
            .addOnSuccessListener {

                onResult(it.exists())
            }
    }



    // ✅ LOAD BLOCKED USERS
    fun getBlockedUsers(
        currentUserId: String,
        onResult: (List<String>) -> Unit
    ) {

        db.child(safeKey(currentUserId))
            .addValueEventListener(
                object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        val list = mutableListOf<String>()

                        for (child in snapshot.children) {

                            child.key?.let {
                                list.add(it)
                            }
                        }

                        onResult(list)
                    }

                    override fun onCancelled(error: DatabaseError) {}
                }
            )
    }
}