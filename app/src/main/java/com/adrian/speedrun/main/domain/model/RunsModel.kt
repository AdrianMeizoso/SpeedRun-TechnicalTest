package com.adrian.speedrun.main.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RunsResponse(
        @Json(name = "data") val runDataList: List<RunData>)

@JsonClass(generateAdapter = true)
data class RunData(
    val id: String,
    val videos: RunVideos,
    val times: RunTimes
)

@JsonClass(generateAdapter = true)
data class RunVideos(
    val links: List<RunLink>)

@JsonClass(generateAdapter = true)
data class RunLink(
    val url: String?
)

@JsonClass(generateAdapter = true)
data class RunTimes(
    val primary: String?)