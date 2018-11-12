package com.adrian.speedrun.main.domain

import androidx.paging.PageKeyedDataSource
import com.adrian.speedrun.main.usecase.GetGames
import io.reactivex.disposables.CompositeDisposable

class GamesPagingDataSource(
        private val getGames: GetGames,
        private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, GameInfo>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GameInfo>) {
        /*
        compositeDisposable.add(getHeroes.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gameInfoList: List<GameInfo>?, _: Throwable? ->
                    gameInfoList?.let { callback.onResult(it, 0, 20) }
                })
                */
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GameInfo>) {
        /*
        getHeroes.offset = params.key
        compositeDisposable.add(getHeroes.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gameInfoList: List<GameInfo>?, _: Throwable? ->
                    gameInfoList?.let { callback.onResult(it, params.key + 20) }
                })
                */
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GameInfo>) {}
}