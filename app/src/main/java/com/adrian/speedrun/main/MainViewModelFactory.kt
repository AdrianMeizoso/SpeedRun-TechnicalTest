package com.adrian.speedrun.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrian.speedrun.main.usecase.GetGames
import com.adrian.speedrun.main.usecase.GetSpeedRun
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val getGames: GetGames, private val getSpeedRun: GetSpeedRun) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getGames, getSpeedRun) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}