package com.adrian.speedrun.main.usecase

import com.adrian.speedrun.main.datasource.RunsDataSource
import com.adrian.speedrun.main.domain.model.GameInfo
import com.adrian.speedrun.main.domain.model.RunData
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSpeedRun @Inject constructor(private val runsRepository: RunsDataSource) {

    fun execute(gameId:String): Single<RunData> {
        return runsRepository
                .getSpeedruns(gameId)
                .flatMap {
                    Single.just(it.runDataList.first())
                }
                .doOnError {
                    it.printStackTrace()
                }
    }
}