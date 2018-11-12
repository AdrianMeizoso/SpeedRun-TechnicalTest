package com.adrian.speedrun.main.repository

import com.adrian.speedrun.main.datasource.RunsApiDataSource
import com.adrian.speedrun.main.datasource.RunsDataSource
import com.adrian.speedrun.main.domain.GamesResponse
import io.reactivex.Single

class RunsRepository(private val runsApiDataSource: RunsApiDataSource) : RunsDataSource {

    override fun getGames(offset: Int): Single<GamesResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSpeedrun(offset: Int): Single<GamesResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(offset: Int): Single<GamesResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}