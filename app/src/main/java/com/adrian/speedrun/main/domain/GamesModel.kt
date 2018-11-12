package com.adrian.speedrun.main.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesResponse(
        @Json(name = "data") val gamesData: GamesData)

@JsonClass(generateAdapter = true)
data class GamesData(
        @Json(name = "results") val gameInfoList: List<GameInfo>)

@JsonClass(generateAdapter = true)
data class GameInfo(
    val id: String,
    val names: GameNames,
    val assets: GameAssets)

@JsonClass(generateAdapter = true)
data class GameNames(
    val international: String,
    val japanese: String,
    val twitch: String)

@JsonClass(generateAdapter = true)
data class GameAssets(
    @Json(name = "cover-large") val coverLarge: GameAsset)

@JsonClass(generateAdapter = true)
data class GameAsset(
    val uri: String)