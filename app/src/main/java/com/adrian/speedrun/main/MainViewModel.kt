package com.adrian.speedrun.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adrian.speedrun.common.BaseViewModel
import com.adrian.speedrun.main.domain.GamesPagingDataSourceFactory
import com.adrian.speedrun.main.domain.model.GameInfo
import com.adrian.speedrun.main.domain.model.RunData
import com.adrian.speedrun.main.domain.model.UserData
import com.adrian.speedrun.main.usecase.GetGames
import com.adrian.speedrun.main.usecase.GetSpeedRun
import com.adrian.speedrun.main.usecase.GetUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(getGames: GetGames,
                    private val getSpeedRun: GetSpeedRun,
                    private val getUser: GetUser) : BaseViewModel() {

    val gamesList: LiveData<PagedList<GameInfo>>
    var runData: MutableLiveData<RunData> = MutableLiveData()
    var userData: MutableLiveData<UserData> = MutableLiveData()

    var position: Int = 0

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

    fun getGameByPos(gameId: Int): GameInfo? {
        position = gameId
        return gamesList.value?.get(gameId)
    }

    fun getSpeedRunByGameId(gameId: String) {
        disposables.add(getSpeedRun.execute(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { runDataInfo, _: Throwable? ->
                runData.value = runDataInfo ?: return@subscribe
        })
    }

    fun getUserById(userId: String) {
        disposables.add(getUser.execute(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { userDataInfo, _: Throwable? ->
                userData.value = userDataInfo ?: return@subscribe
            })
    }
}