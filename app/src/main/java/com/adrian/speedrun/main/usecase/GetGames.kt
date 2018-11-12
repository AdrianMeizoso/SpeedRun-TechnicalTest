package com.adrian.speedrun.main.usecase

import com.adrian.speedrun.main.datasource.RunsDataSource
import com.adrian.speedrun.main.domain.GameInfo
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGames @Inject constructor(private val runsRepository: RunsDataSource) {

    var offset: Int = 0

    fun execute(): Single<List<GameInfo>> {
        return runsRepository
                .getGames(offset)
                .flatMap {
                    Single.just(it.gamesData.gameInfoList)
                }
                .doOnError {
                    it.printStackTrace()
                }
                .doFinally { offset = 0 }
    }
}