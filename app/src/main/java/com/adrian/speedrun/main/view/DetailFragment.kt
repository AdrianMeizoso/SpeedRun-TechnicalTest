package com.adrian.speedrun.main.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adrian.speedrun.R
import com.adrian.speedrun.databinding.FragmentDetailBinding
import com.adrian.speedrun.main.MainViewModel
import com.adrian.speedrun.main.MainViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

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

    private var videoLink: String = ""

    override fun onDetach() {
        super.onDetach()
        mainViewModel.runData = MutableLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        video_button.setOnClickListener {
            if (videoLink.isNotEmpty()) {
                val location = Uri.parse(videoLink)
                val videoIntent = Intent(Intent.ACTION_VIEW, location)

                val packageManager = activity?.packageManager
                val activities = packageManager?.queryIntentActivities(videoIntent, 0)
                val isIntentSafe = (activities?.size ?: 0) > 0

                if (isIntentSafe) startActivity(videoIntent)
            }
        }

        val gameId = arguments?.getString("gameId")
        val gamePos = arguments?.getInt("position")

        gameId?.let { mainViewModel.getSpeedRunByGameId(it) }

        val game = gamePos?.let { mainViewModel.getGameByPos(it) }
        game_name.text = game?.names?.international ?: ""

        mainViewModel.runData.observe(this, Observer {
            videoLink = it.videos?.links?.get(0)?.uri ?: ""
            it.players?.get(0)?.id?.let { userId ->
                mainViewModel.getUserById(userId)
            }
        })

        mainViewModel.userData.observe(this, Observer {
            user_name.text = it.names.international ?: ""
        })
    }
}