package com.adrian.speedrun.main

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adrian.speedrun.common.BaseViewModel
import com.adrian.speedrun.main.domain.GamesPagingDataSourceFactory
import com.adrian.speedrun.main.domain.GameInfo
import com.adrian.speedrun.main.usecase.GetGames

class MainViewModel(getGames: GetGames) : BaseViewModel() {

    val gamesList: LiveData<PagedList<GameInfo>>
    var gamesListHash: HashMap<String, GameInfo> = HashMap()

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
}