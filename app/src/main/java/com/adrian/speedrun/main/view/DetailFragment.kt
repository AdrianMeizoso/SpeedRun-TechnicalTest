package com.adrian.speedrun.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adrian.speedrun.main.MainViewModel
import com.adrian.speedrun.main.MainViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var mainViewModel: MainViewModel

    private var videoLink: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = activity?.run {
            ViewModelProviders.of(this, mainViewModelFactory)
                    .get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        video_button.setOnClickListener {
            if (!videoLink.isEmpty()) {
                val location = Uri.parse(videoLink)
                val videoIntent = Intent(Intent.ACTION_VIEW, location)

                val packageManager = activity!!.packageManager
                val activities = packageManager.queryIntentActivities(videoIntent, 0)
                val isIntentSafe = activities.size > 0

                if (isIntentSafe) {
                    startActivity(videoIntent)
                }
            }
        }

        val gameId = arguments?.getString("game")
        if (gameId != null) {
            val game = mainViewModel.getGameById(gameId)
            if (game != null) {
                game_name.text = game.names.international
                //hero_description.text = game.description
            }
            mainViewModel.getSpeedRun(gameId)
        }

        mainViewModel.runData.observe(this, Observer {
            videoLink = it.videos.links[0].url!!
        })
    }
}
