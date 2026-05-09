package com.cherry.carenet.models

data class OrganizationStats(

    var totalRequests: Int = 0,

    var pendingRequests: Int = 0,

    var completedRequests: Int = 0,

    var emergencyRequests: Int = 0,

    var usersHelped: Int = 0
)