package com.adrian.speedrun.main.datasource

import com.adrian.speedrun.main.domain.GamesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RunsApiDataSource {

    @GET("games")
    fun getGames(@Query("offset") offset: Int)
            : Single<GamesResponse>

    @GET("runs")
    fun getSpeedrun(@Query("game") gameId:String)
            : Single<GamesResponse>

    @GET("users/{idUser}")
    fun getUser(@Path("idUser") userId:String)
            : Single<GamesResponse>
}