package com.cherry.carenet.models

import android.media.Image

data class User(
    var role: String="user",
    var uid: String="",
    var name: String="",
    var email:String="",
    var trustScore: Int=0,
    var bio: String="",
    var address: String="",
    var profileImage: String=""

)
