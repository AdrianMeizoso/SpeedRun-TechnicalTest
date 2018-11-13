package com.adrian.speedrun.main.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "data") val userData: UserData
)

@JsonClass(generateAdapter = true)
data class UserData(
    val id: String,
    val names: UserNames
)

@JsonClass(generateAdapter = true)
data class UserNames(
    val international: String?
)