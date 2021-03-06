package com.adrian.speedrun.main.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.adrian.speedrun.main.domain.model.GameInfo
import com.adrian.speedrun.main.usecase.GetGames
import io.reactivex.disposables.CompositeDisposable

class GamesPagingDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                                   private val getGames: GetGames)
    : DataSource.Factory<Int, GameInfo>() {

    private val usersDataSourceLiveData = MutableLiveData<GamesPagingDataSource>()

    override fun create(): DataSource<Int, GameInfo> {
        val gamesPagingDataSource = GamesPagingDataSource(getGames, compositeDisposable)
        usersDataSourceLiveData.postValue(gamesPagingDataSource)
        return gamesPagingDataSource
    }
}