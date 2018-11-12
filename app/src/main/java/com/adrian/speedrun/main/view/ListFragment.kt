package com.adrian.speedrun.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.adrian.speedrun.R
import com.adrian.speedrun.main.MainViewModel
import com.adrian.speedrun.main.MainViewModelFactory
import com.adrian.speedrun.main.domain.GamesAdapter
import com.adrian.speedrun.main.domain.model.GameInfo
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = activity?.run {
            ViewModelProviders.of(this, mainViewModelFactory)
                    .get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGamesRecycler()
    }

    private fun initGamesRecycler() {
        val linearLayoutManager = GridLayoutManager(context, 2)
        val gamesAdapter = GamesAdapter()
        games_recycler.layoutManager = linearLayoutManager
        games_recycler.adapter = gamesAdapter
        mainViewModel.gamesList.observe(this, Observer {
            gamesAdapter.submitList(it)
            it.toList().forEach { game ->
                mainViewModel.gamesListHash[game.id] = game
            }
        })
    }
}