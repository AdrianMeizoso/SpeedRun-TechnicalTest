package com.adrian.speedrun.main.datasource

import com.adrian.speedrun.main.domain.GamesResponse
import io.reactivex.Single

interface RunsDataSource {

    fun getGames(offset: Int): Single<GamesResponse>
    fun getSpeedrun(offset: Int): Single<GamesResponse>
    fun getUser(offset: Int): Single<GamesResponse>

}