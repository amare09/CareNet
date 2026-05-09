package com.cherry.carenet.data

import com.cherry.carenet.models.HelpRequest
import com.cherry.carenet.models.OrganizationStats
import com.google.firebase.database.*

class OrganizationRepository {

    private val db =
        FirebaseDatabase.getInstance()
            .getReference("requests")



    // ✅ LOAD ALL REQUESTS
    fun getAllRequests(
        onResult: (List<HelpRequest>) -> Unit
    ) {

        db.addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list =
                        mutableListOf<HelpRequest>()

                    for (child in snapshot.children) {

                        val request =
                            child.getValue(
                                HelpRequest::class.java
                            )

                        if (request != null) {

                            list.add(request)
                        }
                    }

                    onResult(list)
                }

                override fun onCancelled(error: DatabaseError) {}
            }
        )
    }



    // ✅ ANALYTICS
    fun getStats(
        onResult: (OrganizationStats) -> Unit
    ) {

        getAllRequests { requests ->

            val stats =
                OrganizationStats(

                    totalRequests =
                        requests.size,

                    pendingRequests =
                        requests.count {
                            it.status == "pending"
                        },

                    completedRequests =
                        requests.count {
                            it.status == "completed"
                        },

                    emergencyRequests =
                        requests.count {
                            it.isEmergency
                        },

                    usersHelped =
                        requests.map {
                            it.senderId
                        }.distinct().size
                )

            onResult(stats)
        }
    }
}