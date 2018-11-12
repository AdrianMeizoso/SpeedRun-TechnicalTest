package com.adrian.speedrun.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adrian.speedrun.common.BaseViewModel
import com.adrian.speedrun.main.domain.GamesPagingDataSourceFactory
import com.adrian.speedrun.main.domain.model.GameInfo
import com.adrian.speedrun.main.domain.model.RunData
import com.adrian.speedrun.main.usecase.GetGames
import com.adrian.speedrun.main.usecase.GetSpeedRun

class MainViewModel(getGames: GetGames, private val getSpeedRun: GetSpeedRun) : BaseViewModel() {

    val gamesList: LiveData<PagedList<GameInfo>>
    var gamesListHash: HashMap<String, GameInfo> = HashMap()

    val runData: MutableLiveData<RunData> = MutableLiveData()

    private val pagedListConfig by lazy {
        PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20)
                .setPageSize(20)
                .setPrefetchDistance(40)
                .build()
    }

    init {
        val sourceFactory = GamesPagingDataSourceFactory(disposables, getGames)
        gamesList = LivePagedListBuilder(sourceFactory, pagedListConfig).build()
    }

    fun getGameById(gameId: String): GameInfo? = gamesListHash[gameId]

    fun getSpeedRun(gameId: String) {
        disposables.add(getSpeedRun.execute(gameId).subscribe { runDataInfo ->
            runData.value = runDataInfo
        })
    }
}