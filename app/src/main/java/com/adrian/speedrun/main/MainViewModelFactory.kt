package com.adrian.speedrun.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adrian.speedrun.main.usecase.GetGames
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private var getGames: GetGames) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getGames) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}