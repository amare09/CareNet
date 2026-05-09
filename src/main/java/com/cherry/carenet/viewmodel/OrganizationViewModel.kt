package com.cherry.carenet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cherry.carenet.data.OrganizationRepository
import com.cherry.carenet.models.HelpRequest
import com.cherry.carenet.models.OrganizationStats

class OrganizationViewModel : ViewModel() {

    private val repo =
        OrganizationRepository()



    val requests =
        mutableStateListOf<HelpRequest>()

    val stats =
        mutableStateOf(
            OrganizationStats()
        )



    init {

        loadDashboard()
    }



    fun loadDashboard() {

        repo.getAllRequests {

            requests.clear()

            requests.addAll(it)
        }



        repo.getStats {

            stats.value = it
        }
    }
}