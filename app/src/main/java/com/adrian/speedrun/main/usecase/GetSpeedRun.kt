package com.adrian.speedrun.main.usecase

import com.adrian.speedrun.main.datasource.RunsDataSource
import com.adrian.speedrun.main.domain.model.RunData
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSpeedRun @Inject constructor(private val runsDataSource: RunsDataSource) {

    fun execute(gameId:String): Single<RunData> {
        return runsDataSource
                .getSpeedruns(gameId)
                .flatMap {
                    if (it.runDataList.isEmpty()) {
                        Single.error(Throwable("Run data null"))
                    } else {
                        Single.just(it.runDataList.first())
                    }
                }
                .doOnError {
                    it.printStackTrace()
                }
    }
}